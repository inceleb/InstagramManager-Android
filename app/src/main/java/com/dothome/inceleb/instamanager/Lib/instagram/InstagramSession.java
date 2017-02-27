package com.dothome.inceleb.instamanager.Lib.instagram;


import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.Context;

import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

public class InstagramSession {
	private Context mContext;
	private SharedPreferences mSharedPref;
	
	private static final String SHARED = "Instagram_Preferences";
	private static final String USERID	= "userid";
	private static final String USERNAME = "username";
	private static final String FULLNAME = "fullname";
	private static final String PROFILPIC = "profilpic";
	private static final String FOLLOW = "follow";
	private static final String FOLLOWED = "followed";
	private static final String ACCESS_TOKEN = "access_token";
	private static final String IS_REGIST = "is_regist";
	
	public InstagramSession(Context context) {
		mContext	= context;
		mSharedPref = context.getSharedPreferences(SHARED, Context.MODE_PRIVATE);
	}

	public void regist(){
		Editor editor = mSharedPref.edit();
		editor.putBoolean(IS_REGIST, true);
		editor.commit();
	}
	public void store(InstagramUser user) {
		Editor editor = mSharedPref.edit();
		
		editor.putString(ACCESS_TOKEN,  user.accessToken);
		editor.putString(USERID, 		user.id);
		editor.putString(USERNAME, 		user.username);
		editor.putString(FULLNAME, 		user.fullName);
		editor.putString(PROFILPIC, 	user.profilPicture);

		
		editor.commit();
	}
	public void store(int follow,int followed){
		Editor editor = mSharedPref.edit();
		editor.putInt(FOLLOW, follow);
		editor.putInt(FOLLOWED, followed);
		editor.commit();
	}

	public void reset() {
		Editor editor = mSharedPref.edit();
		
		editor.putString(ACCESS_TOKEN, 	"");
		editor.putString(USERID, 		"");
		editor.putString(USERNAME, 		"");
		editor.putString(FULLNAME, 		"");
		editor.putString(PROFILPIC, 	"");
		editor.putInt(FOLLOW,0);
		editor.putInt(FOLLOWED,0);
		
		editor.commit();
		
		CookieSyncManager.createInstance(mContext);
		
		CookieManager cookieManager = CookieManager.getInstance();
		cookieManager.removeAllCookie();
	}

	public InstagramUser getUser() {
		if (mSharedPref.getString(ACCESS_TOKEN, "").equals("")) {
			return null;
		}
		
		InstagramUser user 	= new InstagramUser();
		
		user.id				= mSharedPref.getString(USERID, "");
		user.username		= mSharedPref.getString(USERNAME, "");
		user.fullName		= mSharedPref.getString(FULLNAME, "");
		user.profilPicture	= mSharedPref.getString(PROFILPIC, "");
		user.accessToken	= mSharedPref.getString(ACCESS_TOKEN, "");
		user.isRegist		= mSharedPref.getBoolean(IS_REGIST, false);

		return user;
	}
	public int[] getCounts() {
		if (mSharedPref.getString(ACCESS_TOKEN, "").equals("")) {
			return null;
		}

		int counts[]=new int[2];
		counts[0]		= mSharedPref.getInt(FOLLOW, 0);
		counts[1]		= mSharedPref.getInt(FOLLOWED, 0);

		return counts;
	}


	public String getAccessToken() {
		return mSharedPref.getString(ACCESS_TOKEN, "");
	}
	

	public boolean isActive() {
		return (mSharedPref.getString(ACCESS_TOKEN, "").equals("")) ? false : true;
	}
}