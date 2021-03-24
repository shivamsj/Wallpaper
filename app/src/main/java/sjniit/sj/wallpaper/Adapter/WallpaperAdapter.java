package sjniit.sj.wallpaper.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import sjniit.sj.wallpaper.Activity.FullScreenWallpaperActivity;
import sjniit.sj.wallpaper.Fragment.HomeFragment;
import sjniit.sj.wallpaper.Model.WallpaperModel;
import sjniit.sj.wallpaper.R;

public class WallpaperAdapter extends  RecyclerView.Adapter<WallpaperViewHolder>{
    List<WallpaperModel>wallpaperModelList;
    private Context context;

    public WallpaperAdapter(Context context, List<WallpaperModel> wallpaperModelList) {
        this.context = context;
        this.wallpaperModelList = wallpaperModelList;
    }
    @NonNull
    @Override
    public WallpaperViewHolder onCreateViewHolder
            (@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_item_container,parent,false);
        return  new WallpaperViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull WallpaperViewHolder holder, int position) {

        Glide.with(context).load(wallpaperModelList.get(position).getMediumUrl())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                       holder. lottieAnimationView.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.lottieAnimationView.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(holder.imageView);

                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(new Intent(context, FullScreenWallpaperActivity.class)
                                .putExtra("originalUrl",wallpaperModelList.get(position).getOriginalUrl())

                                .putExtra("photographerName",wallpaperModelList.get(position).getPhotographerName())
                                .putExtra("photographerUrl",wallpaperModelList.get(position).getPhotographer_url())

                        );
                    }
                });



    }
    @Override
    public int getItemCount() {
        return wallpaperModelList.size();
    }
}
class WallpaperViewHolder extends RecyclerView.ViewHolder{


    ImageView imageView;
    LottieAnimationView lottieAnimationView;


    public WallpaperViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView=itemView.findViewById(R.id.imageViewItem2);
        lottieAnimationView=itemView.findViewById(R.id.lottie);


    }
}
