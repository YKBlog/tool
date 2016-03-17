package other;

import java.util.Properties;

import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator;
import net.rubyeye.xmemcached.transcoders.SerializingTranscoder;
import net.rubyeye.xmemcached.utils.AddrUtil;

public class Cache {

    private MemcachedClientBuilder createMemcachedClientBuilder( Properties properties) {  
        String addresses = properties.getProperty("ADDRESSES").trim();  
      
        MemcachedClientBuilder builder = new XMemcachedClientBuilder(  
                AddrUtil.getAddresses(addresses));  
      
        // ʹ�ö������ļ�  
        builder.setCommandFactory(new BinaryCommandFactory());  
        // ʹ��һ���Թ�ϣ�㷨��Consistent Hash Strategy��  
        builder.setSessionLocator(new KetamaMemcachedSessionLocator());  
        // ʹ�����л��������  
        builder.setTranscoder(new SerializingTranscoder());  
        // ��������ѹ��������1KBʱ����ѹ��  
        builder.getTranscoder().setCompressionThreshold(1024);  
      
        return builder;  
    } 
}
