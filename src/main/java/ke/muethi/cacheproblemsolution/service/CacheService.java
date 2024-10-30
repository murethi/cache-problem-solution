package ke.muethi.cacheproblemsolution.service;

import ke.muethi.cacheproblemsolution.Cache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class CacheService {
    Map<Object, Cache> cacheStore = new HashMap<>();

    public Map<Object,Cache> put(String key, String value,int ttl) {
         Cache cache = Cache.builder()
                 .value(value)
                 .ttl(LocalDateTime.now().plusSeconds(ttl))
                 .build();
        cacheStore.put(key, cache);
        return cacheStore;
    }

    public Object get(String key) {
        Optional<Cache> cache = Optional.ofNullable(cacheStore.get(key));
       return cache.map(cache1 -> {
           if(cache1.ttl().isAfter(LocalDateTime.now())){
               log.warn("Removing key {} from cache", key);
               cacheStore.remove(key);
           }
           return cache1.value();
       });
    }

    public void delete(String key) {
        cacheStore.remove(key);
    }
}
