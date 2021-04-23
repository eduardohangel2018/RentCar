//package com.android.rentmycar_v1.viewmodel
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//
//class StatusAppViewModel : ViewModel() {
//
//    val componentes: LiveData<ComponentesVisuais> get() = _componentes
//
//    private var _componentes: MutableLiveData<ComponentesVisuais> =
//            MutableLiveData<ComponentesVisuais>().also {
//                it.value = temComponentes
//            }
//
//    var temComponentes: ComponentesVisuais = ComponentesVisuais()
//        set(value) {
//            field = value
//            _componentes.value = value
//        }
//
//}
//
//class ComponentesVisuais(
//        val appBar: Boolean = false,
//        val bottomNavigation: Boolean = false
//)