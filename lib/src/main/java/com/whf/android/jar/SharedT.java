package com.whf.android.jar;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import java.util.HashSet;
import java.util.Set;

/**
 * Local storage tools class
 *
 * @author qf
 * @author wang.hai.fang
 * @since 2.5.0
 */
public final class SharedT {

     /**
     * storage string
     *
     * @param context:Context
     * @param storage_file:storage file name
     * @param key:key
     * @param value:value
     * @return true:OK
     */
    public static boolean putString(Context context, @Nullable String storage_file, String key, @Nullable Set<String> value) {
        SharedPreferences sp = context.getSharedPreferences(storage_file,Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putStringSet(key, value);
        return editor.commit();
    }

    /**
     * get storage string
     *
     * @param context:Context
     * @param storage_file:storage file name
     * @param key:key
     * @return true:OK
     */
    public static Set<String> getString(Context context, @Nullable String storage_file, String key) {
        Set<String> value = new HashSet<>();
        SharedPreferences sp = context.getSharedPreferences(storage_file, Activity.MODE_PRIVATE);
        return sp.getStringSet(key, value);
    }
    
    /**
     * storage string
     *
     * @param context:Context
     * @param storage_file:storage file name
     * @param key:key
     * @param value:value
     * @return true:OK
     */
    public static boolean putString(Context context, @Nullable String storage_file, String key, @Nullable String value) {
        SharedPreferences sp = context.getSharedPreferences(storage_file,
                Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    /**
     * get storage string
     *
     * @param context:Context
     * @param storage_file:storage file name
     * @param key:key
     * @param value:value
     * @return true:OK
     */
    public static String getString(Context context, @Nullable String storage_file, String key, @Nullable String value) {
        SharedPreferences sp = context.getSharedPreferences(storage_file,
                Activity.MODE_PRIVATE);
        return sp.getString(key, value);
    }

    /**
     * storage boolean
     *
     * @param context:Context
     * @param storage_file:storage file name
     * @param key:key
     * @param value:value
     * @return true:OK
     */
    public static boolean putBoolean(Context context, @Nullable String storage_file, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences(storage_file,
                Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    /**
     * get storage boolean
     *
     * @param context:Context
     * @param storage_file:storage file name
     * @param key:key
     * @param value:value
     * @return true:OK
     */
    public static boolean getBoolean(Context context, @Nullable String storage_file, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences(storage_file,
                Activity.MODE_PRIVATE);
        return sp.getBoolean(key, value);
    }

    /**
     * storage int
     *
     * @param context:Context
     * @param storage_file:storage file name
     * @param key:key
     * @param value:value
     * @return true:OK
     */
    public static boolean putInt(Context context, @Nullable String storage_file, String key, int value) {
        SharedPreferences sp = context.getSharedPreferences(storage_file,
                Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    /**
     * get storage int
     *
     * @param context:Context
     * @param storage_file:storage file name
     * @param key:key
     * @param value:value
     * @return true:OK
     */
    public static int getInt(Context context, @Nullable String storage_file, String key, int value) {
        SharedPreferences sp = context.getSharedPreferences(storage_file,
                Activity.MODE_PRIVATE);
        return sp.getInt(key, value);
    }

    /**
     * storage Long
     *
     * @param context:Context
     * @param storage_file:storage file name
     * @param key:key
     * @param value:value
     * @return true:OK
     */
    public static boolean putLong(Context context, @Nullable String storage_file, String key, long value) {
        SharedPreferences sp = context.getSharedPreferences(storage_file,
                Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    /**
     * gte storage Long
     *
     * @param context:Context
     * @param storage_file:storage file name
     * @param key:key
     * @param value:value
     * @return true:OK
     */
    public static long getLong(Context context, @Nullable String storage_file, String key, long value) {
        SharedPreferences sp = context.getSharedPreferences(storage_file,
                Activity.MODE_PRIVATE);
        return sp.getLong(key, value);
    }

}

