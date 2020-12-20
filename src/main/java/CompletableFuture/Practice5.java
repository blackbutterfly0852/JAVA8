package CompletableFuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

// CompletableFuture2
public class Practice5 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 1. 의존성이 있는 경우 : 순서가 정해진 경우
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future1 = " + Thread.currentThread().getName());
            return "future1";
        });
        System.out.println("=================================");

        // Future 의 경우 future1과 future2의 연결 작업을 get() 이후에 진행했어야 하는 단점 존재
        // Java8에서는 연결가능
        CompletableFuture<String> future3 = future1.thenCompose(Practice5::getFuture2);
        System.out.println("future3 = " + future3.get());
        System.out.println("=================================");

        // 2. 의존성이 없는 경우 : 순서와 상관 없는 경우
        CompletableFuture<String> future4 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future4 = " + Thread.currentThread().getName());
            return "future4";
        });

        CompletableFuture<String> future5 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future5 = " + Thread.currentThread().getName());
            return "future5";
        });

        CompletableFuture<String> future6 = future4.thenCombine(future5, (f4, f5) -> f4 + " " + f5);
        System.out.println("future6 = " + future6.get());
        System.out.println("=================================");

        // 3. 여러 태스크가 모두 끝났으 때 콜백
        CompletableFuture<String> future7 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future7 = " + Thread.currentThread().getName());
            return "future7";
        });

        CompletableFuture<String> future8 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future8 = " + Thread.currentThread().getName());
            return "future8";
        });

        // 결과값이 NULL인 문제
        CompletableFuture<Void> future9 = CompletableFuture.allOf(future7, future8).thenAccept(System.out::println);
        System.out.println("future9 = " + future9.get());
        System.out.println("=================================");

        // 결과값 NULL 해결
        List<CompletableFuture<String>> future10 = Arrays.asList(future7, future8);
        CompletableFuture[] future11 = future10.toArray(new CompletableFuture[future10.size()]);
        CompletableFuture<List<String>> future12 = CompletableFuture.allOf(future11).thenApply(v -> {
            return future10.stream().map(CompletableFuture::join).collect(Collectors.toList());
        });

        future12.get().forEach(System.out::println);
        System.out.println("=================================");

        // 4. 둘 중 하나만 랜덤하게 가져오기
        CompletableFuture<Void> future13 = CompletableFuture.anyOf(future7, future8).thenAccept(System.out::println);
        System.out.println("future13 = " + future13.get());
        System.out.println("=================================");

        // 5. 예외처리
        boolean throwError = true;
        CompletableFuture<String> future14 = CompletableFuture.supplyAsync(() -> {
            if (throwError) {
                throw new IllegalArgumentException();
            }
            System.out.println("future14 = " + Thread.currentThread().getName());
            return "future14";
        }).exceptionally(ex -> {
            System.out.println(ex);
            return "future14 Error";
        });

        System.out.println("future14 = " + future14.get());
        System.out.println("=================================");

        // handle() 에러 다루기
        CompletableFuture<String> future15 = CompletableFuture.supplyAsync(() -> {
            if (!throwError) {
                throw new IllegalArgumentException();
            }
            System.out.println("future15 = " + Thread.currentThread().getName());
            return "future15";
        }).handle((result, ex) -> {
            if (ex != null) {
                System.out.println(ex);
                return "future15 Error";
            }
            return result;

        });

        System.out.println("future15 = " + future15.get());
        System.out.println("=================================");

    }

    private static CompletableFuture<String> getFuture2(String message) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(message + " = " + Thread.currentThread().getName());
            return message;
        });
    }


}
