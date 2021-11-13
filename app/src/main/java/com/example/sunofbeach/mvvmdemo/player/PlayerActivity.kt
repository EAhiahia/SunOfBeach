package com.example.sunofbeach.mvvmdemo.player

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sunofbeach.R
import kotlinx.android.synthetic.main.activity_player.*

class PlayerActivity: AppCompatActivity(), IPlayerCallback {

    private val playerPresenter by lazy {
        PlayerPresenter.instance
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        playerPresenter.registerCallback(this)
        initListener()
    }

    /**
     * 给空间设置点击事件
     */
    private fun initListener() {
        playerOrPauseBtn.setOnClickListener {
            //调用presenter层的播放或者暂停方法
            playerPresenter.doPlayerOrPause()
        }
        playNextBtn.setOnClickListener {
            playerPresenter.playNext()
        }
        playPreBtn.setOnClickListener {
            playerPresenter.playPre()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
            playerPresenter?.unRegisterCallback(this)
    }

    override fun onTitleChange(title: String) {
        songTitle?.text = title
    }

    override fun onProgressChange(current: Int) {

    }

    override fun onPlaying() {
        //播放中 --> 显示暂停
        playerOrPauseBtn?.text = "暂停"
    }

    override fun onPlayerPause() {
        //暂停中 --> 显示播放
        playerOrPauseBtn?.text = "播放"
    }

    override fun onCoverChange(cover: String) {
        println("封面改变了...$cover")
    }
}