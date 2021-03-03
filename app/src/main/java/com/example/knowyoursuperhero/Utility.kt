package com.example.knowyoursuperhero

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
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

        fun checkLogin(): String {
            val user = FirebaseAuth.getInstance().currentUser
            var name: String = ""
            if (user != null) {
                val uid = FirebaseAuth.getInstance().currentUser!!.uid
                FirebaseFirestore.getInstance().collection("users")
                        .document(uid).get()
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val user: Users? = task.result?.toObject(Users::class.java)
                                if (user != null) {
                                    name = user.username
                                }
                            }
                        }
            }
            return name
        }
    }
}