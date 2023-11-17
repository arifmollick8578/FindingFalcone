package com.arif.findingfalcone.application

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arif.findingfalcone.data.RetrofitApi
import com.arif.findingfalcone.data.datamodels.requestmodels.FindFalconRequestBody
import com.arif.findingfalcone.data.datamodels.responsemodels.FindFalconResponse
import com.arif.findingfalcone.data.datamodels.responsemodels.PlanetResponse
import com.arif.findingfalcone.data.datamodels.responsemodels.TokenResponse
import com.arif.findingfalcone.data.datamodels.responsemodels.VehicleResponse
import com.arif.findingfalcone.data.repository.FindFalconRepositoryImpl
import com.arif.findingfalcone.data.repository.datasourceimpl.FindFalconRemoteDataSourceImpl
import com.arif.findingfalcone.data.utils.NetworkStatus
import com.arif.findingfalcone.domain.dataclasses.Planet
import com.arif.findingfalcone.domain.dataclasses.Vehicle
import com.arif.findingfalcone.domain.dataclasses.toPlanet
import com.arif.findingfalcone.domain.dataclasses.toVehicle
import com.arif.findingfalcone.domain.repository.FindFalconRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val repository: FindFalconRepository = FindFalconRepositoryImpl(FindFalconRemoteDataSourceImpl(RetrofitApi.getRetrofitApi()))

    private val _vehicles = MutableLiveData<List<VehicleResponse>>()
    val vehicles: LiveData<List<VehicleResponse>> get() = _vehicles

    private val _planets = MutableLiveData<List<PlanetResponse>>()
    val planets: LiveData<List<PlanetResponse>> get() = _planets


    private val _vehicles1 = MutableLiveData<List<Vehicle>>()
    val vehicles1: LiveData<List<Vehicle>> get() = _vehicles1

    private val _planets1 = MutableLiveData<List<Planet>>()
    val planets1: LiveData<List<Planet>> get() = _planets1

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _areDataReady: MutableLiveData<Pair<Boolean, Boolean>> = MutableLiveData()
    val areDataReady: LiveData<Pair<Boolean, Boolean>> get() = _areDataReady

    private val _selectedPlanets: MutableList<Planet> = mutableListOf()

    val selectedPlanets: List<Planet> get() = _selectedPlanets

    private val _selectedVehicles: MutableList<Vehicle> = mutableListOf()
    val selectedVehicles: List<Vehicle> get() = _selectedVehicles

    private val _operationState = MutableSharedFlow<OperationState>()
    val operationState: SharedFlow<OperationState> get() = _operationState.asSharedFlow()

     var currentVehicleSelection: Vehicle? = null
     var currentPlanetSelection: Planet? = null

    private val _result = MutableLiveData<String>()

    val result: LiveData<String> get() = _result

    var token: String? = null

    fun getResult() {

        viewModelScope.launch {
            val job1 = async { fetchDataFromRepository<TokenResponse>(repository.getToken()) {
                println("FATAL:: token: $it")
                token = it.token
            }
            token
            }

//            job1.cancelAndJoin()

            launch {
                fetchDataFromRepository<FindFalconResponse>(repository.getResult(
                    FindFalconRequestBody(
                        job1.await()!!,
                        selectedPlanets.map { it.name } ?: emptyList(),
                        selectedVehicles.map { it.name } ?: emptyList()
                    )
                )) {
                    println("FATAL:: result: $it")
                    when (it) {
                        is FindFalconResponse.FindFalconSuccessResponse -> {
                            _result.postValue("Falcon found at ${it.planet_name}!")
                        }
                        is FindFalconResponse.FindFalconFailureResponse -> {
                            _result.postValue("Unable to find falcon ${it.status}")
                        }
                        is FindFalconResponse.FindFalconExceptionResponse -> {
                            _result.postValue(it.message)
                        }
                    }
                }
            }

        }

//        viewModelScope.launch {
//            fetchDataFromRepository<TokenResponse>(repository.getToken()) {
//                token = it.token
//            }
//        }
//
//        viewModelScope.launch {
//            fetchDataFromRepository<Any>(repository.getResult(
//                FindFalconRequestBody(
//                    token,
//                    planets1.value?.map { it.name } ?: emptyList(),
//                    vehicles1.value?.map { it.name } ?: emptyList()
//                )
//            )) {
//                _result.postValue(it as? String)
//            }
//        }
    }

    fun makeSelected() {
        currentVehicleSelection?.let {
            it.isSelected = true
            _selectedVehicles.add(it)
        }
        currentPlanetSelection?.let {
            it.isSelected = true
            _selectedPlanets.add(it)
        }

        currentPlanetSelection = null
        currentVehicleSelection = null
    }

    fun fetchVehicles() {
        viewModelScope.launch(Dispatchers.IO) {

            fetchDataFromRepository<List<VehicleResponse>>(repository.getVehicles()) {
//                _vehicles.postValue(it)
                _vehicles1.postValue(it.map { it.toVehicle() })
                val existingValue = _areDataReady.value
                _areDataReady.postValue(true to (existingValue?.second ?: false))
            }

//            repository.getVehicles().collect {
//                when (it) {
//                    is NetworkStatus.Success -> {
//                        val data = it.data
//                        if (data is List<*> && data.size > 0 && data[0] is VehicleResponse) {
//                            _vehicles.postValue(data)
//                        }
//                    }
//
//                    is NetworkStatus.Loading -> {
//
//                    }
//
//                    is NetworkStatus.Exception -> {
//                        _error.postValue(it.message)
//                    }
//
//                    is NetworkStatus.Failed -> {
//                        _error.postValue(it.message)
//                    }
//                }
//            }
        }
    }

    fun fetchPlanets() {
        viewModelScope.launch(Dispatchers.IO) {
            fetchDataFromRepository<List<PlanetResponse>>(repository.getPlanets()) {
//                _planets.postValue(it)
                _planets1.postValue(it.map { it.toPlanet() })
                val existingValue = _areDataReady.value
                _areDataReady.postValue((existingValue?.first ?: false) to true)
            }
        }
    }

    fun updateDataOfPlanet(planet: Planet) {
        currentPlanetSelection = planet
//        _selectedPlanets.add(planet)
//        val index = planets1.value?.indexOfFirst { it.name == planet.name && it.distance == planet.distance } ?: return
//        val currentData = _planets1.value?.toMutableList() ?: return
//        currentData[index] = planet
//        _planets1.postValue(
//            currentData
//        )
    }

    fun updateDataOfPlanet(vehicle: Vehicle) {
        currentVehicleSelection = vehicle
//        _selectedVehicles.add(vehicle)
//        val index = planets1.value?.indexOfFirst { it.name == planet.name && it.distance == planet.distance } ?: return
//        val currentData = _planets1.value?.toMutableList() ?: return
//        currentData[index] = planet
//        _planets1.postValue(
//            currentData
//        )
    }

    fun removeSelected(planet: Planet) {
        currentPlanetSelection = null
        _selectedPlanets.remove(planet)
    }

    fun removeSelected(vehicle: Vehicle) {
        currentVehicleSelection = null
//        _selectedVehicles.remove(vehicle)
    }

    private suspend inline fun <reified T> fetchDataFromRepository(
        fetchData: Flow<NetworkStatus>,
        crossinline onSuccess: (T) -> Unit
    ) {
        fetchData.collect {
            when (it) {
                is NetworkStatus.Success -> {
                    (it.data as? T)?.let(onSuccess)
                    _operationState.emit(OperationState.FINISH)
                }

                is NetworkStatus.Loading -> {
                    _operationState.emit(OperationState.IN_PROGRESS)
                }

                is NetworkStatus.Exception -> {
                    _error.postValue(it.message)
                    _operationState.emit(OperationState.FINISH)
                }

                is NetworkStatus.Failed -> {
                    _error.postValue(it.message)
                    _operationState.emit(OperationState.FINISH)
                }
            }
        }
    }
}

enum class OperationState {
    START, IN_PROGRESS, FINISH
}
