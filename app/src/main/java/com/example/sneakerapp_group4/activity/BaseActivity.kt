package com.example.sneakerapp_group4.activity

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sneakerapp_group4.R

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // Đặt cờ cho cửa sổ để mở rộng giao diện ra toàn bộ màn hình,
        // bao gồm cả khu vực phía sau thanh trạng thái và thanh điều hướng.
        // Điều này tạo ra trải nghiệm tràn viền toàn màn hình cho Activity.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

    }
}