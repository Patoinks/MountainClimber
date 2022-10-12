package ipca.grupo2

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import ipca.grupo2.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var analytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        analytics = Firebase.analytics

        val storageRef = FirebaseStorage.getInstance().reference.child("image/images.jpg")
        val localfile = File.createTempFile("tempImage", "jpg")

        storageRef.getFile(localfile).addOnSuccessListener {

            /*if (progressDialog.isShowing)
                progressDialog.dismiss()*/

            val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
            //val bitmap = Bitmap.createScaledBitmap(image, 1200, 628, false)

            binding.imageViewURL.setImageBitmap(bitmap)
            /*val image = bitmap
            Picasso.get().load(image).resize(10, 10).into(bind.imageViewURL)*/

        }.addOnFailureListener {

        }

    }
}