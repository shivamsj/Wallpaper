package sjniit.sj.wallpaper.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import sjniit.sj.wallpaper.NetworkCheckerReceiver;
import sjniit.sj.wallpaper.R;


public class MainActivity extends AppCompatActivity  implements NetworkCheckerReceiver.ConnectivityReceiverListener  {

    NavController navController;
    AppBarConfiguration appBarConfiguration;
    BottomNavigationView bottomNavigationView;
    DrawerLayout drawerLayout;

    BroadcastReceiver broadcastReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.toolbar));
        broadcastReceiver = new NetworkCheckerReceiver();
        //registerNetworkBroadcastReceiver();
        registerReceiver(broadcastReceiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        //unRegisterNetwork();
        checkConnection();

        navController = Navigation.findNavController(this,R.id.nav_host_fragment);


        appBarConfiguration = new AppBarConfiguration.Builder
                (R.id.home,R.id.homeFragment,R.id.videoFragment,R.id.aboutFragment)
                .build();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
//        drawerLayout=findViewById(R.id.navigationViewDrawer);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);




    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController,appBarConfiguration );
    }



    private void checkConnection() {
        boolean isConnected = NetworkCheckerReceiver.isConnected(this);
        showSnack(isConnected);
    }

    private void showSnack(boolean isConnected) {
        String message;
        int color;
        if (isConnected) {
            message = "Good! Connected to Internet";
            color = Color.WHITE;

           // Snackbar snackbar = Snackbar.make(findViewById(R.id.drawer_layout), message, Snackbar.LENGTH_LONG);
           // View snack = snackbar.getView();
           // snack.setBackgroundColor(color);
            //snackbar.show();
        } else {
            message = "Sorry! Not connected to internet";
            color = Color.RED;
            String e = "Retry";
            Snackbar snackbar = Snackbar.make(findViewById(R.id.drawer_layout), message, Snackbar.LENGTH_INDEFINITE);
            View snack = snackbar.getView();
            snack.setBackgroundColor(color);
            snackbar.setAction(e, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkConnection();
                    Toast.makeText(MainActivity.this, "Retry", Toast.LENGTH_SHORT).show();
                }
            });

            snackbar.show();


        }
    }

    /**
     * Callback will be triggered when there is change in
     * network connection
     */
    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkConnection();
        // register connection status listener
        registerReceiver(broadcastReceiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }
    @Override
    protected void onPause() {
        super.onPause();
        checkConnection();
        // register connection status listener
        registerReceiver(broadcastReceiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }


//
}