package juc.future.test1;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

public interface Task<T> extends Callable<T> {

    /*
        任务回滚方法
     */
    void rollback();
}
