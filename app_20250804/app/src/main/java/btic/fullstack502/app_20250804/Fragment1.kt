package btic.fullstack502.app_20250804

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment1.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment1 : Fragment() {
  // TODO: Rename and change types of parameters
  private var param1: String? = null
  private var param2: String? = null


//  기본으로 입력되어 있지 않음
  override fun onAttach(context: Context) {
    super.onAttach(context)

    Log.d("**fullstack502**", "### 프래그먼트 1의 onAttach() 호출###")
  }

//  기본으로 입력되어 있음
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    arguments?.let {
      param1 = it.getString(ARG_PARAM1)
      param2 = it.getString(ARG_PARAM2)
    }

    Log.d("**fullstack502**", "### 프래그먼트 1의 onCreate() 호출###")
  }

//  기본으로 입력되어 있음
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment

    Log.d("**fullstack502**", "### 프래그먼트 1의 onCreateView() 호출###")

    return inflater.inflate(R.layout.fragment_1, container, false)
  }

  //  기본으로 입력되어 있지 않음
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    Log.d("**fullstack502**", "### 프래그먼트 1의 onViewCreated() 호출###")
  }

  override fun onStart() {
    super.onStart()

    Log.d("**fullstack502**", "### 프래그먼트 1의 onStart() 호출###")
  }

  override fun onResume() {
    super.onResume()

    Log.d("**fullstack502**", "### 프래그먼트 1의 onResume() 호출###")
  }

  override fun onPause() {
    super.onPause()

    Log.d("**fullstack502**", "### 프래그먼트 1의 onPause() 호출###")
  }

  override fun onStop() {
    super.onStop()

    Log.d("**fullstack502**", "### 프래그먼트 1의 onStop() 호출###")
  }

  override fun onDestroyView() {
    super.onDestroyView()

    Log.d("**fullstack502**", "### 프래그먼트 1의 onDestroyView() 호출###")
  }

  override fun onDestroy() {
    super.onDestroy()

    Log.d("**fullstack502**", "### 프래그먼트 1의 onDestroy() 호출###")
  }

  override fun onDetach() {
    super.onDetach()

    Log.d("**fullstack502**", "### 프래그먼트 1의 onDetach() 호출###")
  }
  companion object {
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment1.
     */
    // TODO: Rename and change types and number of parameters
    @JvmStatic
    fun newInstance(param1: String, param2: String) =
      Fragment1().apply {
        arguments = Bundle().apply {
          putString(ARG_PARAM1, param1)
          putString(ARG_PARAM2, param2)
        }
      }
  }
}