package com.android.rentmycar_v1.service.repository
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import io.grpc.internal.SharedResourceHolder

private const val TAG = "FirebaseAuth"

class FirebaseAuth(private val firebaseAuth: FirebaseAuth) {

    private fun logout(firebaseAuth: FirebaseAuth) {
        firebaseAuth.signOut()
    }

    private fun verifyUser(firebaseAuth: FirebaseAuth) {
        val userFirebase : FirebaseUser? = firebaseAuth.currentUser
        if (userFirebase != null) {

        } else {

        }
    }

    private fun userLogin(firebaseAuth: FirebaseAuth) {
        firebaseAuth.signInWithEmailAndPassword("eduardohangel@gmail.com","teste123")
                .addOnSuccessListener {
                }
                .addOnFailureListener {
                }
    }

    private fun userAuth(firebaseAuth: FirebaseAuth) {
        val firebaseAuth = Firebase.auth
        val currentUser: FirebaseUser? = firebaseAuth.currentUser
        if(currentUser != null) {
        } else {
        }
    }

    fun register(email: String, password: String): MutableLiveData<Boolean> {
        val liveData = MutableLiveData<Boolean>()
        val registerTask = firebaseAuth.createUserWithEmailAndPassword(email, password)
                registerTask.addOnSuccessListener {
                    Log.i(TAG, "Cadastro realizado com sucesso")
                    liveData.value = true
                }
                registerTask.addOnFailureListener {
                    Log.e(TAG, "Falha no Cadastro", it)
                    liveData.value = false
                }
        return liveData
    }
}