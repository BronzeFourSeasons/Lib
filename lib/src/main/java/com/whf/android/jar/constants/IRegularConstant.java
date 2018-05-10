package com.whf.android.jar.constants;

/**
 * regular expression
 *
 * @author haifeng.wang
 * @since 2.5.0
 */
public interface IRegularConstant {

    /**
     * decimal (Up to eight decimal places)
     */
    String DOUBLE_NUMBER = "^(([1-9]\\d*)|(0))(\\.\\d{0,8})";

    /**
     * positive integer
     */
    String WHOLE_NUMBER = "^[0-9]\\d*$";

    /**
     * ID
     */
    String ID_CARD = "\\d{15}|\\d{18}";

    /**
     * address ip
     */
    String IP_LOCATION = "\\d+\\.\\d+\\.\\d+\\.\\d+";

    /**
     * Postal Code
     */
    String CHINA_POSTCODES = "[1-9]\\d{5}(?!\\d)";

    /**
     * phone
     */
    String PHONE = "\\d{3}-\\d{8}|\\d{4}-\\d{7}";

    /**
     * Password
     */
    String PASSWORD = "^[a-zA-Z][\\w]{4,15}$";


    /**
     * Website
     */
    String WEBSITE = "((http|ftp|https)://)(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?";


}