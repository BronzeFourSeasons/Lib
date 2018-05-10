package com.whf.android.jar;

import android.media.MediaPlayer;
import android.media.MediaRecorder;

import java.io.IOException;

/**
 * 跟录音相关的工具类
 *
 * @author 汪海峰
 * @author wang.hai.fang
 * @since 2.5.0
 */
public final class RecordT {


    //语音操作对象
    private static MediaPlayer mPlayer = null;
    private static MediaRecorder mRecorder = null;

    /**
     * 构造函数
     * 异常获取
     */
    private RecordT() {
        /* cannot be instantiated(不能被实例化) */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 开始录音
     *
     * @param FileName:文件存储路径
     */
    public static void startRecord(String FileName) {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(FileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            mRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mRecorder.start();
    }

    /**
     * 停止录音
     */
    public static void stopRecord() {
        if (mRecorder != null) {
            mRecorder.stop();
            mRecorder.release();
            mRecorder = null;
        }
    }

    /**
     * 开始播放录音
     *
     * @param FileName:文件存储路径
     */
    public static void startRecordPlay(String FileName) {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(FileName);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.print("播放失败");
        }
    }

    /**
     * 停止播放录音
     */
    public static void stopRecordPlay() {
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }


}
