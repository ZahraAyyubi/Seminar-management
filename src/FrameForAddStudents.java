import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FrameForAddStudents {
    JFrame frame;
    Font font;
    JLabel Background;
    JPanel panel;//for add some components
    JLabel label,endTime,subjectOfSeminar,providerOfSeminar,remainingCapacity,numOfStudentsInSeminar,timeLabel;
    JButton addStudent,endingTheSeminar,search,check,listStudentsOfCurrentSeminar;
    JTextField firstName,lastName,studentID,major,university;
    String grade[]={"کارشناسی","کارشناسی ارشد","دکترا","هیچ کدام"};
    JComboBox grades;
    Time timerThread;
    //*****************************************************************************************************************
    //constructor
    public FrameForAddStudents() {
        frame=new JFrame("صفحه ثبت اطلاعات دانشجویان");
        frame.setResizable(false);
        font=new Font("B Nazanin", Font.LAYOUT_RIGHT_TO_LEFT, 18);//changing font
        try {
            Background = new JLabel(new ImageIcon(ImageIO.read(new File("img\\f2.png"))));//for set an image for background
            frame.setContentPane(Background);
            Image icon = Toolkit.getDefaultToolkit().getImage("img\\icon.png");
            frame.setIconImage(icon);
            //for show time in frame
            timeLabel=new JLabel(new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(Calendar.getInstance().getTime()));
            timeLabel.setForeground(Color.white);
            timeLabel.setFont(new Font("", Font.LAYOUT_RIGHT_TO_LEFT, 15));
            timeLabel.setBounds(320,45,200,55);
            Background.add(timeLabel);

            //this object do the update time operation
            timerThread=new Time(timeLabel);
            timerThread.start();

            //for show subject of current seminar
            subjectOfSeminar=new JLabel();
            subjectOfSeminar.setBounds(570,70,200,50);
            subjectOfSeminar.setForeground(Color.white);
            subjectOfSeminar.setFont(new Font("B Nazanin", Font.LAYOUT_RIGHT_TO_LEFT, 15));
            Background.add(subjectOfSeminar);

            //for show provider of current seminar
            providerOfSeminar=new JLabel();
            providerOfSeminar.setBounds(350,70,200,50);
            providerOfSeminar.setForeground(Color.white);
            providerOfSeminar.setFont(new Font("B Nazanin", Font.LAYOUT_RIGHT_TO_LEFT, 15));
            Background.add(providerOfSeminar);

            //for show the ending time of current seminar
            endTime=new JLabel();
            endTime.setBounds(155,70,200,50);
            endTime.setForeground(Color.white);
            endTime.setFont(new Font("B Nazanin", Font.LAYOUT_RIGHT_TO_LEFT, 15));
            Background.add(endTime);


            panel=new JPanel();
            panel.setBackground(Color.white);
            panel.setBounds(155,118, 500, 450);

            //for display the remaining capacity
            remainingCapacity=new JLabel();
            remainingCapacity.setBounds(300,300,200,45);
            remainingCapacity.setForeground(new Color(0,100,130));
            remainingCapacity.setFont(new Font("B Nazanin", Font.LAYOUT_RIGHT_TO_LEFT, 15));
            panel.add(remainingCapacity);

            //for display numbers of students that took place
            numOfStudentsInSeminar=new JLabel();
            numOfStudentsInSeminar.setBounds(100,300,200,45);
            numOfStudentsInSeminar.setForeground(new Color(0,100,130));
            numOfStudentsInSeminar.setFont(new Font("B Nazanin", Font.LAYOUT_RIGHT_TO_LEFT, 15));
            panel.add(numOfStudentsInSeminar);

            label=new JLabel("نام");
            label.setBounds(445,2,50,50);
            label.setForeground(Color.black);
            label.setFont(font);
            panel.add(label);

            //for getting first name of student
            firstName=new JTextField();
            firstName.setBounds(60,15,240,25);
            firstName.setFont(new Font("B Nazanin", Font.LAYOUT_RIGHT_TO_LEFT, 15));
            firstName.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            panel.add(firstName);

            label=new JLabel("نام خانوادگی");
            label.setBounds(380,52,100,50);
            label.setForeground(Color.black);
            label.setFont(font);
            panel.add(label);

            //for getting last name of student
            lastName=new JTextField();
            lastName.setBounds(60,60,240,25);
            lastName.setFont(new Font("B Nazanin", Font.LAYOUT_RIGHT_TO_LEFT, 15));
            lastName.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            panel.add(lastName);

            label=new JLabel("شماره دانشجویی");
            label.setBounds(355,102,120,50);
            label.setForeground(Color.black);
            label.setFont(font);
            panel.add(label);

            //for getting students id
            studentID=new JTextField();
            studentID.setBounds(60,110,240,25);
            studentID.setFont(new Font("B Nazanin", Font.LAYOUT_RIGHT_TO_LEFT, 15));
            studentID.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            panel.add(studentID);

            label=new JLabel("مقطع تحصیلی");
            label.setBounds(368,152,98,65);
            label.setForeground(Color.black);
            label.setFont(font);
            panel.add(label);

            //Allows the user to select an option from the list provided
            grades=new JComboBox(grade);
            grades.setBounds(60,160,240,25);
            panel.add(grades);

            label=new JLabel("رشته تحصیلی");
            label.setBounds(370,200,98,65);
            label.setForeground(Color.black);
            label.setFont(font);
            panel.add(label);

            //for getting student major
            major=new JTextField();
            major.setBounds(60,215,240,25);
            major.setFont(new Font("B Nazanin", Font.LAYOUT_RIGHT_TO_LEFT, 15));
            major.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            panel.add(major);

            label=new JLabel("دانشگاه محل تحصیل");
            label.setBounds(325,250,150,67);
            label.setForeground(Color.black);
            label.setFont(font);
            panel.add(label);

            //for getting name of his/her university
            university=new JTextField();
            university.setBounds(60,270,240,25);
            university.setFont(new Font("B Nazanin", Font.LAYOUT_RIGHT_TO_LEFT, 15));
            university.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            panel.add(university);

            //the button for save information that entered
            addStudent=new JButton("ثبت اطلاعات دانشجو");
            addStudent.setFont(new Font("IranNastaliq", Font.LAYOUT_RIGHT_TO_LEFT,24));
            addStudent.setBackground(new Color(0,100,130));
            addStudent.setForeground(Color.white);
            addStudent.setBounds(255,400,240,45);
            panel.add(addStudent);

            //the button that exit seminar and adding students to current seminar
            endingTheSeminar=new JButton("پایان سمینار");
            endingTheSeminar.setFont(new Font("IranNastaliq", Font.LAYOUT_RIGHT_TO_LEFT,24));
            endingTheSeminar.setBackground(new Color(0,100,130));
            endingTheSeminar.setForeground(Color.white);
            endingTheSeminar.setBounds(5,400,245,45);
            panel.add(endingTheSeminar);

            //the button that can show the list of students that are in seminar
            listStudentsOfCurrentSeminar=new JButton("لیست دانشجویان حاضر در سمینار");
            listStudentsOfCurrentSeminar.setFont(new Font("IranNastaliq", Font.LAYOUT_RIGHT_TO_LEFT,24));
            listStudentsOfCurrentSeminar.setBackground(new Color(0,100,130));
            listStudentsOfCurrentSeminar.setForeground(Color.white);
            listStudentsOfCurrentSeminar.setBounds(255,350,240,45);
            panel.add(listStudentsOfCurrentSeminar);

            //the button for search existence and his/her informations from students that are took place in current seminar
            search=new JButton("جستجوی دانشجودردانشجویان حاضردرسمینار");
            search.setFont(new Font("IranNastaliq", Font.LAYOUT_RIGHT_TO_LEFT,24));
            search.setBackground(new Color(0,100,130));
            search.setForeground(Color.white);
            search.setBounds(5,350,245,45);
            panel.add(search);

            panel.setLayout(null);
            frame.add(panel);

        } catch (IOException e) {
            System.out.println("خطایی در برنامه رخ داده است");
        }
        frame.setSize(850, 800);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
