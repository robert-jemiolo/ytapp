package pl.rjemiolo.ytapp.history;

import android.content.ContentValues;
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

public class HistoryActivity extends AppCompatActivity {

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        HistoryDbHelper dbHelper = new HistoryDbHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();

        showHistory();
    }

    private void showHistory() {
        ListView list = (ListView) findViewById(R.id.historyListView);
        final List<HistoryEntry> history = getHistory();
        if (history.size() > 0){
Log.v("HA", "Znalazlo filmow: " + history.size() );
            ArrayAdapter<HistoryEntry> arrayAdapter = new ArrayAdapter<HistoryEntry>(getApplicationContext(), R.layout.history_item, history) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    if (convertView == null) {
                        convertView = getLayoutInflater().inflate(R.layout.history_item, parent, false);
                    }
                    HistoryEntry entry = history.get(position);

                    TextView id = (TextView) convertView.findViewById(R.id.hItemId);
                    TextView date = (TextView) convertView.findViewById(R.id.hItemDate);
                    TextView video = (TextView) convertView.findViewById(R.id.hItemLink);

                    id.setText(entry.getId());
                    date.setText(entry.getDate());
                    video.setText("https://www.youtube.com/embed/" + entry.getVideo() + "?autoplay=1&vq=small");

                    return convertView;
                }
            };
            list.setAdapter(arrayAdapter);
        }else{
            Toast.makeText(this, "Brak wpisów w historii", Toast.LENGTH_LONG).show();
        }
    }

    public ArrayList<HistoryEntry> getHistory(){
        String[] columns = { HistoryDbHelper.COLUMN_NAME_ID, HistoryDbHelper.COLUMN_NAME_DATE, HistoryDbHelper.COLUMN_NAME_VIDEO };

        Cursor c = db.rawQuery("select * from history", null);
        c.moveToFirst();

        ArrayList<HistoryEntry> list = new ArrayList<>();
        while( c.isAfterLast() == false ) {
            HistoryEntry he = new HistoryEntry();
            he.setId( c.getString( c.getColumnIndex( HistoryDbHelper.COLUMN_NAME_ID) ) );
            he.setDate( c.getString( c.getColumnIndex( HistoryDbHelper.COLUMN_NAME_DATE ) ) );
            he.setVideo( c.getString( c.getColumnIndex( HistoryDbHelper.COLUMN_NAME_VIDEO) ) );
            list.add( he );
            c.moveToNext();
        }
        return list;
    }

    public long insertHistory(String id, String data, String video){
        ContentValues entry = new ContentValues();
       // entry.put( HistoryDbHelper.COLUMN_NAME_ID, id);
        entry.put( HistoryDbHelper.COLUMN_NAME_DATE, data);
        entry.put( HistoryDbHelper.COLUMN_NAME_VIDEO, video);
        return db.insert( HistoryDbHelper.TABLE_NAME, null, entry );
    }

}
