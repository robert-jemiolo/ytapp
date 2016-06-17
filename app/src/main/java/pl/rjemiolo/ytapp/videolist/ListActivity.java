package pl.rjemiolo.ytapp.videolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.text.Editable;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import pl.rjemiolo.ytapp.youtube.Search;
import pl.rjemiolo.ytapp.R;


public class ListActivity extends AppCompatActivity{
    /**
     * Pole wyszukiwarki
     */
    private EditText editSearch;

    private ListView listSearch;

    private Search search;

  //  private ArrayAdapter<String> adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        editSearch = (EditText) this.findViewById(R.id.searchEditText);
        listSearch = (ListView) this.findViewById(R.id.searchListView);

        /* test listy
        String cars[] = {"Mercedes", "Fiat", "Ferrari", "Aston Martin", "Lamborghini", "Skoda", "Volkswagen", "Audi", "Citroen"};

        ArrayList<String> carL = new ArrayList<String>();
        carL.addAll( Arrays.asList(cars) );

        adapter = new ArrayAdapter<String>(this, R.layout.video_item, carL);

        listSearch.setAdapter(adapter);
*/
    Log.v("LA", " ----------  Search begin  @@@@@@@@@@@@@@@ ");

   //     testConn testConn = new testConn();
   //     testConn.YoutubeConnector(this);


        search = new Search();


        //Fragment menu = (Fragment) getFragmentManager().findFragmentById(R.id.menuFragment);
        // czy istnieje?

        addEvents();

    //    buttonSearch = (Button) this.findViewById(R.id.button_search);
    //    buttonSearch.setText(Html.fromHtml(getResources().getString(R.string.button_search)));
    }

    protected void fillList(){

    }



    private void addEvents(){

    /*    listViewActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(listViewActivityIntent);
            }
        });

*/
///*
        editSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if(actionId == 0){
                    search.startSearch(v.getText().toString());
                    return false;
                }
                return true;
            }
        });
       // */

/*
        editSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if( s.length() > 2 ) search.startSearch(s.toString());
            }
        });
       // */
    }

}
