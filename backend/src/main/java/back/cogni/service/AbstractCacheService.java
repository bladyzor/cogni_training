package back.cogni.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public abstract class AbstractCacheService<KEY, VALUE> {

    private LoadingCache<KEY, VALUE> loadingCache;

    public AbstractCacheService() {
        final CacheLoader<KEY, VALUE> cacheLoader = new CacheLoader<KEY, VALUE>() {
            @Override
            public VALUE load(final KEY key) throws IOException {
                return getCacheValue(key);
            }
        };

        loadingCache = CacheBuilder
                .newBuilder()
                .expireAfterWrite(3, TimeUnit.MINUTES)
                .build(cacheLoader);
    }

    public VALUE getCachedValue(final KEY key) {
        try {
            return loadingCache.get(key);
        } catch (CacheLoader.InvalidCacheLoadException | ExecutionException e) {
            //logger
            return null;
        }
    }

    protected abstract VALUE getCacheValue(final KEY key) throws IOException;
}