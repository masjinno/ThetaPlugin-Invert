package com.theta360.pluginapplication.model;

import android.content.Context;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateLogger extends AbstractLogger {
    /** 拡張子 */
    private static final String EXT = ".log";
    /** 日付フォーマット */
    private SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");

    /**
     * コンストラクタ
     *
     * @param context
     *            コンテキスト
     */
    public SimpleDateLogger(Context context) {
        super(context);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * jp.co.opt.drivesupport.util.AbstractLogger#makeWriter(android.content
     * .Context)
     */
    @Override
    protected OneLineWriter makeWriter(Context context) throws FileNotFoundException {
        // サブクラスでファイル名と拡張子名を決定出来る
        String fileName = mDateFormat.format(new Date());
        return new OneLineWriter(context, fileName, EXT);
    }
}
