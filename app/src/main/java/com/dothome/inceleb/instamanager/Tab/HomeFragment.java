package com.dothome.inceleb.instamanager.Tab;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.dothome.inceleb.instamanager.Home.FollowActivity;
import com.dothome.inceleb.instamanager.Home.FollowerActivity;
import com.dothome.inceleb.instamanager.Lib.instagram.InstagramRequest;
import com.dothome.inceleb.instamanager.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.dothome.inceleb.instamanager.Tab.HomeActivity.insta_user;

public class HomeFragment extends Fragment {

    private ArrayList<JSONObject> followList = new ArrayList<JSONObject>();
    private ArrayList<JSONObject> followedList = new ArrayList<JSONObject>();
    private ArrayList<JSONObject> unfollowList = new ArrayList<JSONObject>();
    private ArrayList<JSONObject> onlyfollowList = new ArrayList<JSONObject>();
    private int countCallback=0;

    private OnFragmentInteractionListener mListener;


    TextView tv_account_id=null;
    TextView tv_account_name;
    ImageView iv_profile_pic;
    View iv_profile_pic_gra;
    View iv_profile_pic_back;
    TextView tv_label_follow;
    TextView tv_count_follow;
    TextView tv_label_followed_by;
    TextView tv_count_followed_by;
    TextView tv_count_new_follwed;
    TextView tv_count_new_unfollowed;
    TextView tv_sum_followed;
    TextView tv_sum_unfollowed;
    TextView tv_sum_onlyfollow;
    TextView tv_sum_unfollow;


