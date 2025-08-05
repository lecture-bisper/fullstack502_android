package btic.fullstack502.app_20250805

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import btic.fullstack502.app_20250805.databinding.ActivityPhotoBinding

class PhotoActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    val binding = ActivityPhotoBinding.inflate(layoutInflater)
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

//    사진첩 앱 사용 후 액티비티가 종료되면 실행됨
//    사진첩 앱의 사용 결과를 가져오기
    val requestGalleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
      try {
//        사진첩에서 가져오는 이미지의 크기를 설정
//        매개변수로 파일 경로, X축, Y축 크기 설정
        val calRatio = calculateInSampleSize(
          it.data!!.data!!,
          resources.getDimensionPixelSize(R.dimen.imgSize),
          resources.getDimensionPixelSize(R.dimen.imgSize)
        )

//        안드로이드 시스템에서는 이미지를 Drawable, Bitmap 객체를 통해서 이미지를 표현함
//        Drawable 와 BitMap 은 서로 호환되어 Drawable <-> Bitmap 으로 변환 가능
//        Bitmap 은 BitmapFactory 를 통해서 생성할 수 있음
        
//        BitmapFactory 를 사용하여 위에서 설정한 이미지의 크기로 설정
        val option = BitmapFactory.Options()
        option.inSampleSize = calRatio

//        사진첩 앱에서 전달한 이미지를 contentResolver 를 통해서 이미지 파싱
        var inputStream = contentResolver.openInputStream(it.data!!.data!!)
//        파싱된 이미지 데이터를 안드로이드 시스템에서 사용하는 이미지 형식으로 변환
        val bitmap = BitmapFactory.decodeStream(inputStream, null, option)
//        파싱한 이미지 데이터를 변환했으므로 스트림 닫기
        inputStream!!.close()
        inputStream = null

//        xml 파일의 ImageView 태그에 파싱된 이미지 데이터를 적용
        bitmap?.let {
          binding.ivUser.setImageBitmap(bitmap)
        } ?: let {
          Log.d("**fullstack502**", "### 이미지 없음 ###")
        }
      }
      catch (e: Exception) {
        e.printStackTrace()
      }
    }

//    사진첩 버튼 클릭 이벤트
    binding.btnGallery.setOnClickListener {
//      안드로이드의 사진첩 앱에 접근
//      ACTION_PICK 사용 시 사진첩 앱을 사용할 수 있음
      val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//      가져올 데이터를 이미지 타입으로 제한
      galleryIntent.type = "image/*"
//      인텐트 전달
      requestGalleryLauncher.launch(galleryIntent)
    }
  }

//  사진첩 및 카메라를 사용하여 가져온 이미지의 크기를 작게 설정하는 메소드
  private fun calculateInSampleSize(fileUri: Uri, reqWidth: Int, reqHeight: Int) : Int {
    val options = BitmapFactory.Options()
    options.inJustDecodeBounds = true

    try {
      var inputStream = contentResolver.openInputStream(fileUri)
      BitmapFactory.decodeStream(inputStream, null, options)
      inputStream!!.close()
      inputStream = null
    }
    catch (e: Exception) {
      e.printStackTrace()
    }

    val (height: Int, width: Int) = options.run {
      outHeight to outWidth
    }

    var inSampleSize = 1

    if (height > reqHeight || width > reqWidth) {
      val halfHeight: Int = height / 2
      val halfWidth: Int = width / 2

      while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
        inSampleSize *= 2
      }
    }

    return inSampleSize
  }
}









