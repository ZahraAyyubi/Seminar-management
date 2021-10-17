import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FrameForListStudents implements ActionListener {
    JFrame frame;
    Font font;
    JLabel Background,label;
    JPanel panel;
    DefaultTableModel model;//for add rows or column to a table
    JButton exit;
    ArrayList<JButton> seminarsButtons;//each seminar have an distinct button
    JTable tabel1;//for show list of student
    JTable tabel2;//for show information of seminar
    ArrayList<Seminar> seminars;//for using in  an action listener
    SimpleDateFormat sdf;

    //****************************************************************************************************************************

    //constructor
    public FrameForListStudents(ArrayList<Seminar> seminars) {
        sdf=new SimpleDateFormat("yyyy.MM.dd HH:mm");
        this.seminars=seminars;

        seminarsButtons=new ArrayList<JButton>();
        frame=new JFrame("نمایش دانشجویان حاضر در هر جلسه");
        frame.setResizable(false);
        font=new Font("IranNastaliq", Font.LAYOUT_RIGHT_TO_LEFT, 22);

        try {
            //using an image as a background
            Background = new JLabel(new ImageIcon(ImageIO.read(new File("img\\f2.png"))));
            frame.setContentPane(Background);
            Image icon = Toolkit.getDefaultToolkit().getImage("img\\icon.png");
            frame.setIconImage(icon);

            panel=new JPanel();
            panel.setBackground(Color.white);

            //setting text for button of each seminar
            for(int i=0;i<seminars.size();i++) {
                seminarsButtons.add(new JButton("سمینار  "+(i+1)));
                seminarsButtons.get(i).setFont(new Font("B Nazanin", Font.LAYOUT_RIGHT_TO_LEFT,20));
                seminarsButtons.get(i).setBackground(new Color(0,100,130));
                seminarsButtons.get(i).setForeground(Color.white);
                seminarsButtons.get(i).addActionListener(this);

                panel.add(seminarsButtons.get(i));
            }


            JScrollPane js = new JScrollPane(panel);//add scroll pane to panel of buttons
            js.setBounds(1100,10,300, 800);
            js.setPreferredSize(new Dimension(200,100));
            Background.add(js);
            panel.setLayout(new GridLayout(20,10));
            frame.add(js);

        } catch (IOException e) {
            System.out.println("خطایی در سیستم رخ داده است");
        }
        frame.setSize(1500, 900);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    //this method build a table according to seminar
    public void buildTable(Seminar seminar) {
        model= new DefaultTableModel();
        tabel1=new JTable(model);
        //adding columns
        model.addColumn("نام");
        model.addColumn("نام خانوادگی");
        model.addColumn("شماره دانشجویی");
        model.addColumn("مقطع تحصیلی");
        model.addColumn("رشته تحصیلی");
        model.addColumn("دانشگاه محل تحصیل");
        model.addColumn("زمان ورود به سمینار");
        //adding informations of seminars students as rows of table
        for(int i=0;i<seminar.students.size();i++)
            model.addRow(new Object[] { seminar.students.get(i).firstName, seminar.students.get(i).lastName, seminar.students.get(i).studentID, seminar.students.get(i).grade, seminar.students.get(i).major, seminar.students.get(i).university, sdf.format(seminar.students.get(i).timeOfEntry.getTime())});

        JScrollPane sp=new JScrollPane(tabel1);
        sp.setBounds(10,70,1080,745);
        Background.add(sp);

        model= new DefaultTableModel();
        tabel2=new JTable(model);

        model.addColumn("موضوع سمینار");
        model.addColumn("ارائه دهنده");
        model.addColumn("تاریخ شروع");
        model.addColumn("زمان پایان");
        model.addColumn(" کل ظرفیت");
        model.addColumn("تعداد شرکت کنندگان");
        model.addColumn("ظرفیت باقی مانده");
        model.addRow(new Object[] { seminar.subject, seminar.providerName, sdf.format(seminar.startTime.getTime()), sdf.format(seminar.endingTime.getTime()), seminar.capacity,seminar.students.size(),seminar.capacity-seminar.students.size()});

        JScrollPane sp2=new JScrollPane(tabel2);
        sp2.setBounds(10,10,1080,50);
        Background.add(sp2);

    }
    //add action for each button of a seminar for show informations of seminar
    @Override
    public void actionPerformed(ActionEvent e) {
        for(int j=0;j<seminarsButtons.size();j++) {//index of each button of a seminar is equal with index of that seminar in array of seminars
            if(e.getSource()==seminarsButtons.get(j)) {
                buildTable(seminars.get(j));
            }
        }

    }
}
