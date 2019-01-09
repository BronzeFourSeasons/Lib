package com.whf.android.jar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Base64;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.whf.android.jar.base.BaseApplication;
import com.whf.android.jar.util.RoundImageUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * add images
 *
 * @author : qf.
 * @author wang.hai.fang
 * @since 2.5.0
 */
public final class GlideT {

    private static final int ROUND = 5;

    /**
     * add image
     *
     * @param act:Context
     * @param url:String
     * @param view:View
     */
    public static void onImage(Context act, String url, ImageView view) {
        Glide.with(act)
                .load(url)
                .into(view);
    }

    /**
     * add image
     *
     * @param url:String
     * @param view:View
     */
    public static void onImage(int url, ImageView view) {
        onImage(BaseApplication.getContext(), url, view);
    }

    /**
     * add image
     *
     * @param act:Context
     * @param url:String
     * @param view:View
     */
    public static void onImage(Context act, int url, ImageView view) {
        if (act != null) {
            Glide.with(act)
                    .load(url)
                    .into(view);
        }
    }

    /**
     * add image
     *
     * @param act:Context
     * @param url:String
     * @param view:View
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void onImage(Activity act, int url, ImageView view) {
        if (!act.isDestroyed()) {
            Glide.with(act)
                    .load(url)
                    .into(view);
        }
    }


    /**
     * add image
     *
     * @param fragment:Context
     * @param url:String
     * @param view:View
     */
    public static void onImage(android.app.Fragment fragment, int url, ImageView view) {
        if (fragment != null && fragment.getActivity() != null) {
            Glide.with(fragment)
                    .load(url)
                    .into(view);
        }
    }

    /**
     * add image
     *
     * @param fragment:Context
     * @param url:String
     * @param view:View
     */
    public static void onImage(android.support.v4.app.Fragment fragment, int url, ImageView view) {
        if (fragment != null && fragment.getActivity() != null) {
            Glide.with(fragment)
                    .load(url)
                    .into(view);
        }
    }


    /**
     * load normal  for circle img Circular
     *
     * @param act:Context
     * @param url:Picture path (local, network)
     * @param view:view
     */
    public static void onCircularImage(Context act, int url, ImageView view) {
        Glide.with(act)
                .load(url)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(view);
    }

    /**
     * load normal  for circle img Circular
     *
     * @param act:Context
     * @param url:Picture path (local, network)
     * @param view:view
     */
    public static void onCircularImage(Context act, String url, ImageView view) {
        Glide.with(act)
                .load(url)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(view);
    }

    /**
     * load normal  for round img round
     *
     * @param act:Context
     * @param url:Picture path (local, network)
     * @param view:view
     */
    public static void onRoundImage(Context act, int url, ImageView view) {
        Glide.with(act)
                .load(url)
                .apply(RequestOptions.bitmapTransform(new RoundImageUtil()))
                .into(view);
    }

    /**
     * load normal  for round img round
     *
     * @param act:Context
     * @param url:Picture path (local, network)
     * @param view:view
     * @param dp:Fillet   size(The minimum is 4)
     */
    public static void onRoundImage(Context act, int url, ImageView view, int dp) {
        if (dp < ROUND) {
            dp = ROUND;
        }
        Glide.with(act)
                .load(url)
                .apply(RequestOptions.bitmapTransform(new RoundImageUtil(dp)))
                .into(view);
    }

    /**
     * load normal  for round img round
     *
     * @param act:Context
     * @param url:Picture path (local, network)
     * @param view:view
     */
    public static void onRoundImage(Context act, String url, ImageView view) {
        Glide.with(act)
                .load(url)
                .apply(RequestOptions.bitmapTransform(new RoundImageUtil()))
                .into(view);
    }

    /**
     * load normal  for round img round
     *
     * @param act:Context
     * @param url:Picture path (local, network)
     * @param view:view
     * @param dp:Fillet   size(The minimum is 4)
     */
    public static void onRoundImage(Context act, String url, ImageView view, int dp) {
        if (dp < ROUND) {
            dp = ROUND;
        }
        Glide.with(act)
                .load(url)
                .apply(RequestOptions.bitmapTransform(new RoundImageUtil(dp)))
                .into(view);
    }

    /**
     * 注意：
     * 需要去掉字符串的data:image/png;base64,
     *
     * @param photoBack:Base64字符串
     * @return Bitmap
     */
    public static Bitmap onBaseToBitmap64(String photoBack) {
        Bitmap bitmap = null;
        try {
            LogT.i("图片>" + photoBack.length());
            byte[] bitmapArray = Base64.decode(photoBack.split(",")[1], Base64.DEFAULT);
            LogT.i("图片bitmapArray>" + bitmapArray.length);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            LogT.e("转码>" + e.getMessage());
        }
        return bitmap;
    }


    /**
     * bitmap转为base64
     */
    public static String onBitmapToBase64(Bitmap bitmap) {

        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            LogT.e(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                LogT.e(e.getMessage());
            }
        }
        return result;
    }
}
