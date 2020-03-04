package com.example.newrecycler

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class RandomUserDao private constructor(var context:Context) {

    private val users = MutableLiveData<List<Person>>()
    private val userList = mutableListOf<Person>()
    private var queue: RequestQueue

    init{
        queue = MySingleton.getInstance(context).requestQueue
    }
    companion object {
        @Volatile
        private var INSTANCE: RandomUserDao? = null
        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: RandomUserDao(context).also {
                    INSTANCE = it
                }
            }
    }
    fun addUser(){
        MySingleton.getInstance(context).addToRequestQueue(getJsonObjectRequest())
    }
    fun getUsers()= users

    fun getJsonObjectRequest() : JsonObjectRequest {
        val url = "https://randomuser.me/api/?results=10"
        val jsonObjectRequest= JsonObjectRequest(
            Request.Method.GET, url,null,
            Response.Listener{ response ->
                parseObject(response)
            }, Response.ErrorListener{
                Log.d("error","mani esa mondá se totió yo no sé")
            }
        )
        return jsonObjectRequest
    }
    fun parseObject(response: JSONObject){
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
            userList.add(Person(title,firstName,lastName,email,phone,image))
            Log.d("First",userList.get(0).toString())
            Log.d("JSONParsing",title+" "+firstName+ " "+ lastName+" "+email+" "+phone)

        }
        users.value = userList



    }
}
