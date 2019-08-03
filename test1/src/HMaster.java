import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
public class HMaster extends JFrame {
	Container c = getContentPane(); //获取JFrame面板
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
	String snum;
	Statement st;
    public HMaster(String ss,Statement st1) {
         
        snum=ss;
        st=st1;
        setBak();
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
			Dosu Ss=new Dosu(snum,st);
			tabbedPane.addTab("宿舍信息",Ss);
			Dor sushe=new Dor(snum,st);
			tabbedPane.addTab("人员信息", sushe);
			Vistor vis=new Vistor(snum,st);
			tabbedPane.addTab("来访人员", vis);
			Dele shanchu=new Dele(snum,st);
			tabbedPane.addTab("删除操作", shanchu);
			tabbedPane.setSelectedIndex(0); //设置索引为2的选项卡被选中
			
			
			add(tabbedPane);

	
		 
		 this.setVisible(true);
    }
    public void setBak()
	{
  
	((JPanel)this.getContentPane()).setOpaque(false);
    ImageIcon img = new ImageIcon("C:/Users/zuojiaqiang/Desktop/1.JPG"); //添加图片
	JLabel background = new JLabel(img); 
	this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
	//background.setBounds(0, 0, 500, 300);
	background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
    }

	public static void main(String[] args) {
	

	}

}
class Dosu extends JPanel implements ActionListener{
	String[] col = { "宿舍号", "楼号", "层号","人数" };
    DefaultTableModel mm = new DefaultTableModel(col, 0);
    String snum,Dno;
    JTable table=new JTable();
    JButton submit;
    Statement st;
    public Dosu(String ss,Statement st1)
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
    		find();
    		String strinsert="select * from Dorm";
			ResultSet rs1 = st.executeQuery(strinsert);
			while(rs1.next())
			{
				String doid=rs1.getString(1);
	            String bu=rs1.getString(2);
	            String fl=rs1.getString(3);
	            String num=rs1.getString(4);
	            String[] data={doid,bu,fl,num};
	            mm.addRow(data);
	            System.out.println(data);
			}	
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getCause().getMessage());
    	}
    }  
    private void find()
    {
        submit=new JButton("   更新  ");
        JPanel suguan=new JPanel(new GridLayout(4, 2));
        suguan.add(submit);
        submit.addActionListener(this);
        add(suguan);
    }
    public void actionPerformed(ActionEvent e) {
    	if(e.getSource()==submit)
    	{
    		try {
				//st.executeUpdate(sql);
				String sql1=("select * from Dorm");
				ResultSet sas = st.executeQuery(sql1);	
				while(mm.getRowCount()>0){//把表格进行刷新，下次显示的时候重头开始显示
                    //System.out.println(model.getRowCount());
                    mm.removeRow(mm.getRowCount()-1);
                }
				while(sas.next())
				{
				
					String doid=sas.getString(1);
                    String bu=sas.getString(2);
                    String fl=sas.getString(3);
                    String num=sas.getString(4);
                    String[] data={doid,bu,fl,num};
                    mm.addRow(data);
		            
				}
				
			} catch (SQLException ee) {
				// TODO Auto-generated catch block
				ee.printStackTrace();
			}
    	}
    }
}
class Vistor extends JPanel implements ActionListener{
	PreparedStatement state;
    ResultSet resultSet;
    
