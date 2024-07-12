package campus.tech.kakao.map

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import campus.tech.kakao.map.Contract.LocationEntry
import campus.tech.kakao.map.Contract.SavedLocationEntry


private const val SQL_CREATE_LOCATION_TABLE =
    "CREATE TABLE ${LocationEntry.TABLE_NAME} (" +
            "${LocationEntry.COLUMN_NAME_TITLE} TEXT primary key," +
            "${LocationEntry.COLUMN_NAME_ADDRESS} TEXT,"+
            "${LocationEntry.COLUMN_NAME_CATEGORY} TEXT"+
            ")"

private const val SQL_DELETE_LOCATION_TABLE = "DROP TABLE IF EXISTS ${LocationEntry.TABLE_NAME}"

private const val SQL_CREATE_SAVED_LOCATION_TABLE =
    "CREATE TABLE ${SavedLocationEntry.TABLE_NAME} (" +
            "${SavedLocationEntry.COLUMN_NAME_TITLE} TEXT primary key"+
            ")"

private const val SQL_DELETE_SAVED_LOCATION_TABLE =
    "DROP TABLE IF EXISTS ${SavedLocationEntry.TABLE_NAME}"
class LocationDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_LOCATION_TABLE)
        db.execSQL(SQL_CREATE_SAVED_LOCATION_TABLE)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_LOCATION_TABLE)
        db.execSQL(SQL_DELETE_SAVED_LOCATION_TABLE)
        onCreate(db)
    }
    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "map.db"
    }
}