package com.yey.read.net;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.litesuits.http.LiteHttpClient;
import com.litesuits.http.async.HttpAsyncExecutor;
import com.litesuits.http.data.HttpStatus;
import com.litesuits.http.data.NameValuePair;
import com.litesuits.http.exception.HttpClientException;
import com.litesuits.http.exception.HttpClientException.ClientException;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.exception.HttpNetException;
import com.litesuits.http.exception.HttpNetException.NetException;
import com.litesuits.http.exception.HttpServerException;
import com.litesuits.http.exception.HttpServerException.ServerException;
import com.litesuits.http.request.Request;
import com.litesuits.http.request.content.UrlEncodedFormBody;
import com.litesuits.http.response.handler.HttpExceptionHandler;
import com.litesuits.http.response.handler.HttpResponseHandler;
import com.yey.read.common.AppContext;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by sunnie on 15/6/3.
 */
public class AppServer {

    public static LiteHttpClient liteclient;
    public static final String TAG_CODE = "code";
    public static final String TAG_INFO = "info";
    public static final String TAG_RESULT = "result";
    public static final String TAG_NEXTID = "nextid";

    public static final int REQUEST_SUCCESS = 0;
    public static final int REQUEST_FAILED = -1;
    public static final int REQUEST_ERROR = 1;
    public static final int REQUEST_NO_NETWORK = -2;
    public static final int REQUEST_LOGIN_ERROR_ACCOUNT = 1; //账号不存在
    public static final int REQUEST_LOGIN_ERROR_POSSWORD = 2; //密码不正确
    public static final int REQUEST_CLIENT_ERROR = 111; //客户端异常
    public static final int REQUEST_NETWORK_ERROR = 112; //无可用网络
    public static final int REQUEST_UNREACHABLE_ERROR = 113; //服务器不可访问(或网络不稳定)
    public static final int REQUEST_NETWORKDISABLED_ERROR = 114; //网络类型被设置禁用

    private final static String TAG = "AppServer";
    static final String SERVER_URL ="http://t.kmapp.zgyey.com/";
    static final String SERVER_GET_MAIN = SERVER_URL+"pub/getMainGateway";

    private static AppServer mInstance;
    public static AppServer getInstance(){
        if(mInstance == null){
            mInstance = new AppServer();
        }
        if(liteclient ==null){
            liteclient = LiteHttpClient.newApacheHttpClient(AppContext.getInstance());
        }
        return mInstance;
    }

