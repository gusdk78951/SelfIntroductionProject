package com.hyuna.selfintroductionproject

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SignUpActivity : AppCompatActivity() {
    private lateinit var signupBtn : Button
    private lateinit var id : EditText
    private lateinit var password : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val name = findViewById<EditText>(R.id.name)
        signupBtn = findViewById(R.id.signUp)
        id = findViewById(R.id.id)
        password = findViewById(R.id.password)

        // 회원가입 버튼 클릭 시 -> 하나라도 입력이 없으면 토스트 메세지 출력 / 모두 입력 시 SignInActivity로 이동(id, password 전달)
        signupBtn.setOnClickListener {
            if (name.text.isBlank() || id.text.isBlank() || password.text.isBlank()) {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "회원가입 성공!", Toast.LENGTH_SHORT).show()
                intent.putExtra("id", id.text.toString())
                intent.putExtra("password", password.text.toString())
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }
}