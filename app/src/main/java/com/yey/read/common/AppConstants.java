package com.yey.read.common;

import android.os.Environment;

import java.io.File;

/**
 * Created by sunnie on 15/6/2.
 */
public class AppConstants {
    //公共
    public static final String PARAM_USERID = "userid";

    //登录注册
    public static final String PREF_SPLASH = "splash"; //loading图
    public static final String PREF_LASTTIME = "lasttime"; //上次登录时间
    public static final String PREF_ISLOGIN = "islogin"; //是否登录过

    public static final String PARAM_ACCOUNT = "account";
    public static final String PARAM_PASSWORD = "password";
    public static final String PARAM_TIMESTAMP = "timestamp";

    //home
    public static final String PARAM_FETCHNUM = "fetchnum";
    public static final String PARAM_NEXTID = "nextid";
    public static final String PARAM_KEY = "key";

    //登录返回字段
    public static final String PARAM_UID = "uid";
    public static final String PARAM_POINT = "point";
    public static final String PARAM_VIP = "vip";
    public static final String PARAM_NICKNAME = "nickname";
    public static final String PARAM_AVATAR = "avatar";
    public static final String PARAM_BIRTHDAY = "birthday";
    public static final String PARAM_GENDER = "gender";


    public static final String ROLE = "role";
    public static final String CLIENT = "client";

    public static final String PARAM_KID = "kid";
    public static final String PARAM_TUID = "tuid";
    public static final String PARAM_TPUBLICID = "tpublicid";
    public static final String PARAM_MEMO = "memo";
    public static final String PARAM_FRIEND = "friendid";
    public static final String PARAM_REQID = "reqid";
    public static final String PARAM_ACTION = "action";
    public static final String PARAM_LOCATION = "location";
    public static final String PARAM_JOB = "job";
    public static final String PARAM_NAME = "name";
    public static final String PARAM_KNAME = "kname";
    public static final String PARAM_ADDRESS = "address";
    public static final String PARAM_KADDRESS = "kaddress";
    public static final String PARAM_KLOCATION = "klocation";
    public static final String PARAM_PHONE = "phone";
    public static final String PARAM_KPHONE = "kphone";
    public static final String PARAM_TERM  =  "term";
    public static final String PARAM_GBID = "gbid";
    public static final String PARAM_DESC = "desc";
    public static final String PARAM_KDESC = "kdesc";
    public static final String PARAM_OLDPW = "oldpw";
    public static final String PARAM_NEWPW = "newpw";
    public static final String PARAM_TARGETID = "targetid";
    public static final String PARAM_FUID ="fuid";
    public static final String PARAM_VALUE = "value";
    public static final String PARAM_VTYPE = "vtype";
    public static final String CLIENTID = "clientid";
    public static final String PARAM_PUBLICID = "publicid";
    public static final String PARAM_TYPEID = "typeid";
    public static final String PARAM_PMID = "pmid";
    public static final String PARAM_PMIDS = "pmids";
    public static final String PARAM_PMTYPE = "pmtype";
    public static final String PARAM_STATUS = "status";
    public static final String PARAM_ALBUMID = "albumid";
    public static final String PARAM_ALBUM = "album";
    public static final String PARAM_ALBUMIDS = "albumIds";
    public static final String PARAM_PHOTOIDS = "photoIds";
    public static final String PARAM_DESCRIPTION = "description";
    public static final String PARAM_TITLE = "title";
    public static final String PARAM_AUTHOR = "author";
    public static final String PARAM_MAINGATEWAY = "maingateway";
    public static final String PARAM_HUANXIN = "hx";
    public static final String PARAM_UNAME = "uname";
    public static final String PARAM_CMTID = "cmtid";
    public static final int PUSH_ACTION_FRIENDS = 0; //0进入与好友的即时聊天界面
    public static final int PUSH_ACTION_PUBLICACCOUNT = 1;// 1 公众号推送过来的消息
    public static final int PUSH_ACTION_ADD_FRIENDS = 2; //好友请求
    public static final int PUSH_ACTION_AGREE_FRIENDS = 3; //好友的同意请求
    public static final int PUSH_ACTION_DEL_FRIENDS = 5;   //删除好友
    public static final int PUSH_ACTION_TASK = 6;   //任务
    public static final int PUSH_ACTION_NOTICE = 7;   //通知
    public static final int PUSH_ACTION_GUIDE_MASTER = 10;   //新手指导园长
    public static final int PUSH_ACTION_GUIDE_TEACHER = 11;   //新手指导老师
    public static final int PUSH_ACTION_SYSTEM_MESSAGE = 50;  //推送系统消息 注：大于等于50属于系统消息
    public static final int PUSH_ACTION_QUIT = 8;   //退出登录
    public static final int PUSH_ACTION_SYSTEM = 99;   //系统消息
    public static final int PUSH_CONTENT_TYPE_TEXT = 0;   //消息文本
    public static final int PUSH_CONTENT_TYPE_IMAGE = 1;   //图片
    public static final int PUSH_CONTENT_TYPE_AUDIO = 2;   //语音
    public static final int PUSH_CONTENT_TYPE_VEDIO = 2;   //语音


