import java.sql.*;

 
public class Main {

 public static void main(String [] args)

 {

  String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";

  String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=Documentary";

  String userName="admin";

  String userPwd="123456";

  try

  {

   Class.forName(driverName);

   Connection dbConn=DriverManager.getConnection(dbURL,userName,userPwd);

    System.out.println("�������ݿ�ɹ�");
    Statement st=dbConn.createStatement();
    Login t1=new Login(st);
  }

  catch(Exception e)

  {

   e.printStackTrace();

   System.out.print("����ʧ��");

  }    
 

 }

}