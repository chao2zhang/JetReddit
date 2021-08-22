package com.chaozhang.jetreddit.ui.hot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.chaozhang.jetreddit.databinding.FragmentHotBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HotFragment : Fragment() {

    private var binding: FragmentHotBinding? = null
    private lateinit var adapter: HotRedditAdapter
    private val viewModel by viewModels<HotViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = HotRedditAdapter(LayoutInflater.from(context))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentHotBinding.inflate(inflater).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.recyclerView.adapter = adapter
        binding!!.recyclerView.layoutManager = LinearLayoutManager(view.context)
        viewModel.reddits.observe(viewLifecycleOwner) {
            adapter.reddits = it.data.children
            adapter.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}