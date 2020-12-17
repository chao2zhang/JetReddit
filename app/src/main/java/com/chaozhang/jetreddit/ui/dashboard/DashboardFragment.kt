package com.chaozhang.jetreddit.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.chaozhang.jetreddit.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

  private val dashboardViewModel by viewModels<DashboardViewModel>()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
    val textView: TextView = root.findViewById(R.id.text_dashboard)
    dashboardViewModel.text.observe(viewLifecycleOwner) {
      textView.text = it
    }
    return root
  }
}
