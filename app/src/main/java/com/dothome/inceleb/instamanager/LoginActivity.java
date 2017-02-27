package com.dothome.inceleb.instamanager;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dothome.inceleb.instamanager.Tab.HomeActivity;
import com.dothome.inceleb.instamanager.Lib.instagram.*;
import com.dothome.inceleb.instamanager.Lib.instagram.util.Cons;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;


@SuppressLint({ "NewApi", "SetJavaScriptEnabled" })
public class LoginActivity extends AppCompatActivity {



    private ArrayList<String> interestList;
    private ProgressDialog mSpinner;
    private WebView mWebView;
    private LinearLayout mInterestView;

    private String mAuthUrl;


    static final FrameLayout.LayoutParams FILL = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT);

    static final String TAG = "LOGIN_ACTIVITY";

    private InstagramSession mInstagramSession;
    private Instagram mInstagram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInstagram   = new Instagram(this);
        mInstagramSession   = mInstagram.getSession();

        interestList=new ArrayList<String>();
        mAuthUrl= Cons.AUTH_URL + "client_id=" + Cons.CLIENT_ID + "&redirect_uri=" + Cons.REDIRECT_URI + "&response_type=code"+"&scope="+Cons.SCOPE;

        setContentView(R.layout.activity_login);
        mInterestView=(LinearLayout)findViewById(R.id.interestview);

        if (mInstagramSession.isActive()) {
            Next();
        } else {
            mInstagram.authorize(mAuthListener);
            setUpWebView();
        }
    }

    private void setUpWebView() {
        mSpinner = new ProgressDialog(this);

        mSpinner.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mSpinner.setMessage("Loading...");

        mWebView = new WebView(this);

        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setHorizontalScrollBarEnabled(false);
        mWebView.setWebViewClient(new InstagramWebViewClient());
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(mAuthUrl);
        mWebView.setLayoutParams(FILL);

        WebSettings webSettings = mWebView.getSettings();

        webSettings.setSavePassword(false);
        webSettings.setSaveFormData(false);
        clearCache();


        addContentView(mWebView,FILL);
    }
    public void clearCache() {

        mWebView.clearCache(true);
        mWebView.clearHistory();
        mWebView.clearFormData();
    }
    private void showToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }
    private void Next(){
        if(mInstagramSession.getUser().isRegist){
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        }else{
            mInterestView.setVisibility(View.VISIBLE);
        }
    }

    private Instagram.InstagramAuthListener mAuthListener = new Instagram.InstagramAuthListener() {
        @Override
        public void onSuccess(InstagramUser user) {
            Next();

        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(String error) {
            showToast(error);
        }
    };

    public void onClickbtnJoin(View view) {
        String deviceID = android.provider.Settings.Secure.getString(getBaseContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        String interest="";

        for (String ip:interestList) {
            interest+=ip+"|";
        }
        InstagramRequest request=new InstagramRequest(mInstagramSession.getAccessToken());

        List<NameValuePair> params = new ArrayList<NameValuePair>(2);

        params.add(new BasicNameValuePair("device_id",deviceID));
        params.add(new BasicNameValuePair("interest",interest));
        params.add(new BasicNameValuePair("insta_id",mInstagramSession.getUser().id));


        request.createRequestLocal("POST",Cons.INTEREST_URL,params, new InstagramRequest.InstagramRequestListener(){
            @Override
            public void onSuccess(String response) {
                if(response.equals("OK")){
                    mInstagramSession.regist();
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    finish();
                }else{

                    mInstagram.resetSession();
                    startActivity(new Intent(LoginActivity.this, LoginActivity.class));
                    finish();
                }
            }

            @Override
            public void onError(String error) {
                mInstagram.resetSession();
                startActivity(new Intent(LoginActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    public void onClickCheckbox(View view) {
        String tag=view.getTag().toString();

        for(int i=0;i<interestList.size();i++){
            if(interestList.get(i).equals(tag)){
                interestList.remove(i);
                return;
            }
        }
        interestList.add(tag);
    }

    private class InstagramWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d(TAG, "Redirecting URL " + url);

            if (url.startsWith(Cons.REDIRECT_URI)) {
                if (url.contains("code")) {
                    //성공
                    String temp[] = url.split("=");

                    Log.d(TAG,url);
                    mInstagram.retreiveAccessToken(temp[1]);
                } else if (url.contains("error")) {
                    String temp[] = url.split("=");
                    Log.d(TAG,temp[temp.length-1]);
                    //에러
                } else{

                }
                mWebView.setVisibility(View.GONE);
                return true;
            }

            return false;
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);

            //에러
            mWebView.setVisibility(View.GONE);

            Log.d(TAG, "Page error: " + description);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

            mSpinner.show();

            Log.d(TAG, "Loading URL: " + url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            mSpinner.dismiss();
        }

    }

}
