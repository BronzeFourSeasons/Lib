package com.whf.android.jar.base;

import android.app.Application;
import android.content.Context;

import com.whf.android.jar.util.ResolutionUtil;

/**
 * BaseApplication
 *
 * @author : qf.
 * @author wang.hai.fang
 * @since 2.5.0
 */
public class BaseApplication extends Application {

    protected static Context context;
    /**
     * Current login status
     */
    private User loginUser = new User();

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        ResolutionUtil.getInstance().init(this);
    }

    public static Context getContext() {
        return context;
    }


    public User getLoginUser() {
        return loginUser;
    }

    public void userLogin(User user) {
        loginUser.setUserLogin(user.isUserLogin());
    }

    public void userLogout() {
        loginUser = new User();
    }

    /**
     * Current login status
     */
    private class User {
        /**
         * login status
         */
        private boolean UserLogin;

        /**
         * set login status
         */
        public boolean isUserLogin() {
            return UserLogin;
        }

        /**
         * get login status
         */
        public void setUserLogin(boolean userLogin) {
            UserLogin = userLogin;
        }
    }

}