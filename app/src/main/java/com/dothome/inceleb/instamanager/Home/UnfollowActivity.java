package com.dothome.inceleb.instamanager.Home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dothome.inceleb.instamanager.Model.Follow;
import com.dothome.inceleb.instamanager.R;

import java.util.ArrayList;

public class UnfollowActivity extends AppCompatActivity {


    ListView listView;
    ArrayList<Follow> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unfollow);

        list.add(new Follow("A","B",""));
        list.add(new Follow("AB","BC",""));

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
                convertView = inflater.inflate(R.layout.listitem_unfollow, parent, false);
            }

            // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
            //CircleImageView iconImageView = (CircleImageView) convertView.findViewById(R.id.imageView1) ;
            TextView tv_name = (TextView) convertView.findViewById(R.id.list_name) ;
            TextView tv_id = (TextView) convertView.findViewById(R.id.list_id) ;
            Follow f = list.get(position);

            // 아이템 내 각 위젯에 데이터 반영
            tv_name.setText(f.name);
            tv_id.setText(f.id);

            return convertView;
        }
    }

}
