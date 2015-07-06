package com.yey.read.home.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yey.read.R;
import com.yey.read.common.activity.BaseActivity;
import com.yey.read.home.entity.Book;

import java.util.ArrayList;
import java.util.HashMap;

public class RecommendBookActivity extends BaseActivity{

    @ViewInject(R.id.lv_recommend_book) ListView listView;

    public ArrayList<Book> getBookRecommendsList() {
        return bookRecommendsList;
    }

    public void setBookRecommendsList(ArrayList<Book> bookRecommendsList) {
        this.bookRecommendsList = bookRecommendsList;
    }

    private ArrayList<Book> bookRecommendsList = new ArrayList<Book>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_book);
        ViewUtils.inject(this);
        Bundle bundle = this.getIntent().getExtras();
        bookRecommendsList = (ArrayList<Book>)bundle.getSerializable("1");
        //定义一个动态数组
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
        //在数组中存放数据
        for (Book book : bookRecommendsList) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImage",  imageLoader.loadImageSync(book.getCover()));//加入图片
            map.put("ItemTitle", book.getTitle());
            map.put("ItemAge", book.getAge());
            map.put("ItemReason", book.getReason());
            listItem.add(map);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(RecommendBookActivity.this, listItem,
                R.layout.item_recommend_book,
                new String[]{"ItemImage", "ItemTitle", "ItemAge", "ItemReason"},
                new int[]{R.id.ItemImage, R.id.ItemTitle, R.id.ItemAge, R.id.ItemReason}
        );
        simpleAdapter.setViewBinder(new SimpleAdapter.ViewBinder() {

            @Override
            public boolean setViewValue(View view, Object data,
                                        String textRepresentation) {
                if (view instanceof ImageView && data instanceof Bitmap) {
                    ImageView iv = (ImageView) view;
                    iv.setImageBitmap((Bitmap) data);
                    return true;
                }
                return false;
            }
        });

        listView.setAdapter(simpleAdapter);//为ListView绑定适配器

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("detailBook",bookRecommendsList.get(position));
                openActivity(BookDetailActivity.class,bundle);
            }
        });
    }


}
