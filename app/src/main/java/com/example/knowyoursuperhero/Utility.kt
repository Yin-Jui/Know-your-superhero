package com.example.knowyoursuperhero

import com.firebase.ui.auth.data.model.User
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.android.synthetic.main.fragment_second.*

class Utility {
    companion object {
        fun checkExist(uid: String) {
            FirebaseFirestore.getInstance().collection("users")
                    .document(uid).get()
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val user: Users? = task.result?.toObject(Users::class.java)
                            if (user == null) {
                                //Create a new user in database
                                FirebaseFirestore.getInstance().collection("users")
                                        .document(uid)
                                        .set(Users())
                            }
                        }
                    }
        }

        fun setName(userName: String) {
            val user = FirebaseAuth.getInstance().currentUser
            if (user != null) {
                val uid = FirebaseAuth.getInstance().currentUser!!.uid
                FirebaseFirestore.getInstance().collection("users")
                        .document(uid).get()
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val user: Users? = task.result?.toObject(Users::class.java)
                                if (user != null) {
                                    //Update user's information
                                    user.username = userName;
                                    FirebaseFirestore.getInstance().collection("users")
                                            .document(uid)
                                            .set(user)
                                }
                            }
                        }
            }
        }

        fun setLocation(location: latlng) {
            val user = FirebaseAuth.getInstance().currentUser
            if (user != null) {
                val uid = FirebaseAuth.getInstance().currentUser!!.uid
                FirebaseFirestore.getInstance().collection("users")
                        .document(uid).get()
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val user: Users? = task.result?.toObject(Users::class.java)
                                if (user != null) {
                                    //Update user's information
                                    user.location = location
                                    FirebaseFirestore.getInstance().collection("users")
                                            .document(uid)
                                            .set(user)
                                }
                            }
                        }

            }
        }
    }
}