public class zhongshu {
    static int zs=0;
    static int sum=0;
    public static void main(String args[])
    {
        int arr[]={1,2,3,4,5,5,5,6,7,8};
        int length=arr.length;
        int zs1=findzs(arr,0,length-1);
        System.out.println("众数为;"+zs1);

    }
   private static int zhongjianzhi(int a[],int s,int e)
    {
        int i;
        int counts=0;
        int mid=(s+e)/2;
        for(i=s;i<=e;i++)
        {
            if(a[i]==a[mid])
            {
                counts++;
            }
        }
        return counts;
    }
   private static int firstzx(int a[],int s,int e)
    {
        int i;
        for(i=s;i<=e;i++)
        {
            if(a[i]==a[(s+e)/2])
                break;
        }
        return i;
    }

    private static int findzs(int a[],int s,int e)
    {
        int count=zhongjianzhi(a,s,e);
        int k=firstzx(a,s,e);
        if(count>sum)
        {
           sum=count;
           zs=a[(s+e)/2];
        }
        if(k>count)
        {
            findzs(a,0,k);
        }
        if(count<e-((k-1)+count))
        {
            findzs(a,(k-1)+count,e);
        }
        return zs;
    }

}
