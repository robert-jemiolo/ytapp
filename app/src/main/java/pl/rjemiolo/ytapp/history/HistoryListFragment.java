package pl.rjemiolo.ytapp.history;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import pl.rjemiolo.ytapp.R;

public class HistoryListFragment extends android.app.ListFragment implements AdapterView.OnItemClickListener {

    HistoryDbHelper dbHelper;
    List<HistoryEntry> history;
    private HistoryListFragmentActivityListener hListener;
    public HistoryListFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public void onResume(){
        super.onResume();
        showHistory();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dbHelper = new HistoryDbHelper(getActivity().getApplicationContext());
        return inflater.inflate(R.layout.fragment_history_list, container, false);
    }

    private void showHistory() {
        history = dbHelper.getHistory();
        if (history.size() > 0){
            Log.v("HA", "Znalazlo filmow: " + history.size() );
            ArrayAdapter<HistoryEntry> arrayAdapter = new ArrayAdapter<HistoryEntry>(getActivity().getApplicationContext(), R.layout.history_item, history) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    if (convertView == null) {
                        convertView = getActivity().getLayoutInflater().inflate(R.layout.history_item, parent, false);
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
            setListAdapter(arrayAdapter);
            getListView().setOnItemClickListener(this);
        }else{
            Toast.makeText(getActivity(), "Brak wpisów w historii", Toast.LENGTH_LONG).show();
        }
    }

    public void onAttach(Context context) {
        Log.v("LF", "onAttach");
        super.onAttach(context);
        if (context instanceof HistoryListFragmentActivityListener) {
            hListener = (HistoryListFragmentActivityListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement ListFragmentActivityListener");
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.v("LF", "onItemClick");
        hListener.listFragmentSelectVideo(history.get(position).getId());
    }

    public interface HistoryListFragmentActivityListener {
        void listFragmentSelectVideo(String videoId);
    }

}
