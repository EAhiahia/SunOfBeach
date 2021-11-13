package com.example.sunofbeach.mvvmdemo.player

interface IPlayerCallback {
    fun onTitleChange(title: String)
    fun onProgressChange(current: Int)
    fun onPlaying()
    fun onPlayerPause()
    fun onCoverChange(cover: String)
}