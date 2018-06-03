package com.whf.android.jar.net;

/**
 * 进度监听器
 *
 * @author qf
 * @author wang.hai.fang
 * @since 2.5.0
 */
public interface OnProgressListener {

    /**
     * @param progress 已经下载或上传字节数
     * @param total    总字节数
     * @param done     是否完成
     */
    void onProgress(long progress, long total, boolean done);

}
