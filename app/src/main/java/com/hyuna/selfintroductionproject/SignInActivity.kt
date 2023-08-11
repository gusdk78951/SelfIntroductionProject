package com.hyuna.selfintroductionproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat.startActivity
import android.app.Activity

class SignInActivity : AppCompatActivity() {
    //  ActivityResultLauncher 자료형인 resultLauncher 변수를 전역 변수로 선언
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private lateinit var signupBtn : Button
    private lateinit var id : EditText
    private lateinit var password : EditText

    private fun getRandomImageId(): Int {
        val imageIds = arrayOf(R.drawable.pikachu, R.drawable.bulbasaur, R.drawable.charmander, R.drawable.squirtle, R.drawable.eevee)
        val randomIndex = (0 until imageIds.size).random()
        return imageIds[randomIndex]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val id_data = result.data?.getStringExtra("id")?:""
                val password_data = result.data?.getStringExtra("password")?:""
                id.setText(id_data)
                password.setText(password_data)
            }
        }

        val loginBtn = findViewById<Button>(R.id.login)
        signupBtn = findViewById(R.id.signUp)
        id = findViewById(R.id.id)
        password = findViewById(R.id.password)

        // 로그인 버튼 클릭 시 -> 하나라도 입력이 없으면 토스트 메세지 출력 / 모두 입력 시 HomeActivity로 이동(아이디 전달)
        loginBtn.setOnClickListener {
            if (id.text.isBlank() || password.text.isBlank()) {
                Toast.makeText(this, "아이디/비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()
                val strData = id.text.toString()
                val intent = Intent(this, HomeActivity::class.java)
                val ramdomImageId = getRandomImageId()
                intent.putExtra("idFromSignUpActivity", strData)
                intent.putExtra("ImageId", ramdomImageId)
                startActivity(intent)
            }
        }
        // 회원가입 버튼 클릭 시 -> SignUpActivity로 이동
        signupBtn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)
        }
    }
}