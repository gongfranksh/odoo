package com.odoo.addons.bncmember.models;

import android.content.Context;
import android.net.Uri;

import com.odoo.BuildConfig;
import com.odoo.core.orm.OModel;
import com.odoo.core.orm.fields.OColumn;
import com.odoo.core.orm.fields.types.OVarchar;
import com.odoo.core.support.OUser;

public class BncTags extends OModel {
    public static final String AUTHORITY = BuildConfig.APPLICATION_ID +
            ".addons.bnc_tags";
    public static final String TAG = BncTags.class.getSimpleName();

    public BncTags(Context context, OUser user) {
        super(context, "bnc.tags", user);
    }

    OColumn code = new OColumn("标签编号", OVarchar.class).setSize(100);
    OColumn name = new OColumn("名称", OVarchar.class).setSize(100);

//    public Uri uri() {
//        return buildURI(AUTHORITY);
//    }
}
