package com.example.newrecycler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newrecycler.databinding.ActivityMainBinding
import com.example.recycleviewex.TopSpacingItemDecoration
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnCarItemClickListner{

    lateinit var binding: ActivityMainBinding
    lateinit var carlist: ArrayList<Person>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        carlist = ArrayList()
        addCars()

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

    override fun onItemClick(item: Person, position: Int) {
//        Toast.makeText(this, item.name , Toast.LENGTH_SHORT).show()
         val intent = Intent(this, CarDetailsActivity::class.java)
         intent.putExtra("CARNAME", item.name)
        intent.putExtra("CARDESC", item.lastname)
        intent.putExtra("PHOTO", item.image)
        startActivity(intent)


    }
}
