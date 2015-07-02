package com.yey.read.home.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.yey.read.R;
import com.yey.read.common.activity.BaseActivity;
import com.yey.read.home.entity.Book;
import com.yey.read.home.viewmodel.HomeViewModel;
import com.yey.read.net.OnAppRequestListenerFriend;

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
        new HomeViewModel().getBookRecommends(-1, new OnAppRequestListenerFriend() {
            @Override
            public void onAppRequestFriend(int code, String message, Object obj, int nextid) {
                bookRecommendsList = (ArrayList<Book>) obj;

                //定义一个动态数组
                ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
                //在数组中存放数据
                for (Book book : bookRecommendsList) {
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    map.put("ItemImage",  ImageLoader.getInstance().loadImageSync(book.getCover()));//加入图片
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
            }
        });


    }
}
