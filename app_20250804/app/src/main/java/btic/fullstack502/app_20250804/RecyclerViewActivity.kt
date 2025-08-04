package btic.fullstack502.app_20250804

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import btic.fullstack502.app_20250804.databinding.ActivityRecyclerViewBinding

class RecyclerViewActivity : AppCompatActivity() {

//  RecyclerView 에 출력할 데이터 목록
  private val itemList = mutableListOf<String>()
//  RecyclerView 의 Adapter 클래스 타입의 변수 선언
  private lateinit var adapter: MyRecyclerViewAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    val binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

//    RecyclerView 에 사용할 데이터 목록 생성 (실제 데이터가 없어서 더미 데이터 생성)
    for (i in 1..20) {
      itemList.add("Item $i")
    }

//    MyRecyclerViewAdapter 클래스 타입의 객체 생성, 매개변수로 데이터 목록을 전달
    adapter = MyRecyclerViewAdapter(itemList)

//    현재 엑티비티의 RecyclerView 에 사용하고자하는 layout을 지정함
//    LinearLayoutManager : xml 파일에 layout 을 설정하기 위해 사용하는 LinearLayout 태그를 의미, 소스코드에서 실시간으로 생성하여 사용할 경우 사용하는 클래스
    binding.recyclerView.layoutManager = LinearLayoutManager(this)
//    현재 엑티비티의 RecyclerView 에 MyRecyclerViewAdapter 클래스 타입의 객체를 연동, 생성자의 매개변수로 위에서 생성한 데이터 목록을 전달
    binding.recyclerView.adapter = adapter
//    추가로 RecyclerView 의 모양을 설정 시 사용
    binding.recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

//    버튼 클릭 시 데이터 추가
    binding.btnItemAdd.setOnClickListener {
      val newItem = "Item ${itemList.size +1}"
      adapter.addItem(newItem)
    }
  }
}