    public static final int PARENTROLE = 2;//家长身份
    public static final int TEACHERROLE = 1;//
    public static final int DIRECTORROLE = 0;
    public static final String USER_NICK = "nick";
    public static final String INTENT_KEY_FROMID = "fromId";
    public static final String INTENT_KEY_TYPEID = "typeid";
    public static final String PARAM_CLIENTID = "clientid";
    public static final String PARAM_SYSOP = "sysop";
    public static final String PARAM_APPVER = "appver";
    public static final String PARAM_SYSVER = "client";
    public static final String INTENT_URL = "url";
    public static final String INTENT_SHOWTITLE = "showtitle";
    public static final String INTENT_NAME = "name";
    public static final String RECEIVRE ="receiver";
    public static final String ADDRESS ="address";
    public static final String PHONE ="phone";
    public static final String CODE ="code";
    public static final String PARAM_PHONECODE="phonecode";
    public static final String PARAM_EMAIL="email";
    public static final String PARAM_GID="gid";
    public static final String PARAM_SHARE = "share";
    public static final String PARAM_GNAME = "gname";
    public static final String PARAM_JOINCODE = "joincode";
    public static final String PARAM_CONTACT = "contact";
    public static final String PARAM_GNUM="gnum";
    public static final String PARAM_GTYPE="gtype";
    public static final String PARAM_IMG = "img";
    public static final String PARAM_SND = "snd";
    public static final String PARAM_TXT = "txt";
    public static final String PARAM_DIARYID ="diaryid";
    public static final String PARAM_TOUID ="touid";
    /*可不可以不要跟我写在一起到时候很难找的，谢谢了--->*/
    public static final String CONTACTS = "contacts";
    public static final String CAPETURE = "capetue";
    public static final String CONTACTS_PUAC = "puac";
    public static final String PUACFRAGMENT_BOOKPUAC = "puacfragment_bookpuac";
    public static final String PUACFRAGMENT_LOOKPUAC = "puacfragment_lookpuac";

    public static final String CONTACTADDPUACRESULT_LOOKPUAC = "contactaddpuacresult_lookpuac";
    public static final String CONTACTADDPUACRESULT_BOOKPUAC = "contactaddpuacresult_bookpuac";

    public static final String TASK_BOOKPUAC = "task_bookpuac";
    public static final String TASK_LOOKPUAC = "task_lookpuac";

    public static final String PUACFRAGMENT_SPECIALPUAC = "puacfragment_specialpuac";

    public static final String CONTACTS_PARENT = "parent";
    public static final String CONTACTS_KINDERPARENT = "kinderparent";
    public static final String CONTACTS_KINDERTEACHER = "kinderteacher";
    public static final String CONTACTS_TEACHER = "teacher";
    public static final String CONTACTS_FRIEND = "friend";
    public static final String CONTACTS_TITLE = "title";
    public static final String CONTACTS_ADDFRIEND = "addfriend";
    public static final String CONTACTS_ISFRIEND = "isfriend";
    public static final String CONTACTS_NOFRIEND = "nofriend";
    public static final String CONTACTS_ADDFRIENDRESULT = "addfriendresult";
    public static final String CONTACTS_FRIENDMAIN = "friendmain";
    public static final String CONTACTS_TEACHERMAIN = "teachermain";
    public static final String CONTACTS_PARENTMAIN = "parentmain";
    public static final String CONTACTS_CONTACTPARENTLIST = "contactparentlist";
    public static final String CONTACTS_CONTACTLISTPARENT = "contactlistparent";
    public static final String CONTACTS_ADDPUACRESULT = "addpuacresult";
    public static final String CONTACTS_PUACMAIN = "puacmain";
    public static final String CONTACTS_FRIENDREQUEST = "friendrequest";
    public static final String CONTACTS_ALBUM = "album";
    public static final String SERIVCE_ADRESSBOOKSELECT = "adressbookselect";
    public static final String SERIVCE_TASKMAIN = "taskmain";
    public static final String SERIVCE_POINTEXCHANGEHIS = "pointexchangehis";
    public static final String SERIVCE_POINTEXCHANGEMAIN = "pointexchangemain";
    public static final String SERIVCE_ALLADRESSESSBOOK = "alladdress";

