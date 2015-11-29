package com.zhuanleme.Collection;

/**
 * <p>Project: com.zhuanleme.Collection</p>
 * <p>Title: CollectionUtil.java</p>
 * <p/>
 * <p>Description: CollectionUtil </p>
 * <p/>
 * <p>Copyright: Copyright (c) 2015 </p>
 * <p/>
 *
 * @author zhangdihong
 * @version 1.0
 * @date 2015/11/27
 */

import com.zhuanleme.util.ValidUtil;
import org.apache.commons.collections.map.HashedMap;

import java.util.*;

/**
 * 封装一些机会相关的工具类
 * Collection <--List<--Vector
 * Collection <--List<--ArrayList
 * Collection <--List<--LinkedList
 * Collection <--Set<--HashSet
 * Collection <--Set<--HashSet<--LinkedHashSet
 * Collection <--Set<--SortedSet<-- TreeSet
 * Map <--SortedMap<--TreeMap
 * Map <-- HashMap
 */
public class CollectionUtil {
    //去重
    public static  <T> List<T> removeDuplicate(List<T> list){
        Set set = new HashSet();
        List newList = new ArrayList();
        for (Iterator iterator =  list.iterator(); iterator.hasNext();){
            Object element = iterator.next();
            if(set.add(element)){
                newList.add(element);
            }
        }
        return newList;
    }
    //过滤
    public static <T> List<T> Filter(List<T> list, ListFilter filter){
        List result = new ArrayList();
        if (ValidUtil.isValid(list)) {
            for (T t : list){
                if(filter.filter(t)){
                    result.add(t);
                }
            }
        }
        return result;
    }
    public static <T> Set<T> Filter(Set<T> set, SetFilter filter){
        Set result = new HashSet();
        if(ValidUtil.isValid(set)){
            for (T t : set) {
                if(filter.filter(t)){
                    result.add(t);
                }
            }
        }
        return result;
    }
    public static <T> Queue Filter(Queue<T> queue, QueueFilter filter){
        Queue result = new LinkedList();
        if (ValidUtil.isValid(queue)) {
            for (T t : queue) {
                if(filter.filter(t)){
                    result.add(t);
                }
            }
        }
        return result;
    }

    public static <k, V> Map Filter(Map<k, V> map, MapFilter filter){
        Map result = new HashMap();
        if (ValidUtil.isValid(map)) {
            for(Map.Entry<k, V> entry : map.entrySet()){
                if(filter.filter(entry)){
                    result.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return result;
    }
    public static <T> List<T> intersection(List<T> li1, List<T> li2){
        if (ValidUtil.isValid(li1, li2)) {
            Set<T> set = new HashSet<>(li1);
            set.retainAll(li2);
            return new ArrayList<>(set);
        }
        return new ArrayList<T>();
    }

    public static <T> Set<T> intersection(Set<T> set1, Set<T> set2){
        if (ValidUtil.isValid(set1, set2)) {
            List<T> list = new ArrayList<T>(set1);
            list.retainAll(set2);
            return new HashSet<T>(list);
        }
        return new HashSet<T>();
    }

    public static <k, V> Map<k,V> intersection(Map<k,V> map1, Map<k, V> map2){
        Map<k, V> map = new HashMap<>();
        if (ValidUtil.isValid(map1, map2)) {
            Set<k> setkey1 = new HashSet<>(map1.keySet());
            Set<k> setkey2 = new HashSet<>(map2.keySet());
            setkey1.retainAll(setkey2);
            for (k k : setkey1) {
                map.put(k, map1.get(k));
            }
        }
        return map;
    }
    public static <T> List<T> unicon(List<T> list1, List<T> list2){
        List<T> list = new ArrayList<>();
        list.addAll(list1);
        list.addAll(list2);
        return list;
    }
    public static <T> Set<T> unicon(Set<T> set1, Set<T> set2){
        Set<T> set = new HashSet<>();
        set = set1;
        set.addAll(set2);
        return set;
    }
    public static <T> Queue<T> unicon(Queue<T> queue1, Queue<T> queue2){
        Queue<T> queue = new LinkedList<>(queue1);
        queue.addAll(queue2);
        return queue;
    }
    public static <k, v> Map<k, v> unicon(Map<k, v> map1, Map<k, v> map2){
        Map<k, v> map = new HashedMap();
        map.putAll(map1);
        map.putAll(map2);
        return map;
    }

    public  static <T> List<T> subtract(List<T> list1, List<T> list2){
        List<T> list = new ArrayList<>();
        if(ValidUtil.isValid(list1)){
            list.addAll(list1);
            list.removeAll(list2);
        }
        return list;
    }

    public static <T> Set<T> subtract(Set<T> set1, Set<T> set2){
        Set<T> set = new HashSet<>();
        if(ValidUtil.isValid(set)){
            set.addAll(set1);
            set.removeAll(set2);

        }
        return set;
    }

    public static <T> Queue<T> subtract(Queue<T> queue1, Queue<T> queue2){
        Queue<T> queue = new LinkedList<>();
        if (ValidUtil.isValid(queue1)) {
            queue.addAll(queue1);
            queue.removeAll(queue2);
        }
        return queue;

    }
    public static <K, v> Map<K, v> subtract(Map<K, v> map1, Map<K, v> map2){
        Map<K, v> map = new HashMap<>();
        if(ValidUtil.isValid(map1, map2)){
            Set<K> set1 = new HashSet<>(map1.keySet());
            Set<K> set2 = new HashSet<>(map2.keySet());
            for (K k : set2) {
                set1.remove(k);
            }
            for (K k : set1) {
                map.put(k, map1.get(k));
            }
        }
        return map;
    }

}