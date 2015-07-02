package com.yey.read.home.viewmodel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yey.read.common.AppConstants;
import com.yey.read.home.entity.Book;
import com.yey.read.home.entity.BookList;
import com.yey.read.net.AppServer;
import com.yey.read.net.OnAppRequestListener;
import com.yey.read.net.OnAppRequestListenerFriend;
import com.yey.read.net.OnSendRequestListener;
import com.yey.read.net.OnSendRequestListenerFriend;

import java.util.HashMap;
import java.util.List;

/**
 * Created by sunnie on 15/6/25.
 */
public class HomeViewModel{

    public void getBookRecommends(final int nextId,final OnAppRequestListenerFriend listener){
        HashMap<String, String> params = new HashMap<String, String>();
        params.put(AppConstants.PARAM_FETCHNUM, "4");
        params.put(AppConstants.PARAM_NEXTID, "-1");
        params.put(AppConstants.PARAM_KEY, "");
        String url= "http://192.168.0.82:8082/book/getBookRecommends";
        AppServer.getInstance().sendVolleyRequestStringFriend(params, url, new OnSendRequestListenerFriend() {
            @Override
            public void onSendRequestfriend(int code, String message, String result, int nextid) {
                Object obj;
                if (code == 0) {
                    Gson gson = new Gson();
                    obj = gson.fromJson(result, new TypeToken<List<Book>>() {
                    }.getType());
                } else {
                    obj = result;
                }
                if (listener != null) {
                    listener.onAppRequestFriend(code, message, obj, nextId);
                }
            }
        });
    }

    public void getListDetail(int nextId,final OnAppRequestListener listener){
        HashMap<String, String> params = new HashMap<String, String>();
        params.put(AppConstants.PARAM_USERID, "1082280");
        params.put(AppConstants.PARAM_KEY, "");
        String url= "http://192.168.0.82:8082/book/getListDetail";
        AppServer.getInstance().sendVolleyRequestString(params, url, new OnSendRequestListener() {
            @Override
            public void onSendRequest(int code, String message, String result) {
                Object obj;
                if (code == 0) {
                    Gson gson = new Gson();
                    obj = gson.fromJson(result, BookList.class);
                } else {
                    obj = result;
                }
                if (listener != null) {
                    listener.onAppRequest(code, message, obj);
                }
            }
        });
    }

    public void getBookDetail(int nextId,final OnAppRequestListenerFriend listener){
        HashMap<String, String> params = new HashMap<String, String>();
        params.put(AppConstants.PARAM_FETCHNUM, "10");
        params.put(AppConstants.PARAM_NEXTID, "-1");
        params.put(AppConstants.PARAM_KEY, "");
        String url= "192.168.0.82:8082/book/getBookRecommends";
        AppServer.getInstance().sendVolleyRequestStringFriend(params, url, new OnSendRequestListenerFriend() {
            @Override
            public void onSendRequestfriend(int code, String message, String result, int nextid) {
                Object obj;
                if (code == 0) {
                    obj = result;
                } else {
                    obj = result;
                }
                if (listener != null) {
                    listener.onAppRequestFriend(code, message, obj, nextid);
                }
            }
        });
    }
}