    /**
     * litehttp
     * @param map
     * @param url
     */
    public void sendVolleyRequestString(final HashMap<String, String> map, final String url, final OnSendRequestListener listener){
        final long start1 = System.currentTimeMillis();
        HttpAsyncExecutor asyncExcutor = HttpAsyncExecutor.newInstance(liteclient);
        Request req = new Request(url);
        System.out.println("url----"+url);
        req.setMethod(com.litesuits.http.request.param.HttpMethod.Post);
        LinkedList<NameValuePair> pList = new LinkedList<NameValuePair>();
        for (Map.Entry<String, String> entry : map.entrySet()) {//构建表单字段内容
            pList.add(new NameValuePair(entry.getKey(), entry.getValue()));
        }
        System.out.println("url----"+url+"??"+pList.toString());
        req.setHttpBody(new UrlEncodedFormBody(pList));
        asyncExcutor.execute(req, new HttpResponseHandler() {

            @Override
            protected void onFailure(com.litesuits.http.response.Response res,
                                     HttpException e) {
                final String ress = res.toString();
                new HttpExceptionHandler() {
                    @Override
                    protected void onClientException(HttpClientException e, ClientException type) {
                        if(listener != null){
                            listener.onSendRequest(REQUEST_CLIENT_ERROR, e.toString()+"", ress);
                        }
                    }

                    @Override
                    protected void onNetException(HttpNetException e, NetException type) {
                        if (type == NetException.NetworkError) {
                            if(listener != null){
                                listener.onSendRequest(REQUEST_NETWORK_ERROR, e.toString()+"", ress);
                            }
                        } else if (type == NetException.UnReachable) {
                            if(listener != null){
                                listener.onSendRequest(REQUEST_UNREACHABLE_ERROR, e.toString()+"", ress);
                            }
                        } else if (type == NetException.NetworkDisabled) {
                            if(listener != null){
                                listener.onSendRequest(REQUEST_NETWORKDISABLED_ERROR, e.toString()+"", ress);
                            }
                        }
                    }

                    @Override
                    protected void onServerException(HttpServerException e, ServerException type, HttpStatus status) {

                    }

                }.handleException(e);


            }

            @Override
            protected void onSuccess(com.litesuits.http.response.Response res,
                                     HttpStatus status, NameValuePair[] headers) {
                long end1 = System.currentTimeMillis();
                System.out.println("使用litehttp共用时间　" + (end1 - start1) + "ms");
                int code = REQUEST_NO_NETWORK;
                String message = "请求服务失败";
                String result = null;
                try {
                    String resString=res.getString();
                    JSONObject jObj = new JSONObject(res.getString());
                    if(!jObj.isNull(TAG_CODE)){
                        code = Integer.valueOf(jObj.getString(TAG_CODE));
                    }
                    if(!jObj.isNull(TAG_INFO)){
                        message = jObj.getString(TAG_INFO);
                    }if(!jObj.isNull(TAG_RESULT)){
                        result = jObj.getString(TAG_RESULT);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

                if(listener != null){
                    listener.onSendRequest(code, message, result);
                }

            }


        });
    }

    /**
     * 分页
     * litehttp
     * @param map
     * @param url
     */
    public void sendVolleyRequestStringFriend(final HashMap<String, String> map, final String url, final OnSendRequestListenerFriend listener){
        final long start1 = System.currentTimeMillis();
        HttpAsyncExecutor asyncExcutor = HttpAsyncExecutor.newInstance(liteclient);
        Request req = new Request(url);
        System.out.println("url----"+url);
        req.setMethod(com.litesuits.http.request.param.HttpMethod.Post);
        LinkedList<NameValuePair> pList = new LinkedList<NameValuePair>();
        for (Map.Entry<String, String> entry : map.entrySet()) {//构建表单字段内容
            pList.add(new NameValuePair(entry.getKey(), entry.getValue()));
        }
        System.out.println("url----"+url+"??"+pList.toString());
        req.setHttpBody(new UrlEncodedFormBody(pList));
        asyncExcutor.execute(req, new HttpResponseHandler() {
            @Override
            protected void onSuccess(com.litesuits.http.response.Response res,
                                     HttpStatus status, NameValuePair[] headers) {
                long end1 = System.currentTimeMillis();
                System.out.println("使用litehttp共用时间　" + (end1 - start1) + "ms");
                int code = REQUEST_NO_NETWORK;
                String message = "请求服务失败";
                String result = null;
                int nextid = -1;
                try {
                    String resString=res.getString();
                    JSONObject jObj = new JSONObject(res.getString());
                    if(!jObj.isNull(TAG_CODE)){
                        code = Integer.valueOf(jObj.getString(TAG_CODE));
                    }
                    if(!jObj.isNull(TAG_INFO)){
                        message = jObj.getString(TAG_INFO);
                    }
                    if(!jObj.isNull(TAG_RESULT)){
                        result = jObj.getString(TAG_RESULT);
                    }
                    if(!jObj.isNull(TAG_NEXTID)){
                        nextid = Integer.valueOf(jObj.getString(TAG_NEXTID));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

                if(listener != null){
                    listener.onSendRequestfriend(code, message, result, nextid);
                }

            }

            @Override
            protected void onFailure(com.litesuits.http.response.Response res,
                                     HttpException e) {
                final String ress = res.toString();
                new HttpExceptionHandler() {
                    @Override
                    protected void onClientException(HttpClientException e, ClientException type) {
                        if(listener != null){
                            listener.onSendRequestfriend(REQUEST_CLIENT_ERROR, e.toString()+"", ress, -1);
                        }
                    }

                    @Override
                    protected void onNetException(HttpNetException e, NetException type) {
                        if (type == NetException.NetworkError) {
                            if(listener != null){
                                listener.onSendRequestfriend(REQUEST_NETWORK_ERROR, e.toString()+"", ress, -1);
                            }
                        } else if (type == NetException.UnReachable) {
                            if(listener != null){
                                listener.onSendRequestfriend(REQUEST_UNREACHABLE_ERROR, e.toString()+"", ress, -1);
                            }
                        } else if (type == NetException.NetworkDisabled) {
                            if(listener != null){
                                listener.onSendRequestfriend(REQUEST_NETWORKDISABLED_ERROR, e.toString()+"", ress, -1);
                            }
                        }
                    }

                    @Override
                    protected void onServerException(HttpServerException e, ServerException type, HttpStatus status) {

                    }

                }.handleException(e);


            }


        });
    }

    /**
     * 直接返回json不解析
     * @param map
     * @param url
     */
    public void sendLiteHttpRequestString(final HashMap<String, String> map, final String url, final OnSendRequestListener listener){
        final long start1 = System.currentTimeMillis();
        HttpAsyncExecutor asyncExcutor = HttpAsyncExecutor.newInstance(liteclient);
        Request req = new Request(url);
        System.out.println("url----"+url);
        req.setMethod(com.litesuits.http.request.param.HttpMethod.Post);
        LinkedList<NameValuePair> pList = new LinkedList<NameValuePair>();
        for (Map.Entry<String, String> entry : map.entrySet()) {//构建表单字段内容
            pList.add(new NameValuePair(entry.getKey(), entry.getValue()));
        }
        System.out.println("url----"+url+"??"+pList.toString());
        req.setHttpBody(new UrlEncodedFormBody(pList));
        asyncExcutor.execute(req, new HttpResponseHandler() {
            @Override
            protected void onFailure(com.litesuits.http.response.Response res,
                                     HttpException e) {
                final String ress = res.toString();
                new HttpExceptionHandler() {
                    @Override
                    protected void onClientException(HttpClientException e, ClientException type) {
                        if(listener != null){
                            listener.onSendRequest(REQUEST_CLIENT_ERROR, e+"", ress);
                        }
                    }

                    @Override
                    protected void onNetException(HttpNetException e, NetException type) {
                        if (type == NetException.NetworkError) {
                            if(listener != null){
                                listener.onSendRequest(REQUEST_NETWORK_ERROR, e+"", ress);
                            }
                        } else if (type == NetException.UnReachable) {
                            if(listener != null){
                                listener.onSendRequest(REQUEST_UNREACHABLE_ERROR, e+"", ress);
                            }
                        } else if (type == NetException.NetworkDisabled) {
                            if(listener != null){
                                listener.onSendRequest(REQUEST_NETWORKDISABLED_ERROR, e+"", ress);
                            }
                        }
                    }

                    @Override
                    protected void onServerException(HttpServerException e, ServerException type, HttpStatus status) {

                    }

                }.handleException(e);


            }

            @Override
            protected void onSuccess(com.litesuits.http.response.Response res,
                                     HttpStatus status, NameValuePair[] headers) {
                long end1 = System.currentTimeMillis();
                System.out.println("使用litehttp共用时间　" + (end1 - start1) + "ms");
                int code = REQUEST_SUCCESS;
                String message = "";


                if(listener != null){
                    listener.onSendRequest(code, message, res.getString());
                }

            }
        });
    }



    public void sendVolleyImageRequestString(final RequestParams params, final String url, final OnSendRequestListener listener){
        HttpUtils http=new HttpUtils();
        http.send(HttpRequest.HttpMethod.POST, url, params, new RequestCallBack<String>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                if (isUploading) {
//	                	FriendsterGridviewAdapter.getCirclelist().get(i).setVisibility(View.VISIBLE);
                    DecimalFormat df = new DecimalFormat("##");
                    int value = Integer.parseInt(df.format((double) current / (double) total * 100));
                    System.out.print("==================>" + value + "");
                    System.out.print("==================>" + total + "");
                    System.out.print("==================>" + current + "");
//	                	FriendsterGridviewAdapter.getCirclelist().get(i).setProgressNotInUiThread(value);
                } else {
                }
            }

            @Override
            public void onSuccess(ResponseInfo<String> arg0) {
                long end1 = System.currentTimeMillis();
                int code = REQUEST_NO_NETWORK;
                String message = "请求服务失败";
                String result = null;
                try {
                    JSONObject jObj = new JSONObject(arg0.result);
                    if (!jObj.isNull(TAG_CODE)) {
                        code = Integer.valueOf(jObj.getString(TAG_CODE));
                    }
                    if (!jObj.isNull(TAG_INFO)) {
                        message = jObj.getString(TAG_INFO);
                    }
                    if (!jObj.isNull(TAG_RESULT)) {
                        result = jObj.getString(TAG_RESULT);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

                if (listener != null) {
                    listener.onSendRequest(code, message, result);
                }
            }

            @Override
            public void onFailure(com.lidroid.xutils.exception.HttpException e, String s) {
                final String ress = s.toString();
                new HttpExceptionHandler() {
                    @Override
                    protected void onClientException(HttpClientException e, ClientException type) {
                        if (listener != null) {
                            listener.onSendRequest(REQUEST_CLIENT_ERROR, e + "", ress);
                        }
                    }

                    @Override
                    protected void onNetException(HttpNetException e, NetException type) {
                        if (type == NetException.NetworkError) {
                            if (listener != null) {
                                listener.onSendRequest(REQUEST_NETWORK_ERROR, e + "", ress);
                            }
                        } else if (type == NetException.UnReachable) {
                            if (listener != null) {
                                listener.onSendRequest(REQUEST_UNREACHABLE_ERROR, e + "", ress);
                            }
                        } else if (type == NetException.NetworkDisabled) {
                            if (listener != null) {
                                listener.onSendRequest(REQUEST_NETWORKDISABLED_ERROR, e + "", ress);
                            }
                        }
                    }

                    @Override
                    protected void onServerException(HttpServerException e, ServerException type, HttpStatus status) {

                    }

                }.handleException(e);
            }
        });
    }

}
