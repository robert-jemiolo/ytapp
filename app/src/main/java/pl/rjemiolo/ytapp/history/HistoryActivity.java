package pl.rjemiolo.ytapp.history;

import android.app.ListFragment;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.text.Text;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import pl.rjemiolo.ytapp.R;
import pl.rjemiolo.ytapp.videolist.VideoItem;
import pl.rjemiolo.ytapp.youtube.PlayerActivity;

public class HistoryActivity extends AppCompatActivity implements HistoryListFragment.HistoryListFragmentActivityListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

    }


    @Override
    public void listFragmentSelectVideo(String videoId) {
Log.v("HA", "OK?");
        Intent intent = new Intent(getApplication(), PlayerActivity.class);
        intent.putExtra("VIDEO_ID", videoId);
        startActivity(intent);
    }
}
