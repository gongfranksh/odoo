package com.odoo.addons.bncmember.models;

import android.content.Context;
import android.net.Uri;

import com.odoo.BuildConfig;
import com.odoo.core.orm.OModel;
import com.odoo.core.orm.fields.OColumn;
import com.odoo.core.orm.fields.types.OVarchar;
import com.odoo.core.support.OUser;

public class BncMember extends OModel {
    public static final String AUTHORITY = BuildConfig.APPLICATION_ID +
            ".addons.bncmember.bnc_member";
    public static final String TAG = BncMember.class.getSimpleName();

    public BncMember(Context context,  OUser user) {
        super(context, "bnc.member", user);
        setHasMailChatter(true);
    }

    //    lngBncId = fields.Char(string='lngBncId')
//    OColumn lngBncId = new OColumn("lngBncId", OVarchar.class).setSize(100).setRequired();
    //    lngBusId = fields.Char(string='lngBusId')
//
//    lngvipgrade = fields.Char(string='lngvipgrade')
//    vip_level_name_by_vipgrade = fields.Selection([('', u'未设置'), ('1', u'一般会员'), ('2', u'VIP会员')], string=u'会员等级')
//
//    timestamp = fields.Integer(string=u'更新时间戳')
//
//    OpenDate = fields.Datetime(string=u'OpenDate')
//    RegDate = fields.Datetime(string=u'注册日期')
//    UpdateDate = fields.Datetime(string=u'更新日期')
//    dvipDate = fields.Char(string=u'vip升级日期')
//    Birthday = fields.Datetime(string=u'生日')
//
//    ishandset = fields.Char(string='ishandset')
//    strUid = fields.Char(string='strUid', )
//    lngPayId = fields.Char(string='lngPayId')
//    strPhone = fields.Char(string=u'注册手机')
    OColumn strPhone = new OColumn("注册手机", OVarchar.class).setSize(100);
    //    strBncName = fields.Char(string=u'姓名')
//    OColumn strBncName = new OColumn("姓名", OVarchar.class).setSize(100);
    //            #	strSex= fields.Char(string=u'性别' )
//    OColumn strSex = new OColumn("性别", OVarchar.class).setSize(100);
    //    strSex = fields.Selection([('0', u'未设置'), ('1', u'男性'), ('2', u'女性')], string=u'性别')
//
//    strEMail = fields.Char(string=u'邮箱地址')
//    strProfessionl = fields.Char(string=u'职业')
//    strStatus = fields.Char(string=u'状态', size=5)
//    strBncCardid = fields.Char(string=u'内部卡号')
//    Age = fields.Char(string=u'年龄')
//    OColumn Age = new OColumn("年龄", OVarchar.class).setSize(100);
    //    UpdateMan = fields.Char(string='UpdateMan')
//            #	resid = fields.Many2one('res.partner',u'合作伙伴')
//    resid = fields.Many2one('res.partner', u'合作伙伴', required=True, ondelete='cascade')
//    tagsid = fields.Many2many('bnc.tags', 'bnc_tags_member_rel', 'memid', 'tagid', string=u'用户标签')
//
//            # add for mysql
//            wxid = fields.Integer(string=u'微信id')
//    unionid = fields.Char(string=u'unionid')
//    openid = fields.Char(string=u'openid')
//    nickname = fields.Char(string=u'昵称')
//    OColumn nickname = new OColumn("昵称", OVarchar.class).setSize(100);
//    agent = fields.Text(string=u'agent')
//    bu_name = fields.Char(string=u'bu_name')
//    province = fields.Char(string=u'省份')
//    city = fields.Char(string=u'城市')
//    address = fields.Char(string=u'地址')
//    vip_level_name = fields.Char(string=u'vip_level_name')
//    mysqlstamp = fields.Integer(string=u'资料更新日期戳')
//
//    phone_1 = fields.Char(string=u'手机信息1')
//    phone_2 = fields.Char(string=u'手机信息2')
//    phone_3 = fields.Char(string=u'手机信息3')
//    phone_4 = fields.Char(string=u'手机信息4')
//
//    phone_os = fields.Char(string=u'手机操作系统')
//    phone_brand = fields.Char(string=u'手机品牌')
//    phone_status = fields.Boolean(string=u'手机信息更新状态', default=False)
//
//    num_1 = fields.Char(string=u'归属地')
//    num_2 = fields.Char(string=u'卡类型')
//    num_3 = fields.Char(string=u'运营商')
//    num_4 = fields.Char(string=u'区号')
//    num_5 = fields.Char(string=u'邮编')
//    num_6 = fields.Char(string=u'省份')
//    num_7 = fields.Char(string=u'城市')
//
public Uri uri() {
    return buildURI(AUTHORITY);
}
}
