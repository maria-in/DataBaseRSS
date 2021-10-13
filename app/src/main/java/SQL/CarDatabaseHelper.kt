package SQL

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf

class CarDatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_MARK + " TEXT, " +
                COLUMN_MODEL + " TEXT, " +
                COLUMN_YEAR + " INTEGER);"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addCar(mark: String, model: String, year: Int): String {
        val db: SQLiteDatabase = this.writableDatabase
        val contentValue = contentValuesOf(COLUMN_MARK to mark, COLUMN_MODEL to model, COLUMN_YEAR to year)//ContentValues
        val insertResult: Long = db.insert(TABLE_NAME, null, contentValue)
        if (insertResult.toInt() == -1)
            return "Not insert successfully"
        else
            return "Insert was success"
    }

    fun getAllCar(): Cursor? {
        return try {
            readableDatabase.rawQuery("SELECT * FROM $TABLE_NAME", null)
        } catch (ex: Exception){
            null
        }
    }

    fun deleteCar(idCar: Int): String{
        val db: SQLiteDatabase = this.writableDatabase
        val result: Int = db.delete(TABLE_NAME, "_id=?", arrayOf(idCar.toString()))
        return if (result == -1){
            "Can't delete"
        } else
            "Удалено"
    }

    companion object{
        const val DATABASE_NAME = "CarDatabase.db"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "car_database"
        const val COLUMN_ID = "_id"
        const val COLUMN_MARK = "car_mark"
        const val COLUMN_MODEL = "car_model"
        const val COLUMN_YEAR = "car_year"
    }
}