package pl.rjemiolo.ytapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.text.Editable;
import java.lang.Object;

public class ListActivity extends AppCompatActivity {
    /**
     * Pole wyszukiwarki
     */
    EditText editSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        addEvents();

    //    buttonSearch = (Button) this.findViewById(R.id.button_search);
    //    buttonSearch.setText(Html.fromHtml(getResources().getString(R.string.button_search)));
    }

    private void addEvents(){
        editSearch = (EditText) this.findViewById(R.id.edit_search);

        editSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if( s.length() > 2 ) Log.v("List", "text changed! " + s );
            }
        });
    }
}
