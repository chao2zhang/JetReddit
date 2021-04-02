package com.chaozhang.jetreddit.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.chaozhang.jetreddit.Constants
import com.chaozhang.jetreddit2.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

  private val homeViewModel by viewModels<HomeViewModel>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Log.i(Constants.FLOW_TAG, "Fragment onCreate...")
  }

  override fun onStart() {
    super.onStart()
    Log.i(Constants.FLOW_TAG, "Fragment onStart...")
  }

  override fun onResume() {
    super.onResume()
    Log.i(Constants.FLOW_TAG, "Fragment onResume...")
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    Log.i(Constants.FLOW_TAG, "Fragment onCreateView...")
    homeViewModel.reddits.observe(viewLifecycleOwner, {
      Log.i(Constants.FLOW_TAG, "Observer called...")
      Toast.makeText(context, "Recevied data", Toast.LENGTH_SHORT).show()
    })
    val view = inflater.inflate(R.layout.fragment_home, container, false)
    Thread.sleep(500) // Simulate complex view hierarchy
    return view
  }
}