    public static final String SERIVCE_POINTEXCHANGEDIT = "pointexchangeedit";
    public static final String SERIVCE_POINTEXCHANGEFILL = "pointexchangefill";
    public static final String SERIVCE_POINTEXCHANGEMANAGEFILL = "pointexchangemanegefill";
    public static final String SERIVCE_POINTEXCHANGMANAGEEDIT = "pointexchangemanageedit";
    public static final String SERIVCE_POINTEXCHANGESURE = "pointexchangesureresult";
    public static final String APP_ADDRESS = "address";
    public static final String CONTACTS_DELETFRIEND = "deletfriend";
    public static final String MEINFO = "meinfo";
    public static final String KINDGARTENINFO = "kindergateninfo";
    //public static final String MEMAIN = "memain";
    public static final String MEMAIN_UP = "memain_up";
    public static final String MEMAIN_DOWN = "memain_down";
    public static final String IDSAFE = "idsafe";
    public static final String CONTACTS_REQUEST = "friendrequest";
    public static final String TASKMAIN = "taskmian";

    public static final String ADDKINDER = "addkingder";
    public static final String ADDRESSBOOKMANAGEEDIT = "manageedit";

    public static final String ADDRESSBOOKBEAN = "addressbookbean";
    public static final String ADDRESSFILLSTATE = "fillstate";
    public static final String ADDRESSBOOSTATE = "addressbookstate";



    public static final String SERVICECREATESTATE= "servicecreatestate";
    public static final String CREATEKINDER= "createkinder";
    public static final String CREATECLASS= "createclass";
    public static final String CREATEGENERALGROUP= "creategeneralgroup";

    public static final String GNUM="gnum";
    public static final String STATE="state";
    public static final String CREATESUCCESS="createsuccess";
    public static final String SHARE="share";
    public static final String SREACHGROUPVALUE="sreachgroupvalue";
    public static final String GROUPBEAN="groupbean";
    public static final String GROUPINFOBEAN="groupinfobean";
    public static final String GROUPNAME="groupname";
    public static final String GROUPNUM="groupnum";
    public static final String SERVICEGROUPMEMBER = "servicegroupmember";
    public static final String SERVICEGRADELIST = "servicegradelist";
    public static final String GRADENAME = "gradename";
    public static final String GRADEID = "gradeid";
    public static final String GROUPMEMBER = "groupmember";
    public static final String CODESTRING = "codestring";
    public static final String INPUTTYPE = "intputtype";
    public static final String TITLE = "title";
    public static final String INPUTTYPE_NUMBER = "intputtype_number";
    public static final String INPUTTYPE_STRING = "intputtype_string";
    public static final String GETGROUP = "getgroup";
    public static final String MAINSPEAK = "mainspeak";
    public static final String DESC = "desc";
    public static final String ADDFRIEND = "addfriend";
    public static final String SERVICEGROUP = "servicegroup";
    public static final String FROMDAIRY = "fromdairy";
    public static final String FROMSPEAK = "fromspeak";
    public static final String FROMChat = "fromchat";
    public static final String TXT = "txt";
    public static final String FROMWEB = "fromweb";
    public static final String FROMFRIENDSTER = "fromfriendster";
    public static final String FROMMEINFO = "frommeinfo";
	/*<---可不可以不要跟我写在一起到时候很难找的，谢谢了*/


    public static final String Schedule_Bean="bean";//日程对象跳转标示符
    public static final String PARAM_SUBSCRIPTION = "subscription";
    public static final String PARAM_DEVICEID = "device_user_id";

