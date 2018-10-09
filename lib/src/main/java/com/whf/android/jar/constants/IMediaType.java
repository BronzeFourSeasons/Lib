package com.whf.android.jar.constants;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * http上传类型
 *
 * @author qf
 * @author wang.hai.fang
 * @since 2.5.0
 */

@StringDef({
        IMediaType.MEDIA_IMAGE,
        IMediaType.MEDIA_PNG,
        IMediaType.MEDIA_JPG,
        IMediaType.MEDIA_JSON,
        IMediaType.MEDIA_TEXT,
        IMediaType.MEDIA_TYPE,
        IMediaType.MEDIA_DATE,
        IMediaType.MEDIA_3GP,
        IMediaType.MEDIA_MP4,
        IMediaType.MEDIA_VIDEO,
        IMediaType.MEDIA_AUDIO
})
@Retention(RetentionPolicy.SOURCE)
public @interface IMediaType {
    String MEDIA_IMAGE = "image/*";
    String MEDIA_PNG = "image/png";
    String MEDIA_JPG = "image/jpg";
    
    String MEDIA_JSON = "application/json;charset=utf-8";
    String MEDIA_TEXT = "text/plain";
    String MEDIA_TYPE = "text/x-markdown; charset=utf-8";
    String MEDIA_DATE = "multipart/form-data";
    
    String MEDIA_3GP = "video/3gp";
    String MEDIA_MP4 = "video/mp4";
    String MEDIA_VIDEO = "video/*";

    String MEDIA_AUDIO = "audio/*";
}
