package com.zhuanleme.util;

import com.zhuanleme.algorithmImpl.FileImpl;

import java.io.UnsupportedEncodingException;

/**
 * <p>Project: com.zhuanleme.util</p>
 * <p>Title: Test.java</p>
 * <p/>
 * <p>Description: Test </p>
 * <p/>
 * <p>Copyright: Copyright (c) 2015 </p>
 * <p/>
 *
 * @author zhangdihong
 * @version 1.0
 * @date 2015/8/24
 */
public class Test {

    public static void main(String[] args) throws UnsupportedEncodingException {
//        try {
//            StringBuilder stringBuilder = new StringBuilder();
//            Enumeration<NetworkInterface> networkInterfaceEnumeration = NetworkInterface.getNetworkInterfaces();
//            while (networkInterfaceEnumeration.hasMoreElements()){
//                NetworkInterface netIF = networkInterfaceEnumeration.nextElement();
//
//               stringBuilder.append(netIF.toString()) ;
//
//            }
//
//            int tesaa= stringBuilder.toString().hashCode() << 10;
//            System.out.println(ManagementFactory.getRuntimeMXBean().getName());
//
//            System.out.println(stringBuilder.toString().hashCode());
//            System.out.println(tesaa);
//            System.out.println(Integer.toHexString(tesaa));
//            System.out.println(stringBuilder.toString());
//        } catch (SocketException e) {
//            e.printStackTrace();
//        }
//        System.out.println(ten2Any(100,100));
//       System.out.println(new ObjectId().toString());
//        System.out.println(IdGenerator.get14Id());
//        System.out.println(IdGenerator.get15Id());
//        System.out.println(FilePathUtil.legalFile("c:\\dfdf\\fdfd\\fdfdf"));
//        EmailUtil  testMail = new EmailUtil(true);
//        String subject = "告诉你一件事";
//        String content = "<h1>我怀孕了是你的猴子请负责</h1>";
//         testMail.doSendHtmlEmail(subject,content,"jialesmile@foxmail.com",null);
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.YEAR, 1);
//        System.out.println(DateUtil.SubDay(new Date(),calendar.getTime()));
//        System.out.println(StringUtils.unicode2String("\\u522B\\u7B49\\u4E86"));
//        System.out.println(ten2Any(16,16));
//        System.out.println(ChineseUtil.isChineseByREG("aaaaa"));
//        System.out.println(System.currentTimeMillis());
//        System.out.println(ChineseUtil.chineseCount("门前大桥下流过一群鸭......快来快来数一数二四六七八"));
//        System.out.println(CharsetUtil.getDefaultCharset());
//        System.out.println(Base64.encode());
//        System.out.println(Base64.encodeToString("abcdefjhijklmn".getBytes()));

//        List<File> fileList = FileUtil.searchFile(new File("C:\\Users\\zhangdihong\\Desktop"), "111.txt");
        System.out.print(FileImpl.simpleEncoding("C:\\Users\\zhangdihong\\Desktop\\111.txt"));
    }
    private static final String ten2Any(long num, int base) {
        StringBuilder sb = new StringBuilder(7);
        while (num != 0) {
            sb.append("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt((int) (num % base)));
            System.out.println("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt((int) (num % base)));
            num /= base;
        }
        return sb.reverse().toString();
    }
    private static String _pos(String s, int postion){
        return s.substring(postion * 2, (postion * 2) + 2);
    }

}
