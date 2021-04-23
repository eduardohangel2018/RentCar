package com.android.rentmycar_v1.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.rentmycar_v1.R
import com.android.rentmycar_v1.service.constants.CarConstants
import com.android.rentmycar_v1.service.model.Car
import com.android.rentmycar_v1.viewmodel.CarsFormViewModel
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_car_form.*

class CarFormActivity : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener {

    private val TAG = "CarFormActivity"
    private lateinit var carsViewModel: CarsFormViewModel
    private var mCarId: Int = 0
    // Firebase
    internal var id: String = ""
    private var firestoreDB: FirebaseFirestore? = null
    // Analytics
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    private var spinner:Spinner ? = null
    private var arrayAdapter:ArrayAdapter<String> ? =null
    private var categoryList = arrayOf(
            "Sedan",
            "Hath",
            "Utilitário",
            "Picape",
            "SUV"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_form)

        carsViewModel = ViewModelProvider(this).get(CarsFormViewModel::class.java)

        // Declara Variáveis spinner
        spinner = findViewById(R.id.spinnerCategory)
        val adapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_dropdown_item, categoryList)
        spinner?.adapter = adapter
        spinner?.onItemSelectedListener = this

        // Eventos
        setListeners()
        // Cria observadores
        observe()
        // Carrega os dados
        loadCars()
        // Status Default
        radAvailable.isChecked = true

        // Firebase
//        firestoreDB = FirebaseFirestore.getInstance()
//        val bundle = intent.extras
//        if (bundle != null) {
//            lbName.setText(bundle.getString("UpdateCarName"))
//            lbModel.setText(bundle.getString("UpdateCarModel"))
//            lbPrice.setText(bundle.getString("UpdateCarPrice"))
//            lbDesc.setText(bundle.getString("UpdateCarDesc"))
//            lbCat.setText(bundle.getString("UpdateCarCategory"))
//            buttonImage.setImageResource(bundle.getInt("updateCarImage"))
//        }
//
//        buttonSave.setOnClickListener {
//            val name = lbName.text.toString()
//            val model = lbModel.text.toString()
//            val price = lbPrice.text.toString()
//            val description = lbDesc.text.toString()
//            val category = lbCat.text.toString()
//
//            if (name.isNotEmpty() || model.isNotEmpty() || price.isNotEmpty() || description.isNotEmpty() || category.isNotEmpty()) {
//                if (id.isNotEmpty()) {
//                    updateCloudCar(id, name, model, price, description, category)
//                } else {
//                    addCloudCar(name, model, price, description, category)
//                }
//            }
//            finish()
//        }
//        // End Firebase
    }

    override fun onClick(v: View) {
        val id = v.id
        if (id == R.id.buttonSave) {

            val name = lbName.text.toString()
            val model = lbModel.text.toString()
            val desc = lbDesc.text.toString()
            val price = lbPrice.text.toString()
            val category = lbCat.text.toString()
            val status = radAvailable.isChecked

            carsViewModel.save(mCarId, name, model, price, desc, category, status)
        }
    }

    private fun loadCars() {
        val bundle = intent.extras
        if (bundle != null){

            mCarId = bundle.getInt(CarConstants.carId)
            carsViewModel.load(mCarId)
        }
    }

    private fun observe() {
        carsViewModel.saveCar.observe(this, Observer {
            if (it) {
                Toast.makeText(applicationContext, "Sucesso", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Falha", Toast.LENGTH_SHORT).show()
            }
            finish()
        })
        // Tras os dados do carro
        carsViewModel.car.observe(this, Observer {
            lbName.setText(it.name)
            lbModel.setText(it.model)
            lbDesc.setText(it.desc)
            lbPrice.setText(it.price)
            lbCat.setText(it.category)
            if(it.status) {
                radAvailable.isChecked = true
            } else {
                radUnavailable.isChecked = true
            }
        })
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var carCategory:String = parent?.getItemAtPosition(position) as String
        lbCat.text = carCategory
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Toast.makeText(applicationContext, "Nothing Select", Toast.LENGTH_LONG).show()
    }

    private fun setListeners() {

        buttonSave.setOnClickListener(this)
    }

    // Firebase Functions
//    private fun addCloudCar(name: String, model: String, price: String, description: String, category: String) {
//        val car = Car(name, model, price, description, category).toMap()
//        firestoreDB!!.collection("cars")
//                .add(car)
//                .addOnSuccessListener { documentReference ->
//                    Log.e(TAG, "DocumentReference adicionado")
//                }
//                .addOnFailureListener {
//                    Log.e(TAG, "Erro ao adicionar")
//                }
//    }
//    private fun updateCloudCar(id: String, name: String, model: String, price: String, description: String, category: String) {
//        return
//    }
    // End Firebase functions

}