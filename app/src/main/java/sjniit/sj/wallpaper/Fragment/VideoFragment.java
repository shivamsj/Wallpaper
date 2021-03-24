package sjniit.sj.wallpaper.Fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sjniit.sj.wallpaper.Adapter.VideoAdapter;
import sjniit.sj.wallpaper.Adapter.WallpaperAdapter;
import sjniit.sj.wallpaper.Model.VideoModel;
import sjniit.sj.wallpaper.Model.WallpaperModel;
import sjniit.sj.wallpaper.R;

public class VideoFragment extends Fragment {

    View view;
    VideoAdapter videoAdapter;
    List<VideoModel> videoModelList;
    int pageNumber = 1;

    RecyclerView videoImageRecycler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_video, container, false);

        videoImageRecycler = view.findViewById(R.id.videoImageRecycler);
        videoModelList = new ArrayList<>();
        videoAdapter = new VideoAdapter(requireContext(), videoModelList);
        videoImageRecycler.setAdapter(videoAdapter);

        //setLayout Manger grid
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        videoImageRecycler.setLayoutManager(gridLayoutManager);

        fetchVideo();
        return view;
    }

    public void fetchVideo() {

        StringRequest request = new StringRequest
                (Request.Method.GET, "https://api.pexels.com/videos/popular/?page=" + pageNumber + "  &per_page=80",

                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject jsonObject = new JSONObject(response);

                                    JSONArray jsonArray = jsonObject.getJSONArray("videos");
                                    int length = jsonArray.length();
                                    for (int i = 0; i < length; i++) {

                                        JSONObject object = jsonArray.getJSONObject(i);

                                        int id = object.getInt("id");
                                        //Log.i("Shivam", "onResponse: "+id);
                                        String videoUrl = object.getString("url");
                                        // Log.i("Shivam", "onResponse: "+ videoUrl);
                                        String videoImage = object.getString("image");
                                        //Log.i("Shivam", "onResponse: "+ videoImage);

                                        String videoDuration = object.getString("duration");
                                        //Log.i("Shivam", "onResponse: "+ videoDuration);

                                        //New Json obect
                                        JSONObject objectUser = object.getJSONObject("user");

                                        String userName = objectUser.getString("name");
                                        //Log.i("Shivam", "onResponse: "+ userName);


                                        JSONArray objectVideo = object.getJSONArray("video_files");//New Json obect
                                        JSONObject obj = objectVideo.getJSONObject(0);

                                        String qualityHd = obj.getString("quality");
                                        // Log.i("Shivam", "Quality: "+ qualityHd);

                                        // String qualitySd = obj.getString("qualitySd");
                                        // Log.i("Shivam", "onResponse: "+ qualityHd);

                                        String downloadLink = obj.getString("link");
                                        //Log.i("Shivam", "link: "+ downloadLink);


                                        //Store data in object of wallpaperModel and add in list

                                        VideoModel videoModel = new VideoModel(id, videoUrl, userName, videoImage, videoDuration, qualityHd, "", downloadLink);

                                        videoModelList.add(videoModel);


                                    }
                                    //Date is changed
                                    videoAdapter.notifyDataSetChanged();
                                    pageNumber++;


                                } catch (JSONException e) {

                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                }) {
            //This Method is use for Authorization

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                //hashmap isliye kyouki key value ke pair mai authorization karni hai
                Map<String, String> params = new HashMap<>();
                //put vales
                params.put("Authorization", "563492ad6f91700001000001f8d3ab47dc76456c8d6d0336b1b6a77e");
                return params;
            }
        };

        //Execute String Request

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());//getApplication contex
        requestQueue.add(request);
    }
}