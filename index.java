/*********************************************************************
Program Name : Write a java program to illustrate JSeparator and JMenu.
Programmer Name : Madhavi Sonawane.
Date : 20th October 2021.
**********************************************************************/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.*;
import java.awt.print.PrinterException;
import java.util.logging.*;
class jSepMenu extends JFrame implements ActionListener
{
    Container c;
    JMenuBar mb;
    JMenu m1,m2,e1,help;
    JMenuItem f1,f2,f3,f4,print,s1,cut,copy,paste,select,about;
    JCheckBoxMenuItem c1;
    Font f;
    JSeparator s=new JSeparator();
    JTextArea ta;
    JScrollPane sp;
    jSepMenu()
    {
        c=getContentPane();

        setTitle("Notepad");
        ImageIcon i=new ImageIcon(getClass().getResource("Notepad.jpg"));
        setIconImage(i.getImage());

        f=new Font("Tw Cen MT",Font.PLAIN,23);
        
        mb=new JMenuBar();

        m1=new JMenu("File");
        m2=new JMenu("Edit");
        help=new JMenu("Help");
    
        f1=new JMenuItem("New");
        f2=new JMenuItem("Open");
        f3=new JMenuItem("Save");
        print=new JMenuItem("Print");
        f4=new JMenuItem("Exit");
       
        e1=new JMenu("Format");//JMenu
        cut=new JMenuItem("Cut");
        copy=new JMenuItem("Copy");
        paste=new JMenuItem("Paste");
        select=new JMenuItem("Select All");
        s1=new JMenuItem("Zoom");
        c1=new JCheckBoxMenuItem("Word Wrap",false);
        
        about=new JMenuItem("About");

        about.setToolTipText("About Notepad Application");

        ta=new JTextArea(getSize().height,getSize().width);
        ta.setBorder(BorderFactory.createEmptyBorder());

        sp=new JScrollPane(ta);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        m1.add(f1);//New
        m1.add(s);//JSeparator
        m1.add(f2);//Open
        m1.addSeparator();
        m1.add(f3);//Save
        m1.addSeparator();
        m1.add(print);
        m1.addSeparator();
        m1.add(f4);//Exit

        m2.add(cut);
        m2.add(copy);
        m2.add(paste);
        m2.add(select);

        e1.add(s1);//e1 is JMenu, Adding JMenuItem s1 to e1
        //adding JMenu e1 to Main JMenu m2
        e1.addSeparator();
        e1.add(c1);

        help.add(about);

        //adding JMenu's to JMenuBar
        mb.add(m1);
        mb.add(m2);
        mb.add(e1);
        mb.add(help);

        setJMenuBar(mb);
        c.add(sp);

        //JMenuShortcut's
        KeyStroke ctrlO=KeyStroke.getKeyStroke(KeyEvent.VK_O,Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
        KeyStroke ctrlS=KeyStroke.getKeyStroke(KeyEvent.VK_S,Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
        f2.setAccelerator(ctrlO);
        f3.setAccelerator(ctrlS);
        f1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,KeyEvent.CTRL_DOWN_MASK));
        f4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,KeyEvent.CTRL_DOWN_MASK));
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,KeyEvent.CTRL_DOWN_MASK));
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,KeyEvent.CTRL_DOWN_MASK));
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,KeyEvent.CTRL_DOWN_MASK));
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,KeyEvent.CTRL_DOWN_MASK));
        select.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,KeyEvent.CTRL_DOWN_MASK));
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J,KeyEvent.CTRL_DOWN_MASK));

        //Formatting JMenuItem and JMenu
        ta.setFont(new Font("sans serif",Font.PLAIN,25));//setting Font to JTextArea
        m1.setFont(f);
        m2.setFont(f);
        help.setFont(f);
        //JMenuItem's
        f1.setFont(f);
        f2.setFont(f);
        f3.setFont(f);
        f4.setFont(f);
        print.setFont(f);
        cut.setFont(f);
        copy.setFont(f);
        paste.setFont(f);
        select.setFont(f);
        e1.setFont(f);
        s1.setFont(f);
        c1.setFont(f);
        about.setFont(f);

        f1.addActionListener(this);
        f2.addActionListener(this);
        f3.addActionListener(this);
        f4.addActionListener(this);
        print.addActionListener(this);
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        select.addActionListener(this);
        about.addActionListener(this);
        c1.addItemListener(new ItemListener()
        {
            public void itemStateChanged(ItemEvent e)
            {
                if(c1.isSelected()==true){
                    sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                    sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
                    ta.setLineWrap(true);
                    ta.setWrapStyleWord(true);
                }
                else{
                    sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
                    ta.setLineWrap(false);
                    ta.setWrapStyleWord(false);
                }
            }
        });
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==f1)//new
            ta.setText(null);
        else if(e.getSource()==f2)
        {
            JFileChooser fileChooser=new JFileChooser();
            FileNameExtensionFilter textFilter=new FileNameExtensionFilter("Only text files(.txt)","txt");
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(textFilter);
            
            int action=fileChooser.showOpenDialog(null);

            if(action!=JFileChooser.APPROVE_OPTION){
                return;
            }else{
                try{
                    BufferedReader reader=new BufferedReader(new FileReader(fileChooser.getSelectedFile()));
                    ta.read(reader,null);
                }catch(IOException ex){
                    ex.printStackTrace();
                }
            }
        }
        else if(e.getActionCommand()=="Save")
        {
            JFileChooser fileChooser=new JFileChooser();
            FileNameExtensionFilter textFilter=new FileNameExtensionFilter("Only text files(.txt)","txt");
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(textFilter);

            int action=fileChooser.showSaveDialog(null);
            if(action!=JFileChooser.APPROVE_OPTION){
                return;
            }else{
                String fn=fileChooser.getSelectedFile().getAbsolutePath().toString();
                if(!fn.contains(".txt"))
                    fn+=".txt";
                try{
                    BufferedWriter writer=new BufferedWriter(new FileWriter(fn));
                    ta.write(writer);
                }catch(IOException ex){
                    ex.printStackTrace();
                }
            }
        }
        else if(e.getActionCommand()=="Exit"){
            System.exit(0);
        }
        else if(e.getActionCommand().equalsIgnoreCase("print"))
        {
            try{
                ta.print();
            }catch(PrinterException ex){
                Logger.getLogger(jSepMenu.class.getName()).log(Level.SEVERE,null,ex);
            }
        }
        else if(e.getSource()==cut)
            ta.cut();
        else if(e.getActionCommand()=="Copy")
            ta.copy();
        else if(e.getSource()==paste)
            ta.paste();     
        else if(e.getSource()==select)
            ta.selectAll();
        else if(e.getSource()==about)
        {
            aboutnote an=new aboutnote();
            an.setVisible(true);
            an.setTitle("About Notepad Application");
            an.setBounds(550,100,700,700);
            an.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }
    public static void main(String args[])throws Exception
    {
        jSepMenu j=new jSepMenu();
        j.setVisible(true);
        j.setLocation(150,50);
        j.setSize(1630,800);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setDefaultLookAndFeelDecorated(true);
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
}