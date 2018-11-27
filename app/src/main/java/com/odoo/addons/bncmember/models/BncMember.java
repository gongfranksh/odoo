package com.odoo.addons.bncmember.models;

import android.content.Context;
import android.net.Uri;

import com.odoo.BuildConfig;
import com.odoo.base.addons.res.ResPartner;
import com.odoo.core.orm.OModel;
import com.odoo.core.orm.fields.OColumn;
import com.odoo.core.orm.fields.types.ODateTime;
import com.odoo.core.orm.fields.types.OFloat;
import com.odoo.core.orm.fields.types.OSelection;
import com.odoo.core.orm.fields.types.OVarchar;
import com.odoo.core.support.OUser;

public class BncMember extends OModel {
    public static final String AUTHORITY = BuildConfig.APPLICATION_ID +
            ".addons.bncmember.bnc_member";
    public static final String TAG = BncMember.class.getSimpleName();

    public BncMember(Context context, OUser user) {
        super(context, "bnc.member", user);
        setHasMailChatter(true);
    }


    OColumn strBncCardid = new OColumn("内部卡号", OVarchar.class).setSize(100);
    OColumn strPhone = new OColumn("注册手机", OVarchar.class).setSize(100);
    OColumn nickname = new OColumn("昵称", OVarchar.class).setSize(100);
    OColumn province = new OColumn("省份", OVarchar.class).setSize(100);
    OColumn city = new OColumn("城市", OVarchar.class).setSize(100);
    OColumn address = new OColumn("address", OVarchar.class).setSize(100);
    OColumn vip_level_name = new OColumn("vip_level_name", OVarchar.class).setSize(100);
    OColumn bu_name = new OColumn("bu_name", OVarchar.class).setSize(100);
    OColumn total_amount = new OColumn("消费金额", OFloat.class);
    OColumn resid = new OColumn("'合作伙伴'", ResPartner.class, OColumn.RelationType.ManyToOne);
    OColumn vip_level_name_by_vipgrade = new OColumn("会员等级", OSelection.class)
            .addSelection("", "未设置")
            .addSelection("1", "一般会员")
            .addSelection("2", "VIP会员");
    OColumn strSex = new OColumn("'性别'", OSelection.class)
            .addSelection("0", "未设置")
            .addSelection("1", "男性")
            .addSelection("2", "女性");

    OColumn Birthday = new OColumn("'生日'", ODateTime.class);

    OColumn tagsid  = new OColumn("用户标签", BncTags.class,OColumn.RelationType.ManyToMany);

    OColumn num_1 = new OColumn("归属地", OVarchar.class).setSize(100);
    OColumn num_2 = new OColumn("卡类型", OVarchar.class).setSize(100);
    OColumn num_3 = new OColumn("运营商", OVarchar.class).setSize(100);
    OColumn num_4 = new OColumn("区号", OVarchar.class).setSize(100);
    OColumn num_5 = new OColumn("邮编", OVarchar.class).setSize(100);
    OColumn num_6 = new OColumn("省份", OVarchar.class).setSize(100);
    OColumn num_7 = new OColumn("城市", OVarchar.class).setSize(100);

    public Uri uri() {
        return buildURI(AUTHORITY);
    }
}