    public static final String FLAG_FIRST_LOGINSUCCESS = "first";
    public static final String PREF_VERSION = "version";
    public static final String VERSIONNAME = "versionname";
    public static final String PARAM_CONTENT = "content";
    public static final String PARAM_PRIVATE = "private";
    public static final String PARAM_IMGS = "imgs";



    public static final String SERVICE_ADD_FRIEND="service";//服务跳转到添加好友跳转标示符
    public static final String SERVICE_SHOW_FRIEND="friend_list";//显示朋友的list
    /**显示老师list，跳转界面时的参数标示符*/
    public static final String SERVICE_SHOW_TEACHER="teacher_list";
    /**显示家长的list,跳转界面时的参数标示符*/
    public static final String SERVICE_SHOW_PARENR="parent_list";
    /**在数据库中查询到的list传入adapter中*/
    public static final String  SERVICE_SHOW_DB_TEACHER="db_teacher_list";
    /**获取评论的说说twrid*/
    public static final String PARAM_TWRID = "twrid";
    public static final String PREF_NICKNAME = "nickname";
    public static final String AVATAR = "avatar";


    //存放session的值
    public static final Object SESSION_TARGETFRIEND = "targetFriend";
    //聊天
    public static final int TYPE_NO_IMAGE_TEXT = 5;
    public static final int TYPE_IMAGE_TEXT = 4;
    public static final int TYPE_VIDEO = 3;
    public static final int TYPE_AUDIO = 2;
    public static final int TYPE_IMAGE = 1;
    public static final int TYPE_TEXT = 0;
    public static final String PARAM_CONTENTTYPE = "contenttype";
    public static final int STATUS_SEND_SUCCESS = 0;
    public static final int STATUS_SEND_FAIL = -1;
    public static final int STATUS_SEND_START =-2;

    //选择照片的回调code
    public static final int REQUESTCODE_TAKE_CAMERA = 0x000001;//拍照
    public static final int REQUESTCODE_TAKE_LOCAL = 0x000002;//本地图片


    public static final String VOICE_DIR = (new StringBuilder(String.valueOf(Environment.getExternalStorageDirectory().getAbsolutePath()))).append(File.separator).append("yey").append(File.separator).append("voice").toString();

    //传递消息的TAG
    public static final String EVENT_REFRESHCONTACT = "refreshcontact";
    public static final int PUSH_PMTYPE_FRIEND =0 ;  //个人消息
    public static final int PUSH_PMTYPE_PUBLIC =1 ;  //公众号消息
    public static final int PUSH_PMTYPE_SYSTEM =2 ;  //系统消息

    //每页加载数量
    public static final int PAGESIZE = 5;
    public static final int PAGEINDEX = 1;


    public static final String DIR_ID = "dir_id";
    public static final String DIR_NAME = "dir_name";
    public static final String PHOTOLIST = "photoList";

    //上传文件的type参数
    public static final String PARAM_UPLOAD_AVATAR = "1";
    public static final String PARAM_UPLOAD_CHAT = "2";
    public static final String PARAM_UPLOAD_CLASSPHOTO = "3";//班级相册
    public static final String PARAM_UPLOAD_LIFE= "4";//生活剪影和手工作品
    public static final String PARAM_UPLOAD_WORK="6";
    public static final String PARAM_UPLOAD_AUDIO = "5";
    public static final String PARAM_UPLOAD_BATCH="7";
    public static final String PARAM_UPLOAD_GROW= "8";//成长日记
    public static final String PARAM_UPLOAD_STER= "9";//班级圈
    public static final String PARAM_TOKEN = "token";
    public static final String PARAM_PP = "pp";

    //intent传值的常量
    public static final String BUNDLE_ALBUM = "bundle_album";
    public static final String BUNDLE_INDEX = "bundle_index";
    public static final String INTENT_ALBUM_TYPE = "albumtype";
    public static final String INTENT_IMAGE_TYPE = "imagetype";//图片上传的类型: 原图，高清，普清
    public static final String INTENT_UPLOAD_TYPE = "uploadtype";//上传的业务
    public static final String INTENT_DESC = "intent_desc";//上传的描述
    public static final String BUNDLE_SHARETEXT = "bundle_sharetext";
    public static final String BUNDLE_INVITE = "bundle_invite";
    public static final String INVITETEACHER = "inviteteacher"; //邀请老师
    public static final String INVITEPARENT = "inviteparent"; //邀请家长
    public static final String INTENT_ACTION = "intent_action";
    public static final String HOMEACTIVITY = "homeactivity";

