package other;

import java.util.Set;

import com.google.common.collect.Sets;

/**
 * 
 * @Title: FireWall.java
 * @Description: <br>
 *               <br>自定义需要防火墙
 * @Created on 2016年3月12日 下午4:22:35
 * @author yangkai
 * @version 1.0
 */
public class FireWall {

    private Set<String> allowIps = null;

    /**
     * @param ips 可以是正则模糊匹配，也可以写全IP
     */
    public FireWall(String... ips) {
        allowIps = Sets.newHashSet(ips);
    }

    /**
     * 是否允许
     * @param ip 请求的IP
     * @return true 防火墙允许通过，false 非法IP
     */
    public boolean allow(String ip) {
        if (allowIps.contains(ip))
            return true;
        for (String ap : allowIps) {
            if ("".equals(ip.replaceAll(ap, "")))
                return true;
        }
        return false;
    }
}