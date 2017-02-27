package com.dothome.inceleb.instamanager.Lib.instagram;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.dothome.inceleb.instamanager.Lib.instagram.util.Cons;
//Android app for Instagram account analysis

public class Instagram {
	private Context mContext;

	private InstagramAuthListener mListener;
	private InstagramSession mSession;

	private final String TAG="Instagram";
	

	public Instagram(Context context) {
		mContext		= context;

		mSession		= new InstagramSession(context);

	}
	public void resetSession() {
		mSession.reset();
		
		//mWebView.clearCache();
	}
	public void authorize(InstagramAuthListener listener) {
		mListener = listener;
	}

	public InstagramSession getSession() {
		return mSession;
	}

	public void retreiveAccessToken(String code) {
		Log.d(TAG,code);
		new AccessTokenTask(code).execute();
	}

	public class AccessTokenTask extends AsyncTask<URL, Integer, Long> {		
		ProgressDialog progressDlg;
		InstagramUser user;
		String code;
		
		public AccessTokenTask(String code) {
			this.code		= code;
			progressDlg 	= new ProgressDialog(mContext);
			progressDlg.setMessage("Getting access token...");			
		}
		
		protected void onCancelled() {
			progressDlg.cancel();
		}
		
    	protected void onPreExecute() {
    		progressDlg.show();
    	}
    
        protected Long doInBackground(URL... urls) {         
            long result = 0;
            
            try {
    			List<NameValuePair> params = new ArrayList<NameValuePair>(1);
    			
    			params.add(new BasicNameValuePair("code", 		code));

    			InstagramRequest request	= new InstagramRequest();
    			String response				= request.get(Cons.SIGN_URL, params);
    			
    			if (!response.equals("")) {
    				JSONObject jsonObj 	= (JSONObject) new JSONTokener(response).nextValue(); 		        
    				JSONObject jsonUser	= jsonObj.getJSONObject("user");
    				
    				user				= new InstagramUser();

    				user.accessToken	= jsonObj.getString("access_token");
    				user.id				= jsonUser.getString("id");
    				user.username		= jsonUser.getString("username");
    				user.fullName		= jsonUser.getString("full_name");
    				user.profilPicture	= jsonUser.getString("profile_picture");

    			}    		            
    		} catch (Exception e) { 
    			e.printStackTrace();
    		}
            
            return result;
        }

        protected void onProgressUpdate(Integer... progress) {              	
        }

        protected void onPostExecute(Long result) {        	
        	progressDlg.dismiss();
        	
        	if (user != null) {
        		mSession.store(user);
        		
        		mListener.onSuccess(user);
        	} else {
        		mListener.onError("Failed to get access token");
        	}
        }                
    }
	
	public interface InstagramAuthListener {
		public abstract void onSuccess(InstagramUser user);
		public abstract void onError(String error);
		public abstract void onCancel();
	}
}