    /**图片压缩质量--普通*/
    public static final String COMMON_QUALITY_FOR_PHOTO = "generalPhoto";
    /**图片压缩质量--高清*/
    public static final String HD_QUALITY_FOR_PHOTO = "hdPhoto";
    /**图片压缩质量--原图*/
    public static final String ORIGINAL_FOR_PHOTO = "originalPhoto";

    /**从网上获取地址*/
    public static final  String GETNETADRESS = "getAddressFromNet";
    /**找回密码*/
    public static final  String RESETPASSWORD = "reset_password";
    /**上传照片&&上传音频*/
    public static final  String PARAM_FILETYPE="filetype";
    public static final  String PARAM_VOICE="voice";
    public  static final String PARAM_CommonBrowser="CommonBrowser";
    public  static final String PARAM_PublicAccount="publicAccount";

    public  static final String PARAM_FRIENDSTER_HEAD="friendsterHead";

    /**CC视频api_key*/
    public static final  String CC_API_KEY = "AZdwkF26AJBqYuTSRfis7posFsnFQOTK";
    /**CC视频user_id*/
    public static final  String CC_USER_ID = "C0A5565C00C0601A";
    /**配置下载文件路径*/
    public final static String DOWNLOAD_DIR = "CCDownload";

    //环信模块
    public static final  String SHAREPAFERENCE_TYPE = "defaultrelation";
    /**身份选择*/
    public static final  String[] RELATIONNAME = {"","爸爸","妈妈","爷爷","奶奶","外公","外婆","叔叔","阿姨"};
    /**登陆环信*/
    public final static String HUANXIN_LOGIN = "huanxin_login";
    /**注册环信*/
    public final static String HUANXIN_REGEDIT = "huanxin_regedit";

    /*****************新手引导*********************/
    /**园长接受透传action*/
    public final static int HX_DIRECTOR_ACTION = 71;
    /**老师接受透传action(当存在kid时)*/
    public final static int HX_TEACHER_HAS_KID = 72;
    /**老师接受透传消息(当不存在kid时)*/
    public final static int HX_TEACHER_NO_KID = 73;
    /**园长邀请老师加入的action**/
    public final static int DIRCOTER_INVITE_TEACHER = 74;
    /**园长邀请老师加入的action**/
    public final static int DIRCOTER_INVITE_PARENT = 75;
    /**园长邀请老师加入的action**/
    public final static int TEACHER_INVITE_PARENT = 76;
    /**园长邀请老师加入的action**/
    public final static int COMPLETE_ACCOUNT_INFO = 77;



    /**判断园长是不是第一次点击新建幼儿园的第一步*/
    public final static String DIRECTOR_ISFIRST_CREAT = "is_first_use";
    /**判断老师是不是第一次点击登记幼儿园的第一步*/
    public final static String TEACHER_ISFIRST_CREAT = "is_first_use";
    /**时光树老师能为你做什么(publicId--->作为判断唯一标示)*/
    public final static int TIMETREECANDDFROMID = 17;

    /********用来判断是不是第一次点击的判断标示符*******/
    /**中幼·教师汇*/
    public final static int TIMETREE_TEACHER_PUBLIC = 12;
    /**中幼·园长汇*/
    public final static int TIMETREE_DIRECTOR_PUBLIC = 11;
    /**中幼·家长汇*/
    public final static int TIMETREE_PARENT_PUBLIC = 13;
    /**时光树能为教师做什么*/
    public final static int TIMETREE_DO_TEACHER = 17;
    /**时光树能为园长做什么*/
    public final static int TIMETREE_DO_DIRECTOR = 16;
    /**时光树能为家长做什么*/
    public final static int TIMETREE_DO_PARENT = 18;


    /**********首次引导参数**********/
    public final static String[] PUBLIC_GUIDE_IDS= {"11","12","13"};
    public final static String[] TIMEREE_GUIDE_IDS= {"16","17","18"};
    public final static String TIMETREE_IS_FIRSTLOGIN = "first_login";
    public final static String SHAREPAFERENCE_IS_FIRST_LOGIN= "0$";
    public final static int IS_FIRST_LOOK = 0;//0表示第一次进入时光树，1表示不是第一次
    public final static String isFirstLoginLook= "first_login_look";

}

