package com.test.axiata.apps.movie.youtube

import android.os.Bundle
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.test.axiata.apps.BuildConfig
import com.test.axiata.apps.R
import kotlinx.android.synthetic.main.activity_youtube_player.*

class YoutubeVideoPlayerActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener{

    private var urlVideo = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube_player)

        urlVideo = intent.getStringExtra("url")

        initViews()
    }

    fun initViews(){
        youtubeView.initialize(BuildConfig.GOOGLEAPIKEY, this)

    }

    override fun onInitializationSuccess(provider: YouTubePlayer.Provider?, player: YouTubePlayer?, wasRestored: Boolean) {
        if(!wasRestored){
            player?.loadVideo(urlVideo)
        }
    }

    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {}

}