package adam.illhaveacompany.saveimagesinsqlite

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val STORAGE_PERMISSION_CODE = 1
    private val GALLERY = 2
    //4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        change_background_button.setOnClickListener { view ->
            if(isPermissionAllowed(Manifest.permission.READ_EXTERNAL_STORAGE)){


                val pickPhotoIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

                startActivityForResult(pickPhotoIntent, GALLERY)
            }else{
                requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE,STORAGE_PERMISSION_CODE)
            }
        }//7
    }


    private fun requestPermission(sPermissionName: String, iRequestCode: Int) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, sPermissionName)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }

        ActivityCompat.requestPermissions(this, arrayOf(sPermissionName), iRequestCode)
    }//5


    private fun isPermissionAllowed(sPermission: String) : Boolean {
        val result = ContextCompat.checkSelfPermission(this, sPermission)

        return if (result == PackageManager.PERMISSION_GRANTED) true else false
    }//6

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK) {
            if(requestCode == GALLERY){
                try{
                    if(data!!.data != null) {
                        //this line is not necessarily needed
                        iv_image.visibility = View.VISIBLE
                        iv_image.setImageURI(data.data)
                    }else {
                        Toast.makeText(this, "image not transferable", Toast.LENGTH_SHORT).show()
                    }
                }catch(e: Exception){
                    e.printStackTrace()
                }
            }
        }
    }
}