GlideAPI

加载图片 
onImage(int url, ImageView view)
加载图片 
onImage(Context act, int url, ImageView view)
加载图片 
onImage(Context act, String url, ImageView view)
加载图片 
onImage(Activity act, int url, ImageView view)
加载图片 
onImage(android.app.Fragment fragment, int url, ImageView view)
加载图片 
onImage(android.support.v4.app.Fragment fragment, int url, ImageView view)

加载gif
onGif(Context act, String url, ImageView view) 

加载gif
onGif(Context act, Integer url, ImageView view)

/**
 * 加载圆形图片
 * 如头像
 */
onCircularImage(Context act, String url, ImageView view) 

/**
 * 加载圆角图片
 * @param dp:圆角幅度
 */
onRoundImage(Context act, String url, ImageView view, int dp)

/**
 * 加载圆角图片
 * @param icon:错误图片
 * @param dp:圆角幅度
 */
onRoundImage(Context act, String url, ImageView view,int icon, int dp)

base64转为bitmap
onBaseToBitmap64(String photoBack)

bitmap转为base64
onBitmapToBase64(Bitmap bitmap)

