package com.example.navigationsearchbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //adds teh list when the app is opened
    private lateinit var adapter: ArrayAdapter<*>

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            adapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                resources.getStringArray(R.array.countries_array)
            )
            lv_ListView.adapter = adapter
            lv_ListView.onItemClickListener =
                AdapterView.OnItemClickListener { parent, view, position, id ->
                    Toast.makeText(
                        applicationContext,
                        parent?.getItemAtPosition(position).toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            lv_ListView.emptyView = tv_emptyTextView
        }

        //this initializes the search feature
        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.nav_menu, menu)

            val search = menu?.findItem(R.id.nav_search)
            val searchView = search?.actionView as SearchView
            searchView.queryHint = "search something"
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    adapter.filter.filter(newText)
                    return true
                }
            })
            return super.onCreateOptionsMenu(menu)
        }
    }
