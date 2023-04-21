package com.freeray.otaku.presenter;

import android.app.Activity;

import com.freeray.otaku.view.IBaseView;

public abstract  class BasePresenter<GV extends IBaseView> {
    protected GV mView;
    protected Activity mContext;

   // public static final ComicService comicService = MainFactory.getComicServiceInstance();

    public BasePresenter(Activity context, GV view) {
        mContext = context;
        mView = view;
    }

    public BasePresenter(){}
}
