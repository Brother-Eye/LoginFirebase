package br.com.diegosouza

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import com.google.firebase.auth.FirebaseUser








class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance();
        if(usuarioEstaConectado()){
            abrirSite()
        }

        btentrar.setOnClickListener {
            entrar()

        }
        btcriar.setOnClickListener {
            criarConta()

        }
        btlimpar.setOnClickListener {
            limparCampos()
        }
    }

    private fun usuarioEstaConectado(): Boolean{
        val currentUser = mAuth.currentUser
        return currentUser != null
    }

    private fun  entrar(){
        mAuth.signInWithEmailAndPassword(etemail.text.toString(), etsenha.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                   abrirSite()
                } else {

                    Toast.makeText(
                        this, task.exception?.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

    }

    private fun criarConta(){
        mAuth.createUserWithEmailAndPassword(etemail.text.toString(), etsenha.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    abrirSite()
                } else {
                    Toast.makeText(
                        this, task.exception?.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

    }

    private fun limparCampos(){
        etemail.setText("")
        etsenha.setText("")

    }

    private fun abrirSite(){
        val intent = Intent(this,SiteActivity::class.java)
        startActivity(intent)
        finish()
    }
}
