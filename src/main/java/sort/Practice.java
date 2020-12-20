package sort;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Practice {

    public static void main(String[] args) {
        // Sort vs Parallelsort 비교
        // 알고리듬 효율성은 같다. 시간 O(n logN) 공간 O(n)
        int size = 1500;
        int[] numbers = new int[size];
        Random random = new Random();
        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());
        long start = System.nanoTime();
        Arrays.sort(numbers); // 싱글 쓰레드, Dual Quick sort 이지만 한계가 있다.
        System.out.println("serial sorting took " + (System.nanoTime() - start));

        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());
        start = System.nanoTime();
        Arrays.parallelSort(numbers); // 여러 쓰레드 사용
        System.out.println("parallel sorting took " + (System.nanoTime() - start));
    }
}
