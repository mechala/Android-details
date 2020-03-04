package com.example.newrecycler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.example.newrecycler.databinding.ActivityMainBinding
import com.example.recycleviewex.TopSpacingItemDecoration
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnCarItemClickListner{

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel:RandomUserViewModel
    private var carlist= mutableListOf<Person>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
       // carlist = ArrayList()
        viewModel = ViewModelProvider(this).get(RandomUserViewModel::class.java)
        viewModel.getUser().observe(this, Observer { user->
            run {
                carlist = user as MutableList<Person>

            }
        })
        viewModel.addUser()
        //MySingleton.getInstance(this).addToRequestQueue(getJsonObjectRequest())
        //addCars()

        carRecycler.layoutManager = LinearLayoutManager(this)
        val topSpacingItemDecoration = TopSpacingItemDecoration(30)
        carRecycler.addItemDecoration(topSpacingItemDecoration)
        carRecycler.addItemDecoration(DividerItemDecoration(this,1))
        carRecycler.adapter = CarAdapter(carlist,this)
    }

    fun addCars(){
        carlist.add(Person(
            "Miss",
            "Violet",
            "Horton",
            "violet.horton@example.com",
            "(074)-615-4135",
            "https://randomuser.me/api/portraits/med/women/19.jpg"
        ) )
        carlist.add(Person(
            "Mr",
            "Eugene",
            "May",
            "eugene.may@example.com",
            "(739)-516-9383",
            "https://randomuser.me/api/portraits/med/men/13.jpg"
        ) )
        carlist.add(Person(
            "ms",
            "louella",
            "Rodriguez",
            "louella.rodriguez@example.com",
            "(841)-293-1551",
        "https://randomuser.me/api/portraits/med/women/12.jpg"
        ) )

    }
    fun getStringRequest() : StringRequest {
        val url = "https://randomuser.me/api"
        val stringRequest= StringRequest(
            Request.Method.GET, url, Response.Listener<String>{
                    //response -> tv_result.text = response.toString()
               response -> Log.d("Buenas",response.toString())

            }, Response.ErrorListener{
                Log.d("Buenas","Pequeño error valecita")
            }
        )
        return stringRequest
    }
    /*fun getJsonObjectRequest() :JsonObjectRequest{
        val url = "https://randomuser.me/api/?results=10"
        val jsonObjectRequest= JsonObjectRequest(
            Request.Method.GET, url,null,
            Response.Listener{response ->
                parseObject(response)
            }, Response.ErrorListener{
                Log.d("error","mani esa mondá se totió yo no sé")
            }
        )
        return jsonObjectRequest
    }*/
    /*fun parseObject(response: JSONObject){
        val jsonArrayResult : JSONArray = response.getJSONArray("results")
        val size: Int = jsonArrayResult.length()
        val i: Int=0
        for (i in 0.. size-1){
            val userObject = jsonArrayResult.getJSONObject(i)
            val nameObject = userObject.getJSONObject("name")
            val title = nameObject.getString("title")
            val firstName = nameObject.getString("first")
            val lastName = nameObject.getString("last")
            val email= userObject.getString("email")
            val phone= userObject.getString("phone")
            val picture = userObject.getJSONObject("picture")
            val image=picture.getString("large")
            carlist.add(Person(title,firstName,lastName,email,phone,image))
            Log.d("First",carlist.get(0).toString())
            Log.d("JSONParsing",title+" "+firstName+ " "+ lastName+" "+email+" "+phone)

        }
        carRecycler.adapter = CarAdapter(carlist,this)


    }*/

    override fun onItemClick(item: Person, position: Int) {
//        Toast.makeText(this, item.name , Toast.LENGTH_SHORT).show()
         val intent = Intent(this, CarDetailsActivity::class.java)
        intent.putExtra("Person",item)
        /*intent.putExtra("CARNAME", item.name)
        intent.putExtra("CARDESC", item.lastname)
        intent.putExtra("PHOTO", item.image)
          */
        startActivity(intent)


    }
}
