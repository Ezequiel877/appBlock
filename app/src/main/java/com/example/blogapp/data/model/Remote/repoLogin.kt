package com.example.blogapp.data.model.Remote

import android.graphics.Bitmap
import android.net.Uri
import com.example.blogapp.data.model.DataSource
import com.example.blogapp.data.model.user
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream
import java.util.*

class repoLogin {
    suspend fun singIn(email: String, passwork: String): FirebaseUser? {
        val query = FirebaseAuth.getInstance().signInWithEmailAndPassword(email, passwork).await()
        return query.user
    }

    suspend fun singUp(email: String, passwork: String, username: String): FirebaseUser? {
        val query =
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,passwork).await()
        query.user?.uid?.let {uid->
            FirebaseFirestore.getInstance().collection("user").document(uid)
                .set(user(email, username, " username")).await()

        }
        return query.user
    }

    suspend fun getPhoto(image: Bitmap, username: String) {
        val user = FirebaseAuth.getInstance().currentUser
        val query = FirebaseStorage.getInstance().reference.child("${user?.uid}/login_imagen")
        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val intent = query.putBytes(baos.toByteArray()).await().storage.downloadUrl.await().toString()
        val changerUser = UserProfileChangeRequest.Builder()
            .setDisplayName(username)
            .setPhotoUri(Uri.parse(intent))
            .build()
        user?.updateProfile(changerUser)?.await()
    }

suspend fun getPost(ImagenBITmap: Bitmap, descripcion: String){
    val user=FirebaseAuth.getInstance().currentUser
    val uidItem=UUID.randomUUID().toString()
    val firebase=FirebaseStorage.getInstance().reference.child("${user?.uid}/datos_post/$uidItem")
    val baos = ByteArrayOutputStream()
    ImagenBITmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
    val intent = firebase.putBytes(baos.toByteArray()).await().storage.downloadUrl.await().toString()
    user?.let {
        it.displayName?.let {  display->
        FirebaseFirestore.getInstance().collection("datos_post").add(DataSource(nombre = display,
                imagen = it.photoUrl.toString(),
                detalles = intent,
                ImagenD = descripcion
            ))
        }
    }



}}