package com.yey.read.home.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.yey.read.R;
import com.yey.read.common.fragment.BaseFragment;
import com.yey.read.home.activity.RecommendBookActivity;
import com.yey.read.home.entity.Book;
import com.yey.read.home.entity.BookList;
import com.yey.read.home.viewmodel.HomeViewModel;
import com.yey.read.loading.adapter.ViewPagerAdapter;
import com.yey.read.net.OnAppRequestListener;
import com.yey.read.net.OnAppRequestListenerFriend;
import com.yey.read.util.UtilsLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunnie on 15/6/23.
 */
public class HomeFragment extends BaseFragment implements OnClickListener,OnPageChangeListener {

    @ViewInject(R.id.more_recommend_book) TextView tv_moreRecommendBook ;
    @ViewInject(R.id.to_booklist) TextView tv_toBookList;
    @ViewInject(R.id.cover)ImageView iv_bookCover;
    @ViewInject(R.id.theme)TextView tv_theme;
    @ViewInject(R.id.reason)TextView tv_reason;
    @ViewInject(R.id.viewpager)ViewPager vp_bookCover;

    private ViewPagerAdapter vpAdapter;
    private List<View> views = new ArrayList<View>();
    private HomeViewModel homeViewModel;

    //滑动底部小点图片
    private ImageView[] dots ;
    //记录当前滑动位置
    private int currentIndex;
    //推荐绘本
    private ArrayList<Book> bookRecommendsList = new ArrayList<Book>();
    //个性书单
    private BookList bookList = new BookList();

    public static final String TAG = "HomeFragement";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        UtilsLog.i(TAG, "into onCreateView");
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        ViewUtils.inject(this, view);
        tv_moreRecommendBook.setOnClickListener(this);
        homeViewModel = new HomeViewModel();
        homeViewModel.getBookRecommends(-1, new OnAppRequestListenerFriend() {
            @Override
            public void onAppRequestFriend(int code, String message, Object obj, int nextid) {
                bookRecommendsList = (ArrayList<Book>) obj;
                //初始化引导图片列表
                for (int i=0; i < 4; i++) {
                    ImageView iv = new ImageView(HomeFragment.this.getActivity());
                    ImageLoader.getInstance().displayImage(bookRecommendsList.get(i).getCover(), iv);
                    iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    views.add(iv);
                }
                //初始化adapter
                vp_bookCover = (ViewPager) findViewById(R.id.viewpager);
                vpAdapter = new ViewPagerAdapter(views);
                vp_bookCover.setAdapter(vpAdapter);
                //绑定回调
                //初始化底部小点
                initDots();
            }
        });
        homeViewModel.getListDetail(-1, new OnAppRequestListener() {
            @Override
            public void onAppRequest(int code, String message, Object obj) {
                bookList = (BookList) obj;
                ImageLoader.getInstance().displayImage("http://imgsrc.baidu.com/baike/pic/item/3ac79f3df8dcd100796032fd738b4710b8122f6a.jpg", iv_bookCover);
                iv_bookCover.setScaleType(ImageView.ScaleType.CENTER_CROP);
                tv_toBookList.setText(bookList.getFeature());
                tv_theme.setText(bookList.getTitle());
                tv_reason.setText(bookList.getReason());
            }
        });
        vp_bookCover.setOnPageChangeListener(this);
        return view;


    }

    private void initDots() {
        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);

        dots = new ImageView[bookRecommendsList.size()];

        //循环取得小点图片
        for (int i = 0; i < bookRecommendsList.size(); i++) {
            dots[i] = (ImageView) ll.getChildAt(i);
            dots[i].setSelected(false);//都设为灰色
            dots[i].setOnClickListener(this);
            dots[i].setTag(i);//设置位置tag，方便取出与当前位置对应
        }

        currentIndex = 0;
        dots[currentIndex].setSelected(true);//设置为黄色，即选中状态
    }

    /**
     *设置当前的引导页
     */
    private void setCurView(int position)
    {
        if (position < 0 || position >= bookRecommendsList.size()) {
            return;
        }

        vp_bookCover.setCurrentItem(position);
    }

    /**
     *这只当前引导小点的选中
     */
    private void setCurDot(int positon)
    {
        if (positon < 0 || positon > bookRecommendsList.size() || currentIndex == positon) {
            return;
        }

        dots[positon].setSelected(true);
        dots[currentIndex].setSelected(false);

        currentIndex = positon;
    }

    //当滑动状态改变时调用
    @Override
    public void onPageScrollStateChanged(int arg0) {
        // TODO Auto-generated method stub

    }

    //当当前页面被滑动时调用
    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        // TODO Auto-generated method stub

    }

    //当新的页面被选中时调用
    @Override
    public void onPageSelected(int arg0) {
        //设置底部小点选中状态
        setCurDot(arg0);
    }

    @Override
    public void onClick(View v) {
        if (v == vp_bookCover) {
            int position = (Integer)v.getTag();
            setCurView(position);
            setCurDot(position);
        } else {
            Bundle bundle = new Bundle();
            bundle.putSerializable("1",bookRecommendsList);
            startAnimActivity(RecommendBookActivity.class,bundle);
        }

    }




}
