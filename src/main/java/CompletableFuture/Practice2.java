package CompletableFuture;

import java.util.concurrent.*;

// Executor
public class Practice2 {

    public static void main(String[] args) {
        // 1. 쓰레드 1개
        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
//        executorService.execute(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Thread = " + Thread.currentThread().getName());
//            }
//        });
        executorService1.submit(() -> System.out.println("Thread1 = " + Thread.currentThread().getName()));
        // 쓰레드 종료 필수
        executorService1.shutdown(); // shutdownNow();
        System.out.println("=================================");

        // 2. 쓰레드 2개
        // ExecutorService 내에는 스레드 풀이 존재하며 현재 쓰레드가 두 개 있다.
        // 스레드 풀 앞단(ExecutorService 내부) 에는 BlockingQueue가 존재하며
        // 5개를 들어온 순서대로 넣어놓고, 쓰레드가 빌 때 마다 하나씩 들어가서 작업을 처리한다.
        ExecutorService executorService2 = Executors.newFixedThreadPool(2);
        executorService2.submit(getRunnable("hello = "));
        executorService2.submit(getRunnable("dongwoo = "));
        executorService2.submit(getRunnable("the = "));
        executorService2.submit(getRunnable("Java = "));
        executorService2.submit(getRunnable("Thread = "));
        executorService2.shutdown();

        System.out.println("=================================");

        // 3. 스케쥴
        ScheduledExecutorService executorService3 = Executors.newSingleThreadScheduledExecutor();
        executorService3.schedule(getRunnable("스케줄_2"),2, TimeUnit.SECONDS); // 2초있다가 실행
        executorService3.scheduleAtFixedRate (getRunnable("스케줄_2_2"),2, 2, TimeUnit.SECONDS); // 2초 있다가 2초마다 실행
    }

    private static Runnable getRunnable(String message){
        return () -> System.out.println(message + Thread.currentThread().getName());
    }
}
