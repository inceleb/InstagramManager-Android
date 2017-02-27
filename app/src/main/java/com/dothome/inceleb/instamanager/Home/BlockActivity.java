package com.dothome.inceleb.instamanager.Home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dothome.inceleb.instamanager.R;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dothome.inceleb.instamanager.Model.Delete;
import com.dothome.inceleb.instamanager.Model.Follow;
import com.dothome.inceleb.instamanager.R;

import java.util.ArrayList;

public class BlockActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Delete> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_removed);

        list.add(new Delete("Account_id","님이 사진의 댓글을 삭제하였습니다.","작성했던 댓글 내용","지난주","test","test"));
        list.add(new Delete("Account_id","님이 사진의 댓글을 삭제하였습니다.","작성했던 댓글 내용","지난주","test","test"));
        list.add(new Delete("Account_id","님이 사진의 댓글을 삭제하였습니다.","작성했던 댓글 내용","지난주","test","test"));
        list.add(new Delete("Account_id","님이 사진의 댓글을 삭제하였습니다.","작성했던 댓글 내용","지난주","test","test"));
        list.add(new Delete("Account_id","님이 사진의 댓글을 삭제하였습니다.","작성했던 댓글 내용","지난주","test","test"));

        ListAdapter adapter=new ListAdapter();
        listView=(ListView)findViewById(R.id.list);
        listView.setAdapter(adapter);
    }

    public void onClickProfile(View view) {
        startActivity(new Intent(this,DetailActivity.class));
    }

    public class ListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final int pos = position;
            final Context context = parent.getContext();

            // "listview_item" Layout을 inflate하여 convertView 참조 획득.
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.listitem_remove, parent, false);
            }

            // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
            //CircleImageView iconImageView = (CircleImageView) convertView.findViewById(R.id.imageView1) ;
            TextView tv_message = (TextView) convertView.findViewById(R.id.list_message) ;
            TextView tv_comment = (TextView) convertView.findViewById(R.id.list_comment) ;
            TextView tv_date = (TextView) convertView.findViewById(R.id.list_date) ;
            Delete f = list.get(position);

            // 아이템 내 각 위젯에 데이터 반영
            SpannableString spannableStr = new SpannableString(f.id+" "+f.message);
            // spannableStr.setSpan(new StyleSpan(Typeface.BOLD), 0 ,f.id.length(), 0);

            spannableStr.setSpan(new ForegroundColorSpan(Color.parseColor("#849ef2")),0,f.id.length(),0);
            tv_message.setText(spannableStr);
            tv_comment.setText(f.comment);
            tv_date.setText(f.date);

            return convertView;
        }
    }

}

