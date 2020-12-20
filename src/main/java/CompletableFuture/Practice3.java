package CompletableFuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

// Callable 과 Future
public class Practice3 {

    // Runnable -> void 타입
    // Callable -> Runnable과 유사하지만 리턴값을 받을 수 있다.

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 1. 기본
        ExecutorService executorService1 = Executors.newSingleThreadExecutor();

        Callable<String> callable1 = () -> {
            Thread.sleep(2000L);
            return "Hello";
        };

        Future<String> future1 = executorService1.submit(callable1);
        System.out.println(future1.isDone());
        System.out.println("Started!");
        // true : 현재 작업을 인터럽트 후 종료, false : 기다리고 종료
        // 우선 cancel() 하면 get()으로 값을 가져올 수 없다.
        // future1.cancel(true);
        System.out.println(future1.get()); // 값이 올 때 까지 대기

        System.out.println("End!");
        System.out.println(future1.isDone());
        executorService1.shutdown();


        // 2. invokeAll(), invokeAny
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();

        Callable<String> callable2 = () -> {
            Thread.sleep(2000L);
            return "Hello";
        };
        Callable<String> callable3 = () -> {
            Thread.sleep(3000L);
            return "JPA";
        };
        Callable<String> callable4 = () -> {
            Thread.sleep(1000L);
            return "Dongwoo";
        };

        // invokeAll()은 위의 대기 시간들이 모두 끝날 때까지 대기함 -> 실행 시 한번에 표출
        List<Future<String>> future2 = executorService2.invokeAll(Arrays.asList(callable2,callable3,callable4));

        future2.forEach( f -> {
            try {
                System.out.println(f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        executorService2.shutdown();

        // invokeAny()은 여러 작업 중에 하나라도 먼저 응답이 오면 끝낸다.
        ExecutorService executorService3 = Executors.newFixedThreadPool(4);
        String future3 = executorService3.invokeAny(Arrays.asList(callable2,callable3,callable4));
        System.out.println("future = " + future3);
        executorService3.shutdown();

    }


}
