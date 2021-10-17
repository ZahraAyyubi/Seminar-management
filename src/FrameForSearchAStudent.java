import java.awt.Button;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FrameForSearchAStudent {
    JFrame frame;
    Font font;
    JLabel Background;
    JPanel panel,searchPanel;
    JLabel label;
    JButton search;
    JTextField firstName,lastName,studentID,textField;
    DefaultTableModel model;
    JTable tabel;
    SimpleDateFormat sdf;
    //********************************************************************************************************************************
    //constructor
    public FrameForSearchAStudent() {
        sdf=new SimpleDateFormat("yyyy.MM.dd HH:mm");

        frame=new JFrame("صفحه جستجوی اطلاعات دانشجویان");
        frame.setResizable(false);
        font=new Font("B Nazanin", Font.LAYOUT_RIGHT_TO_LEFT, 15);

        try {
            //use image as a background
            Background = new JLabel(new ImageIcon(ImageIO.read(new File("img\\f2.png"))));
            frame.setContentPane(Background);
            Image icon = Toolkit.getDefaultToolkit().getImage("img\\icon.png");
            frame.setIconImage(icon);

            //this panel use for show information of searched student
            panel=new JPanel();
            panel.setBackground(new Color(0,90,140));
            panel.setBounds(20,90, 700, 500);

            //this panel use for getting information of searched student
            searchPanel=new JPanel();
            searchPanel.setBackground(Color.white);
            searchPanel.setBounds(700,90, 350, 500);

            label=new JLabel("*برای جستجوی دانشجوی موردنظر فیلد های زیررا پر کنید*");
            label.setBounds(25,2,350,50);
            label.setForeground(new Color(130,10,20));
            label.setFont(new Font("B Nazanin", Font.LAYOUT_RIGHT_TO_LEFT, 15));
            searchPanel.add(label);

            label=new JLabel("نام دانشجو");
            label.setBounds(280,50,100,50);
            label.setForeground(Color.black);
            label.setFont(font);
            searchPanel.add(label);

            firstName=new JTextField();
            firstName.setBounds(55,57,180,30);
            firstName.setFont(new Font("B Nazanin", Font.LAYOUT_RIGHT_TO_LEFT, 15));
            firstName.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            searchPanel.add(firstName);

            label=new JLabel("نام خانوادگی");
            label.setBounds(270,105,100,50);
            label.setForeground(Color.black);
            label.setFont(font);
            searchPanel.add(label);

            lastName=new JTextField();
            lastName.setBounds(55,110,180,30);
            lastName.setFont(new Font("B Nazanin", Font.LAYOUT_RIGHT_TO_LEFT, 15));
            lastName.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            searchPanel.add(lastName);

            label=new JLabel("شماره دانشجویی");
            label.setBounds(250,165,98,50);
            label.setForeground(Color.black);
            label.setFont(font);
            searchPanel.add(label);

            studentID=new JTextField();
            studentID.setBounds(55,170,180,30);
            studentID.setFont(new Font("B Nazanin", Font.LAYOUT_RIGHT_TO_LEFT, 15));
            studentID.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            searchPanel.add(studentID);

            label=new JLabel(new ImageIcon(ImageIO.read(new File("img\\f3.png"))));
            label.setBounds(90,260,220,240);
            searchPanel.add(label);

            //the button for search
            search=new JButton("جستجو");
            search.setFont(new Font("IranNastaliq", Font.LAYOUT_RIGHT_TO_LEFT,22));
            search.setBackground(new Color(10,120,170));
            search.setForeground(Color.white);
            search.setBounds(55,220,180,40);
            searchPanel.add(search);

            panel.setLayout(null);
            frame.add(panel);
            searchPanel.setLayout(null);
            frame.add(searchPanel);

        } catch (IOException e) {
            System.out.println("خطایی در اجرای برنامه رخ داده است");
        }
        frame.setSize(1100, 798);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    //in this method if student was existed in seminars shows her/him information and information of seminars that took placed in them
    public void displayInformation(int index, ArrayList<Seminar> seminarsThatTookPlace) {

        Student student=seminarsThatTookPlace.get(seminarsThatTookPlace.size()-1).students.get(index);
        font=new Font("B Nazanin", Font.LAYOUT_RIGHT_TO_LEFT, 15);
        label=new JLabel("نام");
        label.setForeground(Color.white);
        label.setFont(font);
        label.setBounds(620,10,100,50);
        panel.add(label);
        textField=new JTextField(student.firstName);
        textField.setFont(font);
        textField.setEditable(false);
        textField.setBounds(400,10,100,40);
        textField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        panel.add(textField);

        label=new JLabel("نام خانوادگی");
        label.setForeground(Color.white);
        label.setFont(font);
        label.setBounds(200,10,100,50);
        panel.add(label);
        textField=new JTextField(student.lastName);
        textField.setFont(font);
        textField.setEditable(false);
        textField.setBounds(50,10,100,40);
        textField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        panel.add(textField);

        label=new JLabel("شماره دانشجویی");
        label.setForeground(Color.white);
        label.setFont(font);
        label.setBounds(550,70,100,50);
        panel.add(label);
        textField=new JTextField(student.studentID);
        textField.setFont(font);
        textField.setEditable(false);
        textField.setBounds(400,70,100,40);
        panel.add(textField);


        label=new JLabel("مقطع تحصیلی");
        label.setForeground(Color.white);
        label.setFont(font);
        label.setBounds(190,70,100,50);
        panel.add(label);
        textField=new JTextField(student.grade);
        textField.setFont(font);
        textField.setEditable(false);
        textField.setBounds(50,70,100,40);
        textField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        panel.add(textField);


        label=new JLabel("رشته تحصیلی");
        label.setForeground(Color.white);
        label.setFont(font);
        label.setBounds(560,130,100,50);
        panel.add(label);
        textField=new JTextField(student.major);
        textField.setFont(font);
        textField.setEditable(false);
        textField.setBounds(400,130,100,40);
        textField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        panel.add(textField);


        label=new JLabel("دانشگاه");
        label.setForeground(Color.white);
        label.setFont(font);
        label.setBounds(220,130,100,50);
        panel.add(label);
        textField=new JTextField(student.university);
        textField.setFont(font);
        textField.setEditable(false);
        textField.setBounds(50,130,100,40);
        textField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        panel.add(textField);

        label=new JLabel(".............................................................................................................................................................................................................");
        label.setForeground(Color.white);
        label.setFont(font);
        label.setBounds(8,200,800,50);
        panel.add(label);

        label=new JLabel("سمینار هایی که دانشجو در آنها حضور یافته:");
        label.setForeground(Color.white);
        label.setFont(font);
        label.setBounds(450,240,300,50);
        panel.add(label);

        model= new DefaultTableModel();
        tabel=new JTable(model);
        model.addColumn("موضوع سمینار");
        model.addColumn("ارائه دهنده");
        model.addColumn("تاریخ برگزاری");

        //information of seminars that student took place in them shows in an table
        for(int i=0;i<seminarsThatTookPlace.size();i++)
            model.addRow(new Object[] { seminarsThatTookPlace.get(i).subject, seminarsThatTookPlace.get(i).providerName, sdf.format(seminarsThatTookPlace.get(i).startTime.getTime())});

        JScrollPane sp=new JScrollPane(tabel);
        sp.setBounds(15,285,660,200);
        panel.add(sp);

        frame.setContentPane(panel);

    }

    //in this method if student was existed in current seminar shows her/him information and information
    public void displayInformation(int index,Seminar currentSeminar) {

        Student student=currentSeminar.students.get(index);
//		label=new JLabel("نام >> "+student.firstName);
//		label.setForeground(Color.white);
//		label.setFont(font);
//		label.setBounds(600,10,300,50);
//		panel.add(label);
//
//		label=new JLabel("نام خانوادگی >> "+student.lastName);
//		label.setForeground(Color.white);
//		label.setFont(font);
//		label.setBounds(100,10,300,50);
//		panel.add(label);
//
//		label=new JLabel("شماره دانشجویی >>"+student.studentID);
//		label.setForeground(Color.white);
//		label.setFont(font);
//		label.setBounds(550,70,300,50);
//		panel.add(label);
//
//		label=new JLabel("مقطع تحصیلی >>"+student.grade);
//		label.setForeground(Color.white);
//		label.setFont(font);
//		label.setBounds(100,70,300,60);
//		panel.add(label);
//
//		label=new JLabel("رشته تحصیلی >>"+student.major);
//		label.setForeground(Color.white);
//		label.setFont(font);
//		label.setBounds(560,130,300,60);
//		panel.add(label);
//
//		label=new JLabel("دانشگاه  >>"+student.university);
//		label.setForeground(Color.white);
//		label.setFont(font);
//		label.setBounds(125,130,300,50);
//		panel.add(label);

        font=new Font("IranNastaliq", Font.LAYOUT_RIGHT_TO_LEFT, 16);
        label=new JLabel("نام");
        label.setForeground(Color.white);
        label.setFont(font);
        label.setBounds(620,110,100,50);
        panel.add(label);
        textField=new JTextField(student.firstName);
        textField.setFont(font);
        textField.setEditable(false);
        textField.setBounds(400,110,100,40);
        panel.add(textField);

        label=new JLabel("نام خانوادگی");
        label.setForeground(Color.white);
        label.setFont(font);
        label.setBounds(200,110,100,50);
        panel.add(label);
        textField=new JTextField(student.lastName);
        textField.setFont(font);
        textField.setEditable(false);
        textField.setBounds(50,110,100,40);
        panel.add(textField);

        label=new JLabel("شماره دانشجویی");
        label.setForeground(Color.white);
        label.setFont(font);
        label.setBounds(600,170,100,50);
        panel.add(label);
        textField=new JTextField(student.studentID);
        textField.setFont(font);
        textField.setEditable(false);
        textField.setBounds(400,170,100,40);
        panel.add(textField);


        label=new JLabel("مقطع تحصیلی");
        label.setForeground(Color.white);
        label.setFont(font);
        label.setBounds(200,170,100,50);
        panel.add(label);
        textField=new JTextField(student.grade);
        textField.setFont(font);
        textField.setEditable(false);
        textField.setBounds(50,170,100,40);
        panel.add(textField);


        label=new JLabel("رشته تحصیلی");
        label.setForeground(Color.white);
        label.setFont(font);
        label.setBounds(600,230,100,50);
        panel.add(label);
        textField=new JTextField(student.major);
        textField.setFont(font);
        textField.setEditable(false);
        textField.setBounds(400,230,100,40);
        panel.add(textField);


        label=new JLabel("دانشگاه");
        label.setForeground(Color.white);
        label.setFont(font);
        label.setBounds(200,230,100,50);
        panel.add(label);
        textField=new JTextField(student.university);
        textField.setFont(font);
        textField.setEditable(false);
        textField.setBounds(50,230,100,40);
        panel.add(textField);

        label=new JLabel("زمان ورود به سمینار");
        label.setForeground(Color.white);
        label.setFont(font);
        label.setBounds(550,290,200,50);
        panel.add(label);
        textField=new JTextField(student.timeOfEntry.getTime().toString());
        textField.setFont(font);
        textField.setEditable(false);
        textField.setBounds(200,290,300,40);
        panel.add(textField);

        frame.setContentPane(panel);

    }

}

