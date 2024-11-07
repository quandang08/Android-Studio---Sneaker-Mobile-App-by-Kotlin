package com.example.sneakerapp_group4.Adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.sneakerapp_group4.Model.SliderModel
import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.request.RequestOptions
import com.example.sneakerapp_group4.R

// Adapter cho ViewPager2, giúp hiển thị danh sách ảnh trong chế độ trượt.
class SliderAdapter(
    private var sliderItems: List<SliderModel>, // Danh sách các đối tượng SliderModel chứa URL ảnh
    private val viewPager2: ViewPager2 // Đối tượng ViewPager2 để hiển thị các ảnh theo dạng trượt
) : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    private lateinit var context: Context // Context để truy cập vào các tài nguyên
    private val runnable = Runnable {
        sliderItems // Lặp lại danh sách ảnh khi cuộn đến cuối
        notifyDataSetChanged() // Thông báo rằng dữ liệu đã thay đổi
    }

    // Tạo ViewHolder cho từng phần tử trong ViewPager2
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SliderViewHolder {
        context = parent.context // Lấy context từ parent để sử dụng trong Glide
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.slider_item_container, parent, false) // Inflate layout cho từng item
        return SliderViewHolder(view) // Trả về ViewHolder chứa item
    }

    // Gắn dữ liệu cho từng phần tử ViewHolder, sẽ được gọi khi cần hiển thị ảnh
    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.setImage(sliderItems[position], context) // Gắn hình ảnh vào ImageView trong ViewHolder
        if (position == sliderItems.lastIndex - 1) { // Nếu đến phần tử kế cuối
            viewPager2.post(runnable) // Chạy runnable để reset lại danh sách ảnh (tạo hiệu ứng lặp lại)
        }
    }

    // Trả về số lượng phần tử trong danh sách
    override fun getItemCount(): Int {
        return sliderItems.size // Trả về số lượng slider items
    }

    // ViewHolder chứa ImageView hiển thị từng ảnh trong danh sách
    class SliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById<ImageView>(R.id.imageSlider) // ImageView để hiển thị ảnh

        // Hàm setImage giúp tải ảnh từ URL và hiển thị trong imageView
        fun setImage(sliderItem: SliderModel, context: Context) {
            val reqOptions = RequestOptions().transform(CenterInside()) // RequestOptions để điều chỉnh ảnh
            Glide.with(context)
                .load(sliderItem.url) // Tải ảnh từ URL trong sliderItem
                .apply(reqOptions) // Áp dụng các tùy chọn (như CenterInside)
                .into(imageView) // Gắn ảnh vào imageView
        }
    }
}
