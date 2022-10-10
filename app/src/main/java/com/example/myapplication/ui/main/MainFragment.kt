package com.example.myapplication.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.myapplication.R
import com.example.myapplication.room.AppDatabase
import com.example.myapplication.room.MIGRATION_1_2
import com.example.myapplication.room.User
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainFragment : Fragment() {
    var myheroList: List<ResultList>? = null
    companion object {
        fun newInstance() = MainFragment()
    }

    var adapter: ImageGalleryAdapter2? = null
    var recyclerView: RecyclerView? = null
    var listiner: ClickListiner? = null
    var userList :List<User>  = listOf()
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = Room.databaseBuilder(
            requireContext(),
            AppDatabase::class.java, "database-name"
        ).addMigrations(MIGRATION_1_2).build()

        viewLifecycleOwner.lifecycleScope.launch{
            getSuperHeroes(db,view)
        }

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

    }


    private fun loadRecycler(view: View) {
        recyclerView = view.findViewById(
            R.id.recyclerView
        ) as RecyclerView
        listiner = object : ClickListiner() {
            override fun click(index: Int) {
                Toast.makeText(requireContext(), "clicked item index is $index", Toast.LENGTH_LONG).show()
            }
        }

        adapter = ImageGalleryAdapter2(
            userList, requireContext(), listiner
        )
        recyclerView!!.adapter = adapter
        recyclerView!!.layoutManager = LinearLayoutManager(requireContext())
    }



     private fun getSuperHeroes(db:AppDatabase,view: View) : List<ResultList>? {

        RetrofitClient.getInstance().myApi.getsuperHeroes()?.enqueue(object : Callback<Results?> {
            override fun onResponse(call: Call<Results?>, response: Response<Results?>) {
                myheroList = response.body()?.results
                val oneHeroes = myheroList?.let { arrayOfNulls<String>(it.size) }

                if (myheroList != null) {
                    for (i in myheroList!!.indices) {
                        oneHeroes?.set(i, myheroList!![i].name.toString())
                        val user = User(firstName = myheroList!![i].name.first, lastName=myheroList!![i].name.last,
                            pictureLarge = myheroList!![i].picture.large, pictureMedium =myheroList!![i].picture.medium
                            , city = myheroList!![i].location.city, state = myheroList!![i].location.state,
                            country = myheroList!![i].location.country, accepted = false)

                        userList.plus(user)
                        viewLifecycleOwner.lifecycleScope.launch{
                        db.userDao().insertAll(user)
                        }
                    }
                }
                viewLifecycleOwner.lifecycleScope.launch{
                    Log.e("ddddd"," " + db.userDao().getAll())
                   userList= db.userDao().getAll()
                    loadRecycler(view)
                }

            }

            override fun onFailure(call: Call<Results?>, t: Throwable) {
                /*Toast.makeText(
this,
                    "An error has occured",
                    Toast.LENGTH_LONG
                ).show()*/
            }


        })
        return myheroList
    }

}