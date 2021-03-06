package com.dante.girls.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * BaseFragment helps onCreateView, and initViews(when root is null), init data on Activity Created.
 */
public abstract class BaseFragment extends Fragment {

    protected View rootView;
    protected Realm realm;
    protected Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(initLayoutId(), container, false);
            ButterKnife.bind(this, rootView);
            initViews();
        }
        AlwaysInit();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        App.getWatcher(getActivity()).watch(this);
    }

    protected abstract int initLayoutId();

    protected void AlwaysInit() {
        ButterKnife.bind(this, rootView);
        realm = ((BaseActivity) getActivity()).realm;
        toolbar = ((BaseActivity) getActivity()).toolbar;
    }

    protected abstract void initViews();

    protected abstract void initData();

    public void log(String key, String content) {
        if (getUserVisibleHint()) {
            Log.i(getClass().getSimpleName(), key + ": " + content);
        }
    }

    public void log(String key, int content) {
        if (getUserVisibleHint()) {
            Log.i(getClass().getSimpleName(), key + ": " + String.valueOf(content));
        }
    }

    public void log(String key) {
        if (getUserVisibleHint()) {
            Log.i(getClass().getSimpleName(), key);
        }
    }

}
