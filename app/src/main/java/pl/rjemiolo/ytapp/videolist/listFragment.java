package pl.rjemiolo.ytapp.videolist;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import pl.rjemiolo.ytapp.R;

public class ListFragment extends android.app.ListFragment implements AdapterView.OnItemClickListener {

    private ListFragmentActivityListener mListener;
    private ListView videosFound;
    private List<VideoItem> searchResults;

    public ListFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.v("LF", "onCreateView");
        View view = super.onCreateView(inflater, container, savedInstanceState);
        videosFound = (ListView) inflater.inflate(R.layout.fragment_list, container, false).findViewById(R.id.list);
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
        if (context instanceof ListFragmentActivityListener) {
            mListener = (ListFragmentActivityListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement ListFragmentActivityListener");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mListener.listFragmentSelectVideo(searchResults.get(position).getId());
    }

    public interface ListFragmentActivityListener {
        void listFragmentSelectVideo(String videoId);
    }

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
    }


}
