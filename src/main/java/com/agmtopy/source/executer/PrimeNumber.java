package com.agmtopy.source.executer;

public class PrimeNumber {
    public static void main(String[] args) {
        /**
         * min[int] and max[int] is target number range.
         */
        long st = System.currentTimeMillis(); // Start Time
        int min = 30;
        int max = 1000000;
        for (int i = min; i <= max; i++) {
            isPrime2(i);
        }
        long et = System.currentTimeMillis(); // End Time
        System.out.print("Program running cost time is: ");
        System.out.println(et - st + " ms");

    }

    /**
     * is Prime number method 1
     *
     * @param n
     * @return
     */
    public static boolean isPrime1(int n) {
        if (n <= 3) {
            return n > 1;
        }
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * is Prime number method 2
     *
     * @param n
     * @return
     */
    public static boolean isPrime2(int n) {
        if (n <= 3) {
            return n > 1;
        }
        int sqrt = (int) Math.sqrt(n);
        for (int i = 2; i <= sqrt; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * is Prime number method 3
     *
     * @param n
     * @return
     */
    public static boolean isPrime3(int num) {
        if (num <= 3) {
            return num > 1;
        }
        // 不在6的倍数两侧的一定不是质数
        if (num % 6 != 1 && num % 6 != 5) {
            return false;
        }
        int sqrt = (int) Math.sqrt(num);
        for (int i = 5; i <= sqrt; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}
