package sjniit.sj.wallpaper.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import sjniit.sj.wallpaper.R;

public class FullScreenVideoActivity extends AppCompatActivity {
    String url,image,name,link;


    SimpleExoPlayerView simpleExoPlayerView;
    SimpleExoPlayer player;
    TextView txtTitle,txtDesc;
    ProgressBar lottieVideo;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuu_screen_video);
//Hide status bar
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);



        simpleExoPlayerView = findViewById(R.id.videoView);
        txtTitle = findViewById(R.id.txtTitle);
        txtDesc = findViewById(R.id.txtDesc);
        lottieVideo=findViewById(R.id.lottieVideo);


        Intent intent = getIntent();

        url=intent.getStringExtra("url");
        name=intent.getStringExtra("name");
        image=intent.getStringExtra("image");

        txtDesc.setText(url);
        txtTitle.setText(name);

        Log.i("Shivam", "onCreate: "+name);



        setVideoData();





    }

    void setVideoData( ){
        txtTitle.setText(name);

        try {

            // bandwisthmeter is used for
            // getting default bandwidth
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();

            // track selector is used to navigate between
            // video using a default seekbar.
            TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));

            // we are adding our track selector to exoplayer.
            player = ExoPlayerFactory.newSimpleInstance(this, trackSelector);

            // we are parsing a video url
            // and parsing its video uri.
            Uri videouri = Uri.parse(url);

            // we are creating a variable for datasource factory
            // and setting its user agent as 'exoplayer_view'
            DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_video");

            // we are creating a variable for extractor factory
            // and setting it to default extractor factory.
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

            // we are creating a media source with above variables
            // and passing our event handler as null,
            MediaSource mediaSource = new ExtractorMediaSource(videouri, dataSourceFactory, extractorsFactory, null, null);

            // inside our exoplayer view
            // we are setting our player

            // we are preparing our exoplayer
            // with media source.
            simpleExoPlayerView.setPlayer(player);

            // we are preparing our exoplayer
            // with media source.
            player.prepare(mediaSource);

            // we are setting our exoplayer
            // when it is ready.
            player.setPlayWhenReady(true);
            player.setRepeatMode(Player.REPEAT_MODE_ONE);

            // we are setting our exoplayer
            // when it is ready;
            player.addListener(new Player.EventListener() {
                @Override
                public void onLoadingChanged(boolean isLoading) {

                }

                @Override
                public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                    switch (playbackState) {
                        case Player.STATE_READY:
                            //lottieVideo.setVisibility(View.GONE);
                            player.setPlayWhenReady(true);
                            break;
                        case Player.STATE_BUFFERING:
                           //lottieVideo.setVisibility(View.VISIBLE);
                            player.seekTo(0);
                            break;
                        case Player.STATE_IDLE:
                            player.retry();
                            break;
                    }
                }
            });

        } catch (Exception e) {
            // below line is used for
            // handling our errors.
            //Log.e("TAG", "Error : " + e.toString());
        }
    }




    public void downloadVideo(View view) {
        DownloadManager downloadManager= (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
        //pass image url
        Uri uri = Uri.parse(url);
        DownloadManager.Request request =new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        //enqueue mai aap ki basically yha per a jaye gi jo request aap ne create kiya hai object pass it
        downloadManager.enqueue(request);
        Toast.makeText(this, "Downloading Start", Toast.LENGTH_SHORT).show();

    }
}
