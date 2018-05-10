package com.whf.android.jar;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.whf.android.jar.util.CircleImageUtil;
import com.whf.android.jar.util.RoundImageUtil;

/**
 * add images
 *
 * @author : qf.
 * @author wang.hai.fang
 * @since 2.5.0
 */
public final class GlideT {

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
                .placeholder(android.R.mipmap.sym_def_app_icon)
                .error(android.R.mipmap.sym_def_app_icon)
                .into(view);
    }


    /**
     * add GIF image
     *
     * @param act:Context
     * @param url:gif     image
     * @param view:View
     */
    public static void onGif(Context act, String url, ImageView view) {
        Glide.with(act)
                .load(url)
                .asGif()
                .error(android.R.mipmap.sym_def_app_icon)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(view);
    }

    /**
     * add GIF image
     *
     * @param act:Context
     * @param url:gif     image
     * @param view:View
     */
    public static void onGif(Context act, Integer url, ImageView view) {
        Glide.with(act)
                .load(url)
                .asGif()
                .error(android.R.mipmap.sym_def_app_icon)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
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
                .placeholder(android.R.mipmap.sym_def_app_icon)
                .error(android.R.mipmap.sym_def_app_icon)
                .transform(new CircleImageUtil(act))
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
        onRoundImage(act, url, view, android.R.mipmap.sym_def_app_icon, dp);
    }

    /**
     * load normal  for round img round
     *
     * @param act:Context
     * @param url:Picture path (local, network)
     * @param view:view
     * @param dp:Fillet   size(The minimum is 4)
     */
    public static void onRoundImage(Context act, String url, ImageView view, int icon, int dp) {
        if (dp < 4) {
            dp = 4;
        }
        Glide.with(act)
                .load(url)
                .placeholder(icon)
                .error(icon)
                .transform(new RoundImageUtil(act, dp))
                .into(view);
    }


}
