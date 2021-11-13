package com.example.sunofbeach.mvvmdemo.player

/**
 * 播放音乐
 * 暂停音乐
 * 上一首
 * 下一首
 * ===================
 * 播放的状态：
 * 通知UI歌曲封面变化
 * 通知UI进度的变化
 * ===================
 * 上一首，下一首：
 * 通知UI改变成播放状态
 * 通知UI标题变化
 * ======================
 * 暂停音乐
 * -更新UI状态为暂停
 */
class PlayerPresenter private constructor(){

    companion object{
        val instance by lazy {
            PlayerPresenter()
        }
    }

    enum class PlayState{
        NONE,PLAYING, PAUSE, LOADING
    }

    private val callbackList = arrayListOf<IPlayerCallback>()

    private var currentPlayState = PlayState.NONE

    fun registerCallback(callback: IPlayerCallback) {
        if(!callbackList.contains(callback)){
            callbackList.add(callback)
        }

    }

    fun unRegisterCallback(callback: IPlayerCallback){
        callbackList.remove(callback)
    }

    /**
     * 根据状态控制音乐，播放还是暂停
     */
    fun doPlayerOrPause() {
        dispatchTitleChange("当前播放的歌曲标题...")
        dispatchCoverChange("当前播放的歌曲封面...")
        if (currentPlayState!=PlayState.PLAYING) {
            //开始播放音乐
            //TODO:
            dispatchPlayingState()
            currentPlayState = PlayState.PLAYING
        } else {
            //暂停音乐
            dispatchPlayerPauseState()
            currentPlayState = PlayState.PAUSE
        }
    }

    private fun dispatchPlayerPauseState() {
        callbackList.forEach {
            it.onPlayerPause()
        }
    }

    private fun dispatchPlayingState() {
        callbackList.forEach {
            it.onPlaying()
        }
    }

    /**
     * 播放下一首歌曲
     */
    fun playNext() {
        //TODO:播放下一首内容
        //1. 拿到下一首歌曲 --> 变更UI，包括标题和封面
        dispatchTitleChange("切换到下一首，标题变化了...")
        dispatchCoverChange("切换到下一首，封面变化了...")
        //2. 设置给播放器
        //3. 等待播放器的回调通知
    }

    private fun dispatchCoverChange(cover: String) {
        callbackList.forEach {
            it.onCoverChange(cover)
        }
    }

    private fun dispatchTitleChange(title: String) {
        callbackList.forEach {
            it.onTitleChange(title)
        }
    }

    /**
     * 播放上一首歌曲
     */
    fun playPre() {
        dispatchTitleChange("切换到上一首，标题变化了...")
        dispatchCoverChange("切换到上一首，封面变化了...")
        currentPlayState = PlayState.PLAYING
    }
}