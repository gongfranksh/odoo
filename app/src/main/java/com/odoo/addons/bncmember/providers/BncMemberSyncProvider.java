package com.odoo.addons.bncmember.providers;

import com.odoo.addons.bncmember.models.BncMember;
import com.odoo.core.orm.provider.BaseModelProvider;

public class BncMemberSyncProvider extends BaseModelProvider {
    public static final String TAG = BncMemberSyncProvider.class.getSimpleName();
    @Override
    public String authority() {
        return BncMember.AUTHORITY;
    }
}
