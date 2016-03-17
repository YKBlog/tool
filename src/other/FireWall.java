package other;

import java.util.Set;

import com.google.common.collect.Sets;

/**
 * 
 * @Title: FireWall.java
 * @Description: <br>
 *               <br>�Զ�����Ҫ����ǽ
 * @Created on 2016��3��12�� ����4:22:35
 * @author yangkai
 * @version 1.0
 */
public class FireWall {

    private Set<String> allowIps = null;

    /**
     * @param ips ����������ģ��ƥ�䣬Ҳ����дȫIP
     */
    public FireWall(String... ips) {
        allowIps = Sets.newHashSet(ips);
    }

    /**
     * �Ƿ�����
     * @param ip �����IP
     * @return true ����ǽ����ͨ����false �Ƿ�IP
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