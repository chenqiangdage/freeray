package com.freeray.otaku.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.freeray.otaku.R;
import com.freeray.otaku.base.commonconst.Constants;
import com.freeray.otaku.presenter.BasePresenter;
import com.freeray.otaku.util.ZToast;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.orhanobut.hawk.Hawk;

public abstract class BasicActivity<P extends BasePresenter>  extends RxAppCompatActivity {

    //未指定类型的Presenter
    protected P mPresenter;

    //夜间模式
    protected View NightModel;
    //是否是夜间模式
    protected boolean isNight;

    //是否透明的
    protected boolean isTrans;

    //抽象 初始化 presenter方法
    protected abstract void initPresenter(Intent intent);
    //设置布局
    protected abstract int getLayout();
    //初始化布局
    protected abstract void initView();

    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //此段代码段作用
        //app 在应用市场下载完成后，点击打开，按照 app 的一般流程首先会启动 Splash 界面，再进入首页。此时你按下 home 键，
        // 再从桌面点击应用图标，你会发现应用又走到了 Splash 界面再进入首页。
        // 再次按 home 键重复刚才的动作现象依旧，通过将应用结束或杀掉进程应用就会恢复正常。
        // 当然这种现象理论上来说在你安装完应用后只会出现一次，但终归还是会影响用户体验。
        if((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT)!=0)
        {
            finish();
            return;
        }
        setContentView(getLayout());
        //“沉浸式”仅仅支持在不低于android4.4的设备上运行
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            initStatusBar(true);
        }

        initPresenter(getIntent());
        checkPresenterIsNull();
        initView();
    }

    /**
     * 检测presenter是否为空
     */
    private void checkPresenterIsNull() {
        if (mPresenter == null) {
            throw new IllegalStateException("please init mPresenter in initPresenter() method ");
        }
    }

    /**
     * 初始化顶部状态栏 为沉浸式 透明状态
     * @param isTransparent
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void initStatusBar(boolean isTransparent) {
        //获取窗口
        Window window = getWindow();
        //先取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if(isTransparent){
            //View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN 沉浸式状态栏
            //View.SYSTEM_UI_FLAG_LAYOUT_STABLE 设置状态栏字体颜色还是跟随系统
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }else{
            //View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR 默认白底黑字风格
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        isTrans = isTransparent;
        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        //设置了FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS，表明会 Window 负责系统 bar的 background 绘制，
        // 绘制透明背景的系统 bar（状态栏和导航栏），然后用getStatusBarColor()和getNavigationBarColor()的颜色填充相应的区域
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏为透明颜色
        window.setStatusBarColor(Color.TRANSPARENT);
    }

    public void showToast(String text){
        ZToast.makeText(this, text,1000).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
       // switchModel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 切换模型 提供夜间阅览模式
     */
    protected void switchModel() {
        //找到夜间模式遮掩层
        NightModel  = findViewById(R.id.v_night);
        try{
            //hawk 是一个key value形式的数据库，理解为字典型数据库，放本地环境 的
            //开源地址 https://github.com/orhanobut/hawk
            //获取是否是夜间模式
            isNight = Hawk.get(Constants.MODEL);
            if(isNight){
               //
                // visibility这个属性，其属性有3个分别为“visible ”、“invisible”、“gone”。
                // INVISIBLE和GONE的主要区别是：
                // 当控件visibility属性为INVISIBLE时，界面保留了view控件所占有的空间；
                // 而控件属性为GONE时，界面则不保留view控件所占有的空间。
                //遮掩层打开
                NightModel.setVisibility(View.VISIBLE);
            }else{
                //遮掩层隐藏
                NightModel.setVisibility(View.GONE);
            }
        }catch (Exception e){
            NightModel.setVisibility(View.GONE);
        }
    }

    /***
     * 获取屏幕宽度
     * @return 屏幕宽度（px）
     */
    public int getMobileWidth(){
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        return  width;
    }
    /**
     * 获取屏幕高度
     * @return 屏幕高度(px)
     */
    public int getMobileHeight(){
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int height = dm.heightPixels;
        return  height;
    }

    /**
     * 获取状态栏高度
     * @return 高度（px）
     */
    public int getStatusBarHeight(){
        int result = 0;
        int resultId = getResources().getIdentifier("status_bar_height","dimen","android");
        if(resultId>0){
            result =getResources().getDimensionPixelSize(resultId);
        }
        return result;
    }
}
