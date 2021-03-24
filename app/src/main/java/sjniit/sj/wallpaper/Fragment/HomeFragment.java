package sjniit.sj.wallpaper.Fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sjniit.sj.wallpaper.Adapter.WallpaperAdapter;
import sjniit.sj.wallpaper.Model.WallpaperModel;
import sjniit.sj.wallpaper.R;
public class HomeFragment extends Fragment {

    View view;
    RecyclerView recyclerView;
    WallpaperAdapter wallpaperAdapter;
    List<WallpaperModel> wallpaperModelList;
    int pageNumber = 1;

    Boolean isScrolling = false;
    //endless scrolling
    int currentItem, totalItems, scrollOutItems;

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
        view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);

        //setLayout Manger grid
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        //getcontex ke jga this tha
        recyclerView.setLayoutManager(gridLayoutManager);
     //   recyclerView.setLayoutManager(
            //    new StaggeredGridLayoutManager(
             //           2,StaggeredGridLayoutManager.VERTICAL
            //    )
      //  );
        recyclerView.setHasFixedSize(true);

        wallpaperModelList = new ArrayList<>();
        wallpaperAdapter = new WallpaperAdapter(requireContext(), wallpaperModelList);

        recyclerView.setAdapter(wallpaperAdapter);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (isScrolling && (currentItem + scrollOutItems == totalItems)) {
                    isScrolling = false;
                    fetchWallpaper();
                }
            }
        });
        fetchWallpaper();
        return view;
    }

    public void fetchWallpaper() {

        StringRequest request = new StringRequest
                (Request.Method.GET, "https://api.pexels.com/v1/curated/?page=" + pageNumber + "&per_page=80",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject jsonObject = new JSONObject(response);

                                    JSONArray jsonArray = jsonObject.getJSONArray("photos");
                                    int length = jsonArray.length();
                                    for (int i = 0; i < length; i++) {

                                        JSONObject object = jsonArray.getJSONObject(i);

                                        int id = object.getInt("id");
                                        int heightImg = object.getInt("height");
                                        int widthImg = object.getInt("width");
                                        String photographerName = object.getString("photographer");
                                        String photographer_url = object.getString("photographer_url");


                                        JSONObject objectImages = object.getJSONObject("src");//New Json

                                        String originalUrl = objectImages.getString("original");
                                        String mediumUrl = objectImages.getString("medium");
                                        String smallUrl = objectImages.getString("small");
                                        String portraitImg = objectImages.getString("portrait");
                                        String landscapeImg = objectImages.getString("landscape");

                                        //Store data in object of wallpaperModel and add in list
                                        WallpaperModel wallpaperModel = new WallpaperModel
                                                (id, heightImg, widthImg, originalUrl, mediumUrl, smallUrl,photographerName, portraitImg, landscapeImg,photographer_url);

                                        wallpaperModelList.add(wallpaperModel);
                                    }
                                    //Date is changed
                                    wallpaperAdapter.notifyDataSetChanged();
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