    public HomeFragment(){}

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_home, container, false);
        tv_account_id=(TextView)v.findViewById(R.id.account_id);
        tv_account_name=(TextView)v.findViewById(R.id.account_name);
        iv_profile_pic=(ImageView)v.findViewById(R.id.profile_pic);
        iv_profile_pic_gra=(View)v.findViewById(R.id.profile_pic_gra);
        iv_profile_pic_back=(View)v.findViewById(R.id.profile_pic_back);
        tv_count_follow=(TextView)v.findViewById(R.id.count_follows);
        tv_count_followed_by=(TextView)v.findViewById(R.id.count_followed_by);
        tv_label_follow=(TextView)v.findViewById(R.id.label_followes);
        tv_label_followed_by=(TextView)v.findViewById(R.id.label_followed_by);
        tv_count_new_follwed=(TextView)v.findViewById(R.id.new_followed);
        tv_count_new_unfollowed=(TextView)v.findViewById(R.id.new_onfollowed);
        tv_sum_followed=(TextView)v.findViewById(R.id.followed_sum);
        tv_sum_unfollowed=(TextView)v.findViewById(R.id.unfollowed_sum);
        tv_sum_onlyfollow=(TextView)v.findViewById(R.id.onlyfollow_sum);
        tv_sum_unfollow=(TextView)v.findViewById(R.id.unfollow_sum);

        tv_account_id.setText(insta_user.username);
        tv_account_name.setText(insta_user.fullName);




        //대표색상 추출
        Glide.with(this).load(insta_user.profilPicture).asBitmap().into(new BitmapImageViewTarget(iv_profile_pic) {
            @Override
            public void onResourceReady(Bitmap  bitmap, GlideAnimation anim) {
                super.onResourceReady(bitmap, anim);
                if (bitmap != null ) {
                    Palette palette =Palette.from(bitmap).generate();
                    int mutedDark = palette.getDarkMutedColor(0x000000);
                    Log.d("pick_color",mutedDark+"!");

                    GradientDrawable gradient = (GradientDrawable)iv_profile_pic_gra.getBackground();
                    int startColor = mutedDark;
                    int endColor = Color.TRANSPARENT;
                    iv_profile_pic_back.setBackgroundColor(mutedDark);
                    gradient.setColors(new int[]{ startColor, endColor });
                    iv_profile_pic_gra.setBackgroundDrawable(gradient);
//.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
                    RelativeLayout.LayoutParams lp= new RelativeLayout.LayoutParams(iv_profile_pic.getHeight(),iv_profile_pic.getHeight());
                    lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                    iv_profile_pic.setLayoutParams(lp);

                }
            }
        });





        //팔로우, 팔로워 데이터 로드
        InstagramRequest iRequest=new InstagramRequest(insta_user.accessToken);
        iRequest.createRequest("GET", "users/self/", null, new InstagramRequest.InstagramRequestListener() {
            @Override
            public void onSuccess(String response) {
                Log.d("responseSuccess",response);
                try  {
                    JSONObject obj = new JSONObject(response);
                    obj=obj.getJSONObject("data");
                    obj=obj.getJSONObject("counts");
                    String count_follows=obj.getString("follows");
                    String count_followed_by=obj.getString("followed_by");
                    tv_count_follow.setText(count_follows+"");
                    tv_count_followed_by.setText(count_followed_by+"");
                    tv_sum_followed.setText("합계 : "+count_followed_by);
                    tv_sum_unfollowed.setText("합계 : "+ 0);
                    tv_count_new_follwed.setText(0+"");
                    tv_count_new_unfollowed.setText(0+"");
                }catch(JSONException e){

                }
            }

            @Override
            public void onError(String error) {
                Log.d("response",error);
            }
        });

        //팔로우,팔로워 클릭이벤트
        View.OnClickListener onClickGoFollowerListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), FollowerActivity.class));

            }
        };
        View.OnClickListener onClickGoFollowListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), FollowActivity.class));
            }
        };
        tv_label_follow.setOnClickListener(onClickGoFollowListener);
        tv_count_follow.setOnClickListener(onClickGoFollowListener);
        tv_label_followed_by.setOnClickListener(onClickGoFollowerListener);
        tv_count_followed_by.setOnClickListener(onClickGoFollowerListener);



        //팔로우 리스트
        iRequest.createRequest("GET", "/users/self/follows/", null, new InstagramRequest.InstagramRequestListener() {
            @Override
            public void onSuccess(String response) {
                Log.d("follow_list",response);
                try  {
                    JSONObject obj = new JSONObject(response);
                    JSONArray arr=obj.getJSONArray("data");

                    for(int i=0;i<arr.length();i++){
                        followList.add( arr.getJSONObject(i) );
                    }
                    countCallback++;
                    calRelationship();

                }catch(JSONException e){
                }
            }
            @Override
            public void onError(String error) {
                Log.d("response",error);
            }
        });

        /*
        //팔로워 리스트
        iRequest.createRequest("GET", "/users/self/followed-by/", null, new InstagramRequest.InstagramRequestListener() {
            @Override
            public void onSuccess(String response) {
                Log.d("responseSuccess",response);
                try  {
                    JSONObject obj = new JSONObject(response);
                    JSONArray arr=obj.getJSONArray("data");
                    for(int i=0;i<arr.length();i++){
                        followedList.add( arr.getJSONObject(i) );
                    }
                    countCallback++;
                    calRelationship();

                }catch(JSONException e){
                }
            }
            @Override
            public void onError(String error) {
                Log.d("response",error);
            }
        });*/





        return v;

    }



    private void calRelationship(){
        if(countCallback<2)return;
        try {
            for(int i=0;i<followList.size();i++){

                boolean isSearch=false;
                for(int j=0;j<followedList.size();j++){
                    if( followList.get(i).getString("id").equals(followedList.get(j).getString("id")) ){
                        isSearch=true;
                        break;
                    }
                }
                if(!isSearch)
                    unfollowList.add(followList.get(i));
            }
/*
            for(int i=0;i<followedList.size();i++){
                boolean isSearch=false;
                for(int j=0;j<followList.size();j++){
                    if( followedList.get(i).getString("id").equals(followList.get(j).getString("id")) ){
                        isSearch=true;
                        break;
                    }
                }
                if(!isSearch)
                    onlyfollowList.add(followList.get(i));
            }*/

          //  tv_sum_onlyfollow.setText("합계 :"+onlyfollowList.size());
            tv_sum_unfollow.setText("합계 :"+unfollowList.size());
            Log.d("TEST1",followedList.size()+"");
            Log.d("TEST2",unfollowList.size()+"");
            Log.d("TEST3",onlyfollowList.size()+"");
            Log.d("TEST4",followList.size()+"");

        }catch (JSONException e){

        }

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
