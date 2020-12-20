package CompletableFuture;

import java.util.concurrent.*;

// CompletableFuture1
public class Practice4 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = new CompletableFuture<>();
        //CompletableFuture<String> future1 = CompletableFuture.completedFuture("Dongwoo");
        future1.complete("Dongwoo");
        System.out.println(future1.get());
        System.out.println("=================================");

        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
            System.out.println("future2 = " + Thread.currentThread().getName());
        });
        System.out.println(future2.get());
        System.out.println("=================================");

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future3 = " + Thread.currentThread().getName());
            return "future3";
        });
        System.out.println(future3.get());
        System.out.println("=================================");


        // CompletableFuture 는 get() 하기전 중간작업이 가능하다.
        // thenApply : 리턴값 존재.
        CompletableFuture<String> future4 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future4_1 = " + Thread.currentThread().getName());
            return "future4";
        }).thenApply((s) -> {
            System.out.println("future4_2 = " + Thread.currentThread().getName());
            return s.toUpperCase();
        });
        System.out.println(future4.get());
        System.out.println("=================================");

        // thenAccept : 리턴값이 없는 경우
        CompletableFuture<Void> future5 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future5_1 = " + Thread.currentThread().getName());
            return "future5";
        }).thenAccept((s) -> {
            System.out.println("future5_2 = " + Thread.currentThread().getName());
            System.out.println("future5_3_s.toUpperCase() = " + s.toUpperCase());

        });
        System.out.println(future5.get());
        System.out.println("=================================");

        // thenRun : 리턴값이 필요없고 무언가 실행만 하면된다. 결과값(future5) 참조 못함.
        CompletableFuture<Void> future6 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future6_1 = " + Thread.currentThread().getName());
            return "future6";
        }).thenRun(() -> {
            System.out.println("future6_2 = " + Thread.currentThread().getName());
        });
        System.out.println(future6.get());
        System.out.println("=================================");

        // 위의 코드를 봤을 때, ForkJoinPool.commonPool 을 활용해 별도의 Executor를 사용하지 않는다.
        // ForkJoinPool : Java7, 쓰레드가 Dequeue 에서 직접 가져온다.
        // Executor 를 사용하는 경우, supplyAsync 두 번째 인자에 executorService1 대입
        ExecutorService executorService1 = Executors.newFixedThreadPool(4);
        CompletableFuture<Void> future7 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future7_1 = " + Thread.currentThread().getName());
            return "future7";
        },executorService1).thenRun(() -> {
            System.out.println("future7_2 = " + Thread.currentThread().getName());
        });
        System.out.println(future7.get());
        System.out.println("=================================");

    }
}
