package btic.fullstack502.app_20250804

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import btic.fullstack502.app_20250804.databinding.ItemKakaoBinding

class KakaoAdapter(val dataList: MutableList<KakaoData>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): RecyclerView.ViewHolder {
    return KakaoHolder(ItemKakaoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
  }

  override fun getItemCount(): Int {
    return dataList.size
  }

  override fun onBindViewHolder(
    holder: RecyclerView.ViewHolder,
    position: Int
  ) {
    val binding = (holder as KakaoHolder).binding

    binding.tvName.text = dataList[position].name
    binding.tvContent.text = dataList[position].content
    binding.tvTime.text = dataList[position].time
  }
}









