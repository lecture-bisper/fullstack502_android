package btic.fullstack502.app_20250806

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import btic.fullstack502.app_20250806.databinding.ActivityDatabaseBinding
import btic.fullstack502.app_20250806.helper.DBHelper
import btic.fullstack502.app_20250806.helper.PhoneBookData

class DatabaseActivity : AppCompatActivity() {

  private val binding by lazy { ActivityDatabaseBinding.inflate(layoutInflater) }
//  SQLiteOpenHelper 클래스를 상속받은 DBHelper 클래스의 객체 생성, 지연 초기화 방식 사용
  private val dbHelper by lazy { DBHelper(this) }
//  실제 데이터베이스 제어를 위한 객체 생성, 지연 초기화 방식 사용
//  readableDatebase : 읽기 전용 모드로 접속
//  writableDatabase : 읽기, 쓰기 모드로 접속
  private val database by lazy { dbHelper.writableDatabase }

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
    binding.btnInsert.setOnClickListener {
//      EditText 태그에 입력한 내용을 가져오기 
      val name: String = binding.etName.text.toString()
      val phone: String = binding.etPhone.text.toString()
      val email: String = binding.etEmail.text.toString()
      val addr: String = binding.etAddr.text.toString()

//      SQLite 로 전달할 PhoneBookData 클래스 타입의 객체 생성
      val data = PhoneBookData(name = name, phone = phone, email = email, addr = addr)

      dbHelper.dbInsert(database, data)
      clearEditViewText()
    }

    binding.btnUpdate.setOnClickListener {
      val seq: Int = binding.etSeq.text.toString().toInt()
      val name: String = binding.etName.text.toString()
      val phone: String = binding.etPhone.text.toString()
      val email: String = binding.etEmail.text.toString()
      val addr: String = binding.etAddr.text.toString()

      val data = PhoneBookData(seq = seq, name = name, phone = phone, email = email, addr = addr)

      dbHelper.dbUpdate(database, data)
      clearEditViewText()
    }

    binding.btnDelete.setOnClickListener {
      val seq = binding.etSeq.text.toString().toInt()
      dbHelper.dbDelete(database, seq)
      clearEditViewText()
    }

    binding.btnSelectSeq.setOnClickListener {
      val seq = binding.etSeq.text.toString().toInt()
      val result = dbHelper.dbSelectSeq(database, seq)
      binding.etResult.setText(result)
      clearEditViewText()
    }

    binding.btnSelectName.setOnClickListener {
      val name = binding.etName.text.toString()
      val result = dbHelper.dbSelectName(database, name)
      binding.etResult.setText(result)
      clearEditViewText()
    }

    binding.btnSelectAll.setOnClickListener {
      val result = dbHelper.dbSelectAll(database)
      binding.etResult.setText(result)
      clearEditViewText()
    }
  }

  private fun clearEditViewText() {
    binding.etSeq.setText("")
    binding.etName.setText("")
    binding.etPhone.setText("")
    binding.etEmail.setText("")
    binding.etAddr.setText("")
  }
}





