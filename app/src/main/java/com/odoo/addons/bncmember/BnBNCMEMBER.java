package com.odoo.addons.bncmember;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.odoo.R;
import com.odoo.addons.bncmember.models.BncMember;
import com.odoo.core.orm.ODataRow;
import com.odoo.core.support.addons.fragment.BaseFragment;
import com.odoo.core.support.addons.fragment.IOnSearchViewChangeListener;
import com.odoo.core.support.addons.fragment.ISyncStatusObserverListener;
import com.odoo.core.support.drawer.ODrawerItem;
import com.odoo.core.support.list.OCursorListAdapter;
import com.odoo.core.utils.OControls;
import com.odoo.core.utils.OCursorUtils;

import java.util.ArrayList;
import java.util.List;

public class BnBNCMEMBER extends BaseFragment implements
        ISyncStatusObserverListener,
        LoaderManager.LoaderCallbacks<Cursor>,
        SwipeRefreshLayout.OnRefreshListener,
        OCursorListAdapter.OnViewBindListener,
        IOnSearchViewChangeListener,
        View.OnClickListener,
        AdapterView.OnItemClickListener {
    public static final String TAG = BnBNCMEMBER.class.getSimpleName();
    public static final String KEY = BnBNCMEMBER.class.getSimpleName();
    private View mView;
    private String mCurFilter = null;
    private OCursorListAdapter mAdapter = null;
    private boolean syncRequested = false;

    @Override
    public List<ODrawerItem> drawerMenus(Context context) {
        List<ODrawerItem> items = new ArrayList<>();
        items.add(new ODrawerItem(KEY).setTitle("百乐卡")
                .setIcon(R.drawable.ic_action_customers)
                .setInstance(new BnBNCMEMBER()));
        return items;
    }

    @Override
    public Class<BncMember> database() {
        return BncMember.class;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        setHasSyncStatusObserver(KEY, this, db());
        return inflater.inflate(R.layout.common_listview, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasSwipeRefreshView(view, R.id.swipe_container, this);
        mView = view;
//        mType = Type.valueOf(getArguments().getString(EXTRA_KEY_TYPE));
        ListView mPartnersList = (ListView) view.findViewById(R.id.listview);
        mAdapter = new OCursorListAdapter(getActivity(), null, R.layout.bnc_member_row_list);
        mAdapter.setOnViewBindListener(this);
        mAdapter.setHasSectionIndexers(true, "strPhone");
        mPartnersList.setAdapter(mAdapter);
        mPartnersList.setFastScrollAlwaysVisible(true);
        mPartnersList.setOnItemClickListener(this);
        setHasSyncStatusObserver(TAG, this, db());
        setHasFloatingButton(view, R.id.fabButton, mPartnersList, this);
        getLoaderManager().initLoader(0, null,
                this);
    }




    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.changeCursor(null);
    }

    @Override
    public void onStatusChange(Boolean refreshing) {
        getLoaderManager().restartLoader(0, null, this);
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        menu.clear();
//        inflater.inflate(R.menu.menu_partners, menu);
//        setHasSearchView(this, menu, R.id.menu_partner_search);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onRefresh() {

        if (inNetwork()) {
            parent().sync().requestSync(BncMember.AUTHORITY);
            setSwipeRefreshing(true);
        } else {
            hideRefreshingProgress();
            Toast.makeText(getActivity(), _s(R.string.toast_network_required), Toast.LENGTH_LONG)
                    .show();
        }
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ODataRow row = OCursorUtils.toDatarow((Cursor) mAdapter.getItem(i));
    }


    @Override
    public boolean onSearchViewTextChange(String newFilter) {
        return false;
    }

    @Override
    public void onSearchViewClose() {

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
        return new CursorLoader(getActivity(), db().uri(), null, null, null, null);

        //        String where = "";
//        List<String> args = new ArrayList<>();
//        String selection = (args.size() > 0) ? where : null;
//        String[] selectionArgs = (args.size() > 0) ? args.toArray(new String[args.size()]) : null;
//        Uri pp2 = db().uri();
//        CursorLoader pp = new CursorLoader(getActivity(), db().uri(),
//                null, selection, selectionArgs, "strPhone");
//
//        return new CursorLoader(getActivity(), db().uri(),
//                null, selection, selectionArgs, "strPhone");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.changeCursor(data);
        if (data.getCount() > 0) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    OControls.setGone(mView, R.id.loadingProgress);
                    OControls.setVisible(mView, R.id.swipe_container);
                    OControls.setGone(mView, R.id.data_list_no_item);
                    setHasSwipeRefreshView(mView, R.id.swipe_container, BnBNCMEMBER.this);
                }
            }, 500);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    OControls.setGone(mView, R.id.loadingProgress);
                    OControls.setGone(mView, R.id.swipe_container);
                    OControls.setVisible(mView, R.id.data_list_no_item);
                    setHasSwipeRefreshView(mView, R.id.data_list_no_item, BnBNCMEMBER.this);
                    OControls.setImage(mView, R.id.icon, R.drawable.ic_action_customers);
                    OControls.setText(mView, R.id.title, _s(R.string.label_no_customer_found));
                    OControls.setText(mView, R.id.subTitle, "");
                }
            }, 500);
            if (db().isEmptyTable() && !syncRequested) {
                syncRequested = true;
                onRefresh();
            }
        }
    }

    @Override
    public void onViewBind(View view, Cursor cursor, ODataRow row) {

        OControls.setText(view, R.id.strPhone, row.getString("strPhone"));
        OControls.setText(view, R.id.strbnccardid, row.getString("strBncCardid"));
        OControls.setText(view, R.id.nickname, row.getString("nickname"));
        OControls.setText(view, R.id.viplevelname, row.getString("total_amount"));


    }
}
