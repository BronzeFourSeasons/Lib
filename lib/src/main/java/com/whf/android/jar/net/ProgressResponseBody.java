package com.whf.android.jar.net;


import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * 计算下载百分比
 * Description: 带进度 下载请求体
 *
 * @author qf
 * @author wang.hai.fang
 * @since 2.5.0
 */
public class ProgressResponseBody extends ResponseBody {
    private final ResponseBody responseBody;
    private final OnProgressListener progressListener;

    /**
     * BufferedSource 是okio库中的输入流，这里就当作inputStream来使用。
     */
    private BufferedSource bufferedSource;

    public ProgressResponseBody(ResponseBody responseBody, OnProgressListener progressListener) {
        this.responseBody = responseBody;
        this.progressListener = progressListener;
    }

    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }


    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(source(responseBody.source()));
        }
        return bufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {
            long totalBytesRead = 0L;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                if (null != progressListener) {
                    progressListener.onProgress(totalBytesRead, responseBody.contentLength(), bytesRead == -1);
                }
                return bytesRead;
            }
        };
    }
}
