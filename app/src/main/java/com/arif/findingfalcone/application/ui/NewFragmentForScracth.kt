package com.arif.findingfalcone.application.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.SpinnerAdapter
import androidx.constraintlayout.widget.ConstraintLayout
import com.arif.findingfalcone.R
import com.arif.findingfalcone.application.MockAdapter
import com.arif.findingfalcone.data.MockData

class NewFragmentForScracth : Fragment() {

    private var counter: Int = 4

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_for_scracth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (counter > 0) {
            val spinner = Spinner(context)
            spinner.adapter = ArrayAdapter(requireContext(), R.layout.spinner_item, MockData.getPlanets().map { it.title })

            spinner.onItemSelectedListener =
                object : OnItemSelectedListener {
                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        if (p2 == 0) onNothingSelected(p0)
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {

                    }

                }

            view.findViewById<ConstraintLayout>(R.id.container).addView(spinner, ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT))
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment NewFragmentForScracth.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = NewFragmentForScracth()
    }
}