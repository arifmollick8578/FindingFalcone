package com.arif.findingfalcone.application.ui

import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.arif.findingfalcone.R
import com.arif.findingfalcone.application.MainViewModel
import com.arif.findingfalcone.application.OperationState
import com.arif.findingfalcone.application.adapter.PlanetAdapter2
import com.arif.findingfalcone.application.adapter.VehicleAdapter2
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class FindFalconMissionFragment : Fragment() {
    private lateinit var rv1: RecyclerView
    private lateinit var rv2: RecyclerView
    private lateinit var planetAdapter2: PlanetAdapter2
    private lateinit var vehicleAdapter2: VehicleAdapter2

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_find_falcon_mission, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        rv1 = view.findViewById(R.id.rv1)
//        rv2 = view.findViewById(R.id.rv2)
//


        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        // iteration 3

//        planetAdapter2 = PlanetAdapter2(emptyList()) {
//            false
////            viewModel.updateDataOfPlanet(it)
//        }
//
//        rv1.adapter = planetAdapter2
//
//        viewModel.vehicles1.observe(viewLifecycleOwner) {
////        viewModel.vehicles.observe(viewLifecycleOwner) {
////            rv2.adapter = VehicleAdapter2(it) {
////                println("FATAL:: vehicle item clicked: $it")
////            }
//
//            rv2.adapter = VehicleAdapter2(it) {
//                val selectedItems = viewModel.selectedVehicles
//
//                when {
//                    // is already selected:
//                    selectedItems.contains(it) -> {
//                        viewModel.removeSelected(it)
//                        true
//                    }
//
//                    // check count
//                    selectedItems.size >= 4 -> {
//                        Snackbar.make(requireView(), "Already 4 items selected", Snackbar.LENGTH_LONG).show()
//                        false
//                    }
//
//                    else -> {
//                        viewModel.updateDataOfPlanet(it)
//                        true
//                    }
//                }
//
//
////                viewModel.updateDataOfPlanet(it)
//            }
//        }

        // iteration 1
//        viewModel.planets.observe(viewLifecycleOwner) {
//            rv1.adapter = PlanetAdapter2(it) {
//                println("FATAL:: plaent item clicked: $it")
//            }
//        }


        // iteration 3
//        viewModel.planets1.observe(viewLifecycleOwner) {
////            planetAdapter2.dispatchItems(it)
//            rv1.adapter = PlanetAdapter2(it) {
//                val selectedItems = viewModel.selectedPlanets
//
//                when {
//                    // is already selected:
//                    selectedItems.contains(it) -> {
//                        viewModel.removeSelected(it)
//                        true
//                    }
//
//                    // check count
//                    selectedItems.size >= 4 -> {
//                        Snackbar.make(requireView(), "Already 4 items selected", Snackbar.LENGTH_LONG).show()
//                        false
//                    }
//
//                    else -> {
//                        viewModel.updateDataOfPlanet(it)
//                        true
//                    }
//                }
//
//
////                viewModel.updateDataOfPlanet(it)
//            }
//
////            rv1.adapter = PlanetAdapter2(it) {
////                println("FATAL:: plaent item clicked: $it")
////                viewModel.updateDataOfPlanet(it)
////            }
//        }

        lifecycleScope.launch {
            viewModel.operationState.collect {
                handleOperationState(it)
            }
        }

//        viewModel.planets1.observe(viewLifecycleOwner) {
//            val selection = SelectionLayoutSection(requireContext())
//
//            selection.addPlanets(it) {
//                val selectedItems = viewModel.selectedPlanets
//
//                when {
//                    // is already selected:
//                    selectedItems.contains(it) -> {
//                        viewModel.removeSelected(it)
//                        true
//                    }
//
//                    // check count
//                    selectedItems.size >= 4 -> {
//                        Snackbar.make(requireView(), "Already 4 items selected", Snackbar.LENGTH_LONG).show()
//                        false
//                    }
//
//                    else -> {
//                        viewModel.updateDataOfPlanet(it)
//                        true
//                    }
//                }
//
//            }
//
//            view.findViewById<LinearLayout>(R.id.container).addView(selection, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
//        }


        viewModel.result.observe(viewLifecycleOwner) {
            Snackbar.make(requireView(), it, Snackbar.LENGTH_LONG).show()
        }

        viewModel.areDataReady.observe(viewLifecycleOwner) {
            println("FATAL:: aredata ready: $it")
            if (it.first && it.second) {
                addMoreData(view)

                // If data fetched successfully.. then start rendering data

//                val select = SelectionLayoutSection(requireContext())
//                select.addPlanets(viewModel.planets1.value ?: emptyList()) {
//                    val selectedItems = viewModel.selectedPlanets
//                    when {
//                        // is already selected:
//                        selectedItems.contains(it) -> {
//                            viewModel.removeSelected(it)
//                            true
//                        }
//
//                        // check count
//                        selectedItems.isNotEmpty() -> {
//                            Snackbar.make(requireView(), "Items already selected!", Snackbar.LENGTH_LONG).show()
//                            false
//                        }
//
//                        else -> {
//                            viewModel.updateDataOfPlanet(it)
//                            true
//                        }
//                    }
//                }
//
//                select.addVehicles(viewModel.vehicles1.value ?: emptyList()) {
//                    val selectedItems = viewModel.selectedVehicles
//
//                    when {
//                        // is already selected:
//                        selectedItems.contains(it) -> {
//                            viewModel.removeSelected(it)
//                            true
//                        }
//
//                        // check count
//                        selectedItems.isNotEmpty() -> {
//                            Snackbar.make(requireView(), "Items already selected!", Snackbar.LENGTH_LONG).show()
//                            false
//                        }
//
//                        else -> {
//                            viewModel.updateDataOfPlanet(it)
//                            true
//                        }
//                    }
//
//                }
//
//                select.addActionButtonListener {
//
//                }
//
//                view.findViewById<LinearLayout>(R.id.container).addView(select)
            }
        }

        viewModel.fetchPlanets()
        viewModel.fetchVehicles()

// iteration 1

//        var vehicleAdapter = VehicleAdapter(emptyList())
//        var planetAdapter = PlanetAdapter(emptyList())
//
//        val rv1 = view.findViewById<RecyclerView>(R.id.rv1)
//        val rv2 = view.findViewById<RecyclerView>(R.id.rv2)
//
//        rv1.adapter = MockAdapter(MockData.getPlanets())
//        rv2.adapter = MockAdapter(MockData.getPlanets())
//
//        println("FATAL:: fragment is displayed here..")
//
//        lifecycleScope.launch(Dispatchers.IO) {
//
//            try {
//                val request = RetrofitApi.getRetrofitApi().getVehicles()
//                println("FATAL:: request: $request")
//
//                if (request.isSuccessful) {
//                    val body = request.body()
//                    println("FATAL:: request body: $body")
//
//                    println("FATAL:: responses: vehicle size: ${body?.size}.. ")
//
//
//                    withContext(Dispatchers.Main) {
//                        val rv1 = view.findViewById<RecyclerView>(R.id.rv1)
////                    val rv2 = view.findViewById<RecyclerView>(R.id.rv2)
//
//                        println("FATAL:: main dispatcher: ")
//
//                        body?.let {
//                            vehicleAdapter = VehicleAdapter(it)
//                            rv1.adapter = vehicleAdapter
//                        }
//
////                    rv2.adapter = PlanetAdapter(planets)
//                    }
//                } else {
//                    println("FATAL:: error body: ${request.errorBody()}")
//                }
//            } catch (e: Exception) {
//                println("FATAL:: api error: $e")
//            }
////                val vehicle = RetrofitApi.getRetrofitApi()
////                    .getVehicles()
////
////                println("FATAL:: responses: vehicle size: ${vehicle.size}.. ")
//
////                withContext(Dispatchers.Main) {
////                    val rv1 = view.findViewById<RecyclerView>(R.id.rv1)
//////                    val rv2 = view.findViewById<RecyclerView>(R.id.rv2)
////
////                    println("FATAL:: main dispatcher: ")
////
////                    rv1.adapter = VehicleAdapter(vehicle)
//////                    rv2.adapter = PlanetAdapter(planets)
////                }
//        }
//
//        lifecycleScope.launch {
//            val planets = RetrofitApi.getRetrofitApi().getPlanets()
//
//            println("FATAL:: responses: planets size: ${planets.size}.. ")
//            withContext(Dispatchers.Main) {
//                val rv2 = view.findViewById<RecyclerView>(R.id.rv2)
//
//                println("FATAL:: main dispatcher 2: ")
//
//                planetAdapter = PlanetAdapter(planets)
//                rv2.adapter = PlanetAdapter(planets)
////                rv2.adapter = PlanetAdapter(planets)
//            }
//        }
//
//
//
//        view.findViewById<Button>(R.id.submit).setOnClickListener {
//
//            lifecycleScope.launch {
//                val token = RetrofitApi.getRetrofitApi().getToken()
//
//                println("FATAL:: token: $token")
//
//                val result = RetrofitApi.getRetrofitApi().findFalconApi(
//                    FindFalconRequestBody(
//                        token.token,
//                        listOf("Donlon", "Enchai", "Pingasor", "Sapir"),
//                        listOf("Space pod", "Space rocket", "Space rocket", "Space rocket")
////                        planetAdapter.selectedItems,
////                        vehicleAdapter.selectedItems
//                    )
//                )
//
//                Snackbar.make(view, "Result: ${result}", Snackbar.LENGTH_LONG).show()
//            }
//
//        }


//        binding.adapter = VehicleAdapter(MockData.getPlanets())

    }

    private fun addMoreData(view: View) {
        val actionButtonText =
        if (viewModel.selectedVehicles.size >= 3 && viewModel.selectedPlanets.size >= 3) {
            "Submit"
        } else {
            "Next"
        }

        val select = SelectionLayoutSection(requireContext())
        select.addPlanets(viewModel.planets1.value ?: emptyList()) {
            val selectedItems = viewModel.selectedPlanets
            when {
                // is already selected:
                viewModel.currentPlanetSelection == it -> {
                    viewModel.removeSelected(it)
                    true
                }

                // check count
                viewModel.currentPlanetSelection != null && viewModel.currentPlanetSelection != it -> {
//                    Snackbar.make(requireView(), "Items already selected!", Snackbar.LENGTH_LONG).show()
                    viewModel.updateDataOfPlanet(it)
                    true
                }

                else -> {
                    viewModel.updateDataOfPlanet(it)
                    true
                }
            }
        }

        select.addVehicles(viewModel.vehicles1.value ?: emptyList()) {
            when {
                // is already selected:
                viewModel.currentVehicleSelection == it -> {
                    viewModel.removeSelected(it)
                    true
                }

                // check count
                viewModel.currentVehicleSelection != null && viewModel.currentVehicleSelection != it -> {
//                    Snackbar.make(requireView(), "Items already selected!", Snackbar.LENGTH_LONG).show()
                    viewModel.updateDataOfPlanet(it)
                    true
                }

                else -> {
                    viewModel.updateDataOfPlanet(it)
                    true
                }
            }
        }

//        select.addActionButtonText(actionButtonText)

//        select.addActionButtonListener {
//            if (viewModel.selectedVehicles.size >= 3 && viewModel.selectedPlanets.size >= 3) {
//                viewModel.makeSelected()
//                viewModel.getResult()
//                select.disableButton()
//            } else {
//                if (viewModel.currentVehicleSelection == null || viewModel.currentPlanetSelection == null) {
//                    Snackbar.make(requireView(), "Please make sure to add vehicle or planet", Snackbar.LENGTH_LONG).show()
//                } else {
//                viewModel.makeSelected()
//                addMoreData(view)
//                }
//            }
//        }

        view.findViewById<LinearLayout>(R.id.container).addView(select)

    }

    private fun handleOperationState(state: OperationState) {
        when (state) {
            OperationState.START -> {
                requireView().findViewById<ProgressBar>(R.id.pb1).visibility = View.VISIBLE
//                requireView().findViewById<ProgressBar>(R.id.pb2).visibility = View.VISIBLE
            }

            OperationState.IN_PROGRESS -> {
                requireView().findViewById<ProgressBar>(R.id.pb1).visibility = View.VISIBLE
//                requireView().findViewById<ProgressBar>(R.id.pb1).visibility = View.VISIBLE
//                requireView().findViewById<ProgressBar>(R.id.pb2) = View.VISIBLE
            }

            OperationState.FINISH -> {
                requireView().findViewById<ProgressBar>(R.id.pb1).visibility = View.GONE
//                requireView().findViewById<ProgressBar>(R.id.pb2).visibility = View.GONE
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment FindFalconMissionFragment.
         */
        @JvmStatic
        fun newInstance() = FindFalconMissionFragment()
    }
}