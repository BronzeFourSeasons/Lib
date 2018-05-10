package com.whf.android.jar.constants;

/**
 * picture selection
 *
 * @author haifeng.wang
 * @since 2.5.0
 */
public interface IPictureConstant {

    /* Correct code name */
    int YES = 200;

    /* Error code */
    int NO = 100;

    /**
     * Get pictures from (Album)
     * (If there is conflict, remember to amend)
     */
    int GET_BY_ALBUM = 801;

    /**
     * Get pictures from (Photograph)
     * (If there is conflict, remember to amend)
     */
    int GET_BY_CAMERA = 803;

    /**
     * Get pictures from (Cut)
     * (If there is conflict, remember to amend)
     */
    int CROP = 805;


    /* Default output picture name for photo and album */
    String OUTPUT_IMAGE = "temp.jpg";

}
