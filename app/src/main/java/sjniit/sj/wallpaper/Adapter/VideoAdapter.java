package sjniit.sj.wallpaper.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.exoplayer2.ExoPlayerFactory;
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


import java.util.List;

import sjniit.sj.wallpaper.Activity.FullScreenVideoActivity;
import sjniit.sj.wallpaper.Activity.FullScreenWallpaperActivity;
import sjniit.sj.wallpaper.Model.VideoModel;
import sjniit.sj.wallpaper.R;

public class VideoAdapter extends RecyclerView.Adapter<VideoViewHolder> {

    private Context context;
    private List<VideoModel> videoModelList;

    public VideoAdapter(Context context, List<VideoModel> videoModelList) {
        this.context = context;
        this.videoModelList = videoModelList;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.post_video_container, parent, false);
        return new VideoViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        //how to load

        //Log.i("Shivam", "link: " + (videoModelList.get(position).getVideoImage()));
        Glide.with(context).load(videoModelList.get(position).getVideoImage())

                .listener(new RequestListener<Drawable>() {

                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.lottieAnimationViewVideo.setVisibility(View.GONE);

                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.lottieAnimationViewVideo.setVisibility(View.GONE);
                        return false;
                    }


                })


                .into(holder.imageViewVideo);
        holder.videoDuration.setText(videoModelList.get(position).getVideoDuration());











        holder.imageViewVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, FullScreenVideoActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .putExtra("url", videoModelList.get(position).getDownloadLink())

                        .putExtra("name", videoModelList.get(position).getUserName())
                        .putExtra("image", videoModelList.get(position).getVideoImage())

                );
            }
        });


    }

    @Override
    public int getItemCount() {
        return videoModelList.size();
    }
}

class VideoViewHolder extends RecyclerView.ViewHolder {

    ImageView imageViewVideo;
    LottieAnimationView lottieAnimationViewVideo;
    TextView videoDuration;

    public VideoViewHolder(@NonNull View itemView) {
        super(itemView);

        imageViewVideo = itemView.findViewById(R.id.imageViewVideo);
        lottieAnimationViewVideo = itemView.findViewById(R.id.lottieVideo);
        videoDuration=itemView.findViewById(R.id.videduration);
    }

}

