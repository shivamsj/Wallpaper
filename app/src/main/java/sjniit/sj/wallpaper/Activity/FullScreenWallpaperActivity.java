package sjniit.sj.wallpaper.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.github.chrisbanes.photoview.PhotoView;

import java.io.IOException;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;
import sjniit.sj.wallpaper.R;

public class FullScreenWallpaperActivity extends AppCompatActivity {

    String originalUrl= "";
    PhotoView photoView ;
   // String smallUrl="";
    CircleImageView photoImg;
    LottieAnimationView lottieAnimationView;

    TextView photographer_name,photographer_url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_wallpaper);
//         //Hide status bar
       getWindow().setFlags(
               WindowManager.LayoutParams.FLAG_FULLSCREEN,
               WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent intent = getIntent();
        lottieAnimationView=findViewById(R.id.lottie_anim);

        originalUrl=intent.getStringExtra("originalUrl");
        photoView= findViewById(R.id.photoView);
        Glide.with(this).load(originalUrl)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        lottieAnimationView.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        lottieAnimationView.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(photoView);


        //originalUrl=intent.getStringExtra("originalUrl");
        photoImg=findViewById(R.id.photographer_img);
        Glide.with(this).load(originalUrl).into(photoImg);


        String name= intent.getStringExtra("photographerName");
        photographer_name=findViewById(R.id.photographer_name);
        Log.i("TAG", "URL"+name);
        photographer_name.setText(name);

        String url =intent.getStringExtra("photographerUrl");
        photographer_url=findViewById(R.id.photographer_url);
        photographer_url.setText(url);



    }

    public void setWallpaperEvent(View view) {
        //set image on mobile
        WallpaperManager wallpaperManager=WallpaperManager.getInstance(this);

        Bitmap bitmap =((BitmapDrawable)photoView.getDrawable()).getBitmap();
        try {
            wallpaperManager.setBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void downloadWallpapaerEvent(View view) {

        DownloadManager downloadManager= (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
        //pass image url
        Uri uri = Uri.parse(originalUrl);
        DownloadManager.Request request =new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        //enqueue mai aap ki basically yha per a jaye gi jo request aap ne create kiya hai object pass it
        downloadManager.enqueue(request);
        Toast.makeText(this, "Downloading Start", Toast.LENGTH_SHORT).show();

    }
}