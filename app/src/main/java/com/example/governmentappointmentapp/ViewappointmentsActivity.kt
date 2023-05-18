package com.example.governmentappointmentapp

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.king.mysimplefirebasedatabaseapp.Appointment

class ViewappointmentsActivity : AppCompatActivity() {
    lateinit var mListAppointments:ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewappointments)
        mListAppointments = findViewById(R.id.mListAppointments)

        var appointments:ArrayList<Appointment> = ArrayList()
        var myAdapter = CustomAdapter(applicationContext,appointments)
        var progress = ProgressDialog(this)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")

        //Access the table in the database

        var my_db = FirebaseDatabase.getInstance().reference.child("Appointments")
        //Start retrieving data
        progress.show()
        my_db.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                //Get the data and put it on the arraylist users
                appointments.clear()
                for (snap in p0.children){
                    var appointment = snap.getValue(Appointment::class.java)
                    appointments.add(appointment!!)
                }
                //Notify the adapter that data has changed
                myAdapter.notifyDataSetChanged()
                progress.dismiss()
            }

            override fun onCancelled(p0: DatabaseError) {
                progress.dismiss()
                Toast.makeText(applicationContext,"DB Locked",Toast.LENGTH_LONG).show()
            }
        })

        mListAppointments.adapter = myAdapter
    }
}
