package adam.illhaveacompany.saveimagesinsqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.media.Image

class DatabaseHandler (context: Context):
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object {
        //changed when I want to add a column
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "PictureDatabase"

        private const val TABLE_PICTURE = "PictureTABLE"

        private const val KEY_ID = "_id"
        private const val KEY_PICTURE = "pictureBitmap"
    }//9

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_PICTURE_TABLE = ("CREATE TABLE " + TABLE_PICTURE + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_PICTURE + " TEXT" + ")")
        //pushes the code through
        db?.execSQL(CREATE_PICTURE_TABLE)
    }//10

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_PICTURE)
        onCreate(db)
    }//11

    fun addPicture(picture: Picture) : Long {
        val db = this.writableDatabase

        val contentValues = ContentValues()

        contentValues.put(KEY_ID, picture.image)

        val success = db.insert(TABLE_PICTURE, null, contentValues)
        db.close()
        return success
    }

}