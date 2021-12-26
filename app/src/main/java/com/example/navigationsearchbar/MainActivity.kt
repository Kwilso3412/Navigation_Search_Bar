package com.example.navigationsearchbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ArrayAdapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // to fix the list view try these suggestions https://www.raywenderlich.com/155-android-listview-tutorial-with-kotlin

        adapter = ArrayAdapter( this, android.R.layout.simple_list_item_1, resources.getStringArray(R.array.countries_array))
        ListView.adapter = adapter
        lv_listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            Toast.makeText(applicationContext, parent?.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show()
        }
        lv_listView.emptyView = tv_emptyTextView


        override fun onCreateOptionsMenu(menu: Menu?): Boolean{
            menuInflater.inflate(R.menu.nav_menu, menu)

            val search = menu?.findItem(R.id.nav_search)
            val searchView = search?.actionVeiw as SearchView
            searchView.queryHint ="search something"
            searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
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
}