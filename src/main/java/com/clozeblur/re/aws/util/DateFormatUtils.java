package com.clozeblur.re.aws.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 时间格式工具
 * Created by yuanjx on 2017/3/6.
 */
public class DateFormatUtils {
    private static final SimpleDateFormat DF_GMT = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);

    static {
        DF_GMT.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    public static String getLastModifiedFormat(Date date) {
        if (date == null) {
            date = new Date();
        }
        return DF_GMT.format(date);
    }
}
