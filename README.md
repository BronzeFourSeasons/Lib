# LibDemo
我的项目基础

# 引入方式 #
![引入方式](https://github.com/BronzeFourSeasons/Lib/blob/master/API/UseBag.png)


# 具体使用步骤 #

GlideT [加载图片工具类](https://github.com/BronzeFourSeasons/Lib/blob/master/API/Glide.md#glideapi)

##  1.界面跳转 ##
IntentT.onIntent(this,xxxx.class); 
IntentT.onIntent(this,xxxx.class,mBundle); --带参数或不带参数跳转。

##  2.KeyBoardT --开关软键盘 ##  
KeyBoardT.openKeyBord(mEditView,this); 
KeyBoardT.closeKeyBord(mEditView,this);
--输入框的软键盘键盘(展示/关闭)。

## 3.LogT --log重简写 ##

## 4.Toast重简写 ##  
ToastT.makeTextLong(); ToastT.makeTextShort();

## 5.SharedT --键值对存储 ##
SharedT.putString(this,"文件名","键","值");
SharedT.getString(this,"文件名","键","没有显示的值");

## 6.NetT --网络状态工具类 ##  
NetT.isConnected();     -- 是否有网；
NetT.isWifi();          --是否是WiFi连接；
NetT.openNetSetting();  --打开网络设置界面
NetT.openWifiSetting();  --打开wifi设置界面
NetT.open4GSetting();  --打开4G网络设置界面

base.BaseCommonAdapter --对BaseAdapter简写；

## 7.在线预览pdf文件 ##
 Bundle bundle = new Bundle();
 bundle.putString(IConstant.PDF_URL, "http://1.12.123.14:55/tkke.pdf");
 startActivity(IntentT.onIntent(context, PDFDatabaseActivity.class, bundle));

## 8.弹框输入提示输入界面 ##
> Bundle bundle = new Bundle();
- bundle.putString(DiaInputActivity.TITLE, "设置标题");
- bundle.putString(DiaInputActivity.MESSAGE, "设置输入提示");
- bundle.putString(DiaInputActivity.BUTTON_1, "设置按钮1显示值");
- bundle.putString(DiaInputActivity.BUTTON_2, "设置按钮2显示值");
- startActivityForResult(IntentT.onIntent(context, DiaInputActivity.class, bundle), DiaInputActivity.INT_INTENT);

-   @Override
-   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
-         super.onActivityResult(requestCode, resultCode, data);
-         if (requestCode == DiaInputActivity.INT_INTENT && resultCode == DiaInputActivity.INT_INTENT) {
-             int v = data.getIntExtra(DiaInputActivity.KEY, 0);--按钮判断
-             String opinion = data.getStringExtra(DiaInputActivity.OPINION);--输入的值
-             LogT.i("KEY>>" + v + " type>>" + type + " opinion>>" + opinion);
-             if (DiaInputActivity.INT_YES == v) {    
-             }
-         }
-    }

## 9.http上传类型 ##
IMediaType.MEDIA_PNG,
IMediaType.MEDIA_JPG,
