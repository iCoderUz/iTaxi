package icoder.itaxi.uz.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import icoder.itaxi.uz.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnSubmit.setOnClickListener {
            onSignIn()
        }
    }

    private fun onSignIn() {
        Log.d("ok", "0")
        FirebaseDatabase.getInstance().getReference("clients").addValueEventListener(object :
            ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                Log.d("ok", "1")
                if (p0.child(phone.text.toString()).exists()) {
                    Log.d("ok", "2")
                    //val client: Client? = p0.child(phone.text.toString()).getValue(Client::class.java)
                    if (yandex.isChecked)
                        startActivity(Intent(applicationContext, MainActivity::class.java))
                    else
                        startActivity(Intent(applicationContext, OsmMapActivity::class.java))
                }
            }
        })
    }
}
