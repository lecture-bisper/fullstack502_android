package btic.fullstack502.app_20250808

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import btic.fullstack502.app_20250808.databinding.ActivityGlideBinding
import com.bumptech.glide.Glide

class GlideActivity : AppCompatActivity() {

  private val binding by lazy { ActivityGlideBinding.inflate(layoutInflater) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

    binding.btnGlideUse.setOnClickListener {
      val url = binding.etGlide.text.toString()

//      Glide 객체 생성
      Glide.with(this)
//        가져올 이미지 url 설정, 리소스 및 외부 url
        .load(url)
//        가져올 이미지 크기 설정 (기본값 자동)
        .override(300, 300)
//        다른 이미지 로딩 중 출력할 이미지 설정
        .placeholder(R.drawable.dog02)
//        지정한 이미지를 못 가져올 경우 출력할 이미지 설정
        .error(R.drawable.cat_error)
//        이미지를 출력할 ImageView 태그 설정
        .into(binding.ivGlideImage)

      binding.etGlide.setText("")
    }
  }
}