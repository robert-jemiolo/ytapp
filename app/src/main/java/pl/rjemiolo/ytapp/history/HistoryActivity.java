package pl.rjemiolo.ytapp.history;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import pl.rjemiolo.ytapp.R;
import pl.rjemiolo.ytapp.youtube.PlayerActivity;

public class HistoryActivity extends AppCompatActivity implements HistoryListFragment.HistoryListFragmentActivityListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

    }

    @Override
    public void listFragmentSelectVideo(String videoId) {
        Intent intent = new Intent(getApplication(), PlayerActivity.class);
        intent.putExtra("VIDEO_ID", videoId);
        startActivity(intent);
    }
}
