package btic.fullstack502.app_20250806

import android.content.ContentUris
import android.content.ContentValues
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import btic.fullstack502.app_20250806.databinding.ActivityFileBinding
import java.io.File
import kotlin.apply

class FileActivity : AppCompatActivity() {

  private val binding by lazy { ActivityFileBinding.inflate(layoutInflater) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

    initEvent()
  }

  private fun initEvent() {
//    내부 저장소에 저장하는 파일은 특별한 권한없이 바로 접근이 가능함
//    내부 저장소는 외부에서 특정 앱의 내부 저장소에 접근하는 것이 불가능
//    내부 저장소는 용량이 작음
    
    
    binding.btnSystemInFileWrite.setOnClickListener {
//      File() : Java.io 에서 제공하는 파일 관련 클래스
//      filesDir : 현재 앱의 내부 저장소 경로
//      첫번째 매개변수 : 파일이 위치할 경로
//      두번재 매개변수 : 실제 파일명
      val file = File(filesDir, "system_in_test.txt")
//      파일을 쓰기 위한 스트림 생성
      val writeStream = file.writer()
//      write() 를 사용하여 파일에 내용 쓰기
      writeStream.write("hello world!!\n")
      writeStream.write("안녕하세요\n")
      writeStream.write("내부 저장소에 파일 저장!!\n")
//      파일에 내용 쓰기 완료, 메모리 버퍼를 비운 후 파일 닫기
      writeStream.flush()
      writeStream.close()
      Toast.makeText(this, "내부 저장소에 파일 저장 완료!!", Toast.LENGTH_SHORT).show()
    }

    binding.btnSystemInFileRead.setOnClickListener {
//      지정한 경로 및 지정한 파일명을 열기
      val file = File(filesDir, "system_in_test.txt")
//      BufferedReader 스트림 생성
      val readStream = file.reader().buffered()
//      파일 안의 내용을 한줄씩 읽어옴
      readStream.forEachLine {
        Log.d("**fullstack502**", "### 파일 내용 : $it ###")
      }
      readStream.close()
      Toast.makeText(this, "내부 저장소에서 파일 읽기 완료!!", Toast.LENGTH_SHORT).show()
    }


//    앱별 외부 저장소에 파일 쓰기
    binding.btnSystemOutFileWrite.setOnClickListener {
//      앱 전용의 외부 저장소의 경우 권한이 필요없도록 변경됨
//      매니페스트 파일에 외부 저장소 사용 권한만 등록 후 사용
      
//      getExternalFilesDir : 현재 앱의 외부 저장소 경로
//      매개변수 : 외부 저장소에 존재하는 파일 종류를 설정,
//          Environment.DIRECTORY_PICTURES,Environment.DIRECTORY_DOCUMENTS, Environment.DIRECTORY_MUSIC, Environment.DIRECTORY_MOVIES
//      앱별 외부 저장소 : /storage/emulated/0/Android/data/패키지명/files
      val file = File(getExternalFilesDir(null), "system_out_test.txt")
      val writeStream = file.writer()
      writeStream.write("앱별 외부 저장소에 파일 쓰기!!\n")
      writeStream.write("두번째 줄~~~\n")
      writeStream.flush()
      writeStream.close()
      Toast.makeText(this, "앱별 외부 저장소에 파일 쓰기 완료!!", Toast.LENGTH_SHORT).show()
    }

//    앱별 외부 저장소에서 파일 읽기
    binding.btnSystemOutFileRead.setOnClickListener {
      val file = File(getExternalFilesDir(null), "system_out_test.txt")
      val readStream = file.reader().buffered()
      readStream.forEachLine {
        Log.d("**fullstack502**", "### 앱별 외부 저장소 파일 내용 : $it ###")
      }
      Toast.makeText(this, "앱별 외부 저장소에서 파일 읽기 완료!!", Toast.LENGTH_SHORT).show()
    }


//  공용 Download 폴더에 파일 쓰기
    binding.btnDownloadFileWrite.setOnClickListener {
//      파일 쓰기를 위한 메타 데이터 생성
//      공용 폴더에 접근하기 위해서는 반드시 MediaStore 클래스를 사용해야 함
//      DISPLAY_NAME : 파일 이름
//      MIME_TYPE : 파일 타입, 확장자
//      RELATIVE_PATH : 공용 폴더 경로
      val contentValue = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, "download_test.txt")
        put(MediaStore.MediaColumns.MIME_TYPE, "text/plain")
        put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
      }

//      contentResolver : 안드로이드 시스템에서 현재 앱이 다른 앱이나 시스템의 데이터를 접근할 수 있도록 하는 API
//      공용 폴더에 데이터를 읽기/쓰기 시 contentResolver 를 사용해야 함

//      ContentValues() 를 사용하여 생성한 메타 데이터로 파일을 쓰기 위한 정보를 설정
      val uri = contentResolver.insert(MediaStore.Files.getContentUri("external"), contentValue)

//      uri 정보가 있는지 확인 후 있을 경우에만 실행
      uri?.let { uri ->
//        contentResolver 를 사용하여 파일을 쓰기 위한 OutputStream 을 생성 
        contentResolver.openOutputStream(uri)?.use { outputStream ->
//          write() 를 사용하여 파일에 데이터 쓰기
          outputStream.write("공용 저장소 Downloads 폴더에 데이터 저장\n".toByteArray())
          outputStream.write("공용 저장소 Downloads 폴더에 두번째 줄 저장".toByteArray())
        }
      }
    }

    binding.btnDownloadFileRead.setOnClickListener {
//      읽어올 파일의 경로 설정
      val collection = MediaStore.Files.getContentUri("external")
      val projection = arrayOf(MediaStore.Files.FileColumns._ID)
//      읽어올 파일명 설정
      val selection = "${MediaStore.Files.FileColumns.DISPLAY_NAME} = ?"
      val selectionArgs = arrayOf("download_test.txt")

//      위에서 설정한 메타 데이터를 기반으로 contentResolver 를 사용하여 파일 정보를 가져옴
      contentResolver.query(collection, projection, selection, selectionArgs, null)?.use { cursor ->
//        가져온 데이터가 있는지 확인 후 출력
        if (cursor.moveToNext()) {
          val id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID))
//          가져올 파일 uri 를 설정
          val uri = ContentUris.withAppendedId(collection, id)

//          contentResolver 를 사용하여 파일을 읽을 InputStream 을 생성
          contentResolver.openInputStream(uri)?.use { inputStream ->
//            가져온 InputStream 으로 파일 내용을 읽어옴
            val fileContent = inputStream.bufferedReader().use { it.readText() }
            Log.d("**fullstack502**", "### 공용폴더 Downloads의 파일 내용 : $fileContent ###")
          }
        }
      }
    }
  }
}









