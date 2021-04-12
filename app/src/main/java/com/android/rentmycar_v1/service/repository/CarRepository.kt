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

    // Insere o Carro
    // Utilizo o Boolean para saber se deu falha ou sucesso
    fun save(car: CarModel): Boolean {
        return try {
            val db = mCarsDatabaseHelper.writableDatabase

            // ContanteValues é uma classe que passo valores
            val contentValues = ContentValues()
            contentValues.put(DatabaseConstants.CARS.COLUMNS.NAME, car.name)
            contentValues.put(DatabaseConstants.CARS.COLUMNS.MODEL, car.model)
            contentValues.put(DatabaseConstants.CARS.COLUMNS.DESC, car.desc)
            contentValues.put(DatabaseConstants.CARS.COLUMNS.PRICE, car.price)
            contentValues.put(DatabaseConstants.CARS.COLUMNS.CATEGORY, car.category)
            contentValues.put(DatabaseConstants.CARS.COLUMNS.STATUS, car.status)

            db.insert(DatabaseConstants.CARS.TABLE_NAME, null, contentValues)
            true
        } catch (e: Exception) {
            false
        }
    }

    // Carrega os Carros
    fun get(id: Int): CarModel? {
        var car: CarModel? = null
        return try {
            val db = mCarsDatabaseHelper.readableDatabase

            val projection = arrayOf(
                    DatabaseConstants.CARS.COLUMNS.NAME,
                    DatabaseConstants.CARS.COLUMNS.MODEL,
                    DatabaseConstants.CARS.COLUMNS.DESC,
                    DatabaseConstants.CARS.COLUMNS.PRICE,
                    DatabaseConstants.CARS.COLUMNS.CATEGORY,
                    DatabaseConstants.CARS.COLUMNS.STATUS,
            )
            val selection = DatabaseConstants.CARS.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            val cursor = db.query(
                    DatabaseConstants.CARS.TABLE_NAME,
                    projection,
                    selection,
                    args,
                    null,
                    null,
                    null
            )

            if (cursor != null && cursor.count > 0) {
                cursor.moveToFirst()

                val name = cursor.getString(cursor.getColumnIndex(DatabaseConstants.CARS.COLUMNS.NAME))
                val model = cursor.getString(cursor.getColumnIndex(DatabaseConstants.CARS.COLUMNS.MODEL))
                val desc = cursor.getString(cursor.getColumnIndex(DatabaseConstants.CARS.COLUMNS.DESC))
                val price = cursor.getString(cursor.getColumnIndex(DatabaseConstants.CARS.COLUMNS.PRICE))
                val category = cursor.getString(cursor.getColumnIndex(DatabaseConstants.CARS.COLUMNS.CATEGORY))
                val status = (cursor.getInt(cursor.getColumnIndex(DatabaseConstants.CARS.COLUMNS.STATUS)) == 1)

                car = CarModel(id, name, model, price, desc, category, status)
            }

            car
        } catch (e: Exception) {
            car
        }
    }

    // Lista todos os carros
    fun getAll(): List<CarModel>{
        val list: MutableList<CarModel> = ArrayList()
        return try {
            val db = mCarsDatabaseHelper.readableDatabase

            val projection = arrayOf(
                    DatabaseConstants.CARS.COLUMNS.ID,
                    DatabaseConstants.CARS.COLUMNS.NAME,
                    DatabaseConstants.CARS.COLUMNS.MODEL,
                    DatabaseConstants.CARS.COLUMNS.DESC,
                    DatabaseConstants.CARS.COLUMNS.PRICE,
                    DatabaseConstants.CARS.COLUMNS.CATEGORY,
                    DatabaseConstants.CARS.COLUMNS.STATUS
            )
            val cursor = db.query(
                    DatabaseConstants.CARS.TABLE_NAME,
                    projection,
                    null,
                    null,
                    null,
                    null,
                    null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndex(DatabaseConstants.CARS.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DatabaseConstants.CARS.COLUMNS.NAME))
                    val model = cursor.getString(cursor.getColumnIndex(DatabaseConstants.CARS.COLUMNS.MODEL))
                    val desc = cursor.getString(cursor.getColumnIndex(DatabaseConstants.CARS.COLUMNS.DESC))
                    val price = cursor.getString(cursor.getColumnIndex(DatabaseConstants.CARS.COLUMNS.PRICE))
                    val category = cursor.getString(cursor.getColumnIndex(DatabaseConstants.CARS.COLUMNS.CATEGORY))
                    val status = (cursor.getInt(cursor.getColumnIndex(DatabaseConstants.CARS.COLUMNS.STATUS)) == 1)

                    val car = CarModel(id, name, model, desc, price, category, status)
                    list.add(car)
                }
            }
            cursor?.close()
            list
        } catch (e: Exception) {
            list
        }
    }

    // Lista os Carros Disponíveis
    fun getAvailable(): List<CarModel> {
        val list: MutableList<CarModel> = ArrayList()
        return try {
            val db = mCarsDatabaseHelper.readableDatabase

            val cursor = db.rawQuery("SELECT id, name, model, description, price, category, status FROM cars WHERE status = 0", null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndex(DatabaseConstants.CARS.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DatabaseConstants.CARS.COLUMNS.NAME))
                    val model = cursor.getString(cursor.getColumnIndex(DatabaseConstants.CARS.COLUMNS.MODEL))
                    val desc = cursor.getString(cursor.getColumnIndex(DatabaseConstants.CARS.COLUMNS.DESC))
                    val price = cursor.getString(cursor.getColumnIndex(DatabaseConstants.CARS.COLUMNS.PRICE))
                    val category = cursor.getString(cursor.getColumnIndex(DatabaseConstants.CARS.COLUMNS.CATEGORY))
                    val status = (cursor.getInt(cursor.getColumnIndex(DatabaseConstants.CARS.COLUMNS.STATUS)) == 1)

                    val car = CarModel(id, name, model, desc, price, category, status)
                    list.add(car)
                }
            }
            cursor?.close()
            list
        } catch (e: Exception) {
            list
        }
    }

    // Lista os Carros Indisponíveis
    fun getUnavailable(): List<CarModel> {
        val list: MutableList<CarModel> = ArrayList()
        return try {
            val db = mCarsDatabaseHelper.readableDatabase

            val cursor = db.rawQuery("SELECT id, name, model, description, price, category, status FROM cars WHERE status = 1", null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndex(DatabaseConstants.CARS.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DatabaseConstants.CARS.COLUMNS.NAME))
                    val model = cursor.getString(cursor.getColumnIndex(DatabaseConstants.CARS.COLUMNS.MODEL))
                    val desc = cursor.getString(cursor.getColumnIndex(DatabaseConstants.CARS.COLUMNS.DESC))
                    val price = cursor.getString(cursor.getColumnIndex(DatabaseConstants.CARS.COLUMNS.PRICE))
                    val category = cursor.getString(cursor.getColumnIndex(DatabaseConstants.CARS.COLUMNS.CATEGORY))
                    val status = (cursor.getInt(cursor.getColumnIndex(DatabaseConstants.CARS.COLUMNS.STATUS)) == 1)

                    val car = CarModel(id, name, model, desc, price, category, status)
                    list.add(car)
                }
            }
            cursor?.close()
            list
        } catch (e: Exception) {
            list
        }
    }

    // Atualiza os Carros
    fun update(car: CarModel): Boolean {
        return try {
            val db = mCarsDatabaseHelper.writableDatabase

            val values = ContentValues()
            values.put(DatabaseConstants.CARS.COLUMNS.NAME, car.name)
            values.put(DatabaseConstants.CARS.COLUMNS.MODEL, car.model)
            values.put(DatabaseConstants.CARS.COLUMNS.DESC, car.desc)
            values.put(DatabaseConstants.CARS.COLUMNS.PRICE, car.price)
            values.put(DatabaseConstants.CARS.COLUMNS.CATEGORY, car.category)
            values.put(DatabaseConstants.CARS.COLUMNS.STATUS, car.status)

            // Atualiza através do ID, onde o ID seja igual ao encontrado
            val selection = DatabaseConstants.CARS.COLUMNS.ID + " = ?"
            val args = arrayOf(car.id.toString())

            db.update(DatabaseConstants.CARS.TABLE_NAME, values, selection, args)

            true
        } catch (e: Exception) {
            false
        }
    }

    // Remove os Carros
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
}

