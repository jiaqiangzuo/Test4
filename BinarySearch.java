import java.util.Scanner;
public class BinarySearch {
    public static void main(String args[])
    {
        Scanner cin = new Scanner(System.in);
        int[] str=new int[10];
        int l=str.length;
        System.out.println("输入数组：");
        for(int i=0;i<l;i++)
        {
            str[i] = cin.nextInt();
        }
        System.out.println("输入a和b的值：");
        int a=cin.nextInt();int b=cin.nextInt();
        bins(str,a,b,0,l);
    }
    public static void bins(int x[],int a,int b,int min,int max) {
        int mid=(min+max)/2;
        if(x[mid]<a)
            bins(x,a,b,mid,max);
        else if(x[mid]>b)
            bins(x,a,b,min,mid);
        else{
            for(int i=mid;i<=max&&x[i]<=b;i++)
            {
                System.out.print(x[i]+" ");
            }
            for(int j=mid-1;j>=min&&x[j]>=a;j--)
            {
                System.out.print(x[j]+" ");
            }
        }
    }
}
