package btic.fullstack502.app_20250804

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import btic.fullstack502.app_20250804.databinding.ItemRecyclerViewBinding

//  Recycler View의 요소가 될 UI 파일을 파싱한 ViewHolder 를 여러개 가지는 Adapter 클래스
//  매개변수로 Recycler View 에 표시할 데이터를 받아옴
class MyRecyclerViewAdapter(val dataList: MutableList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

//  ViewHolder 가 생성될 때 실행됨, 전체 UI를 생성함
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    return MyRecyclerViewHolder(ItemRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
  }

//  매개변수로 받아온 데이터의 수 반환, 매개변수의 크기에 따라 ViewHolder가 생성됨
  override fun getItemCount(): Int {
    return dataList.size
  }

//  생성된 Recycler View 의 ViewHolder 에 데이터를 연동, 매개변수로 받은 dataList의 크기만큼 반복 실행됨
//  첫번째 매개변수 : RecyclerView.ViewHolder 타입의 객체를 가져옴, RecyclerView.ViewHolder 의 객체 안에 들어있는 것은 자식 클래스 타입의 객체인 MyRecyclerViewHolder 임
//  두번째 매개변수 : MyRecyclerViewAdapter 클래스 타입의 객체를 생성 시 매개변수로 받아온 데이터의 index 번호
  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    Log.d("**fullstack502**", "onBindViewHolder : $position")
  
//  자식 클래스 타입의 객체로 강제 타입 변환, 자식 클래스 타입의 객체가 가지고 있는 태그 정보를 변수에 저장
    val binding = (holder as MyRecyclerViewHolder).binding

//  itemData : 강제 타입 변환된 객체가 item_recycler_view.xml 파일에 있는 태그의 id 임
//  xml 파일의 태그를 선택하여 필요한 데이터를 화면에 출력
    binding.itemData.text = dataList[position]

//  지정한 태그에 클릭 이벤트 설정
    binding.itemData.setOnClickListener {
      Log.d("**fullstack502**", "item data click : $position")
    }
  }

//  기존 데이터에 새로운 데이터를 추가하는 메소드
  public fun addItem(data: String) {
//    매개변수로 받은 dataList에 데이터 추가
    dataList.add(data)
//    추가된 데이터를 적용
    notifyItemInserted(dataList.size - 1)
  }
}









