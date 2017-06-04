package BinarySearch;

/**
 * Created by Administrator on 2017/6/4.
 */
public class Binary50PowXN {
    public double myPow(double x, int n) {
        //忘记了n还可以是负数
        if (n == 0) {
            return 1;
        }
        if (x == 0 && n < 0) {
            return Double.POSITIVE_INFINITY;
        }
        if (n == 1) {
            return x;
        } else if (n == 2) {
            return x * x;
        } else if (n == -1) {
            return 1 / x;
        } else if (n == -2) {
            return 1 / (x * x);
        } else {
            int half = n / 2;
            int imple = n - half * 2;
            double halfValue = myPow(x, half);
            return halfValue * halfValue * myPow(x, imple);
        }
        /*
        之前一直用int mid = n/2 return myPow(x, mid)*myPow(x, n - mid) 这样有时间问题

        参考答案加以解决, 也不能用:
        int half = n / 2;
        int imple = n - half * 2;
        return  myPow(x, half)*myPow(x, half)*myPow(x, imple);会溢出. 必须先设好halfValue


        Failed test: 0.00001 2147483647
        与x正负无关

        */
    }

    public double myPowAnswer(double x, int n) {
        if (n == 0) {
            return 1;
        }

            if (n == 1) {
            return x;
        }

        boolean isNegative = false;
            if (n < 0) {
            isNegative = true;
            n *= -1;
        }

        int k = n / 2;
        int l = n - k * 2;
        double t1 = myPow(x, k);
        double t2 = myPow(x, l);
            if (isNegative) {
            return 1/(t1*t1*t2); //值得学习
        } else {
            return t1*t1*t2;
        }
}
}
