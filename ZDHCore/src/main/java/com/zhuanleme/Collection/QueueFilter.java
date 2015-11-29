package com.zhuanleme.Collection;

/**
 * <p>Project: com.zhuanleme.Collection</p>
 * <p>Title: QueueFilter.java</p>
 * <p/>
 * <p>Description: QueueFilter </p>
 * <p/>
 * <p>Copyright: Copyright (c) 2015 </p>
 * <p/>
 *
 * @author zhangdihong
 * @version 1.0
 * @date 2015/11/27
 */
public interface QueueFilter<T> {
    public boolean filter(T t);
}
