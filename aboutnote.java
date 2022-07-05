import java.awt.*;
import javax.swing.*;
public class aboutnote extends JFrame
{
    JLabel l1,l2;
    Container c;
    aboutnote()
    {
        c=getContentPane();
        setLayout(null);

        l1=new JLabel(new ImageIcon("Notepad.jpg"));
        l1.setBounds(210,20,250,230);

        l2=new JLabel("<html><body><h1><center>Hello !!</center></h1><br/><h2><center>Welcome to Notepad Application.</h2><br/><br/><h4>Notepad is simple text editor use to create programs, text files.</center></h4><br/><br/><footer><center>© All Rights Reserved 2022</center></footer></body></html>",JLabel.CENTER);
        l2.setBounds(100,150,500,500);

        c.add(l1);
        c.add(l2);

        c.setBackground(Color.lightGray);
        l1.setBorder(BorderFactory.createMatteBorder(5,5,5,5,Color.darkGray));

        setIconImage(new ImageIcon(getClass().getResource("Notepad.jpg")).getImage());
    }
    public static void main(String args[])
    {
        aboutnote an=new aboutnote();
        an.setTitle("About Notepad Application");
        an.setVisible(true);
        an.setBounds(550,200,700,700);
        an.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}