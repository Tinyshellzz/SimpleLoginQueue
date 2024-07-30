package com.tinyshellzz.simpleLoginQueue.service;

import com.tinyshellzz.simpleLoginQueue.utils.Pair;

import java.util.Date;
import java.util.UUID;

import static com.tinyshellzz.simpleLoginQueue.ObjectPool.queue;

public class QueueService {
    public static int waitLength = 1;
    public static Date serverEmptyTime;

    /**
     *
     * @param uuid
     * @return
     *  - -1: 未找到
     *  - 其他: 玩家的下标
     */
    public static int getIndex(UUID uuid) {
        int size = queue.size();
        int ret = -1;

        for (int i = 0; i < size; ) {
            Pair<UUID, Date> p = queue.get(i);
            long diff = new Date().getTime() - p.getSecond().getTime();
            // 超过10分钟没连，就将玩家移出队列
            if(diff / (60 * 1000) >= 10) {
                queue.remove(i);
                size--;
                continue;
            }
            if(p.getFirst().equals(uuid)) {
                ret = i;
            }

            i++;
        }

        return ret;
    }

    public static void updateWaitLength() {
        // 超过30s没人登录, 就扩大waitLength
        long diff = new Date().getTime() - serverEmptyTime.getTime();
        if(diff / 1000 >= 30) {
            waitLength = Math.min(queue.size(), waitLength<<1);
        }
    }

    public static boolean isInWaitList(int index) {
        return index < waitLength;
    }
}
