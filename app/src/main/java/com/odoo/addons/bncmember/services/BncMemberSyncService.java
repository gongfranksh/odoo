package com.odoo.addons.bncmember.services;

import android.content.Context;
import android.os.Bundle;

import com.odoo.addons.bncmember.models.BncMember;
import com.odoo.core.service.OSyncAdapter;
import com.odoo.core.service.OSyncService;
import com.odoo.core.support.OUser;

public class BncMemberSyncService extends OSyncService {
    public static final String TAG = BncMemberSyncService.class.getSimpleName();
    @Override
    public OSyncAdapter getSyncAdapter(OSyncService service, Context context) {
        return new OSyncAdapter(getApplicationContext(), BncMember.class, this, true);
    }

    @Override
    public void performDataSync(OSyncAdapter adapter, Bundle extras, OUser user) {
        adapter.syncDataLimit(80);
    }
}
