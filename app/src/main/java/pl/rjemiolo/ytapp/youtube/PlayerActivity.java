package pl.rjemiolo.ytapp.youtube;

import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
//import com.google.android.youtube.player.YouTubePlayerView;

import pl.rjemiolo.ytapp.R;
import pl.rjemiolo.ytapp.history.HistoryDbHelper;

public class PlayerActivity extends YouTubeBaseActivity {//} implements YouTubePlayer.OnInitializedListener {

//    private YouTubePlayerView youTubePlayerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        runVideo();


//-------
//        String path="http://www.ted.com/talks/download/video/8584/talk/761";
//        String path1="http://commonsware.com/misc/test2.3gp";
//        String path2="https://www.youtube.com/80a7213a-8720-482d-a528-f808d7e167b5";
//
//        Uri uri=Uri.parse(path2);
//
//        VideoView video=(VideoView)findViewById(R.id.video_view);
//        video.setVideoURI(uri);
//        video.start();
//------
        // youTubePlayerView = (YouTubePlayerView) findViewById(R.id.player_view);
        // youTubePlayerView.initialize( YouTubeConnect.getApiKey(), this);
    }

    private void addVideoToHistory(String videoId) {
        HistoryDbHelper dbHelper = new HistoryDbHelper(getApplicationContext());
        dbHelper.insertHistory("", "", videoId);
    }

    private void runVideo() {
        String videoId = getIntent().getStringExtra("VIDEO_ID");
        Toast.makeText(this, "Prosze czekac. Trwa ladowanie...", Toast.LENGTH_LONG).show();
        addVideoToHistory(videoId);
        WebView webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.loadUrl("https://www.youtube.com/embed/" + videoId + "?autoplay=1&vq=small");
        webView.setWebChromeClient(new WebChromeClient());
    }

/*
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!b) {
            youTubePlayer.cueVideo(getIntent().getStringExtra("VIDEO_ID"));
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Nie udalo sie zaladowac playera", Toast.LENGTH_LONG).show();
    }*/
}
