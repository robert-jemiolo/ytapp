package pl.rjemiolo.ytapp.videolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import java.util.List;

import android.os.Handler;

import pl.rjemiolo.ytapp.youtube.PlayerActivity;
import pl.rjemiolo.ytapp.R;
import pl.rjemiolo.ytapp.youtube.YouTubeConnect;


public class ListActivity extends AppCompatActivity implements ListFragment.ListFragmentActivityListener {
    private EditText searchInput;
    private Handler handler;

    private List<VideoItem> searchResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        searchInput = (EditText) findViewById(R.id.searchEditText);
        handler = new Handler();


        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 2) searchOnYoutube(s.toString());
            }
        });


    }

    private void searchOnYoutube(final String keywords) {
        new Thread() {
            @Override
            public void run() {
                YouTubeConnect yc = new YouTubeConnect(ListActivity.this);
                searchResults = yc.search(keywords);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.v("LA", "Znalazlo filmy");
                        updateVideosFound();
                    }
                });
            }
        }.start();
    }

    private void updateVideosFound() {
        Log.v("LA", "Ustawiamy filmy");
        ListFragment fragment = (ListFragment) getFragmentManager().findFragmentById(R.id.fragment_list);
        if (fragment != null && fragment.isInLayout()) {
            Log.v("LA", "Wchodze do metody - przekazuje filmy");
            fragment.updateVideoList(searchResults);
        } else {
            Log.v("LA", "Fragment nie jest widoczny");
        }
    }

    @Override
    public void listFragmentSelectVideo(String videoId) {
        Log.v("LA", "Wybrany film!");
        Intent intent = new Intent(getApplication(), PlayerActivity.class);
        intent.putExtra("VIDEO_ID", videoId);
        startActivity(intent);
    }

}

