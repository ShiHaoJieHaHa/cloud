package own.config;

import com.google.common.cache.CacheLoader;
import com.google.common.util.concurrent.ListenableFuture;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {


    @Bean
    public CacheLoader<Object, Object> cacheLoader() {


        CacheLoader<Object, Object> cacheLoader = new CacheLoader<Object, Object>() {

            @Override
            public Object load(Object key) throws Exception {
                return null;
            }

            // 重写这个方法将oldValue值返回回去，进而刷新缓存


            @Override
            public ListenableFuture<Object> reload(Object key, Object oldValue) throws Exception {
                return (ListenableFuture<Object>) oldValue;
            }
        };

        return cacheLoader;
    }
}
