package com.breezefieldbeena.features.mylearning

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.breezefieldbeena.R
import com.breezefieldbeena.app.types.FragType
import com.breezefieldbeena.base.presentation.BaseFragment
import com.breezefieldbeena.features.dashboard.presentation.DashboardActivity
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSource
import com.pnikosis.materialishprogress.ProgressWheel

class RetryPlayFrag: BaseFragment() {
    private lateinit var mContext: Context
    private lateinit var progress_wheel: ProgressWheel
    private lateinit var style_player_retry: StyledPlayerView
    lateinit var exoPlayer: ExoPlayer
    lateinit var mediaSource: MediaSource

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    companion object{
        var play_url:String=""
        var question_id:Int=0
        var topic_id:Int=0
        var content_id:Int=0
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_retry_play, container, false)
        initView(view)
        requireActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        return view
    }


    private fun initView(view: View) {
        style_player_retry = view.findViewById(R.id.style_player_retry)
        progress_wheel = view.findViewById(R.id.progress_wheel_retry_play)
        progress_wheel.stopSpinning()
        playRetryVid()
    }

    fun playRetryVid(){
        exoPlayer = ExoPlayer.Builder(mContext)
            .setRenderersFactory(
                DefaultRenderersFactory(mContext).setEnableDecoderFallback(
                    true
                )
            ).setSeekForwardIncrementMs(10000L)
            .setSeekBackIncrementMs(10000L).build()

        exoPlayer.seekTo(0)
        exoPlayer.repeatMode = Player.REPEAT_MODE_OFF

        val dataSourceFactory = DefaultDataSource.Factory(mContext)

        mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(MediaItem.fromUri(Uri.parse(play_url)))
        exoPlayer.setMediaSource(mediaSource)
        exoPlayer.prepare()

        exoPlayer.playWhenReady = true
        exoPlayer.play()

        style_player_retry.visibility = View.VISIBLE
        style_player_retry.player = exoPlayer

        exoPlayer.addListener(object : Player.Listener{
            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                super.onPlayerStateChanged(playWhenReady, playbackState)
                when(playbackState){
                    Player.STATE_READY -> {
                        progress_wheel.stopSpinning()
                    }
                    Player.STATE_BUFFERING -> {
                        progress_wheel.spin()
                    }
                    Player.STATE_ENDED -> {
                        progress_wheel.stopSpinning()
                        RetryQuestionFrag.topic_id = topic_id
                        RetryQuestionFrag.content_id = content_id
                        RetryQuestionFrag.question_id = question_id
                        (mContext as DashboardActivity).loadFragment(FragType.RetryQuestionFrag, false, "")
                    }
                }
            }
        })
    }

    override fun onPause() {
        super.onPause()
        println("tag_Retry onPause")
        try {
            exoPlayer.stop()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}