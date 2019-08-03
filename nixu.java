public class nixu {
    public static void main(String args[])
    {
        int arr[]={4,2,3,1};
        System.out.println(nixushu(arr));
    }
    public static int nixushu(int a[])
    {
        if(a==null||a.length==0)
            return 0;
        int low=0;
        int high=a.length-1;
        return findnixu(a,low,high);
    }
    public static int findnixu(int a[],int low,int high)
    {
        if(low==high)
            return 0;
        int mid=(high+low)/2;
        int left=findnixu(a,low,mid);
        int right=findnixu(a,mid+1,high);
        int count=merge(a,mid,low,high);
        return left+right+count;
    }
    public static int merge(int a[],int mid,int low,int high)
    {
        int[] temp=new int[a.length+1];
        for(int i=low;i<=high;i++)
        {
            temp[i]=a[i];
        }
        int leftEnd=mid;
        int rightEnd=high;
        int index=high;
        int count=0;
        while(leftEnd>=low&&rightEnd>=mid+1)
        {
            if(temp[leftEnd]>temp[rightEnd])
            {
                count=count+rightEnd-mid;
                a[index]=temp[leftEnd];
                leftEnd--;
            }
            else {
                a[index]=temp[rightEnd];
                rightEnd--;
            }
            index--;
        }
        while (leftEnd>=low)
        {
            a[index]=temp[leftEnd];
            leftEnd--;
            index--;
        }
        while (rightEnd>=mid+1)
        {
            a[index]=temp[rightEnd];
            rightEnd--;
            index--;
        }
        return count++;
    }


}
