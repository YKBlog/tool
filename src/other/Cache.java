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
      
        // 使用二进制文件  
        builder.setCommandFactory(new BinaryCommandFactory());  
        // 使用一致性哈希算法（Consistent Hash Strategy）  
        builder.setSessionLocator(new KetamaMemcachedSessionLocator());  
        // 使用序列化传输编码  
        builder.setTranscoder(new SerializingTranscoder());  
        // 进行数据压缩，大于1KB时进行压缩  
        builder.getTranscoder().setCompressionThreshold(1024);  
      
        return builder;  
    } 
}
