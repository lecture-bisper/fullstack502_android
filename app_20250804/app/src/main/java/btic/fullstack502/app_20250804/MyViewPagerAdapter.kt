package btic.fullstack502.app_20250804

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

//  ViewPager2 는 FragmentStateAdapter 를 상속받아 사용함
//  현재 엑티비티를 매개변수로 받아서 사용
class MyViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

//  스와이프 시 화면을 변경할 프래그먼트가 저장될 리스트를 클래스의 필드로 선언
  var fragmentList = listOf<Fragment>()

//  등록된 프래그먼트의 수 반환
  override fun getItemCount(): Int {
    return fragmentList.size
  }

//  프래그먼트 리스트의 수 만큼 ViewPager2 에서 사용할 프래그먼트를 생성
  override fun createFragment(position: Int): Fragment {
    return fragmentList[position]
  }
}









