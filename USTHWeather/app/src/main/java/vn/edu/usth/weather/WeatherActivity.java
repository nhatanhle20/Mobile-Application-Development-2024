package vn.edu.usth.weather;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
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

        mediaPlayer = MediaPlayer.create(this, R.raw.music);  // Replace 'your_music_file' with your actual MP3 file in res/raw

        // Start playing music as soon as the activity is created
        if (mediaPlayer != null) {
            mediaPlayer.start();  // Automatically start playing the music
            Log.i(TAG, "Music started playing automatically");
        }
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
