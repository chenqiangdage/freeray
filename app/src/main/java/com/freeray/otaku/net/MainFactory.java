package com.freeray.otaku.net;

/**
 * Created by 皓然 on 2017/7/4.
 */

public class MainFactory {

    public static BaseNetService baseNetService;
    protected static final Object monitor = new Object();

    public static BaseNetService getBaseNetServiceInstance(){
        synchronized (monitor){
            if(baseNetService==null){
                baseNetService = new MainNetRetrofit().getService();
            }
            return baseNetService;
        }
    }
}
