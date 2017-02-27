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

public class NoticeFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public NoticeFragment() {
        // Required empty public constructor
    }
    public static NoticeFragment newInstance() {
        NoticeFragment fragment = new NoticeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_notice, container, false);
        RelativeLayout rl_graph=(RelativeLayout) v.findViewById(R.id.list_graph);
        RelativeLayout rl_best_like_notice=(RelativeLayout) v.findViewById(R.id.best_like_notice);
        RelativeLayout rl_best_comments_notice=(RelativeLayout) v.findViewById(R.id.best_comments_notice);
        RelativeLayout rl_worst_likes_notice=(RelativeLayout) v.findViewById(R.id.worst_likes_notice);
        RelativeLayout rl_best_hash=(RelativeLayout) v.findViewById(R.id.best_hash);
        rl_graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), com.dothome.inceleb.instamanager.NoticeInsight.GraphActivity.class));
            }
        });
        rl_best_like_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getContext(), .class));
            }
        });
        rl_best_comments_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getContext(), BestPanActivity.class));
            }
        });
        rl_worst_likes_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getContext(), WorstFollwerActivity.class));
            }
        });
        rl_best_hash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getContext(), WorstFollwerActivity.class));
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
