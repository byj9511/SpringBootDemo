package lock;

import java.util.concurrent.TimeUnit;

public interface RedisLock {
    boolean tryLock(String key, long timeout, TimeUnit timeUnit);
    boolean tryRelease(String key);
}
