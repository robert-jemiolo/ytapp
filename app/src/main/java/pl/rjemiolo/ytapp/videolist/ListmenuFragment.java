package pl.rjemiolo.ytapp.videolist;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.rjemiolo.ytapp.R;

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
        return inflater.inflate(R.layout.fragment_listmenu, container, false);
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
