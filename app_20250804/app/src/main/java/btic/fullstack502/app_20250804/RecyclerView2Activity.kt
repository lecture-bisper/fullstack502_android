package btic.fullstack502.app_20250804

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import btic.fullstack502.app_20250804.databinding.ActivityRecyclerView2Binding

class RecyclerView2Activity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    val binding = ActivityRecyclerView2Binding.inflate(layoutInflater)
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

    val itemList = dumyItems()

    val kakaoAdapter = KakaoAdapter(itemList)

    binding.recyclerView2.layoutManager = LinearLayoutManager(this)
    binding.recyclerView2.adapter = kakaoAdapter
    binding.recyclerView2.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

  }

  private fun dumyItems(): MutableList<KakaoData> {
    val itemList = mutableListOf<KakaoData>()

    for (i in 1..20) {
      itemList.add(KakaoData("사용자$i", "안녕하세요 $i", "09:${10 + i}"))
    }

    return itemList
  }
}











