import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Stu extends JFrame {
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
	String snum;
	Statement st;
    public Stu(String ss,Statement st1) {
         
        snum=ss;
        st=st1;
    	this.setBounds(500, 100, 1000, 800);
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		 tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);//设置选项卡标签的布局方式为滚动布局
			tabbedPane.addChangeListener(new ChangeListener() {//添加时间监听器
				
				@Override
				public void stateChanged(ChangeEvent e) {
					// TODO Auto-generated method stub
					
					int selectedIndex = tabbedPane.getSelectedIndex();//获得被选中选项卡的索引
					String title = tabbedPane.getTitleAt(selectedIndex);//获得指定索引的选项卡标签
					System.out.println(title);
				}
			});
			DormitoryInfo sushe=new DormitoryInfo(snum,st);
			StuIfo geren = new StuIfo(snum,st);
			tabbedPane.addTab("个人信息", geren);
			tabbedPane.addTab("宿舍信息", sushe);//将标签组件添加到选项卡中，并且要求有提示
			Repair weixiu = new Repair(snum,st);
			tabbedPane.addTab("维修信息", weixiu);
			Fee dian = new Fee(snum,st);
			tabbedPane.addTab("电费", dian);
			
			tabbedPane.setSelectedIndex(0); //设置索引为2的选项卡被选中
			
			
			add(tabbedPane);

	
		 
		 this.setVisible(true);
    }
    public static void main(String args[])
    {	
    }
}
//宿舍信息
class DormitoryInfo extends JPanel {
	String[] col = { "学号", "姓名", "性别","专业","宿舍号","入住时间" };
    DefaultTableModel mm = new DefaultTableModel(col, 0);
    String snum,Dno;
    JTable table=new JTable();
    Statement st;
    public DormitoryInfo(String ss,Statement st1)
    {
    	snum=ss;
    	st=st1;
    	setLayout(new FlowLayout());
    	 
        table.setModel(mm);
        table.setRowSorter(new TableRowSorter<>(mm));//排序
        JPanel jPanel=new JPanel(new FlowLayout());
        JScrollPane js=new JScrollPane(table);
        jPanel.add(js);
        add(jPanel);
        search();

    }
    
    private void search()
    {
    	try
    	{
    		String strinsert="select * from Stu";
			ResultSet rs = st.executeQuery(strinsert);
			while(rs.next())
			{
				String acc=rs.getString(1);
				//JOptionPane.showMessageDialog(null, acc1);
				
				//System.out.println(acc+"111");
				
				if(acc.equals(snum))
				{
					Dno=rs.getString(8);
					//JOptionPane.showMessageDialog(null, Dno);
					break;
				}
				
			}
			String strinsert1="select * from Stu where Doid = "+Dno;
			ResultSet rs1 = st.executeQuery(strinsert1);
			while(rs1.next())
			{
				String Sno=rs1.getString(1);
	            String Sname=rs1.getString(2);
	            String Ssex=rs1.getString(4);
	            String Sdept=rs1.getString(6);
	            String Dno=rs1.getString(8);
	            String Scheckin=rs1.getString(7);
	            String[] data={Sno,Sname,Ssex,Sdept,Dno,Scheckin};
	            mm.addRow(data);
	            System.out.println(data);
			}

    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getCause().getMessage());
    	}
    } 

}
//维修信息
class Repair extends JPanel implements ActionListener{
	String[] col = { "宿舍号", "报修时间", "报修原因" };
    DefaultTableModel mm = new DefaultTableModel(col, 0);
    String snum,Dno;
    JTable table=new JTable();
    Statement st;
    JButton submit;
    JTextField t2;
    JTextField t3;
    public Repair(String ss,Statement st1)
    {
    	snum=ss;
    	st=st1;
    	setLayout(new FlowLayout());
        table.setModel(mm);
        table.setRowSorter(new TableRowSorter<>(mm));//排序
        JPanel jPanel=new JPanel(new FlowLayout());
        JScrollPane js=new JScrollPane(table);
        jPanel.add(js);
        add(jPanel);
        search();
    }
    
