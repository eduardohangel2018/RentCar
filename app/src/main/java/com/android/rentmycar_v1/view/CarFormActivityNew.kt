package com.android.rentmycar_v1.view
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.android.rentmycar_v1.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_car_form_new.*
import kotlinx.android.synthetic.main.activity_car_form_new.view.*

//class CarFormActivityNew: AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener {
//
//    private lateinit var mViewModel: CarsFormViewModelNew
//
//    private var spinner:Spinner ? = null
//    private var arrayAdapter:ArrayAdapter<String> ? =null
//    private var categoryList = arrayOf(
//            "Sedan",
//            "Hath",
//            "Utilitário",
//            "Picape",
//            "SUV"
//    )
//
//    companion object {
//        const val NAME = "name"
//        const val MODEL = "model"
//        const val PRICE = "price"
//        const val CATEGORY = "category"
//        var document = FirebaseFirestore.getInstance().collection("cars").document("rent")
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_car_form_new)
//
//        mViewModel = ViewModelProvider(this).get(CarsFormViewModelNew::class.java)
//
//        // Declara Variáveis
//        spinner = findViewById(R.id.spinnerCat)
//        val adapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_dropdown_item, categoryList)
//        spinner?.adapter = adapter
//        spinner?.onItemSelectedListener = this
//    }
//
//    override fun onClick(v: View?) {
//        val id = v?.id
//    }
//
//    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//        var carCategory:String = parent?.getItemAtPosition(position) as String
//        lbCat.text = carCategory
//    }
//
//    override fun onNothingSelected(parent: AdapterView<*>?) {
//        Toast.makeText(applicationContext, "Nothing Select", Toast.LENGTH_LONG).show()
//    }
//
//    // Salva carro no Firestore
//    fun saveCar(view: View?) {
//        var carName:String = lbName.text.toString()
//        var carModel:String = lbModel.text.toString()
//        var carPrice:String = lbPrice.text.toString()
//        var carCategory:String = lbCat.text.toString()
//
//        // Verifica se são vazios
//        if (carName.isEmpty() || carModel.isEmpty() || carPrice.isEmpty() || carCategory.isEmpty())
//            return Toast.makeText(applicationContext, "Falta Preencher Algum Campo", Toast.LENGTH_LONG).show()
//
//        // Cria uma estrutura de par e valor
//        var dataToSave = HashMap<String, Any>()
//        dataToSave.put(NAME, carName)
//        dataToSave.put(MODEL, carModel)
//        dataToSave.put(PRICE, carPrice)
//        dataToSave.put(CATEGORY, carCategory)
//        document.set(dataToSave)
//                .addOnSuccessListener {
//                    Log.e("Main", "Carro Salvo!")
//                    Toast.makeText(applicationContext, "O Carro foi Adicionado", Toast.LENGTH_SHORT).show()
//                }
//                .addOnFailureListener {
//                    Log.e("Main", "Erro ao Adicionar o Carro")
//                    Toast.makeText(applicationContext, "Carro não foi Adicionado", Toast.LENGTH_SHORT).show()
//                }
//    }
//}

