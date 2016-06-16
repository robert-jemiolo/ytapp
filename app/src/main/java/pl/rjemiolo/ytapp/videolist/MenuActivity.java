package pl.rjemiolo.ytapp.videolist;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import pl.rjemiolo.ytapp.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        Log.v("ALA", "ALA MA KOTA");

        if( getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ){


            finish();
            return;
        }
//        setContentView(R.layout.);
    }


}
