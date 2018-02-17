package com.clozeblur.re.aws.migrate.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by yuanjx
 * on 2017/12/6
 */
public class AwsMigrateQueue extends LinkedBlockingQueue<String> {

    private AwsMigrateQueue() {}

    private static final long serialVersionUID = -8140615139477277295L;
    private static final Logger logger = LoggerFactory.getLogger(AwsMigrateQueue.class);

    private static BlockingQueue<String> queue = new LinkedBlockingQueue<>(300);

    public static Integer getQueueSize() {
        return queue.size();
    }

    public static Boolean containsAws(String name) {
        return queue.contains(name);
    }

    public static String getAws() {
        try {
            return queue.take();
        } catch (InterruptedException e) {
            logger.error("获取队列异常,异常信息: {}", e);
        }
        return null;
    }

    public static void putAws(String name) {
        try {
            queue.put(name);
        } catch (InterruptedException e) {
            logger.error("插入队列异常,异常信息: {}", e);
        }
    }
}
