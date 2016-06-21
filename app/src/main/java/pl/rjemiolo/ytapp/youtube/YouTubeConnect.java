package pl.rjemiolo.ytapp.youtube;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import pl.rjemiolo.ytapp.R;
import pl.rjemiolo.ytapp.videolist.VideoItem;

public class YouTubeConnect {
    private YouTube youTube;
    private YouTube.Search.List query;

    private final static String PROPERTIES_FILENAME = "youtube.properties";
    private final static long RESULTS_NUM = 15;

    private Context context;

    public YouTubeConnect(Context ctx) {
        context = ctx;
        youTube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
            @Override
            public void initialize(com.google.api.client.http.HttpRequest request) throws IOException {
            }
        }).setApplicationName(ctx.getString(R.string.app_name)).build();


        try {
            query = youTube.search().list("id,snippet");
            query.setKey(getApiKey());
            query.setType("video");
            query.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
            query.setMaxResults(RESULTS_NUM);
        } catch (IOException e) {
            Log.d("YTC", "Could not initialize: " + e.getMessage());
            System.exit(1);
        }
    }

    public static String getApiKey() {
        Properties properties = new Properties();
        try {
            InputStream in = Search.class.getResourceAsStream("/" + PROPERTIES_FILENAME);
            properties.load(in);
        } catch (IOException e) {
            Log.d("YTC", "Could not read properties: " + e.getMessage());
            System.exit(1);
        }
        return properties.getProperty("youtube.apikey");
    }

    public List<VideoItem> search(String phrase) {
        query.setQ(phrase);
        try {
            SearchListResponse response = query.execute();
            List<SearchResult> results = response.getItems();
            List<VideoItem> items = new ArrayList<VideoItem>();

            for (SearchResult result : results) {
                VideoItem item = new VideoItem();
                item.setTitle(result.getSnippet().getTitle());
                item.setDescription(result.getSnippet().getDescription());
                item.setThumbnailURL(result.getSnippet().getThumbnails().getDefault().getUrl());
                item.setId(result.getId().getVideoId());
                items.add(item);
            }

            return items;
        } catch (IOException e) {
            Toast.makeText( context, "Wystapil blad przy wyszukiwaniu", Toast.LENGTH_LONG).show();
            return null;
        }
    }
}
