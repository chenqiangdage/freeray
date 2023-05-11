package com.freeray.otaku.entity.database;

import android.content.Context;

import com.freeray.otaku.greendao.gen.DaoMaster;
import com.freeray.otaku.greendao.gen.DaoSession;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 *
 */

public class DaoHelper<T> {
    private DaoManager manager;
    private Class<T> clazz;
    public DaoHelper(Context context) {
        manager = DaoManager.getInstance(context);
    }
    private Class<T> getClazz() {
        if (clazz == null) {//获取泛型的Class对象
            clazz = ((Class<T>) (((ParameterizedType) (this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]));
        }
        return clazz;
    }
    // 插入增加
    public boolean insert(T t) {
        return manager.getDaoSession().insert(t) != -1;
    }
    //插入集合
    public boolean insertList(final List<T> datas) {
        boolean flag = false;
        try {
            manager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (T t : datas) {
                        manager.getDaoSession().insertOrReplace(t);
                    }
                }
            });
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    //删除
    public boolean delete(T t) {
        try {
            manager.getDaoSession().delete(t);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    // 删除所有
    public boolean deleteAll() {
        try {
            manager.getDaoSession().deleteAll(clazz);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //列出所有
    public List<T> listAll() {
        return manager.getDaoSession().loadAll(getClazz());
    }


    public T find(long id) {
        return manager.getDaoSession().load(clazz, id);
    }


    //更新
    public boolean update(T t) {
        try {
            manager.getDaoSession().update(t);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }





    //queryRaw查询
    public List<T> queryAll(String where, String... selectionArgs) {
        List<T> list = manager.getDaoSession().queryRaw(clazz, where, selectionArgs);
        return list;
    }
    //biuld查询
    public List<T> queryBuilder() {
        List<T> list = manager.getDaoSession().queryBuilder(clazz).list();
        return list;
    }
    //查询全部，dao查询
    public List<T> queryDaoAll(Class clazz) {
        DaoMaster daoMaster = manager.getDaoMaster();
        DaoSession session = daoMaster.newSession();
        List<T> list = session.loadAll(clazz);
        return list;
    }


}