	String[] col = { "姓名", "来访时间", "来访目的" };
    DefaultTableModel mm = new DefaultTableModel(col, 0);
    JTable table=new JTable();
    Statement st;
    String snum;
    JButton submit;
    JTextField t1;
    JTextField t2;
    JTextField t3;
    public Vistor(String ss,Statement st1){
    	snum=ss;
    	st=st1;
    	setLayout(new FlowLayout());
        table.setModel(mm);
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
    		tianjia();
    		String strinsert="select * from Vistor1";
			ResultSet rs1 = st.executeQuery(strinsert);
			while(rs1.next())
			{
				String name=rs1.getString(1);
	            String time=rs1.getString(2);
	            String mudi=rs1.getString(3);
	            String[] data={name,time,mudi};
	            mm.addRow(data);
	            System.out.println(data);
			}

    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getCause().getMessage());
    	}
    }
    private void tianjia()
    {
    	
    	JLabel l1=new JLabel("   姓名：");
    	JLabel l2=new JLabel("   来访时间 :");
    	JLabel l3=new JLabel("   来访目的 :");
    	Font f = new Font("宋体",Font.PLAIN,16); 
    	l1.setFont(f);
    	l2.setFont(f);
    	l3.setFont(f);
    	t1=new JTextField(15);
    	t2=new JTextField(15);
    	t3=new JTextField(15);
    	submit=new JButton("   确定  ");
        submit.addActionListener(this);
        JPanel suguan=new JPanel(new GridLayout(4, 2));
        suguan.add(l1);
        suguan.add(t1);
       suguan.add(l2);
       suguan.add(t2);
       suguan.add(l3);
       suguan.add(t3);
       suguan.add(submit);
       add(suguan);
    }
    public void actionPerformed(ActionEvent e) {
    	if(e.getSource()==submit)
    	{
    		String s1,s2,s3;
    		s1=t1.getText();
    		s2=t2.getText();
    		s3=t3.getText();
    		String sql=("insert into Vistor1 (Vis_Name,VistorTime,VistorM) values ('"+s1+"','"+s2+"','"+s3+"')");
    		String sql1=("select * from Vistor1");
    		t1.setText("");t2.setText("");t3.setText("");
    		try {
				st.executeUpdate(sql);
				ResultSet sas = st.executeQuery(sql1);
				
				while(mm.getRowCount()>0){//把表格进行刷新，下次显示的时候重头开始显示
                    //System.out.println(model.getRowCount());
                    mm.removeRow(mm.getRowCount()-1);
                }
				while(sas.next())
				{
				
					String name=sas.getString(1);
                    String time=sas.getString(2);
                    String mudi=sas.getString(3);
                    String[] data={name,time,mudi};
                    mm.addRow(data);
		            
				}
				
			} catch (SQLException ee) {
				// TODO Auto-generated catch block
				ee.printStackTrace();
			}
    	}
    }
}
//人员信息
class Dor extends JPanel implements ActionListener{
	PreparedStatement state;
    ResultSet resultSet;
	String[] col = { "学号", "姓名", "性别","专业","宿舍号","入住时间" };
    DefaultTableModel mm = new DefaultTableModel(col, 0);
    JTable table=new JTable();
    JPanel suguan,suguan1;
    Statement st;
    String snum;
    String mem;
    int mem1;
    JButton submit,submit1;
    JLabel Doid,ID,Sex,Pro,Add,sushe,Name,pass,pho;
	JTextField DoidText,IDText,SexText,ProText,AddText,susheText,NameText,passText,phoText;
    JTextField t1;
    public Dor(String ss,Statement st1){
    	snum=ss;
    	st=st1;
    	setLayout(new FlowLayout());
        table.setModel(mm);
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
    		chaxun();
    		String strinsert="select * from Stu";
			ResultSet rs1 = st.executeQuery(strinsert);
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
    	catch(Exception p)
    	{
    		System.out.println(p.getCause().getMessage());
    	}
    }
    private void chaxun() {
    	JLabel l1=new JLabel("宿舍号");
    	t1=new JTextField(4);
    	Doid=new JLabel("宿舍号");
    	ID=new JLabel("学号");
    	Sex=new JLabel("性别");
    	Pro=new JLabel("专业");
    	Add=new JLabel("地址");	
    	Name=new JLabel("姓名");
    	pass=new JLabel("密码");
    	pho=new JLabel("电话");
    	DoidText=new JTextField(4);
    	IDText=new JTextField(7);
    	SexText=new JTextField(4);
    	ProText=new JTextField(10);
    	AddText=new JTextField(10);
    	NameText=new JTextField(10);
    	passText=new JTextField(10);
    	phoText=new JTextField(11);
    	submit=new JButton("   确定  ");
    	submit1=new JButton(" 添加");
    	suguan1=new JPanel(new GridLayout(3,2));
    	suguan=new JPanel(new GridLayout(10,2));
    	suguan1.add(l1);
    	suguan1.add(t1);
    	suguan1.add(submit);
    	suguan.add(ID);
    	suguan.add(IDText);
    	suguan.add(Name);
    	suguan.add(NameText);
    	suguan.add(pass);
    	suguan.add(passText);
    	suguan.add(Sex);
    	suguan.add(SexText);
    	suguan.add(pho);
    	suguan.add(phoText);
    	suguan.add(Pro);
    	suguan.add(ProText);
    	suguan.add(Add);
    	suguan.add(AddText);
    	suguan.add(Doid);
    	suguan.add(DoidText);
    	suguan.add(submit1);
    	submit.addActionListener(this);
    	submit1.addActionListener(this);
    	add(suguan1);
    	add(suguan);
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==submit)
		{
			try {
			String strinsert="select * from Stu";
			ResultSet rs = st.executeQuery(strinsert);	
			String s=t1.getText();
			String strinsert1="select * from Stu where Doid = "+s;
			ResultSet rs1 = st.executeQuery(strinsert1);
			while(mm.getRowCount()>0){//把表格进行刷新，下次显示的时候重头开始显示
                //System.out.println(model.getRowCount());
                mm.removeRow(mm.getRowCount()-1);
            }
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

				
			} catch (SQLException ee) {
				ee.printStackTrace();
			}
		}
		if(e.getSource()==submit1)
		{
			String t1,t2,t3,t4,t5,t6,t7,t8;
			t1=IDText.getText();
			t2=NameText.getText();
			t3=SexText.getText();
			t4=ProText.getText();
			t5=AddText.getText();
			t6=DoidText.getText();
			t7=passText.getText();
			t8=phoText.getText();
			
			
			String sql = ("select * from Dorm where DoID = '"+t6+"'");
			try {
				int k=-1;
				String sql3 = ("select * from Dorm");
				ResultSet rs1 = st.executeQuery(sql3);
				
				while(rs1.next())
				{
					String mem2 = rs1.getString(1);
					int a = Integer.valueOf(mem2);
					int b = Integer.valueOf(t6);
					
					if(a==b)
					{
					
						k=1;
						break;
					}
				}
			
				if(k==1)
				{
					ResultSet rs = st.executeQuery(sql);
				while(rs.next())
				{
					String mem = rs.getString(4);
					int mem1 = Integer.valueOf(mem);
					if(mem1>5)
					{
						JOptionPane.showMessageDialog(null, "当前宿舍人数已满!");
						break;
					}
					else
					{
							mem1++;
							String sql1 =( "update Dorm set member = '"+String.valueOf(mem1)+"' where DoID = '"+t6+"'");
							st.executeUpdate(sql1);
							
							String sql2 = ("insert into Stu (ID,Name,Password,Sex,Phonenumber,Professional_class,Address,Doid) values ( '"+t1+"','"+t2+"','"+t7+"','"+t3+"','"+t8+"','"+t4+"','"+t5+"','"+t6+"')");
							
							try {
								st.executeUpdate(sql2);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							JOptionPane.showMessageDialog(null, "添加成功！");
							while(mm.getRowCount()>0){//把表格进行刷新，下次显示的时候重头开始显示
				                
				                mm.removeRow(mm.getRowCount()-1);
				            }
							
							String strinsert1="select * from Stu";
							ResultSet rs5 = st.executeQuery(strinsert1);
							while(rs5.next())
							{
								String Sno=rs5.getString(1);
					            String Sname=rs5.getString(2);
					            String Ssex=rs5.getString(4);
					            String Sdept=rs5.getString(6);
					            String Dno=rs5.getString(8);
					            String Scheckin=rs5.getString(7);
					            String[] data={Sno,Sname,Ssex,Sdept,Dno,Scheckin};
					            mm.addRow(data);
					            System.out.println(data);
							}
						
					}
					break;}}
				else
				{
					JOptionPane.showMessageDialog(null, "没有该宿舍！");
				}
				}
			 catch (SQLException e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getCause().getMessage());}
			}
				}
			
		
	
}
class Dele extends JPanel implements ActionListener{
	PreparedStatement state;
    ResultSet resultSet;
	String[] col = { "学号", "姓名", "性别","专业","宿舍号","入住时间" };
    DefaultTableModel mm = new DefaultTableModel(col, 0);
    JTable table=new JTable();
    JPanel suguan;
    Statement st;
    String snum,mem;
    JButton submit,submit1;
    JTextField t1;
    public Dele(String ss,Statement st1){
    	snum=ss;
    	st=st1;
    	setLayout(new FlowLayout());
        table.setModel(mm);
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
    		chaxun();
    		String strinsert="select * from Stu";
			ResultSet rs1 = st.executeQuery(strinsert);
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
    	catch(Exception p)
    	{
    		System.out.println(p.getCause().getMessage());
    	}
    }
    private void chaxun() {
    	JLabel l1=new JLabel("学号");
    	t1=new JTextField(4);
    	submit=new JButton("   确定  ");
    	JPanel suguan=new JPanel(new GridLayout(3,2));
    	suguan.add(l1);
    	suguan.add(t1);
    	suguan.add(submit);
    	submit.addActionListener(this);
    	
    	add(suguan);
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==submit)
		{
			String s1=t1.getText();
			String sql0="select *from Stu where ID ="+s1;
			t1.setText("");
		
				
			String sql=("delete from Stu where ID="+s1);
		    String sql1="select * from Stu";
		    try {
		    	String sql2="select * from Dorm";
		    	ResultSet rs = st.executeQuery(sql2);
				st.executeUpdate(sql);
				ResultSet sas = st.executeQuery(sql1);
				JOptionPane.showMessageDialog(null, "删除成功！");
				while(mm.getRowCount()>0){//把表格进行刷新，下次显示的时候重头开始显示
                    //System.out.println(model.getRowCount());
                    mm.removeRow(mm.getRowCount()-1);
                }
				while(sas.next())
				{
				
					String Sno=sas.getString(1);
		            String Sname=sas.getString(2);
		            String Ssex=sas.getString(4);
		            String Sdept=sas.getString(6);
		            String Dno=sas.getString(8);
		            String Scheckin=sas.getString(7);
		            String[] data={Sno,Sname,Ssex,Sdept,Dno,Scheckin};
		            mm.addRow(data);
				}
			} catch (SQLException ee) {
				// TODO Auto-generated catch block
				ee.printStackTrace();
			}
			}
		
	}
}