    private void search()
    {
    	try
    	{
    		inquire();
    		String strinsert="select * from Stu";
			ResultSet rs = st.executeQuery(strinsert);
			while(rs.next())
			{
				String acc=rs.getString(1);
				if(acc.equals(snum))
				{
					Dno=rs.getString(8);
					break;
				}
			}
			String strinsert1="select * from Repair where Doid = "+Dno;
			ResultSet rs1 = st.executeQuery(strinsert1);
			while(rs1.next())
			{
				String Sno=rs1.getString(1);
	            String Sname=rs1.getString(2);
	            String Ssex=rs1.getString(3);
	            
	            String[] data={Sno,Sname,Ssex};
	            mm.addRow(data);
	            
			}

    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getCause().getMessage());
    	}
    }
    
    
    
    
    private void inquire(){//学生只能查询任意宿舍的电话
    	JLabel l2=new JLabel("   报修时间 :");
    	JLabel l3=new JLabel("   报修原因 :");
    	Font f = new Font("宋体",Font.PLAIN,16); 
    	l2.setFont(f);
    	l3.setFont(f);
    	t2=new JTextField(15);
    	t3=new JTextField(15);
    	submit=new JButton("   报修  ");
        submit.addActionListener(this);
        JPanel suguan=new JPanel(new GridLayout(3, 2));
       suguan.add(l2);
       suguan.add(t2);
       suguan.add(l3);
       suguan.add(t3);
       suguan.add(submit);
       
       add(suguan);
       
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==submit)
		{
			String ti,re;
			ti=t2.getText();
			re=t3.getText();
			
			String sql0=("select * from Repair where Doid = "+Dno);
			String sql=("insert into Repair (Doid,Time,reason) values ('"+Dno+"','"+ti+"','"+re+"')");
			String sql1=("select * from Repair where Doid = "+Dno);
			
			try {
				ResultSet si = st.executeQuery(sql0);
				int k=1;
				while(si.next())
				{
					String op = si.getString(3);
					if(re.equals(op))
					{
						k=-1;
						break;
					}
				}
				if(k==1) {
				st.executeUpdate(sql);
				ResultSet sas = st.executeQuery(sql1);
				
				while(mm.getRowCount()>0){//把表格进行刷新，下次显示的时候重头开始显示
                    //System.out.println(model.getRowCount());
                    mm.removeRow(mm.getRowCount()-1);
                }
				
				while(sas.next())
				{
					String Sno=sas.getString(1);
		            String Sname=sas.getString(2);
		            String Ssex=sas.getString(3);
  
		            String[] data={Sno,Sname,Ssex};
		            mm.addRow(data);
		            
				}
				}
				else
					JOptionPane.showMessageDialog(null, "已报修，请等候！");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}
}



//个人信息
class StuIfo extends JPanel implements ActionListener{
	JPanel Ifo;
	
	JTextField t1;
	JTextField t2;
	JTextField t3;
	JTextField t4;
	JTextField t5;
	JTextField t6;
	JTextField t7;
	JButton b1,b2;
	
	JLabel l1,l2,l3,l4,l5,l6,l7;
	
	String snum="";
	Statement st=null;
	public StuIfo(String ss,Statement st1) {
		
		snum = ss;
		
		st = st1;
		setLayout(new FlowLayout());
		try {
			
			String sql = ("select * from Stu where ID = "+snum);
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next())
				{String t11 = rs.getString(1);
				String t22 = rs.getString(2);
				String t33 = rs.getString(4);
				String t44 = rs.getString(5);
				String t55 = rs.getString(6);
				String t66 = rs.getString(7);
				String t77 = rs.getString(8);
			
				
				t1 = new JTextField(15);
                t1.setText(t11);
                t1.setEditable(false);
				t2 = new JTextField(15);
				t2.setText(t22);
				t2.setEditable(false);
				t3 = new JTextField(15);
				t3.setText(t33);
				t3.setEditable(false);
				t4 = new JTextField(15);
				t4.setText(t44);
				t4.setEditable(false);
				t5 = new JTextField(15);
				t5.setText(t55);
				t5.setEditable(false);
				t6 = new JTextField(15);
				t6.setText(t66);
				t6.setEditable(false);
				t7 = new JTextField(15);
				t7.setText(t77);
			    t7.setEditable(false);
				
				Ifo = new JPanel(new GridLayout(15, 2));
				
				//********空格
				
				JLabel tb1 = new JLabel("  ");
				JLabel tb2 = new JLabel("  ");
				JLabel tb3 = new JLabel("  ");
				JLabel tb4 = new JLabel("  ");
				JLabel tb5 = new JLabel("  ");
				JLabel tb6 = new JLabel("  ");
				JLabel tb7 = new JLabel("  ");
				JLabel tb8 = new JLabel("  ");
				JLabel tb9 = new JLabel("  ");
				JLabel tb10 = new JLabel("  ");
				Ifo.add(tb1);
				Ifo.add(tb2);
				Ifo.add(tb3);
				Ifo.add(tb5);
				Ifo.add(tb6);
				Ifo.add(tb7);
				Ifo.add(tb8);
				Ifo.add(tb4);
				
				l1 = new JLabel("学号 ：");
				JLabel l2 = new JLabel("姓名 ：");
				JLabel l3 = new JLabel("性别 ：");
				JLabel l4 = new JLabel("电话 ：");
				JLabel l5 = new JLabel("专业 ：");
				JLabel l6 = new JLabel("住址 ：");
				JLabel l7 = new JLabel("宿舍号 ：");
				
				Font f = new Font("宋体",Font.PLAIN,16); 
				l1.setFont(f);
				l2.setFont(f);
				l3.setFont(f);
				l4.setFont(f);
				l5.setFont(f);
				l6.setFont(f);
				l7.setFont(f);
				
				t1.setFont(f);
				t2.setFont(f);
				t3.setFont(f);
				t4.setFont(f);
				t5.setFont(f);
				t6.setFont(f);
				t7.setFont(f);
				
				
				
				Ifo.add(l1);
				Ifo.add(t1);
				Ifo.add(l2);
				Ifo.add(t2);
				Ifo.add(l3);
				Ifo.add(t3);
				Ifo.add(l4);
				Ifo.add(t4);
				Ifo.add(l5);
				Ifo.add(t5);
				Ifo.add(l6);
				Ifo.add(t6);
				Ifo.add(l7);
				Ifo.add(t7);
				
				Ifo.add(tb9);
				Ifo.add(tb10);
				b1 = new JButton("修改");
				b2 = new JButton("保存");
				b1.addActionListener(this);
				b2.addActionListener(this);
				Ifo.add(b1);
				Ifo.add(b2);
				
				add(Ifo);
				
				
				}
				
				
			
			
			
			
			
		}
		catch(Exception e)
		{
			System.out.println(e.getCause().getMessage());
		}
		
		
		
		
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==b1)
		{
			t4.setEditable(true);
			t5.setEditable(true);
			t6.setEditable(true);
			
		}
		else
		{
			String str1 = t4.getText();
			String str2 = t5.getText();
			String str3 = t6.getText();
			
			String sql1 = ("update Stu set Phonenumber = '"+str1+"'where ID = '"+snum+"'");
			String sql2 = ("update Stu set Professional_class = '"+str2+"'where ID = '"+snum+"'");
			String sql3 = ("update Stu set Address = '"+str3+"'where ID = '"+snum+"'");
			try {
			st.executeUpdate(sql1);
			st.executeUpdate(sql2);
			st.executeUpdate(sql3);
			}
			catch(Exception e)
			{
				
			}
			t4.setEditable(false);
			t5.setEditable(false);
			t6.setEditable(false);
		}
	}
	
}

