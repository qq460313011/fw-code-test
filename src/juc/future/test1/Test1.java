package juc.future.test1;

/**
 *
 */
public class Test1 {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Task a = new Task() {
            @Override
            public void rollback() {
                System.out.println("任务1回滚.....");
            }

            @Override
            public Object call() throws Exception {
                System.out.println("任务1开始.....");
                Thread.sleep(2000);
                System.out.println("任务1.....");
                Thread.sleep(5000);
                return null;
            }
        };

        Task b = new Task() {
            @Override
            public void rollback() {
                System.out.println("任务2回滚.....");
            }

            @Override
            public Object call() throws Exception {
                System.out.println("任务2开始.....");
                Thread.sleep(500);
                throw new Exception();
               // System.out.println("任务2.....");
               // Thread.sleep(5000);
              //  return null;
            }
        };

        Task c = new Task() {
            @Override
            public void rollback() {
                System.out.println("任务3回滚.....");
            }

            @Override
            public Object call() throws Exception {
                System.out.println("任务3开始.....");
                Thread.sleep(1000);
                System.out.println("任务3.....");
                Thread.sleep(5000);
                return null;
            }
        };

        taskManager.addTask(a);
        taskManager.addTask(b);
        taskManager.addTask(c);
        taskManager.done();

    }

}
