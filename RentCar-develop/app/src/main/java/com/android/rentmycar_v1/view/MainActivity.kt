package com.android.rentmycar_v1.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.android.rentmycar_v1.R
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_car_form.*
import java.util.Observer

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration

//    var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firebaseAuth = Firebase.auth

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            startActivity(Intent(applicationContext, CarFormActivity::class.java))
        }

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nav_all, R.id.nav_available, R.id.nav_rented
        ), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    // Start Firebase
//    fun addCloudCar(view: View) {
//        var name:String = lbName.text.toString()
//        var model:String = lbModel.text.toString()
//        var price:String = lbPrice.text.toString()
//        var desc:String = lbDesc.text.toString()
//        var category:String = lbCat.text.toString()
//
//        if(name.isEmpty() || model.isEmpty() || price.isEmpty() || desc.isEmpty() || category.isEmpty()) return;
//        var dataToSave = HashMap<String, Any>()
//        dataToSave.put(NAME, lbName)
//        dataToSave.put(MODEL, lbModel)
//        dataToSave.put(PRICE, lbPrice)
//        dataToSave.put(DESCRIPTION, lbDesc)
//        dataToSave.put(CATEGORY, lbCat)
//        document.set(dataToSave)
//                .addOnSuccessListener {
//                    Log.e("Main", "Carro Calvo!")
//                    Toast.makeText(applicationContext, "Car has been added!", Toast.LENGTH_SHORT).show()
//                }
//                .addOnFailureListener { e ->
//                    Log.e("Main", "Erro ao Adicionar o Carro", e)
//                    Toast.makeText(applicationContext, "Car could not be added!", Toast.LENGTH_SHORT).show()
//                }
//    }
//
//    companion object {
//        const val NAME = "name"
//        const val MODEL = "model"
//        const val PRICE = "price"
//        const val DESCRIPTION = "description"
//        const val CATEGORY = "category"
//        var document = FirebaseFirestore.getInstance().document("rent/cars")
//    }
}