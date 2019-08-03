class IllegaNameExpection extends Exception
{
    public IllegaNameExpection(){super();}
}
class IllegaAddressExpection extends Exception
{

    public IllegaAddressExpection(){super();}
}
public class Student {
    String name;
    String Address;
    public void SetName(String s) throws IllegaNameExpection
    {
        if(s.length()>5||s.length()<1)
            throw new IllegaNameExpection();
        else
            System.out.println(s);

    }
    public void setAddress(String s) throws IllegaAddressExpection
    {
        if(s.contains("市")||s.contains("省"))
            System.out.println(s);

        else
            throw new IllegaAddressExpection();
    }
    public static void main(String args[]) throws IllegaAddressExpection, IllegaNameExpection {
            Student s1 = new Student();
            try {
                s1.setAddress("河北");
            }
            catch (IllegaAddressExpection ex)
            {
                  System.out.println("地址不含省或市，错误！");
            }
            try{
                s1.SetName("左家强");
            }
            catch(IllegaNameExpection ex)
            {
                System.out.println("姓名长度错误！");
            }
    }
}
