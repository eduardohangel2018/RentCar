package com.android.rentmycar_v1.service.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.provider.FontsContract
import com.android.rentmycar_v1.service.constants.DatabaseConstants

// O SQLiteOpenHelper identifica se tenho meu database criado
class AppDatabase(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {
    // Executa caso não exista o database
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_CARS)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "Cars.db"

        private const val CREATE_TABLE_CARS =
                ("create table " + DatabaseConstants.CARS.TABLE_NAME + " ("
                        + DatabaseConstants.CARS.COLUMNS.ID + " integer primary key autoincrement, "
                        + DatabaseConstants.CARS.COLUMNS.NAME + " text, "
                        + DatabaseConstants.CARS.COLUMNS.MODEL + " text, "
                        + DatabaseConstants.CARS.COLUMNS.DESCRIPTION + " text, "
                        + DatabaseConstants.CARS.COLUMNS.PRICE + " text, "
                        + DatabaseConstants.CARS.COLUMNS.CATEGORY + " text, "
                        + DatabaseConstants.CARS.COLUMNS.STATUS + " integer);")

    }
}