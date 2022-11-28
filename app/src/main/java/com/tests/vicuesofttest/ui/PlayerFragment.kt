package com.tests.vicuesofttest.ui

import android.content.ClipData
import android.content.ClipDescription
import android.net.Uri
import android.os.Bundle
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.View
import android.view.View.DragShadowBuilder
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

        videoView.setVideoURI(Uri.parse(videoPath))
        videoView.start()
        videoView.setOnCompletionListener { videoView.resume() }

        val editText = binding.editThisTextFieldFragmentPlay
        binding.BtnFragmentPlayAddText.setOnClickListener {
            if (editText.visibility == View.INVISIBLE)
                editText.visibility = View.VISIBLE
        }

        editText.setOnLongClickListener {
            val clipText: String = editText.text.toString()
            val clipItem = ClipData.Item(clipText)
            val mimeType = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(clipText, mimeType, clipItem)

            val dragShadowBuilder = DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)

            it.visibility = View.INVISIBLE
            true
        }

        val dragListener = View.OnDragListener { dragView, dragEvent ->
            when (dragEvent.action) {
                DragEvent.ACTION_DRAG_STARTED -> {
                    dragEvent.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)
                }
                DragEvent.ACTION_DRAG_ENTERED -> {
                    dragView.invalidate()
                    true
                }
                DragEvent.ACTION_DRAG_LOCATION -> {
                    true
                }
                DragEvent.ACTION_DRAG_EXITED -> {
                    dragView.invalidate()
                    true
                }
                DragEvent.ACTION_DROP -> {
                    val newView = dragEvent.localState as View
                    newView.translationX = dragEvent.x
                    newView.translationY = dragEvent.y
                    newView.visibility = View.VISIBLE
                    true
                }

                DragEvent.ACTION_DRAG_ENDED -> {
                    dragView.invalidate()
                    true
                }
                else -> false
            }
        }
        binding.VideoViewContainerFragmentPlay.setOnDragListener(dragListener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}