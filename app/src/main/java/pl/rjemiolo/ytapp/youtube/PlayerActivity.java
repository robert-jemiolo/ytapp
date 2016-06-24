package pl.rjemiolo.ytapp.youtube;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;

import pl.rjemiolo.ytapp.R;
import pl.rjemiolo.ytapp.history.HistoryDbHelper;

public class PlayerActivity extends YouTubeBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        runVideo();
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

}
