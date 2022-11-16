import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.plaf.metal.*;
import java.io.*;


class editor extends JFrame implements ActionListener {

    // text object
    JTextArea t;

    //frame object
    JFrame f;

    editor(){
        //initialiize frame to editor
        f=new JFrame("editor");

        try{
            //set theme
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());

        }catch(Exception e){}

        //initializes textarea
        t=new JTextArea();

        //initialize menubar
        JMenuBar mb=new JMenuBar();

        //creating file menu for the menubar.step 1
        JMenu m1=new JMenu("File");

        //creating menu item for the file menu.step 2
        JMenuItem i1=new JMenuItem("New");
        JMenuItem i2=new JMenuItem("Open");
        JMenuItem i3=new JMenuItem("Save");
        JMenuItem i4=new JMenuItem("Print");


        //calling actionListener to each menu item.step 3
        i1.addActionListener(this);
        i2.addActionListener(this);
        i3.addActionListener(this);
        i4.addActionListener(this);

        //this will add these item into the menu. step 4
        m1.add(i1);
        m1.add(i2);
        m1.add(i3);
        m1.add(i4);

        //creating Edit menu for the menubar.step 1
        JMenu m2=new JMenu("Edit");

        //creating menu item for the edit menu.step 2
        JMenuItem i5=new JMenuItem("Cut");
        JMenuItem i6=new JMenuItem("Copy");
        JMenuItem i7=new JMenuItem("Paste");


        //calling actionListener to each menu item.step 3
        i5.addActionListener(this);
        i6.addActionListener(this);
        i7.addActionListener(this);


        //add these item into the menu. step 4
        m2.add(i5);
        m2.add(i6);
        m2.add(i7);

        //creating the close button step 1
        JMenuItem cl=new JMenuItem("Close");
        //calling actionListener to close menu item.step 3
        cl.addActionListener(this);

        //adding all the menus to the menu bar
        mb.add(m1);
        mb.add(m2);
        mb.add(cl);

        //adding the menubar to the frame
        f.setJMenuBar(mb);
        //adding the textarea to the frame
        f.add(t);
        f.setSize(600,400);
        f.show();

    }
    public void actionPerformed(ActionEvent e){

        //create the button pressed by the user into string s
        String s=e.getActionCommand();

        //create if else condition statement fro each case
        if(s.equals("Cut")){
            t.cut();
        }
        else if (s.equals("Copy")){
            t.copy();
        }
        else if (s.equals("Paste")){
            t.paste();
        }
        else if (s.equals("Save")){
            //creating pointer for the directory
            JFileChooser j=new JFileChooser("C:");

            //invoke the save dialog box
            int r=j.showSaveDialog(null);
            if(r== JFileChooser.APPROVE_OPTION){

                //Set file label to the path of the selected directory.
                File fi=new File(j.getSelectedFile().getAbsolutePath());

                try{
                    //create filewriter
                    FileWriter write=new FileWriter(fi,false);

                    // create bufferwriter
                    BufferedWriter buffer=new BufferedWriter(write);

                    buffer.write(t.getText());
                    buffer.flush();
                    buffer.close();

                }
                catch(Exception ev){
                    JOptionPane.showMessageDialog(f,ev.getMessage());
                }
            }else{
                JOptionPane.showMessageDialog(f,"User has cancelled the operation");
            }
        }
        else if (s.equals("Open")){
            JFileChooser j=new JFileChooser("C:");
            int r=j.showOpenDialog(null);
            if(r== JFileChooser.APPROVE_OPTION){

                //Set file label to the path of the selected directory.
                File fi=new File(j.getSelectedFile().getAbsolutePath());

                try{
                    String s1=""; String s2="";
                    //create filewriter
                    FileReader read=new FileReader(fi);

                    // create bufferwriter
                    BufferedReader buffer=new BufferedReader(read);
                    s1=buffer.readLine();

                    while((s2=buffer.readLine())!=null){
                        s1=s1+"\n"+s2;
                    }
                    t.setText(s1);
                }
                catch(Exception ev){
                    JOptionPane.showMessageDialog(f,ev.getMessage());
                }
            }else{
                JOptionPane.showMessageDialog(f,"User has cancelled the operation");
            }
        }
        else if (s.equals("Print")){
            try{
                t.print();
            }catch(Exception evt){
                JOptionPane.showMessageDialog(f,evt.getMessage());
            }
        }
        else if (s.equals("New")){
            t.setText("");
        }
        else if (s.equals("Close")){
            f.setVisible(false);
        }
    }
//main class to call the editor

public static void main(String[] args) {
    editor e = new editor();

    }
}
