package com.yey.read.common.fragment;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yey.read.R;
import com.yey.read.common.AppContext;
import com.yey.read.util.DialogTips;
import com.yey.read.widget.LoadingDialog;

public abstract class BaseFragment extends Fragment {
	protected View contentView;
	public AppContext appcontext;
	public LayoutInflater mInflater;
	
	private Handler handler = new Handler();
	private LoadingDialog loadingdialog;
	public void runOnWorkThread(Runnable action) {
		new Thread(action).start();
	}

	public void runOnUiThread(Runnable action) {
		handler.post(action);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		appcontext = AppContext.getInstance();
		mInflater = LayoutInflater.from(getActivity());
	}

	
	public BaseFragment() {
		
	}

	Toast mToast;

	public void ShowToast(String text) {
            if(getActivity()!=null){
	        View layout= LayoutInflater.from(getActivity()).inflate(R.layout.common_toast, null);
	        TextView textView=(TextView)layout.findViewById(R.id.toast_tv);
	        textView.setText(text);
	        Toast toast=new Toast(getActivity());
	        toast.setDuration(Toast.LENGTH_SHORT);
	        toast.setView(layout);
	        toast.show();}
	}

	public void ShowToast(int text) {
	        View layout= LayoutInflater.from(getActivity()).inflate(R.layout.common_toast, null);
	        TextView textView=(TextView)layout.findViewById(R.id.toast_tv);
	        textView.setText(text+"");
	        Toast toast=new Toast(getActivity());
	        toast.setDuration(Toast.LENGTH_SHORT);
	        toast.setView(layout);
	        toast.show();
	}
    /**
     * 描述：对话框dialog （确认，取消）.
     * @param title 对话框标题内容
     * @param msg  对话框提示内容
     * @param mOkOnClickListener  点击确认按钮的事件监听
     */
    public void showDialog(String title,String msg,DialogInterface.OnClickListener mOkOnClickListener) {
        AlertDialog.Builder builder = new Builder(getActivity());
        builder.setMessage(msg);
        builder.setTitle(title);
        builder.setPositiveButton("确认",mOkOnClickListener);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    public void showDialog(String title,String negName,String posiName,boolean hasbody,DialogInterface.OnClickListener mOkOnClickListener){
       DialogTips dialog = new DialogTips(getActivity(),title,negName,posiName);
       dialog.SetOnSuccessListener(mOkOnClickListener);
       dialog.setTitle(title);
       dialog.setHasBody(hasbody);
       dialog.show();
    }

    /**
     * 自动与对话框
     * @param title
     * @param negName
     * @param posiName
     * @param hasNegtive
     * @param hasbody
     * @param mOkOnClickListener
     */
    public void showDialog(String title,String negName,String posiName,
            boolean hasNegtive,boolean hasbody,DialogInterface.OnClickListener mOkOnClickListener){
        DialogTips dialog = new DialogTips(getActivity(),title,negName,hasNegtive,posiName,hasbody);
        dialog.SetOnSuccessListener(mOkOnClickListener);
        dialog.setTitle(title);
        dialog.setHasBody(hasbody);
        dialog.show();
    }

	public View findViewById(int paramInt) {
		return getView().findViewById(paramInt);
	}

	/**
	 * 动画启动页面 startAnimActivity
	 * @throws
	 */
	public void startAnimActivity(Intent intent) {
		this.startActivity(intent);
	}

	public void startAnimActivity(Class<?> cla) {
		getActivity().startActivity(new Intent(getActivity(), cla));
	}

	protected void startAnimActivity(Class<?> pClass, Bundle pBundle) {
		Intent intent = new Intent(getActivity(), pClass);
		if (pBundle != null) {
			intent.putExtras(pBundle);
		}
		getActivity().startActivity(intent);
	}

	public void showDialog(String title,String buttonText,String message,DialogInterface.OnClickListener onSuccessListener) {
		DialogTips dialog = new DialogTips(getActivity(),title,message, buttonText,true,true);
		// 设置成功事件
		dialog.SetOnSuccessListener(onSuccessListener);
		// 显示确认对话框
		dialog.show();
		dialog = null;
	}

    public void showDialog(String title,String message,String buttonText,DialogInterface.OnClickListener onSuccessListener,DialogInterface.OnClickListener  OnCancelListener) {
        DialogTips dialog = new DialogTips(getActivity(),title,message, buttonText,true,true);
        // 设置成功事件
        dialog.SetOnSuccessListener(onSuccessListener);
        dialog.SetOnCancelListener(OnCancelListener);
        // 显示确认对话框
        dialog.show();
        dialog = null;
    }

	/**
	 * 显示加载对话框
	 * showLoadingDialog
	 *void
	 * @exception
	 * @since  1.0.0
	 */
	public void showLoadingDialog(String text){
		if(loadingdialog!=null){
			loadingdialog.setText(text);
			if(!loadingdialog.isShowing()){
				loadingdialog.show();
			}
		}else{
			loadingdialog = new LoadingDialog(getActivity(), text);
			loadingdialog.show();
		}

	}

	/**
	 * 取消加载对话框
	 * showLoadingDialog
	 *void
	 * @exception
	 * @since  1.0.0
	 */
	public void cancelLoadingDialog(){
		if(loadingdialog!=null && loadingdialog.isShowing()){
			loadingdialog.dismiss();
		}

	}

	public void showDialog(String title,String[] item,DialogInterface.OnClickListener mOkOnClickListener,int selectPosition) {
		 AlertDialog.Builder builder = new Builder(getActivity());
		 builder.setTitle(title);
		 builder.setSingleChoiceItems(item,selectPosition,mOkOnClickListener);
		 builder.setNegativeButton("取消", null);
		 builder.create().show();
	}

}

