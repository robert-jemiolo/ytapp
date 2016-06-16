package pl.rjemiolo.ytapp.videolist;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import android.os.Handler;
import android.widget.VideoView;

import pl.rjemiolo.ytapp.youtube.PlayerActivity;
import pl.rjemiolo.ytapp.R;
import pl.rjemiolo.ytapp.youtube.YouTubeConnect;


public class ListActivity extends AppCompatActivity implements ListFragment.ListFragmentActivityListener {
    private EditText searchInput;
//    private ListView videosFound;
    private Handler handler;

    private List<VideoItem> searchResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        searchInput = (EditText) findViewById(R.id.searchEditText);
//        videosFound = (ListView) findViewById(R.id.searchListView);

        handler = new Handler();

//        searchInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if(actionId == 0){
//                    searchOnYoutube(v.getText().toString());
//                    return false;
//                }
//                return true;
//            }
//        });

        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if( s.length() > 2 ) searchOnYoutube(s.toString());
            }
        });


    }

    private void searchOnYoutube(final String keywords) {
        new Thread(){
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
        if( fragment != null && fragment.isInLayout()){
            Log.v("LA", "Wchodze do metody - przekazuje filmy");
            fragment.updateVideoList(searchResults);
        }else{
            Log.v("LA", "Fragment nie jest widoczny");
        }
    }

/*
    private void updateVideosFound() {
        ArrayAdapter<VideoItem> adapter = new ArrayAdapter<VideoItem>(getApplicationContext(), R.layout.video_item, searchResults) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.video_item, parent, false);
                }
                ImageView thumbnail = (ImageView) convertView.findViewById(R.id.itemVideoThumbnail);
                TextView title = (TextView) convertView.findViewById(R.id.itemVideoTitle);
                TextView description = (TextView) convertView.findViewById(R.id.itemVideoDescription);
                VideoItem searchResult = searchResults.get(position);

                Picasso.with(getApplicationContext()).load(searchResult.getThumbnailURL()).into(thumbnail);
                title.setText(searchResult.getTitle());
                description.setText(searchResult.getDescription());
                return convertView;
            }
        };
        videosFound.setAdapter(adapter);
    }
*/
    @Override
    public void listFragmentSelectVideo(String videoId) {
        Log.v("LA", "Wybrany film!");
        Intent intent = new Intent(getApplication(), PlayerActivity.class);
        intent.putExtra("VIDEO_ID", videoId);
        startActivity(intent);
    }

}

