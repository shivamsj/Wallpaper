package sjniit.sj.wallpaper.Fragment;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.renderscript.Element;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Calendar;

import mehdi.sakout.aboutpage.AboutPage;
import sjniit.sj.wallpaper.Activity.MainActivity;
import sjniit.sj.wallpaper.R;


public class AboutFragment extends Fragment {
    View view;
    Typeface typeface;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onStart() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        super.onStart();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    view= inflater.inflate(R.layout.fragment_about, container, false);
        //typeface = Typeface.createFromAsset(requireActivity().getAssets(), "font/ubuntu_bold.ttf");




        return new AboutPage(getContext())
                    .isRTL(false)

                    .setDescription(getString(R.string.app_description))
                    .addGroup(getString(R.string.contact_group))
                    .addEmail("sjshree2812@gmail.com", "Contact us")

                    .setImage(R.drawable.pp)

                // .setCustomFont(typeface)


                    . addFacebook("100007680799083")
                    .addYoutube("UCfisEMlMvrB2LNIrq30Wnrg")
       //. addTwitter(String AccountID)
                //.addItem(new VersionElement())

       //. addPlayStore(String PackageName)
      // . addInstagram(String AccountID)
       . addGitHub("shivamsj")
                .addGroup(getString(R.string.application_information_group))
                    .create();


    }
}