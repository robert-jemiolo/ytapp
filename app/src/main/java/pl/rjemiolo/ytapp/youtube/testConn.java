package pl.rjemiolo.ytapp.youtube;

import android.content.Context;
import android.util.Log;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

import java.io.IOException;
import java.util.List;

import pl.rjemiolo.ytapp.R;

/**
 * Created by rjemiolo on 2016-06-17.
 */
public class testConn {
    private YouTube youtube;
    private YouTube.Search.List query;

    // Your developer key goes here
    public static final String KEY
            = "AIzaSyAG2fsi7JIMdHhFqHh7_58tf8SdgbEEyHg";

    public void YoutubeConnector(Context context) {
        youtube = new YouTube.Builder(new NetHttpTransport(),
                new JacksonFactory(), new HttpRequestInitializer() {
            @Override
            public void initialize(HttpRequest hr) throws IOException {}
        }).setApplicationName(context.getString(R.string.app_name)).build();

        try{
            query = youtube.search().list("id,snippet");
            query.setKey(KEY);
            query.setType("video");
            query.setFields("items(id/videoId,snippet/title,snippet/description,snippet/thumbnails/default/url)");
            query.setQ("cats");

         //   List<SearchResult> searchResultList = searchResponse.getItems();

        }catch(IOException e){
            Log.d("YC", "Could not initialize: "+e);
        }

        try{
            SearchListResponse response = query.execute();
            List<SearchResult> results = response.getItems();

            for(SearchResult result:results){
                Log.i("OK: ", result.getSnippet().getTitle());
            }
        }catch(Exception e){
            Log.d("YC", "Could not search: "+e);
        }


    }
}
