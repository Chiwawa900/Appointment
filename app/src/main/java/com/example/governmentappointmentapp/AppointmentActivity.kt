package com.example.governmentappointmentapp

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.king.mysimplefirebasedatabaseapp.Appointment

class AppointmentActivity : AppCompatActivity() {
    lateinit var mBtnSave:Button
    lateinit var mBtnView:Button
    lateinit var mEdtName:EditText
    lateinit var mEdtEmail:EditText
    lateinit var mEdtAppointmentReason:EditText
    lateinit var progress:ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointment)
        mBtnSave = findViewById(R.id.mBtnSave)
        mBtnView = findViewById(R.id.mBtnView)
        mEdtName = findViewById(R.id.mEdtName)
        mEdtEmail = findViewById(R.id.mEdtEmail)
        mEdtAppointmentReason = findViewById(R.id.mEdtAppointmentReason)

        mBtnSave.setOnClickListener {
            //Receive data from the user
            var name = mEdtName.text.toString()
            var email = mEdtEmail.text.toString()
            var reason = mEdtAppointmentReason.text.toString()
            var time = System.currentTimeMillis()

            var progress = ProgressDialog(this)
            progress.setTitle("Booking")
            progress.setMessage("Please wait...")

            if (name.isEmpty() or email.isEmpty() or reason.isEmpty()){
                Toast.makeText(this,"Please fill all the inputs",Toast.LENGTH_LONG).show()
            }else{
                //Proceed to save data
                //Create a child in the database
                var my_child = FirebaseDatabase.getInstance().reference.child("Appointments/$time")
                var data = Appointment(name,email,reason)

                //To save data, simply set the data to my_child
                progress.show()
                my_child.setValue(data).addOnCompleteListener { task->
                    progress.dismiss()
                    if (task.isSuccessful){
                        mEdtName.setText(null)
                        mEdtEmail.setText(null)
                        mEdtAppointmentReason.setText(null)
                        Toast.makeText(this,"Booking successful",Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this,"Booking failed",Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        mBtnView.setOnClickListener {
            startActivity(Intent(this,ViewappointmentsActivity::class.java))
        }
    }
}