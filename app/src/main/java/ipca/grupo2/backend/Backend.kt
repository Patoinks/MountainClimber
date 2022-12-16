package ipca.grupo2.backend

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage

object Backend {
    private var sr = Firebase.storage
    private var fs = Firebase.firestore
    private var au = Firebase.auth

    fun getFS() : FirebaseFirestore {
        return fs;
    }

    fun getSR() : FirebaseStorage {
        return sr;
    }

    fun getAU() : FirebaseAuth {
        return au;
    }

    fun getCurrentUser() : FirebaseUser? {
        return au.currentUser;
    }
}
