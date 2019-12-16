package com.st.shiro.cache;

import com.st.redis.config.RedisUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.SerializationUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Shumao
 * @description
 * @date 2019/12/8 15:53
 */
@Component
public class RedisCache<K, V> implements Cache<K, V> {

    private final String SHIRO_CACHE = "shiro_cache_";

    @Autowired
    private RedisUtil redisUtil;

    private String getKey(K k) {
        return SHIRO_CACHE + k.toString();
    }

    @Override
    public V get(K k) throws CacheException {
        byte[] value = (byte[]) redisUtil.get(getKey(k));
        if (value != null) {
            return (V) SerializationUtils.deserialize(value);
        }
        return null;
    }

    @Override
    public V put(K k, V v) throws CacheException {
        String key = getKey(k);
        byte[] value = SerializationUtils.serialize(v);
        redisUtil.set(key, value);
        redisUtil.expire(key, 600);
        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
        String key = getKey(k);
        byte[] value = (byte[]) redisUtil.get(key);
        redisUtil.del(key);
        if (value != null) {
            return (V) SerializationUtils.deserialize(value);
        }
        return null;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return keys().size();
    }

    @Override
    public Set<K> keys() {
        Set<String> keys = redisUtil.keys(SHIRO_CACHE + "*");
        return (Set<K>) keys;
    }

    @Override
    public Collection<V> values() {
        Set<String> keys = redisUtil.keys(SHIRO_CACHE + "*");
        Set<V> values = new HashSet<>();
        if (CollectionUtils.isEmpty(keys)) {
            return values;
        }
        for (String key : keys) {
            V value = (V) SerializationUtils.deserialize((byte[]) redisUtil.get(key));
            values.add(value);
        }
        return values;
    }
}
