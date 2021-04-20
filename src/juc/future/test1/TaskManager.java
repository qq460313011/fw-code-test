package juc.future.test1;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class TaskManager {

    private List<MyFutureTask> taskList = new LinkedList<>();

    private boolean isSuccess = false;

    public void addTask(Task task) {
        taskList.add(new MyFutureTask(task));
    }

    public void done() {
        CompletableFuture[] completableFutures = taskList.stream().map(task ->
                CompletableFuture
                        .runAsync(task)
                        .whenCompleteAsync(this::done1))
                .collect(Collectors.toList())
                .toArray(new CompletableFuture[taskList.size()]);
        CompletableFuture.allOf(completableFutures).join();
    }


    private void done1(Void v, Throwable t) {
        if (!isSuccess) {
            isSuccess = true;
            try {
                for (MyFutureTask futureTask : taskList) {
                    futureTask.get();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                for (MyFutureTask futureTask : taskList) {
                    //报异常了，完成的任务跑一下回调
                    if (!futureTask.isCancelled()) {
                        futureTask.cancel(true);
                        Task task = (Task) futureTask.getCallable();
                        task.rollback();
                    }
                }
            }
        }
    }


}
