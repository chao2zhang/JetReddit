package com.chaozhang.jetreddit.ui.notifications

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
class NotificationsFragment : Fragment() {

  private val notificationsViewModel by viewModels<NotificationsViewModel>()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val root = inflater.inflate(R.layout.fragment_notifications, container, false)
    val textView: TextView = root.findViewById(R.id.text_notifications)
    notificationsViewModel.text.observe(viewLifecycleOwner) {
      textView.text = it
    }
    return root
  }
}
