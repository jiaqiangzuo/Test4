import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.event.*;
import  javax.swing.*;


public class Login extends JFrame implements ActionListener,ListSelectionListener{
	JTextField act;
	JPasswordField cod;
	JButton b1,b2;
    String type1;
	JComboBox<String> com1;
	Container c = getContentPane(); //��ȡJFrame���
	static String type[]= {"ѧ��","�޹�"}; 
	
	//***********************
	
    //***********************
	Statement st1;
	public Login  (Statement st)
	{
		super("�������ϵͳ");
		st1=st;
		setBak(); //���ñ�������
		this.setBounds(500, 300, 500, 300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);	
		this.setLayout(new GridLayout(4,1));
		JPanel l1= new JPanel();
		l1.setOpaque(false); 
		JPanel l2= new JPanel();
		l2.setOpaque(false);
		JPanel l3= new JPanel();
		l3.setOpaque(false);
		JPanel l4= new JPanel();
		l4.setOpaque(false);
		Label op1 = new Label("�˺� :");
		Label op2 = new Label("���� :");
		Font f = new Font("����",Font.PLAIN,16); 
		op1.setFont(f);
		op2.setFont(f);
		l1.add(op1);
		act=new JTextField(10);
		l1.add(act);
		add(l1);
		l2.add(op2);
		cod=new JPasswordField(10);
		l2.add(cod);
		add(l2);
		
		b1=new JButton("��½");
		b2=new JButton("�˳�");
		l3.add(b1);
		l3.add(b2);
		add(l3);
		com1=new JComboBox<String>(Login.type);
		l4.add(com1);
		add(l4);
		b1.addActionListener(this);
		b2.addActionListener(this);
		
		 
		this.setVisible(true);
	}
	//����
	public void setBak()
	{
  
	((JPanel)this.getContentPane()).setOpaque(false);
    ImageIcon img = new ImageIcon("C:/Users/zuojiaqiang/Desktop/1.JPG"); //���ͼƬ
	JLabel background = new JLabel(img); 
	this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
	//background.setBounds(0, 0, 500, 300);
	background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
    }

	public static void main(String args[])
	{
		
	}
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		type1=(String)com1.getSelectedItem();
		JOptionPane.showMessageDialog(null, type1);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==b1)
		{
			String acc=act.getText();
			char[] cdd=cod.getPassword();
			String cdd2=String.valueOf(cdd);
			type1=(String)com1.getSelectedItem();
			
			if(act.getText().trim().equals("")||cod.getText().trim().equals(""))
			{
				JOptionPane.showMessageDialog(null, "�˺Ż����벻��Ϊ�գ�");
			}
			else
			{
				//JOptionPane.showMessageDialog(null, type1);
				if(type1=="ѧ��")
				{
					int k=-1;
					
					try {
						String strinsert="select * from Stu";
						ResultSet rs = st1.executeQuery(strinsert);
						
						while(rs.next())
						{
							String acc1=rs.getString(1);							
							String cdd1=rs.getString(3);
							if(acc1.equals(acc)&&cdd1.equals(cdd2))
							{
								k=1;
								break;
							}
							
							
						}
					    }
						catch(Exception e)
						{
							
						}
						if(k==1)
						{
							Stu t2=new Stu(acc,st1);
							this.dispose();
						}
						else
						{
							JOptionPane.showMessageDialog(null, "�˺Ż��������");
						}
				}
				else if(type1=="�޹�")
				{
			int k=-1;
					
					try {
						String strinsert="select * from Homemaster";
						ResultSet rs = st1.executeQuery(strinsert);
						
						while(rs.next())
						{
							String acc1=rs.getString(1);
							String cdd1=rs.getString(3);
							//JOptionPane.showMessageDialog(null, acc1);
							//System.out.println(acc1+"111");
							//System.out.println(acc+"111"+"2222");
							//System.out.println(cdd1+"1111");
							if(acc1.equals(acc)&&cdd1.equals(cdd2))
							{
								//JOptionPane.showMessageDialog(null, acc1);
								k=1;
								break;
							}
							
							
						}
					    }
						catch(Exception e)
						{
							
						}
						if(k==1)
						{
							HMaster hm=new HMaster(acc,st1);
							this.dispose();
						}
						else
						{
							JOptionPane.showMessageDialog(null, "�˺Ż��������");
						}
				}
				
			}
		}
		else
		{
			System.exit(0);
		}
	}
   
}
