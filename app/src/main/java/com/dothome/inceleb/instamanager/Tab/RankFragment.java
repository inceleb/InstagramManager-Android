package com.dothome.inceleb.instamanager.Tab;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dothome.inceleb.instamanager.R;

public class RankFragment extends Fragment{
    TextView tab_follower;
    TextView tab_hash;
    LinearLayout follower_wrap;
    LinearLayout hash_wrap;


    private OnFragmentInteractionListener mListener;

    public RankFragment() {
        // Required empty public constructor
    }
    public static RankFragment newInstance() {
        RankFragment fragment = new RankFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_rank, container, false);
        tab_follower=(TextView)v.findViewById(R.id.rank_tab_follower);
        tab_hash=(TextView)v.findViewById(R.id.rank_tab_hash);
        follower_wrap=(LinearLayout) v.findViewById(R.id.rank_follewer);
        hash_wrap=(LinearLayout)v.findViewById(R.id.rank_hash);
        View.OnClickListener tabClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.equals(tab_follower)){
                    tab_follower.setTextColor(ContextCompat.getColor(getContext(), R.color.colorRankBackground) );
                    tab_follower.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark) );
                    tab_hash.setTextColor(Color.parseColor("#ffffff"));
                    tab_hash.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorHomeActivityBackground) );
                    follower_wrap.setVisibility(View.VISIBLE);
                    hash_wrap.setVisibility(View.GONE);
                }else{

                    tab_hash.setTextColor(ContextCompat.getColor(getContext(), R.color.colorRankBackground) );
                    tab_hash.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark) );
                    tab_follower.setTextColor(Color.parseColor("#ffffff"));
                    tab_follower.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorHomeActivityBackground) );
                    follower_wrap.setVisibility(View.GONE);
                    hash_wrap.setVisibility(View.VISIBLE);
                }
            }
        };
        tab_follower.setOnClickListener(tabClickListener);
        tab_hash.setOnClickListener(tabClickListener);
        //  rank_tab;
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
