package com.zhuanleme.bean;

import java.util.Map;

/**
 * <p>Project: com.zhuanleme.bean</p>
 * <p>Title: BeanUtil.java</p>
 * <p/>
 * <p>Description: BeanUtil </p>
 * <p/>
 * <p>Copyright: Copyright (c) 2015 </p>
 * <p/>
 * <p>Company: 华炜云商科技有限公司 www.hwtech.cc</p>
 *
 * @author zhangdihong
 * @version 1.0
 * @date 2015/11/29
 */
public class BeanUtil {

    public static Map<String, BeanStruct> getBeanStruct(Object object){
        return BeanFactory.BEAN_SIMPLE_PROPERTIES.get(object);
    }

    public static Map<String, BeanStruct> getBeanStructIgnore(Object object){
        return BeanFactory.BEAN_SIMPLE_PROPERTIES_IGNORE.get(object);
    }
    
}
