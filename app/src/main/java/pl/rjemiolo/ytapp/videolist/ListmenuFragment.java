package pl.rjemiolo.ytapp.videolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import pl.rjemiolo.ytapp.R;
import pl.rjemiolo.ytapp.general.AuthorActivity;
import pl.rjemiolo.ytapp.history.HistoryActivity;
import pl.rjemiolo.ytapp.history.HistoryDbHelper;
import pl.rjemiolo.ytapp.history.HistoryEntry;
import pl.rjemiolo.ytapp.youtube.PlayerActivity;

public class ListmenuFragment extends Fragment {

    public ListmenuFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listmenu, container, false);


        Button home = (Button) view.findViewById(R.id.menuListButton);
        if (home != null) {
            home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), ListActivity.class);
                    startActivity(intent);
                }
            });
        }
        Button history = (Button) view.findViewById(R.id.menuHistoryButton);
        if (history != null) {
            history.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), HistoryActivity.class);
                    startActivity(intent);
                }
            });
        }
        Button last = (Button) view.findViewById(R.id.menuLastPreviewButton);
        if (last != null) {
            last.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HistoryDbHelper dbHelper = new HistoryDbHelper(getContext());
                    HistoryEntry he = dbHelper.getLatestHistoryEntry();
                    Intent intent = new Intent(getContext(), PlayerActivity.class);
                    intent.putExtra("VIDEO_ID", he.getVideo());
                    startActivity(intent);
                }
            });
        }
        Button author = (Button) view.findViewById(R.id.menuAuthor);
        if (author != null) {
            author.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), AuthorActivity.class);
                    startActivity(intent);
                }
            });
        }

        return view;
    }

}
