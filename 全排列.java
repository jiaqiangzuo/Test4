public class 全排列 {
    private static int cnt = 0;

    public static void Swap(int[] c,int i,int j)
    {
        int tmp = c[i];
        c[i] = c[j];
        c[j] = tmp;
    }

    public static void AllPermutation(int[] c,int start)   //全排列算法
    {
        if(start==c.length-1)
        {    //例如  3+7=10  、 7+3=10，两者算一种情况
            if(c[0]<c[3])      //避免等式左边两数交换，只取一种情况即可
            {
                int A = c[0];
                int B = c[1]*10+c[2];
                int C = c[3];
                int D = c[4]*10+c[5];
                int E = c[6];
                int F = c[7]*10+c[8];
                if(A*D*F+C*B*F==E*B*D)    //把除法表达式转换为乘法表达式
                {
                    cnt++;               //统计个数
                    System.out.println(A+"/"+B+"+"+C+"/"+D+"="+E+"/"+F);
                }
            }
        }
        else
        {
            for(int i=start,t=c.length;i<t;++i)
            {
                Swap(c,i,start);
                AllPermutation(c,start+1);
                Swap(c,start,i);
            }
        }
    }

    public static void main(String[] args)
    {
        long start = System.currentTimeMillis();  //与本题无关，计算程序耗时的
        int[] c = {1,2,3,4,5,6,7,8,9};
        AllPermutation(c,0);    //全排列算法
        System.out.println(cnt);   //输出符合条件的个数
        long last = System.currentTimeMillis() - start;   //与本题无关，计算程序耗时的
        System.out.println("耗时："+last+"ms");  //与本题无关，计算程序耗时的
    }
}
