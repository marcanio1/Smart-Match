package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.lorentzos.flingswipe.SwipeFlingAdapterView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var al = ArrayList<String>()
    private var arrayAdapter: ArrayAdapter<String>? = null
    private var i = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)






        al.add("Suraj");
        al.add("Rishab");
        al.add("Jayant");
        al.add("Eric");

        //choose your favorite adapter
        arrayAdapter = ArrayAdapter(this, R.layout.item, R.id.helloText, al );

        //set the listener and the adapter
        frame.adapter = arrayAdapter;
        frame.setFlingListener(object: SwipeFlingAdapterView.onFlingListener {

            override fun removeFirstObjectInAdapter() {
                Log.d("LIST", "removed object!");
                al.removeAt(0);
                arrayAdapter?.notifyDataSetChanged();
            }

            override fun onLeftCardExit(p0: Any?) {
                Toast.makeText(this@MainActivity, "Left!", Toast.LENGTH_SHORT).show();
            }

            override fun onRightCardExit(p0: Any?) {
                Toast.makeText(this@MainActivity, "Right!", Toast.LENGTH_SHORT).show();
            }

            override fun onAdapterAboutToEmpty(p0: Int) {
                al.add("XML $i");
                arrayAdapter?.notifyDataSetChanged();
                Log.d("LIST", "notified");
                i++;
            }

            override fun onScroll(p0: Float) {

            }
        })
    }
}







