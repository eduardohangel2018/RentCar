package com.android.rentmycar_v1.service.repository

import android.content.ContentValues
import android.content.Context
import com.android.rentmycar_v1.service.constants.DatabaseConstants
import com.android.rentmycar_v1.service.model.CarModel
import java.util.ArrayList

class CarRepository private constructor(context: Context) {

    var mCarsDatabaseHelper: AppDatabase = AppDatabase(context)

    companion object {

        private lateinit var repository: CarRepository

        fun getInstance(context: Context): CarRepository {
            // Verifico se foi inicializada
            if (!::repository.isInitialized) {
                return CarRepository(context)
            }
            return repository
        }
    }

    // Utilizo o Boolean para saber se deu falha ou sucesso
    fun save(car: CarModel): Boolean {
        return try {
            val db = mCarsDatabaseHelper.writableDatabase

            // ContanteValues é uma classe que passo valores
            val values = ContentValues()
            values.put(DatabaseConstants.CARS.COLUMNS.NAME, car.name)
            values.put(DatabaseConstants.CARS.COLUMNS.MODEL, car.model)
            values.put(DatabaseConstants.CARS.COLUMNS.PRICE, car.price)
            values.put(DatabaseConstants.CARS.COLUMNS.DESC, car.desc)
            values.put(DatabaseConstants.CARS.COLUMNS.CATEGORY, car.category)
            values.put(DatabaseConstants.CARS.COLUMNS.STATUS, car.status)

            db.insert(DatabaseConstants.CARS.TABLE_NAME, null, values)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getAll(): List<CarModel> {
        // Minha mutableList passa o CarModel e recebe um Array
        val list: MutableList<CarModel> = ArrayList()
        return list
    }

    fun getPresence(): List<CarModel> {
        val list: MutableList<CarModel> = ArrayList()
        return list
    }


    fun update(car: CarModel): Boolean {
        return try {
            val db = mCarsDatabaseHelper.writableDatabase

            val values = ContentValues()
            values.put(DatabaseConstants.CARS.COLUMNS.NAME, car.name)
            values.put(DatabaseConstants.CARS.COLUMNS.STATUS, car.status)

            val selection = DatabaseConstants.CARS.COLUMNS.ID + " = ?"
            val args = arrayOf(car.id.toString())

            db.update(DatabaseConstants.CARS.TABLE_NAME, values, selection, args)

            true
        } catch (e: Exception) {
            false
        }
    }

    fun delete(id: Int): Boolean {
        return try {
            val db = mCarsDatabaseHelper.writableDatabase

            val selection = DatabaseConstants.CARS.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            db.delete(DatabaseConstants.CARS.TABLE_NAME, selection, args)

            true
        } catch (e: Exception) {
            false
        }
    }

//    fun get(id: Int): CarModel? {
//
//        var car: CarModel? = null
//        return try {
//            val db = mCarsDatabaseHelper.writableDatabase
//
//            val projection = arrayOf(DatabaseConstants.CARS.COLUMNS.NAME, DatabaseConstants.CARS.COLUMNS.STATUS)
//
//            val selection = DatabaseConstants.CARS.COLUMNS.ID + " = ?"
//            val args = arrayOf(id.toString())
//
//            val cursor = db.query(
//                DatabaseConstants.CARS.TABLE_NAME,
//                projection,
//                selection,
//                args,
//                null,
//                null,
//                null
//            )
//
//            if (cursor != null && cursor.count > 0) {
//                cursor.moveToFirst()
//
//                val name = cursor.getString(cursor.getColumnIndex(DatabaseConstants.CARS.COLUMNS.NAME))
//                val status = (cursor.getInt(cursor.getColumnIndex(DatabaseConstants.CARS.COLUMNS.STATUS)) == 1)
//
//                car = CarModel(id, name,)
//            }
//
//            cursor?.close()
//            car
//        } catch (e: Exception) {
//            car
//        }
//    }
}

