package btic.fullstack502.app_20250804

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import btic.fullstack502.app_20250804.databinding.ActivityFragmentBinding

class FragmentActivity : AppCompatActivity() {

  private var count: Int = 0

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    val binding = ActivityFragmentBinding.inflate(layoutInflater)
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

//    프래그먼트를 제어하기 위한 프래그먼트 매니저 가져오기
    val fragmentManager: FragmentManager = supportFragmentManager
//    SQL의 트랜젝션과 비슷한 기능을 하는 FragmentTransaction 객체를 FragmentManager 를 사용하여 생성
//    commit() 을 호출 시 지정된 프래그먼트를 화면에 출력함 (현재는 빈화면)
    var fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

//    사용자가 만들어 놓은 프래그먼트 객채를 생성
    val fragment1 = Fragment1()
    val fragment2 = Fragment2()

//    add() : FragmentTransaction 에 프래그먼트를 추가
//    replace() : 이미 등록된 프래그먼트를 다른 프래그먼트로 교체
//    remove() : 이미 등록된 프래그먼트를 삭제
//    프래그먼트 트랜젝션에 프래그먼트 객체 추가
    fragmentTransaction.add(R.id.layout_fragment_base, fragment1)
//    commit() : FragmentTransaction 에 등록된 프래그먼트를 화면에 출력
    fragmentTransaction.commit()

//    버튼 이벤트
    binding.btnFrag1.setOnClickListener {
      count++
//      프래그먼트 변경 시 트랜젝션을 새로 지정
      fragmentTransaction = fragmentManager.beginTransaction()
//      등록된 프래그먼트를 지정한 위치에 지정한 프래그먼트로 교체
      fragmentTransaction.replace(R.id.layout_fragment_base, fragment1)
//      트랜젝션과 관련된 화면 전환 및 상태 변경 최적화
      fragmentTransaction.setReorderingAllowed(true)
//      프래그먼트를 사용하여 여러번 화면을 변경했을 경우 변경 내역을 저장하고 있다가 뒤로가기 버튼 클릭 시 저장된 프래그먼트 화면을 출력
//      addToBackStack() 를 사용하지 않으면 저장된 프래그먼트가 없기 때문에 화면 변경 시 기존 프래그먼트가 삭제 됨
      fragmentTransaction.addToBackStack(null)
//      지정된 프래그먼트로 화면 출력
      fragmentTransaction.commit()
    }

    binding.btnFrag2.setOnClickListener {
      count++
      fragmentTransaction = fragmentManager.beginTransaction()
      fragmentTransaction.replace(R.id.layout_fragment_base, fragment2)
      fragmentTransaction.setReorderingAllowed(true)
      fragmentTransaction.addToBackStack(null)
      fragmentTransaction.commit()
    }
  }
}










