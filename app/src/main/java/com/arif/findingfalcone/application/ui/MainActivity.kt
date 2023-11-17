package com.arif.findingfalcone.application.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arif.findingfalcone.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, FindFalconMissionFragment.newInstance())
//            .add(FindFalconMissionFragment.newInstance(), "FindFalcon")
            .commit()
    }
}
