package CompletableFuture;

// 자바 Concurrent 프로그래밍 소개
public class Practice {

    public static void main(String[] args) throws InterruptedException {
        // 1. Concurrent 소프트웨어 : 동시에 여러 작업을 할 수 있는 소프트웨어
        // 2. 자바 : 멀티프로세서, 멀티쓰레드
        // 3. 멀티쓰레드 프로그래밍 -> 쓰레드와 프로세스의 차이?
        // 1) 방법1
        MyThread myThread = new MyThread();
        myThread.start();
        System.out.println("Hello");

        // 2) 방법2
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread1: " + Thread.currentThread().getName());

            }
        });


        // 3) 방법3
        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread2: " + Thread.currentThread().getName());
        });
        thread2.start();
        thread1.start();

        // 4. Interrupt
        Thread thread3 = new Thread(() -> {
            while (true) {
                System.out.println("Thread3: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    System.out.println("exit!");
                    return;
                }
            }
        });
        thread3.start();
        Thread.sleep(3000L);
        thread3.interrupt(); // 종료가 아니라, InterruptedException 호출한 것, 종료는 내가 지정한 것.

        // 5 . Join
        Thread thread4 = new Thread(() -> {
            System.out.println("Thread4: " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);

            }

        });
        thread4.start();
        thread4.join(); // 3초 쓰레드 기다렸다가 조인한다. -> InterruptedException
        System.out.println(thread4 + " is finished");

    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Thread0: " + Thread.currentThread().getName());
        }
    }
}
