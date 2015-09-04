package com.zhuanleme.common;

import java.io.IOException;
import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.net.NetworkInterface;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.Enumeration;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>Project: com.zhuanleme.common</p>
 * <p>Title: ObjectId.java</p>
 * <p/>
 * <p>Description: ObjectId </p>
 * <p/>
 * <p>Copyright: Copyright (c) 2015 </p>
 * <p/>
 *
 * @author zhangdihong
 * @version 1.0
 * @date 2015/9/4
 */
public class ObjectId implements Comparable<ObjectId>, Serializable {

    final int _machine;
    final int _time;
    final int _inc;
    boolean _new;
    // thread safe increase num
    private static AtomicInteger _nextInteger = new AtomicInteger((new Random()).nextInt());
    private static final int _genmachine;

    static {
        try {
            // generation 2-byte machine piece based on NICs ifo
            final int machinePiece;
            {
                StringBuilder stringBuilder = new StringBuilder();
                // machine NICs
                Enumeration<NetworkInterface> networkInterfaceEnumeration = NetworkInterface.getNetworkInterfaces();
                while (networkInterfaceEnumeration.hasMoreElements()) {
                    NetworkInterface networkInterface = networkInterfaceEnumeration.nextElement();
                    stringBuilder.append(networkInterface.toString());
                }
                machinePiece = stringBuilder.toString().hashCode() << 16;
            }
            final int processPiece;
            {
                int processId = new Random().nextInt();
                try {
                    processId = ManagementFactory.getRuntimeMXBean().getName().hashCode();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
                ClassLoader classLoader = ObjectId.class.getClassLoader();
                int classLoaderId = classLoader != null ? System.identityHashCode(classLoader) : 0;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(Integer.toHexString(processId));
                stringBuilder.append(Integer.toHexString(classLoaderId));
                processPiece = stringBuilder.toString().hashCode() & 0xFFFF;
            }
            _genmachine = machinePiece | processPiece;
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    /**
     * create a new objectId
     */
    public ObjectId() {
        _time = (int) (System.currentTimeMillis() / 1000);
        _machine = _genmachine;
        _inc = _nextInteger.getAndIncrement();
        _new = true;
    }

    /**
     * create a new objectId from a string
     *
     * @param s
     */
    public ObjectId(String s) {
        this(s, false);
    }

    /**
     * create a new object id assigned Date
     *
     * @param time
     */
    public ObjectId(Date time) {
        this(time, _genmachine, _nextInteger.getAndIncrement());
    }

    /**
     * create a new object id assigned Date and increment
     *
     * @param time
     */
    public ObjectId(Date time, int inc) {
        this(time, _genmachine, inc);
    }

    /**
     * create a new object id assigned Date and machine and increment
     *
     * @param time
     * @param machine
     * @param inc
     */
    public ObjectId(Date time, int machine, int inc) {
        _machine = machine;
        _time = (int) (time.getTime() / 1000);
        _inc = inc;
        _new = false;
    }

    /**
     * @param s
     * @param babble
     */
    //TODO after testing to  comment
    public ObjectId(String s, boolean babble) {
        if (!isvalid(s)) {
            throw new IllegalArgumentException("invalid ObjectId[" + s + "]");
        }
        if (babble)
            s = babbleToMongod(s);
        byte[] b = new byte[12];
        for (int i = 0; i < b.length; i++) {
            b[i] = (byte) Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16);
        }
        ByteBuffer byteBuffer = ByteBuffer.wrap(b);
        _time = byteBuffer.getInt();
        _machine = byteBuffer.getInt();
        _inc = byteBuffer.getInt();
        _new = false;
    }

    /**
     * create a object Id for 12 byte
     *
     * @param bytes
     */
    public ObjectId(byte[] bytes) {
        if (bytes.length != 12)
            throw new IllegalArgumentException("need 12 bytes");
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        _time = byteBuffer.getInt();
        _inc = byteBuffer.getInt();
        _machine = byteBuffer.getInt();
        _new = false;
    }

    public ObjectId(int time, int machine, int inc) {
        _machine = machine;
        _time = time;
        _inc = inc;
        _new = false;
    }

    public int hashCode() {
        int hc = _time;
        hc += (_machine * 111);
        hc += (_inc * 17);
        return hc;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        ObjectId otherObject = massageToObjectId(o);
        if (null == otherObject)
            return false;
        return _time == otherObject._time && _machine == otherObject._machine && _inc == otherObject._inc;
    }

    public String toString() {
        return toStringBabble();
    }

    //TODO whath mean
    public String toStringBabble() {
        return babbleToMongod(toStringMongod());
    }

    int _compareUnsigned(int i, int j) {
        long li = 0xFFFFFFFFL;
        li = i & li;
        long lj = 0xFFFFFFFFL;
        lj = j & lj;
        long diff = li - lj;
        if (diff < Integer.MIN_VALUE)
            return Integer.MIN_VALUE;
        if (diff > Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
        return (int) diff;
    }

    public int compareTo(ObjectId objectId) {
        if (null == objectId)
            return -1;
        int x = _compareUnsigned(_time, objectId._time);
        if (x != 0)
            return x;
        x = _compareUnsigned(_machine, objectId._machine);
        if (x != 0)
            return x;
        return _compareUnsigned(_inc, objectId._inc);
    }

    public int getMachine() {
        return _machine;
    }

    public long getTime() {
        return _time * 1000L;
    }

    public int _inc() {
        return _inc;
    }

    public int getTimeSecond() {
        return _time;
    }

    public int getInc() {
        return _inc;
    }

    public int _machine() {
        return _machine;
    }

    public boolean _new() {
        return _new;
    }

    public void notNew() {
        _new = false;
    }

    public static int getGenmachine() {
        return _genmachine;
    }

    public static int getCurrentInc() {
        return _nextInteger.get();
    }

    /**
     * checks string could be a objectId
     *
     * @param s
     * @return whether the strng could be Object Id
     */
    public static boolean isvalid(String s) {
        if (null == s) return false;
        final int len = s.length();
        if (len != 24) return false;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9')
                continue;
            if (c >= 'a' && c <= 'f')
                continue;
            if (c >= 'A' && c <= 'F')
                continue;
            return false;
        }
        return true;
    }

    /**
     * convert to be a objectId
     *
     * @param object
     * @return
     */
    public static ObjectId massageToObjectId(Object object) {
        if (null == object) return null;
        if (object instanceof ObjectId) return (ObjectId) object;
        if (object instanceof String) {
            String s = object.toString();
            if (isvalid(s)) {
                return new ObjectId(s);
            }
        }
        return null;
    }

    public static String _pos(String s, int positon) {
        return s.substring(positon * 2, (positon * 2) + 2);
    }

    /**
     * @param babble
     * @return
     */
    //TODO 这个方法需要测试
    public static String babbleToMongod(String babble) {
        if (!isvalid(babble))
            throw new IllegalArgumentException("Invalid Object Id:" + babble);
        StringBuilder stringBuilder = new StringBuilder(24);
        for (int i = 7; i >= 0; i--)
            stringBuilder.append(_pos(babble, i));
        for (int i = 11; i >= 0; i--)
            stringBuilder.append(_pos(babble, i));
        return stringBuilder.toString();
    }

    public String toStringMongod() {
        byte b[] = toByteArray();
        StringBuilder stringBuilder = new StringBuilder(24);
        for (int i = 0; i < b.length; i++) {
            int x = b[i] & 0xFF;
            String s = Integer.toHexString(x);
            if (s.length() == 1)
                stringBuilder.append("0");
                stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }

    public byte[] toByteArray() {
        byte[] bytes = new byte[12];
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        byteBuffer.putInt(_time);
        byteBuffer.putInt(_genmachine);
        byteBuffer.putInt(_inc);
        return bytes;
    }

    public static int _flip(int x) {
        int z = 0;
        z |= ((x << 24) & 0xFF000000);
        z |= ((x << 8) & 0x00FF0000);
        z |= ((x >> 8) & 0x0000FF00);
        z |= ((x >> 24) & 0x000000FF);
        return z;
    }
}
