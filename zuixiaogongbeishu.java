

import java.util.Scanner;
public class zuixiaogongbeishu {
    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        System.out.println("输入第一个数：");
        int a = s.nextInt();
        System.out.println("输入第二个数：");
        int b = s.nextInt();
        zuixiaogongbeishu c=new zuixiaogongbeishu();
        c.gbs(a,b);
    }
    void gbs(int x,int y)
    {
        int n1=x;
        int n2=y;
        while (x != y) {
            if (x > y) {
                x = x - y;
            } else y = y - x;
        }
        System.out.println("最小公倍数为："+n1*n2/x);
    }

}
