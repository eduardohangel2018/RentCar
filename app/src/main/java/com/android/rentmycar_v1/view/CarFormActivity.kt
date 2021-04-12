package com.android.rentmycar_v1.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.rentmycar_v1.R
import com.android.rentmycar_v1.service.constants.CarConstants
import com.android.rentmycar_v1.viewmodel.CarsFormViewModel
import kotlinx.android.synthetic.main.activity_car_form.*
import kotlinx.android.synthetic.main.row_car.*

class CarFormActivity : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener {

    private lateinit var mViewModel: CarsFormViewModel
    private var mCarId: Int = 0

    private var spinner:Spinner ? = null
//    private var arrayAdapter:ArrayAdapter<String> ? =null
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

        mViewModel = ViewModelProvider(this).get(CarsFormViewModel::class.java)

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
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var carCategory:String = parent?.getItemAtPosition(position) as String
        lbCat.text = carCategory
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Toast.makeText(applicationContext, "Nothing Select", Toast.LENGTH_LONG).show()
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

            mViewModel.save(mCarId, name, model, price, desc, category, status)
        }
    }

    private fun loadCars() {
        val bundle = intent.extras
        if (bundle != null){

            mCarId = bundle.getInt(CarConstants.carId)
            mViewModel.load(mCarId)
        }
    }


    private fun setListeners() {

        buttonSave.setOnClickListener(this)
    }

    private fun observe() {
        mViewModel.saveCar.observe(this, Observer {
            if (it) {
                Toast.makeText(applicationContext, "Sucesso", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Falha", Toast.LENGTH_SHORT).show()
            }
            finish()
        })
        // Tras os dados do carro
        mViewModel.car.observe(this, Observer {
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
}