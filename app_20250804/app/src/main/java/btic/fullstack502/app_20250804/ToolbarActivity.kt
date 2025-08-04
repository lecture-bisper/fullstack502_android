package btic.fullstack502.app_20250804

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
// xml 에서 actionViewClass를 androidx.appcompat.widget.SearchView 로 설정했으므로 코틀린 파일에서도 androidx.appcompat.widget.SearchView 를 임포트해야 함
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import btic.fullstack502.app_20250804.databinding.ActivityToolbarBinding

class ToolbarActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    val binding = ActivityToolbarBinding.inflate(layoutInflater)
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

    setSupportActionBar(binding.toolbar)
    supportActionBar?.title = getString(R.string.app_name)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)

  }

  override fun onSupportNavigateUp(): Boolean {
    super.onSupportNavigateUp()

    Log.d("**fullstack502**", "툴바의 뒤로가기 버튼 클릭!!")
    Toast.makeText(this, "툴바의 뒤로가기 버튼 클릭!!", Toast.LENGTH_SHORT).show()
    onBackPressedDispatcher.onBackPressed()

    return true
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//    소스코드로 직접 메뉴를 추가
//    val menuItem1: MenuItem? = menu?.add(0, 0, 0, "메뉴1")
//    val menuItem2: MenuItem? = menu?.add(0, 1, 0, "메뉴2")

//    xml 파일로 생성한 메뉴를 현재 엑티비티에 추가
    menuInflater.inflate(R.menu.menu_main, menu)

//    findItem(메뉴 리소스 id) : 메뉴 xml 파일의 태그 중 지정한 id를 가지고 있는 태그를 검색하여 가져옴
    val menuItem1 = menu?.findItem(R.id.menu1)
//    가져온 메뉴를 사용하여 이벤트 설정
    menuItem1?.setOnMenuItemClickListener {
      Log.d("**fullstack502**", "xml 파일로 생성한 메뉴 1 클릭!!")
      Toast.makeText(this, "xml 파일로 생성한 메뉴 1 클릭!!", Toast.LENGTH_SHORT).show()
      true
    }

    val menuItem2 = menu?.findItem(R.id.menu2)
    menuItem2?.setOnMenuItemClickListener {
      Log.d("**fullstack502**", "xml 파일로 생성한 메뉴 2 클릭!!")
      Toast.makeText(this, "xml 파일로 생성한 메뉴 2 클릭!!", Toast.LENGTH_SHORT).show()
      true
    }

//    SearchView 사용하기
    val menuItem3 = menu?.findItem(R.id.menu3)
//    검색된 메뉴 버튼에서 SearchView 객체 가져오기
    val searchView = menuItem3?.actionView as SearchView
//    이벤트 등록
    searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
//      입력된 텍스트의 변경이 발생 시 동작하는 이벤트, 현재까지 입력된 텍스트를 매개변수로 받아옴
      override fun onQueryTextChange(newText: String?): Boolean {
        Log.d("**fullstack502**", "입력 중인 내용 : $newText")
        return true
      }
//      텍스트 입력 후 확인 버튼을 클릭했을 경우 동작하는 이벤트, 입력된 모든 텍스트를 매개변수로 받아옴
      override fun onQueryTextSubmit(query: String?): Boolean {
        Log.d("**fullstack502**", "입력한 내용 : $query")
        Toast.makeText(this@ToolbarActivity, "입력한 내용 $query", Toast.LENGTH_SHORT).show()
        return true
      }
    })
    
    return super.onCreateOptionsMenu(menu)
  }

//  소스코드로 직접 메뉴를 추가 시 발생하는 이벤트를 처리하는 메소드
  override fun onOptionsItemSelected(item: MenuItem): Boolean {

    when (item.itemId) {
      0 -> {
        Log.d("**fullstack502**", "첫번째 메뉴 클릭!!")
        Toast.makeText(this, "첫번째 메뉴 클릭!!", Toast.LENGTH_SHORT).show()
        return true
      }
      1 -> {
        Log.d("**fullstack502**", "두번째 메뉴 클릭!!")
        Toast.makeText(this, "두번째 메뉴 클릭!!", Toast.LENGTH_SHORT).show()
        return true
      }
      else -> return super.onOptionsItemSelected(item)
    }
  }
}











