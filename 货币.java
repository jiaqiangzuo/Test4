import java.util.Scanner;
public class 货币 {
        private static final int MAX_M=20;
        private static final int MAX_N=100000;
        private static int[][] table = new int[MAX_N][MAX_M];
        private static int[] value = new int[MAX_N];

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            while(true){
                System.out.println("输入硬币种类的数目：");
                int m = sc.nextInt();
                System.out.println("从小到大输入硬币的面值，其中第一个是1：");

                for(int i=1;i<=m;i++)
                    value[i]= sc.nextInt();
                System.out.println("输入要兑换的钱的面值：");
                int n = sc.nextInt();

                for(int i=0;i<=n;i++)//初始化
                    table[i][0]=0;
                for(int i=0;i<=m;i++)
                    table[0][i]=0;

                for(int i=1;i<=n;i++){
                    for(int j=1;j<=m;j++){
                        if(value[j] == i)
                            table[i][j]=1;
                        else if(value[j]<i)
                            table[i][j]=table[i-value[j]][j]+1;
                        else table[i][j]=table[i][j-1];
                    }
                }
                System.out.println("兑换的最小硬币的个数是：" +table[n][m]);
                System.out.println("---------------------------------");
            }
        }

    }

