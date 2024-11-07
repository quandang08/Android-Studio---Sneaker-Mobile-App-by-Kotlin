package com.example.sneakerapp_group4.activity

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.sneakerapp_group4.Model.MainViewModel
import com.example.sneakerapp_group4.Model.SliderModel
import com.example.sneakerapp_group4.Adapter.SliderAdapter
import com.example.sneakerapp_group4.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private val viewModel = MainViewModel()
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBanner()
    }
    //Phương thức initBanner, được sử dụng để tải dữ liệu banner và hiển thị nó trên giao diện người dùng.
    //Đảm bảo rằng dữ liệu banner được tải và hiển thị đúng cách, đồng thời cung cấp trải nghiệm mượt mà cho người dùng nhờ có ProgressBar khi dữ liệu đang được tải.
    private fun initBanner() {
        binding.progressBarBanner.visibility = View.VISIBLE
        viewModel.banners.observe(this, { items ->
            banners(items)
            binding.progressBarBanner.visibility = View.GONE
        })
                viewModel.loadBanners()
    }

    private fun banners(images:List<SliderModel>){
        binding.viewpagerSlider.adapter = SliderAdapter(images,binding.viewpagerSlider)
        binding.viewpagerSlider.clipToPadding = false
        binding.viewpagerSlider.clipChildren = false
        binding.viewpagerSlider.offscreenPageLimit = 3
        binding.viewpagerSlider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40)) //
        }
        binding.viewpagerSlider.setPageTransformer(compositePageTransformer)
        if (images.size > 1){
            binding.dotIndicator.visibility = View.VISIBLE
            binding.dotIndicator.attachTo(binding.viewpagerSlider)
        }
    }
}