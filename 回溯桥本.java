import java.util.Scanner;
public class 回溯桥本 {
    public static void main(String[] args) {
        int n = 9;
        int[] a = new int[n + 3];
        int i = 1;
        a[i] = 1;
        int g;
        int cnt = 0;
        while (true) {
            g = 1;
            for (int k = i - 1; k >= 1; --k) {
                if (a[i] == a[k])
                {
                    g = 0;
                    break;
                }
            }
            if (g != 0 && i == n && a[1] < a[4])
            {
                int A = a[1];
                int B = a[2] * 10 + a[3];
                int C = a[4];
                int D = a[5] * 10 + a[6];
                int E = a[7];
                int F = a[8] * 10 + a[9];
                if (A * D * F + C * B * F == E * B * D)
                {
                    cnt++;
                    System.out.println(A + "/" + B + "+" + C + "/" + D + "=" + E + "/" + F);
                }
            }
            if (i < n && g != 0)
            {
                ++i;
                a[i] = 1;
                continue;
            }
            while (a[i] == n && i > 1)
            {
                --i;
            }
            if (a[i] == n && i == 1) {
                break;
            } else {
                a[i] = a[i] + 1;
            }
        }
        System.out.println("共有"+cnt+"个");
    }

}