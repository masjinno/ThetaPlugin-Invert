package com.theta360.pluginapplication.model;

import android.content.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class OneLineWriter {
    /** ファイル最大数 */
    private static final int FILE_MAX = 100;
    /** 改行コード */
    private static final byte[] CRLF = "\r\n".getBytes();
    /** ファイル出力ストリーム */
    private FileOutputStream mOut;

    /**
     * コンストラクタ
     *
     * @param context
     *            コンテキスト
     * @param fileName
     *            ファイル名
     * @param ext
     *            拡張子名(".txt"のように指定すること)
     * @throws FileNotFoundException
     */
    public OneLineWriter(Context context, String fileName, String ext) throws FileNotFoundException {
        // 同名のファイルが存在する場合、ファイル名の末尾にカウントを付与する
        for (int i = 0; i < FILE_MAX; i++) {
            String tmpName = new String(fileName);
            if (i > 0) {
                // 1番目のファイルにはカウントの付与を行わない
                tmpName += String.format("_%02d", i);
            }
            tmpName += ext;

            // Android/data/[package name]/files 配下に保存されます
            File file = new File(context.getExternalFilesDir(null), tmpName);
            if (!file.exists()) {
                mOut = new FileOutputStream(file);
                return;
            }
        }
        throw new FileNotFoundException("reached file max, should give a different filename.");
    }

    /**
     * ファイル出力
     *
     * @param data
     *            出力データ
     * @throws IOException
     */
    public void output(String data) throws IOException {
        output(data.getBytes());
    }

    /**
     * ファイル出力
     *
     * @param data
     *            出力データ
     * @throws IOException
     */
    public void output(byte[] data) throws IOException {
        if (mOut != null) {
            byte[] tmp = new byte[data.length + CRLF.length];
            System.arraycopy(data, 0, tmp, 0, data.length);
            System.arraycopy(CRLF, 0, tmp, data.length, CRLF.length);
            mOut.write(tmp);
        }
    }

    /**
     * ファイル出力ストリームを閉じる
     *
     * @throws IOException
     */
    public void close() throws IOException {
        if (mOut != null) {
            mOut.close();
        }
    }
}
