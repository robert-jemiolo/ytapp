package pl.rjemiolo.ytapp.videolist;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import pl.rjemiolo.ytapp.R;
import pl.rjemiolo.ytapp.history.HistoryActivity;
import pl.rjemiolo.ytapp.youtube.PlayerActivity;

public class ListFragment extends android.app.ListFragment implements AdapterView.OnItemClickListener {

    private ListFragmentActivityListener mListener;
    private ListView videosFound;
    private List<VideoItem> searchResults;
    private Bundle activityBundle;
    private Context activityContext;
    private View view;
    ArrayAdapter<VideoItem> adapter;

    public ListFragment() {
    }

//    public static ListFragment newInstance(String param1, String param2) {
//        Log.v("LF", "Dostalem wyniki");
//        ListFragment fragment = new ListFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
//        return fragment;
//    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        Log.v("LF", "onCreate");
//        super.onCreate(savedInstanceState);
//        activityBundle= savedInstanceState;
//    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.v("LF", "onCreateView");
        View view = super.onCreateView(inflater, container, savedInstanceState);
//        ListView listView = (ListView) view.findViewById(android.R.id.list);
//        ViewGroup parent = (ViewGroup) listView.getParent();

        videosFound = (ListView) inflater.inflate(R.layout.fragment_list, container, false).findViewById(R.id.list);

//        int listViewIndex = parent.indexOfChild(listView);
//        parent.removeViewAt(listViewIndex);
//        RelativeLayout relLayout = (RelativeLayout) inflater.inflate( R.layout.fragment_list, container, false);
//        parent.addView(relLayout , listViewIndex, listView.getLayoutParams());
//        return view;



        Log.v("LF", "onCreateView1");
//        Log.v("LF", "onCreateView1");
//        addClickListener();
//        view = inflater.inflate(R.layout.fragment_list, container, false);
//        Log.v("LF", "onCreateView2");
////        updateVideoList(null);
//        return view;
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onAttach(Context context) {
        Log.v("LF", "onAttach");
        super.onAttach(context);
        if (context instanceof ListFragmentActivityListener) {
            mListener = (ListFragmentActivityListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement ListFragmentActivityListener");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.v("LF", "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
//        List<VideoItem> lista = new ArrayList<VideoItem>();
//        VideoItem item = new VideoItem();
//        item.setTitle("Ala ma kota");
//        lista.add(item);
//        VideoItem item2 = new VideoItem();
//        item2.setTitle("Ala ma kota");
//        lista.add(item2);
//        searchResults=lista;
//Log.e("LF", this.getContext().toString());
//        adapter = new ArrayAdapter<VideoItem>( this.getContext(), R.layout.video_item, searchResults);
//        videosFound.setAdapter(adapter);
//        videosFound.setBackgroundColor(Color.parseColor("#00ff00"));
//        videosFound.invalidate();


//        List<String> your_array_list = new ArrayList<String>();
//        your_array_list.add("foo");
//        your_array_list.add("fo33");
//        your_array_list.add("fo555o");
//        your_array_list.add("bar");
//
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>( getActivity().getApplicationContext(),  R.layout.item,  your_array_list );
//
//        videosFound.setAdapter(arrayAdapter);

    }

    @Override
    public void onDetach() {
        Log.v("LF", "onDetach");
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.v("LF", "onItemClick");
        mListener.listFragmentSelectVideo(searchResults.get(position).getId());
    }

    public interface ListFragmentActivityListener {
        void listFragmentSelectVideo(String videoId);
    }

//    private void addClickListener() {
//        videosFound.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//       public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    // }
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=Hxy8BZGQ5Jo")));
//            }
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                mListener.listFragmentSelectVideo( searchResults.get(position).getId() );
//                Intent intent = new Intent(getApplication(), PlayerActivity.class);
////                System.out.println( searchResults.get(position).getId() );
//                intent.putExtra("VIDEO_ID", searchResults.get(position).getId());
//                startActivity(intent);
//            }
//        });
//    }


    public void updateVideoList(List<VideoItem> results) {

        searchResults = results;

        ArrayAdapter<VideoItem> arrayAdapter = new ArrayAdapter<VideoItem>(getActivity(), R.layout.video_item, searchResults) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                Log.v("LF", "Uzupelniam");
                if (convertView == null) {
                    convertView = getActivity().getLayoutInflater().inflate(R.layout.video_item, parent, false);
                }
                ImageView thumbnail = (ImageView) convertView.findViewById(R.id.itemVideoThumbnail);
                TextView title = (TextView) convertView.findViewById(R.id.itemVideoTitle);
                TextView description = (TextView) convertView.findViewById(R.id.itemVideoDescription);
                VideoItem searchResult = searchResults.get(position);

                Picasso.with(getContext().getApplicationContext()).load(searchResult.getThumbnailURL()).into(thumbnail);
                title.setText(searchResult.getTitle());
                description.setText(searchResult.getDescription());
                return convertView;
            }
        };
        setListAdapter(arrayAdapter);
        getListView().setOnItemClickListener(this);

//        List<VideoItem> lista = new ArrayList<VideoItem>();
//        VideoItem item = new VideoItem();
//        item.setTitle("Ala ma kota");
//        lista.add(item);
//        VideoItem item2 = new VideoItem();
//        item2.setTitle("Ala ma kota");
//        lista.add(item2);
        Log.v("LF", "Dostalem wyniki");

        //   view.findViewById(R.id.searchListView).setBackgroundColor(0xFF00FF00);
        //   view.findViewById(R.id.searchListView).invalidate();
/*
        ArrayAdapter<VideoItem> adapter = new ArrayAdapter<VideoItem>( getActivity().getApplicationContext(), R.layout.video_item, searchResults) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                Log.v("LF", "Uzupelniam");
                if (convertView == null) {
                    convertView = getActivity().getLayoutInflater().inflate(R.layout.video_item, parent, false);
                }
//                ImageView thumbnail = (ImageView) convertView.findViewById(R.id.itemVideoThumbnail);
//                TextView title = (TextView) convertView.findViewById(R.id.itemVideoTitle);
//                TextView description = (TextView) convertView.findViewById(R.id.itemVideoDescription);
//                VideoItem searchResult = searchResults.get(position);
//
//                Picasso.with(getContext().getApplicationContext()).load(searchResult.getThumbnailURL()).into(thumbnail);
//                title.setText(searchResult.getTitle());
//                description.setText(searchResult.getDescription());
                return convertView;
            }
        };
        videosFound.setAdapter(adapter);
//        adapter.notifyDataSetChanged();

// */
//        ArrayAdapter<VideoItem> adapter = new ArrayAdapter<VideoItem>( getActivity().getApplicationContext(), R.layout.video_item, searchResults) {
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent) {
//Log.v("LF", "Uzupelniam");
//                if (convertView == null) {
//                    convertView = getLayoutInflater(activityBundle).inflate(R.layout.video_item, parent, false);
//                }
//                ImageView thumbnail = (ImageView) convertView.findViewById(R.id.itemVideoThumbnail);
//                TextView title = (TextView) convertView.findViewById(R.id.itemVideoTitle);
//                TextView description = (TextView) convertView.findViewById(R.id.itemVideoDescription);
//                VideoItem searchResult = searchResults.get(position);
//
//                Picasso.with(getContext().getApplicationContext()).load(searchResult.getThumbnailURL()).into(thumbnail);
//                title.setText(searchResult.getTitle());
//                description.setText(searchResult.getDescription());
//                return convertView;
//            }
//        };
//        videosFound.setAdapter(adapter);
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
}
