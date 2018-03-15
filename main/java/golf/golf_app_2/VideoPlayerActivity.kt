package golf.golf_app_2

/**
 * Created by Ryan Saylock on 3/11/2018.
 */
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.widget.VideoView


import kotlinx.android.synthetic.main.content_main.*


class VideoPlayerActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main)

        configureVideoView()
    }

    private fun configureVideoView() {
        val videoView1 = findViewById<VideoView>(R.id.videoView1) as VideoView
        val path = "android.resource://" + getPackageName() + "/" + R.raw.swing1        //the path for the video, in project tree under res directory create a raw directory and place video here
        videoView1.setVideoURI(Uri.parse(path))                                         //setting the video uri


        val button = findViewById<Button>(R.id.button1)
        button.setOnClickListener({
            val isPlaying = videoView1.isPlaying
            button.setText(if (isPlaying) R.string.play else R.string.pause)

            val msg = getString(if (isPlaying) R.string.paused else R.string.playing)
            Toast.makeText(this@VideoPlayerActivity, msg, Toast.LENGTH_SHORT).show()
            if(isPlaying){
                videoView1.pause()
            } else {
                videoView1.start()
            }
        })
        //videoView1.setVideoPath("c:\\Users\\Ryan Saylock\\Downloads\\3D\\swing1.mp4")


    }
}


