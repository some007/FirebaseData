package com.example.firebasedata

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    private lateinit var database: FirebaseDatabase
    private lateinit var reference : DatabaseReference
    //lateinit var progress:ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        database = FirebaseDatabase.getInstance()
        reference = database.getReference("Users")

        data_Add.setOnClickListener(View.OnClickListener {
            storeData()
        })

        get_Data.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this,GetData::class.java))

        })


    }

    private fun storeData()
    {
        var name = et_Name.text.toString().trim()
        var number = et_Number.text.toString().trim()

        if(name.isNotEmpty() && number.isNotEmpty())
        {
           // progress.show()
                var model = DatabaseModel(name,number)
                var id = reference.push().key


                //here we can send the data to the firebase
                reference.child(id!!).setValue(model)
                et_Name.setText("")
                et_Number.setText("")
           // progress.dismiss()
            Toast.makeText(this,"Data Added",Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(this,"All field Required!!",Toast.LENGTH_LONG).show()
        }
    }

}