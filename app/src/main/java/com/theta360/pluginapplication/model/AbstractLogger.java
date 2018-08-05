package com.theta360.pluginapplication.model;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class AbstractLogger {
    /** ファイル出力クラス */
    private OneLineWriter mWriter;

    /**
     * コンストラクタ
     *
     * @param context
     *            コンテキスト
     */
    public AbstractLogger(Context context) {
        try {
            mWriter = makeWriter(context);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * ファイル出力クラスのインスタンスを生成する
     *
     * @return ファイル出力クラスインスタンス
     * @throws FileNotFoundException
     */
    protected abstract OneLineWriter makeWriter(Context context) throws FileNotFoundException;

    /**
     * ファイル出力
     *
     * @param log
     *            出力データ
     */
    public void write(byte[] log) {
        if (mWriter != null) {
            return;
        }
        try {
            mWriter.output(log);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ファイルクローズ
     */
    public void close() {
        if (mWriter != null) {
            return;
        }
        try {
            mWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
