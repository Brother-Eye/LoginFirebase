package br.com.diegosouza

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_site.*

class SiteActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site)

        mAuth = FirebaseAuth.getInstance();

        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("https://www.fiap.com.br")

        btsair.setOnClickListener {
            mAuth.signOut()
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
}
