package pl.rjemiolo.ytapp.youtube;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
//import com.google.api.services.samples.youtube.cmdline.Auth;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class Search extends AsyncTask<String, Integer, String> {

    /**
     * Define a global variable that identifies the name of a file that
     * contains the developer's API key.
     */
    private static final String PROPERTIES_FILENAME = "youtube.properties";

    private static final long NUMBER_OF_VIDEOS_RETURNED = 15;

    /**
     * Define a global instance of a Youtube object, which will be used
     * to make YouTube Data API requests.
     */
    private static YouTube youtube;


    public void startSearch(String query){
        Log.v("SEARCH", query);
        this.execute(query);
//
//        // Read the developer key from the properties file.
//        Properties properties = new Properties();
//        try {
//            InputStream in = Search.class.getResourceAsStream("/" + PROPERTIES_FILENAME);
//            properties.load(in);
//
//        } catch (IOException e) {
//            System.err.println("There was an error reading " + PROPERTIES_FILENAME + ": " + e.getCause()
//                    + " : " + e.getMessage());
//            System.exit(1);
//        }
//
//        try {
//            // This object is used to make YouTube Data API requests. The last
//            // argument is required, but since we don't need anything
//            // initialized when the HttpRequest is initialized, we override
//            // the interface and provide a no-op function.
//            youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, new HttpRequestInitializer() {
//                public void initialize(HttpRequest request) throws IOException {
//                }
//            }).setApplicationName("youtube-cmdline-search-sample").build();
//Log.v("SS", "       1");
//            // Prompt the user to enter a query term.
//            String queryTerm = getInputQuery();
//
//            // Define the API request for retrieving search results.
//            YouTube.Search.List search = youtube.search().list("id,snippet");
//Log.v("SS", "       2");
//
//            // Set your developer key from the {{ Google Cloud Console }} for
//            // non-authenticated requests. See:
//            // {{ https://cloud.google.com/console }}
//            String apiKey = properties.getProperty("youtube.apikey");
//            search.setKey(apiKey);
//            search.setQ(queryTerm);
//Log.v("SS", "       3");
//
//            // Restrict the search results to only include videos. See:
//            // https://developers.google.com/youtube/v3/docs/search/list#type
//            search.setType("video");
//
//            // To increase efficiency, only retrieve the fields that the
//            // application uses.
//            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
//            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
//Log.v("SS", "       4");
//
//            // Call the API and print results.
//            SearchListResponse searchResponse = search.execute();
//            List<SearchResult> searchResultList = searchResponse.getItems();
//            if (searchResultList != null) {
//                prettyPrint(searchResultList.iterator(), queryTerm);
//            }else{
//                System.err.println("Chyba nie znalazlo zadnych wynikow !!!!!!!!!!!!!!!!!!!!!!");
//            }
//Log.v("SS", "       5");
//        } catch (GoogleJsonResponseException e) {
//            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
//                    + e.getDetails().getMessage());
//        } catch (IOException e) {
//            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
//        } catch (Throwable t) {
//            t.printStackTrace();
//        }
    }

    /*
     * Prints out all results in the Iterator. For each result, print the
     * title, video ID, and thumbnail.
     *
     * @param iteratorSearchResults Iterator of SearchResults to print
     *
     * @param query Search query (String)
     */
    private static void prettyPrint(Iterator<SearchResult> iteratorSearchResults, String query) {

        System.out.println("\n=============================================================");
        System.out.println(
                "   First " + NUMBER_OF_VIDEOS_RETURNED + " videos for search on \"" + query + "\".");
        System.out.println("=============================================================\n");

        if (!iteratorSearchResults.hasNext()) {
            System.out.println(" There aren't any results for your query.");
        }

        while (iteratorSearchResults.hasNext()) {

            SearchResult singleVideo = iteratorSearchResults.next();
            ResourceId rId = singleVideo.getId();

            // Confirm that the result represents a video. Otherwise, the
            // item will not contain a video ID.
            if (rId.getKind().equals("youtube#video")) {
                Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();

                System.out.println(" Video Id" + rId.getVideoId());
                System.out.println(" Title: " + singleVideo.getSnippet().getTitle());
                System.out.println(" Thumbnail: " + thumbnail.getUrl());
                System.out.println("\n-------------------------------------------------------------\n");
            }
        }
    }

    @Override
    protected String doInBackground(String... params) {
        // Read the developer key from the properties file.
        Properties properties = new Properties();
        try {
            InputStream in = Search.class.getResourceAsStream("/" + PROPERTIES_FILENAME);
            properties.load(in);

        } catch (IOException e) {
            System.err.println("There was an error reading " + PROPERTIES_FILENAME + ": " + e.getCause() + " : " + e.getMessage());
            System.exit(1);
        }

        try {
            // This object is used to make YouTube Data API requests. The last
            // argument is required, but since we don't need anything
            // initialized when the HttpRequest is initialized, we override
            // the interface and provide a no-op function.
            youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
                public void initialize(com.google.api.client.http.HttpRequest request) throws IOException {}
            }).setApplicationName("YTApp").build();

            // Define the API request for retrieving search results.
            YouTube.Search.List search = youtube.search().list("id,snippet");
            String apiKey = properties.getProperty("youtube.apikey");
            search.setKey(apiKey);
            search.setQ(params[0]);

            // Restrict the search results to only include videos. See:
            // https://developers.google.com/youtube/v3/docs/search/list#type
            search.setType("video");

            // To increase efficiency, only retrieve the fields that the
            // application uses.
            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);

            SearchListResponse searchResponse = search.execute();
            List<SearchResult> searchResultList = searchResponse.getItems();
            if (searchResultList != null) {
                prettyPrint(searchResultList.iterator(), params[0]);
            }else{
                System.err.println("Chyba nie znalazlo zadnych wynikow !!!!!!!!!!!!!!!!!!!!!!");
            }
        } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Exception e) {
            System.err.println("There was ERROR: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return null;
    }
}
