import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class move extends Frame implements ActionListener {
    JButton b1,b2,b3,b4;
    public int x=300,y=150;
    public move()
    {
        setTitle("小车移动");
        setSize(650,365);
        setVisible(true);
        b1=new JButton("向上移");
        b2=new JButton("向下移");
        b3=new JButton("向左移");
        b4=new JButton("向右移");
        this.setLayout(new BorderLayout());
        this.add(b1,"North");
        this.add(b2,"South");
        this.add(b3,"West");
        this.add(b4,"East");
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);

        this.setVisible(true);
    }
    public void paint(Graphics g)
    {
        g.setColor(Color.cyan);
        g.fillRect(x,y,20,30);
        g.fillOval(x-10,y+10,10,10);
        g.fillOval(x+20,y+10,10,10);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj=e.getSource();
        if(obj==b1)
        {
            y=y-10;
        }
        if(obj==b2)
        {
            y+=10;
        }
        if(obj==b3)
        {
            x-=5;
        }
        if(obj==b4){
            x+=5;
        }
        this.repaint();
    }
    public static void main(String args[])
    {
     new move();
    }
}
