package com.dothome.inceleb.instamanager.Tab;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.dothome.inceleb.instamanager.R;
import com.dothome.inceleb.instamanager.UserInsight.BestFollwerActivity;
import com.dothome.inceleb.instamanager.UserInsight.BestPanActivity;
import com.dothome.inceleb.instamanager.UserInsight.GraphActivity;
import com.dothome.inceleb.instamanager.UserInsight.WorstFollwerActivity;

public class UserFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public UserFragment() {
        // Required empty public constructor
    }
    public static UserFragment newInstance() {
        UserFragment fragment = new UserFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_user, container, false);
        RelativeLayout rl_graph=(RelativeLayout) v.findViewById(R.id.list_graph);
        RelativeLayout rl_best_follower=(RelativeLayout) v.findViewById(R.id.list_best_follower);
        RelativeLayout rl_best_pan=(RelativeLayout) v.findViewById(R.id.list_best_pan);
        RelativeLayout rl_worst_follower=(RelativeLayout) v.findViewById(R.id.list_worst_follower);
        rl_graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), GraphActivity.class));
            }
        });
        rl_best_follower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), BestFollwerActivity.class));
            }
        });
        rl_best_pan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), BestPanActivity.class));
            }
        });
        rl_worst_follower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), WorstFollwerActivity.class));
            }
        });
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
