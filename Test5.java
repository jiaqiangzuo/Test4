public class Test5 {
    public static void main(String args[]) {
        int a[] = {1, 2, 3, 4};
        for (int i = 0; i < 5; i++) {
            try
            {
                System.out.println(a[i]);
                System.out.println(a[i]/i);
            }
            catch(ArithmeticException e)
            {
                System.out.println("算数异常！");
            }
            catch(ArrayIndexOutOfBoundsException e)
            {
                System.out.println("数组越界！");
            }
        }
    }
}
