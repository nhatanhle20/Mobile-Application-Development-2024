package vn.edu.usth.weather;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;


import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class WeatherActivity extends AppCompatActivity {
    private static final String TAG = "WeatherActivity";
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        // Set up the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set up the ViewPager2 and adapter for multiple WeatherAndForecastFragments
        ViewPager2 viewPager = findViewById(R.id.view_pager);
        WeatherPagerAdapter adapter = new WeatherPagerAdapter(this);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    // Set the title for each tab
                    switch (position) {
                        case 0:
                            tab.setText("HANOI, VIETNAM");
                            break;
                        case 1:
                            tab.setText("PARIS, FRANCE");
                            break;
                        case 2:
                            tab.setText("TOULOUSE, FRANCE");
                            break;
                    }
                }).attach();

        mediaPlayer = MediaPlayer.create(this, R.raw.music);

        // Start playing music as soon as the activity is created
        if (mediaPlayer != null) {
            mediaPlayer.start();  // Automatically start playing the music
            Log.i(TAG, "Music started playing automatically");
        }
    }

    // Create the options menu and inflate the menu resource (menu_weather.xml)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_weather, menu);  // Inflate your custom menu
        return true;
    }

    // Handle toolbar item clicks
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // Handle Refresh action
        if (id == R.id.action_refresh) {
            Toast.makeText(this, "Refreshing", Toast.LENGTH_SHORT).show();
            // Add your refresh logic here
            return true;
        }

        // Handle Settings action
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, PrefActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Release the MediaPlayer when the activity is destroyed
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            Log.i(TAG, "MediaPlayer resources released");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop called");
    }
}
