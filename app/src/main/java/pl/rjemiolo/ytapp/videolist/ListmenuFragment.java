package pl.rjemiolo.ytapp.videolist;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import pl.rjemiolo.ytapp.R;
import pl.rjemiolo.ytapp.history.HistoryActivity;
import pl.rjemiolo.ytapp.youtube.PlayerActivity;

public class ListmenuFragment extends Fragment {

//    private ListmenuFragmentInterface mListener;

    public ListmenuFragment() {}

//    public static ListmenuFragment newInstance(String param1, String param2) {
//        ListmenuFragment fragment = new ListmenuFragment();
//        Bundle args = new Bundle();
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//        View view = super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_listmenu, container, false);


        Button home = (Button)view.findViewById(R.id.menuListButton);
        if( home != null ) {
            home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), ListActivity.class);
                    startActivity(intent);
                }
            });
        }
        Button history = (Button)view.findViewById(R.id.menuHistoryButton);
        if( history != null) {
            history.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), HistoryActivity.class);
                    startActivity(intent);
                }
            });
        }
        Button last = (Button)view.findViewById(R.id.menuLastPreviewButton);
        if( last != null ) {
            last.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), PlayerActivity.class);
                    intent.putExtra("VIDEO_ID", "Y9iw4NqURos");
                    startActivity(intent);
                }
            });
        }

        return view;
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof ListmenuFragmentInterface) {
//            mListener = (ListmenuFragmentInterface) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement ListmenuFragmentInterface");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

//    public interface ListmenuFragmentInterface {
//        void ChangeActivity(String activity);
//    }
}
