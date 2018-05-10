# Lib
我的项目基础

引入方式：
1.在项目bulid.gradle中添加
allprojects {
repositories {
    jcenter()
    maven { url 'https://jitpack.io' }
}
2.在app的bulid.gradle中添加引用  


具体使用步骤：
GlideT：  --加载图片;
1. GlideT.onImage(this,"http://1.12.123.14:55/tkke.jpg",mImageView); -- 加载图片。
2. GlideT.onGif(this,"http://1.12.123.14:55/tkke.gif",mImageView);  --加载动态图片。
3. GlideT.onCircularImage(this,"http://1.12.123.14:55/tkke.jpg",mImageView);  --加载图片,图片圆形展示。
4. GlideT.onRoundImage(this,"http://1.12.123.14:55/tkke.jpg",mImageView); // GlideT.onRoundImage(this,"http://1.12.123.14:55/tkke.jpg"，R.mipmap.ic.png,mImageView); --加载图片,图片圆角展示。R.mipmap.ic.png(默认图片)

IntentT   --界面跳转;
IntentT.onIntent(this,xxxx.class); -IntentT.onIntent(this,xxxx.class,mBundle); --带参数或不带参数跳转。

KeyBoardT --开关软键盘;
KeyBoardT.openKeyBord(mEditView,this);   KeyBoardT.closeKeyBord(mEditView,this);   --输入框的软键盘键盘(展示/关闭)。

LogT --log重简写
ToastT --Toast重简写
ToastT.makeTextLong(); ToastT.makeTextShort();

SharedT --键值对存储;
SharedT.putString(this,"文件名","键","值");
SharedT.getString(this,"文件名","键","没有显示的值");

NetT --网络状态工具类;
NetT.isConnected();     -- 是否有网；
NetT.isWifi();          --是否是WiFi连接；
NetT.openNetSetting();  --打开网络设置界面
NetT.openWifiSetting();  --打开wifi设置界面
NetT.open4GSetting();  --打开4G网络设置界面

base.BaseCommonAdapter --对BaseAdapter简写；

