import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FrameforListStudentsOfCurrentSeminar {
    JFrame frame;
    Font font;
    JLabel Background;
    DefaultTableModel model;
    JTable tabel;
    SimpleDateFormat sdf;
    //******************************************************************************************************************************
    //constructor
    public FrameforListStudentsOfCurrentSeminar() {

        sdf=new SimpleDateFormat("yyyy.MM.dd HH:mm");
        frame=new JFrame("نمایش دانشجویان حاضر درجلسه");
        frame.setResizable(false);
        font=new Font("IranNastaliq", Font.LAYOUT_RIGHT_TO_LEFT, 22);
        try {
            Background = new JLabel(new ImageIcon(ImageIO.read(new File("img\\f2.png"))));
            frame.setContentPane(Background);
            Image icon = Toolkit.getDefaultToolkit().getImage("img\\icon.png");
            frame.setIconImage(icon);

        } catch (IOException e) {
            System.out.println("خطایی در اجرای برنامه رخ داده است");
        }
        frame.setSize(1100, 900);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    //building table for display information of students of current seminar
    public void buildTable(Seminar seminar) {
        model= new DefaultTableModel();
        tabel=new JTable(model);
        model.addColumn("نام");
        model.addColumn("نام خانوادگی");
        model.addColumn("شماره دانشجویی");
        model.addColumn("مقطع تحصیلی");
        model.addColumn("رشته تحصیلی");
        model.addColumn("دانشگاه محل تحصیل");
        model.addColumn("زمان ورود به سمینار");
        for(int i=0;i<seminar.students.size();i++)
            model.addRow(new Object[] { seminar.students.get(i).firstName, seminar.students.get(i).lastName, seminar.students.get(i).studentID, seminar.students.get(i).grade, seminar.students.get(i).major, seminar.students.get(i).university, sdf.format(seminar.students.get(i).timeOfEntry.getTime())});

        JScrollPane sp=new JScrollPane(tabel);
        sp.setBounds(10,20,1050,800);
        Background.add(sp);
    }

}


