package com.jzk.hebi_wms.http.callback;

/**
 * $dsc
 * author: timi
 * create at: 2018-07-02 09:32
 */

public interface HttpDownloadCallBack {
    /**
     * 下载进度
     *
     * @param progress
     * @param total
     */
    void onProgress(int progress, long total);

    /**
     * 下载完成
     */
    void downLoadSuccess();
}
