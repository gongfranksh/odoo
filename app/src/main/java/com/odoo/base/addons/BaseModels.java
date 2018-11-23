package com.odoo.base.addons;

import android.content.Context;

import com.odoo.addons.bncmember.models.BncMember;
import com.odoo.core.orm.OModel;
import com.odoo.core.support.OUser;

import java.util.ArrayList;
import java.util.List;

public class BaseModels {
    public static final String TAG = BaseModels.class.getSimpleName();

    public static List<OModel> baseModels(Context context, OUser user) {
        List<OModel> models = new ArrayList<>();
        models.add(new BncMember(context, user));
        return models;
    }
}
