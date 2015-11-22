package com.zhuanleme.WEB;

import com.zhuanleme.util.StringUtils;

/**
 * <p>Project: com.zhuanleme.WEB</p>
 * <p>Title: WebUtil.java</p>
 * <p/>
 * <p>Description: WebUtil </p>
 * <p/>
 * <p>Copyright: Copyright (c) 2015 </p>
 * <p/>
 * @author zhangdihong
 * @version 1.0
 * @date 2015/11/17
 */
public class WebUtil {

    public  static String unhtml(String content){
        if(StringUtils.isEmpty(content)){
            return "";
        }
        String html = content;
        html = html.replaceAll("'", "&apos;");
        html = html.replaceAll("\"", "&quot;");
        html = html.replaceAll("\t", "&nbsp;&nbsp;");
        html = html.replaceAll("<", "&lt;");
        html = html.replaceAll(">", "&gt;");
        return html;
    }
    public static String html(String content){
        if(StringUtils.isEmpty(content)){
            return "";
        }
        String html = content;
        html = html.replaceAll("&apos", "'");
        html = html.replaceAll("&quot", "\"");
        html = html.replaceAll("&nbsp;", " ");
        html = html.replaceAll("&lt;", "<");
        html = html.replaceAll("&gt", ">");
        return html;
    }

}
