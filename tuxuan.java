import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class tuxuan extends Frame implements ActionListener {
    JButton b1;JButton b2;
    JRadioButton jc1,jc2,jc3,jc4;
    JTextField t1,t2,t3,t4;
    JTextField jt2;
    private paint p=new paint();
    public tuxuan(){
        super("推选");
        this.setSize(400,600);
        this.setLocation(200,300);
        this.setBackground(Color.lightGray);
        this.setLayout(new BorderLayout());
        JPanel JNorth=new JPanel();
        JNorth.setLayout(new GridLayout(1,1));
        JLabel jl1=new JLabel("总票数：");
        jt2=new JTextField();
        JNorth.add(jl1);JNorth.add(jt2);
        add(JNorth);
        add(JNorth,"North");
        JPanel JWest=new JPanel();
        JWest.setLayout(new GridLayout(5,1));
        JLabel Jlabel=new JLabel("候选人");
        jc1=new JRadioButton("梅西");
        jc2=new JRadioButton("c罗");
        jc3=new JRadioButton("内马尔");
        jc4=new JRadioButton("本泽马");
        JWest.add(Jlabel); JWest.add(jc1);JWest.add(jc2);JWest.add(jc3);JWest.add(jc4);
        add(JWest);

        JPanel JEast=new JPanel();
        JEast.setLayout(new GridLayout(5,2));
        JLabel j1=new JLabel("票数");
        t1=new JTextField();
        t2=new JTextField();
        t3=new JTextField();
        t4=new JTextField();
        JEast.add(j1);JEast.add(t1);JEast.add(t2);JEast.add(t3);JEast.add(t4);
        add(JWest,"West");
        add(JEast,"East");
        JPanel JSouth=new JPanel();
        JSouth.setLayout(new GridLayout(1,1));
        b1=new JButton("投票");
        b2=new JButton("取消");
        JSouth.add(b1);JSouth.add(b2);
        add(JSouth,"South");
        b1.addActionListener(this);
        b2.addActionListener(this);
        add(p,"Center");
        this.setVisible(true);

    }
    public void actionPerformed(ActionEvent e){
        Object obj=e.getSource();

        if(obj==b1){
            if(jc1.isSelected())p.a++;
            if(jc2.isSelected())p.b++;
            if(jc3.isSelected())p.c++;
            if(jc4.isSelected())p.d++;
            p.sum=p.a+p.b+p.c+p.d;
            jt2.setText(String.valueOf(p.sum));
            t1.setText(String.valueOf(p.a));
            t2.setText(String.valueOf(p.b));
            t3.setText(String.valueOf(p.c));
            t4.setText(String.valueOf(p.d));
            p.repaint();
        }
        else{
            System.exit(-1);
        }
    }
    public static void main(String args[]){
        new tuxuan();
    }
}
class paint extends Canvas{
    public int a=0;int b=0;int c=0;int d=0;int sum=0;
    public void paint(Graphics g)
    {
        g.setColor(Color.cyan);
        g.fillRect(10,150,a*5,10);
        g.setColor(Color.red);
        g.fillRect(10,250,b*5,10);
        g.setColor(Color.yellow);
        g.fillRect(10,360,c*5,10);
        g.setColor(Color.pink);
        g.fillRect(10,460,d*5,10);
    }

}


