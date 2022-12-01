package com.tests.vicuesofttest.ui

import android.content.ClipData
import android.content.ClipDescription
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.View
import android.view.View.DragShadowBuilder
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.tests.vicuesofttest.databinding.FragmentPlayerBinding
import com.tests.vicuesofttest.domain.DataResponse
import com.tests.vicuesofttest.domain.VideoAndPosterService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val BASE_URL = "https://dev.bgrem.deelvin.com/api/"

class PlayerFragment : Fragment(), OnPosterClickListener {
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

        val videoAndPoster: VideoAndPosterService = retrofitInit()

        videoAndPoster.getAll().enqueue(object :
            Callback<List<DataResponse>> {
            override fun onResponse(
                call: Call<List<DataResponse>>,
                response: Response<List<DataResponse>>
            ) {
                if (!response.isSuccessful) {
                    Toast.makeText(requireContext(), "responce error", Toast.LENGTH_SHORT).show()
                    return
                }
                val bodyResponse = response.body()!!
                videoPlayerInit(bodyResponse[0].fileUrl)
                recyclerInit(bodyResponse)
            }

            override fun onFailure(call: Call<List<DataResponse>>, t: Throwable) {
                Log.i("network", "onFailure: ${t.message}")
                Toast.makeText(requireContext(), "network error", Toast.LENGTH_SHORT).show()
            }
        })

        textDrugAndDrop()

    }

    private fun textDrugAndDrop() {
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

    private fun videoPlayerInit(videoPath: String) {
        val videoView = binding.VideoViewFragmentPlay

        videoView.setVideoURI(Uri.parse(videoPath))
        videoView.start()
        videoView.setOnCompletionListener { videoView.resume() }
    }

    private fun recyclerInit(dataResponse: List<DataResponse>) {
        val recycler = binding.RecyclerFragmentPlay
        recycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recycler.adapter = RecyclerViewAdapter(dataResponse, this)
    }

    private fun retrofitInit(): VideoAndPosterService {
        val moshi: Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        return retrofit.create(VideoAndPosterService::class.java)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onPosterClick(videoPath: String) {
        videoPlayerInit(videoPath)
    }
}