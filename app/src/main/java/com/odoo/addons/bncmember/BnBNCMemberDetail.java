package com.odoo.addons.bncmember;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.odoo.App;
import com.odoo.R;
import com.odoo.addons.bncmember.models.BncMember;
import com.odoo.addons.customers.CustomerDetails;
import com.odoo.addons.customers.Customers;
import com.odoo.addons.customers.utils.ShareUtil;
import com.odoo.core.orm.ODataRow;
import com.odoo.core.orm.OModel;
import com.odoo.core.orm.OValues;
import com.odoo.core.orm.fields.OColumn;
import com.odoo.core.support.OdooCompatActivity;
import com.odoo.core.utils.OAlert;
import com.odoo.core.utils.OResource;
import com.odoo.core.utils.OStringColorUtil;

import odoo.controls.OField;
import odoo.controls.OForm;

public class BnBNCMemberDetail extends OdooCompatActivity
        implements View.OnClickListener, OField.IOnFieldValueChangeListener {

    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;
    private App app;
    private BncMember bncMember;
    private Bundle extras;
    private OForm mForm;
    private Boolean mEditMode = false;
    private Menu mMenu;
    private ODataRow record = null;

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onFieldValueChange(OField field, Object value) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bncmember_detail);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.bncmember_collapsing_toolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        app = (App) getApplicationContext();
        setMode(mEditMode);
        bncMember = new BncMember(this, null);
        extras = getIntent().getExtras();
        if (!hasRecordInExtra())
            mEditMode = true;
        setupToolbar();
//        int rowId=extras.getInt(OColumn.ROW_ID);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_bncmember_save:
                OValues values = mForm.getValues();
                if (values != null) {
                    bncMember.update(record.getInt(OColumn.ROW_ID), values);
                    Toast.makeText(this, R.string.toast_information_saved, Toast.LENGTH_LONG).show();
                    mEditMode = !mEditMode;
                    setupToolbar();
                } else {
                    final int row_id = bncMember.insert(values);
                    if (row_id != OModel.INVALID_ROW_ID) {
                        finish();
                    }
                }

                break;
            case R.id.menu_bncmember_cancel:
            case R.id.menu_bncmember_edit:
                if (hasRecordInExtra()) {
                    mEditMode = !mEditMode;
                    setMode(mEditMode);
                    mForm.setEditable(mEditMode);
                    mForm.initForm(record);
//                    setCustomerImage();
                } else {
                    finish();
                }
                break;
            case R.id.menu_bncmember_share:
                ShareUtil.shareContact(this, record, true);
                break;
            case R.id.menu_bncmember_import:
                ShareUtil.shareContact(this, record, false);
                break;
            case R.id.menu_bncmember_delete:
                OAlert.showConfirm(this, OResource.string(this,
                        R.string.confirm_are_you_sure_want_to_delete),
                        new OAlert.OnAlertConfirmListener() {
                            @Override
                            public void onConfirmChoiceSelect(OAlert.ConfirmType type) {
                                if (type == OAlert.ConfirmType.POSITIVE) {
                                    // Deleting record and finishing activity if success.
                                    if (bncMember.delete(record.getInt(OColumn.ROW_ID))) {
                                        Toast.makeText(BnBNCMemberDetail.this, R.string.toast_record_deleted,
                                                Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                }
                            }
                        });

                break;
        }
        return super.

                onOptionsItemSelected(item);

    }

    private void setupToolbar() {
        if (!hasRecordInExtra()) {
            setMode(mEditMode);
//            userImage.setColorFilter(Color.parseColor("#ffffff"));
//            userImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            mForm.setEditable(mEditMode);
            mForm.initForm(null);
        } else {
            int rowId = extras.getInt(OColumn.ROW_ID);
            record = bncMember.browse(rowId);
//            record.put("full_address", resPartner.getAddress(record));
//            checkControls();
            setMode(mEditMode);
            mForm.setEditable(mEditMode);
            mForm.initForm(record);
            collapsingToolbarLayout.setTitle(record.getString("name"));
//            setCustomerImage();
            if (record.getInt("id") != 0 && record.getString("large_image").equals("false")) {
//                CustomerDetails.BigImageLoader bigImageLoader = new CustomerDetails.BigImageLoader();
//                bigImageLoader.execute(record.getInt("id"));
            }
        }
    }

    private boolean hasRecordInExtra() {
        boolean pp = extras.containsKey(OColumn.ROW_ID);

        return extras != null && extras.containsKey(OColumn.ROW_ID);
    }

    private void setMode(Boolean edit) {
        findViewById(R.id.captureImage).setVisibility(edit ? View.VISIBLE : View.GONE);
        if (mMenu != null) {
            mMenu.findItem(R.id.menu_bncmember_detail_more).setVisible(!edit);
            mMenu.findItem(R.id.menu_bncmember_edit).setVisible(!edit);
            mMenu.findItem(R.id.menu_bncmember_save).setVisible(edit);
            mMenu.findItem(R.id.menu_bncmember_cancel).setVisible(edit);
        }
        int color = Color.DKGRAY;
        if (record != null) {
            color = OStringColorUtil.getStringColor(this, record.getString("name"));
        }
        if (edit) {
            if (!hasRecordInExtra()) {
                collapsingToolbarLayout.setTitle("New");
            }
            mForm = (OForm) findViewById(R.id.bncmemberFormEdit);
            findViewById(R.id.bncmember_edit_layout).setVisibility(View.GONE);
            findViewById(R.id.bncmember_view_layout).setVisibility(View.VISIBLE);
            OField is_company = (OField) findViewById(R.id.is_company_edit);
            is_company.setOnValueChangeListener(this);
        } else {
            mForm = (OForm) findViewById(R.id.bncmemberForm);
            findViewById(R.id.bncmember_edit_layout).setVisibility(View.GONE);
            findViewById(R.id.bncmember_view_layout).setVisibility(View.VISIBLE);
        }
        setColor(color);
    }

    private void setColor(int color) {
        mForm.setIconTintColor(color);
    }

}
