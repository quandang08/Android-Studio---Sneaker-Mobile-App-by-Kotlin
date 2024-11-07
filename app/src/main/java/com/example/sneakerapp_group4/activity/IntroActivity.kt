package com.example.sneakerapp_group4.activity

import android.content.Intent
import android.os.Bundle
import com.example.sneakerapp_group4.databinding.ActivityIntroBinding

class IntroActivity : BaseActivity(){
    private lateinit var binding:ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Nhap vao button SignIn se chuyen sang Main - Trang Tru
        binding.signInButton.setOnClickListener {
            startActivity(Intent(this@IntroActivity, MainActivity::class.java))
        }

    }
}