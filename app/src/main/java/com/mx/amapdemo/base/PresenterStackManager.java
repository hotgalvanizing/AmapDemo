package com.mx.amapdemo.base;

import com.mx.amapdemo.maphome.presenter.IHomePresenter;

import java.util.LinkedList;

/**
 * Created by dongp on 2018/12/27.
 */

public class PresenterStackManager {

    private static LinkedList<BasePresenter> linkedList = new LinkedList<>();

    public static void add(BasePresenter presenter) {
        linkedList.addFirst(presenter);
    }

    public static void remove(BasePresenter presenter) {
        linkedList.remove(presenter);

    }

    public static BasePresenter getTop() {
        return linkedList.getFirst();
    }

//    public static <T extends BasePresenter> T getPresenter(Class<T> clazz) {
//        for (int x = linkedList.size() - 1; x >= 0; x--) {
//            BasePresenter presenter = linkedList.get(x);
//            if (presenter.getClass().equals(clazz)) {
//                return (T) presenter;
//            }
//        }
//        return null;
//    }

    public static IHomePresenter getFuPresenter() {
        for (int x = linkedList.size() - 1; x >= 0; x--) {
            BasePresenter presenter = linkedList.get(x);
            if (presenter instanceof IHomePresenter) {
                return (IHomePresenter) presenter;
            }
        }
        return null;
    }
}
