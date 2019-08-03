public class SANJIAO {
    double a,b,c;
    static double area;
    public class bushisanjiaoxingException extends Exception
    {

    }
    public SANJIAO(double a,double b,double c) throws bushisanjiaoxingException
    {
        this.a=a;
        this.b=b;
        this.c=c;
        if(a+b<=c||b+c<=a||a+c<=b)
        {
            throw new bushisanjiaoxingException();
        }
    }
    public void Area()
    {
        double p=(a+b+c)/2.0;
        double q=p*(p-a)*(p-b)*(p-c);
        area=Math.sqrt(q);
        System.out.println(area);
    }
    public static void main(String args[])
    {
        try {
            SANJIAO s = new SANJIAO(1,2,3);
            s.Area();
        }
        catch (bushisanjiaoxingException e)
        {
            System.out.println("不是三角形");
        }
    }
}
