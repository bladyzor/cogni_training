package back.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public abstract class CacheService<K, V> {

    private LoadingCache<K, V> loadingCache;

    public CacheService() {
        final CacheLoader<K, V> cacheLoader = new CacheLoader<K, V>() {
            @Override
            public V load(final K key) throws IOException {
                return cacheReturnValue(key);
            }
        };

        loadingCache = CacheBuilder
                .newBuilder()
                .expireAfterWrite(3, TimeUnit.MINUTES)
                .build(cacheLoader);
    }

    public V getCachedValue(final K key) {
        try {
            return loadingCache.get(key);
        } catch (CacheLoader.InvalidCacheLoadException | ExecutionException e) {
            //logger
            return null;
        }
    }

    protected abstract V cacheReturnValue(final K key) throws IOException;
}