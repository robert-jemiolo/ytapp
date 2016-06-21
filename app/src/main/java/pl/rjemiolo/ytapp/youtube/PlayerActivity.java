package pl.rjemiolo.ytapp.youtube;

import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import pl.rjemiolo.ytapp.R;

public class PlayerActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private YouTubePlayerView youTubePlayerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        Toast.makeText(this, "Prosze czekac. Trwa ladowanie...", Toast.LENGTH_LONG).show();
        WebView webView = (WebView) findViewById(R.id.webview);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.loadUrl("http://www.youtube.com/embed/" + "Hxy8BZGQ5Jo" + "?autoplay=1&vq=small");
        webView.setWebChromeClient(new WebChromeClient());

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


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!b) {
            youTubePlayer.cueVideo(getIntent().getStringExtra("VIDEO_ID"));
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Nie udalo sie zaladowac playera", Toast.LENGTH_LONG).show();
    }
}
