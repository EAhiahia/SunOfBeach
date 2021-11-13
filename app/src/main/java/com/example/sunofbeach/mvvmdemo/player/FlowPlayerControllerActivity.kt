package com.example.sunofbeach.mvvmdemo.player

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sunofbeach.R
import kotlinx.android.synthetic.main.activity_flow_layout.*

class FlowPlayerControllerActivity: AppCompatActivity(), IPlayerCallback {

    private val playerPresenter by lazy {
        PlayerPresenter.instance
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow_layout)
        playerPresenter.registerCallback(this)
        initListener()
    }

    private fun initListener() {
        playOrPauseBtn.setOnClickListener {
            playerPresenter.doPlayerOrPause()
        }
    }

    override fun onTitleChange(title: String) {

    }

    override fun onProgressChange(current: Int) {

    }

    override fun onPlaying() {
        playOrPauseBtn.text = "暂停"
    }

    override fun onPlayerPause() {
        playOrPauseBtn.text = "播放"
    }

    override fun onCoverChange(cover: String) {

    }

    override fun onDestroy() {
        super.onDestroy()
        playerPresenter.unRegisterCallback(this)
    }
}