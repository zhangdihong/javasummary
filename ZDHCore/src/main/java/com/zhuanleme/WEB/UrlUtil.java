package com.zhuanleme.WEB;

import java.util.regex.Pattern;

/**
 * <p>Project: com.zhuanleme.WEB</p>
 * <p>Title: UrlUtil.java</p>
 * <p/>
 * <p>Description: UrlUtil </p>
 * <p/>
 * <p>Copyright: Copyright (c) 2015 </p>
 * <p/>
 *
 * @author zhangdihong
 * @version 1.0
 * @date 2015/10/30
 */
public class UrlUtil {

    public static final String SCHEME_PATTERN = "([^:/?#]+):";

    public static final String HTTP_PATTERN = "(http|https):";

    public static final String USERINFO_PATTERN = "([^@/]*)";

    public static final String HOST_PATTERN = "([^/?#:]*)";

    public static final String PORT_PATTERN = "(\\d*)";

    public static final String PATH_PATTERN = "([^?#]*)";

    public static final String QUERY_PATTERN = "([^#]*)";

    public static final String LAST_PATTERN = "(.*)";

    public static final Pattern URI_PATTERN = Pattern.compile("^(" + SCHEME_PATTERN + ")?" + "(//(" + USERINFO_PATTERN + "@)?" + HOST_PATTERN + "(:"+ PORT_PATTERN + ")?" + ")?"+PATH_PATTERN + "(\\?" + QUERY_PATTERN +")?"+ "(#"+LAST_PATTERN +")?");

    public static final Pattern HTTP_URL_PATTERN = Pattern.compile('^' + HTTP_PATTERN + "(//(" + USERINFO_PATTERN + "@)?" + HOST_PATTERN + "(:" + PORT_PATTERN + ")?" + ")?" + PATH_PATTERN + "(\\?" + LAST_PATTERN + ")?");

}