class Fee extends JPanel implements ActionListener{
	JLabel l1,l2;
	JButton b1,b2;
	JTextField t1,t2;
	String snum;
	Statement st;
	String key ;
	int a=0;
	public Fee(String ss,Statement st1) {
		st=st1;
		snum = ss;
		String sql = ("select * from Stu where ID = "+snum);
		
		try {
				
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next())
			{
				String dd = rs.getString(8);
			    key = dd;
			String sql1 = ("select * from Fees where Doid = "+dd);
			ResultSet rs1 = st.executeQuery(sql1);
			while(rs1.next())
			{
				String res = rs1.getString(2);
				a = Integer.valueOf(res);
				
				JPanel Ifo = new JPanel(new GridLayout(3, 2));
				
				l1 = new JLabel("剩余电费：");
				l2 = new JLabel("充值金额：");
				Font f = new Font("宋体",Font.PLAIN,16); 
				l1.setFont(f);
				l2.setFont(f);
				b1 = new JButton("充值");
				t1 = new JTextField(15);
				t1.setText(res);
				t1.setEditable(false);
				t2 = new JTextField(15);
				b1.addActionListener(this);
				Ifo.add(l1);
				Ifo.add(t1);
				Ifo.add(l2);
				Ifo.add(t2);
				Ifo.add(b1);
				
				this.add(Ifo);
			break;	
			}
				break;
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getCause().getMessage());
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==b1)
		{
			String cc = t2.getText();
			int io=1;
			for(int i=0;i<cc.length();i++)
			{
				if(cc.charAt(i)<'0'||cc.charAt(i)>'9')
				{
					io=-1;
					break;
				}
			}
			if(io==-1)
			{
				JOptionPane.showMessageDialog(null, "输入字符非法！");
				return;
			}
			int b = Integer.valueOf(cc);
			
			System.out.println(a+b);
			String dd = String.valueOf(a+b);
			//update Stu set Phonenumber = '"+str1+"'where ID = '"+snum+"'"
			String sql2 = ("update Fees set Res_charge = '"+dd+"'where Doid = '"+key+"'");
			try {
				st.executeUpdate(sql2);
				String sql3 = ("select * from Fees where Doid = "+key);
				ResultSet rs2 = st.executeQuery(sql3);
				
				while(rs2.next())
				{
					String ff = rs2.getString(2);
					t1.setText(ff);
					t2.setText("");
					JOptionPane.showMessageDialog(null, "充值成功！");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}