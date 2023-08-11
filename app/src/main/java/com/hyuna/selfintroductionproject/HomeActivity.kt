package com.hyuna.selfintroductionproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // SignInActivity의 아이디 text값 받아오기
        val strData = intent.getStringExtra("idFromSignUpActivity")
        val idText = findViewById<TextView>(R.id.idText)
        idText.setText(strData)

        // 종료 버튼 클릭 시 현재 액티비티 종료
        val btn_close = findViewById<Button>(R.id.close)
        btn_close.setOnClickListener {
            finish()
        }

        val imageView = findViewById<ImageView>(R.id.imageView)
        val imageId = intent.getIntExtra("ImageId", 0)
        imageView.setImageResource(imageId)
    }
}