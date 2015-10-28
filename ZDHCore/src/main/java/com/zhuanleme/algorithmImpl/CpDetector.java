package com.zhuanleme.algorithmImpl;

import info.monitorenter.cpdetector.io.*;

/**
 * <p>Project: com.zhuanleme.algorithmImpl</p>
 * <p>Title: CpDetector.java</p>
 * <p/>
 * <p>Description: CpDetector </p>
 * <p/>
 * <p>Copyright: Copyright (c) 2015 </p>
 * <p/>
 * @author zhangdihong
 * @version 1.0
 * @date 2015/10/28
 */

public class CpDetector {

    public static CodepageDetectorProxy codepageDetectorProxy = CodepageDetectorProxy.getInstance();
    static {
        codepageDetectorProxy.add(new ParsingDetector(false));//ParsingDetector可用于检查HTML、XML等文件或字符流的编码,构造方法中的参数用于指示是否显示探测过程的详细信息，为false不显示。
        codepageDetectorProxy.add(JChardetFacade.getInstance());//JChardetFacade封装了由Mozilla组织提供的JChardet，它可以完成大多数文件的编码 测定.比如下面的ASCIIDetector、UnicodeDetector等
        codepageDetectorProxy.add(ASCIIDetector.getInstance());//ASCIIDetector用于ASCII编码测定
        codepageDetectorProxy.add(UnicodeDetector.getInstance());//UnicodeDetector用于Unicode家族编码的测定
    }
}
