package com.tests.vicuesofttest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tests.vicuesofttest.databinding.FragmentPlayerBinding


class PlayerFragment : Fragment() {
    private var _binding: FragmentPlayerBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayerBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = binding.RecyclerFragmentPlay
        recycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recycler.adapter = RecyclerViewAdapter()

        val videoView = binding.VideoViewFragmentPlay
        val videoPath =
            "https://storage.googleapis.com/assets-stage-bgrem-deelvin-com/bg/videos/nature_3.mp4"

        videoView.setVideoPath(videoPath)
        videoView.start()

        videoView.setOnCompletionListener { videoView.resume() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}