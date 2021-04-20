package juc.future.test1;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 拿FutureTask中的回调Callable
 * @param <T>
 */
public class MyFutureTask<T> extends FutureTask<T> {

    private Callable<T> callable;

    public MyFutureTask(Callable<T> callable) {
        super(callable);
        this.callable = callable;
    }

    public MyFutureTask(Runnable runnable, T result) {
        super(runnable, result);
    }

    public Callable<T> getCallable() {
        return callable;
    }
}
