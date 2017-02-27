package com.dothome.inceleb.instamanager.Home;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.dothome.inceleb.instamanager.Model.Follow;
import com.dothome.inceleb.instamanager.R;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    GridView gridView;
    ArrayList<String> list=new ArrayList<>();

    LinearLayout favoriteLL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        list.add("sfd");
        list.add("sfd");
        list.add("sfd");
        list.add("sfd");
        list.add("sfd");
        list.add("sfd");
        list.add("sfd");
        list.add("sfd");
        list.add("sfd");
        list.add("sfd");
        list.add("sfd");
        list.add("sfd");
        list.add("sfd");
        list.add("sfd");
        list.add("sfd");
        list.add("sfd");
        list.add("sfd");

        ListAdapter adapter=new ListAdapter();
        gridView=(GridView) findViewById(R.id.gridview);
        gridView.setAdapter(adapter);

        favoriteLL=(LinearLayout)findViewById(R.id.btm);



    }

    public void onClickFav(View view) {
        favoriteLL.setVisibility(View.VISIBLE);
    }

    public void onClickCan(View view) {
        favoriteLL.setVisibility(View.GONE);
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
            SquareImageView imageView;
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                imageView = new SquareImageView(context);
                imageView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(0, 0,0, 0);
            } else {
                imageView = (SquareImageView) convertView;
            }

            imageView.setImageResource(R.drawable.test);
            return imageView;

        }
    }
    public class SquareImageView extends ImageView {

        public SquareImageView(Context context) {
            super(context);
        }

        public SquareImageView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public SquareImageView(Context context, AttributeSet attributeSet, int defStyle) {
            super(context, attributeSet, defStyle);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);

            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
            } else {
                setMeasuredDimension(getMeasuredHeight(), getMeasuredHeight());
            }
        }
    }


}
