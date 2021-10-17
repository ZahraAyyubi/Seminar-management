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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FrameForNewSeminar {
    JFrame frame;
    Font font;//for changing font
    JLabel Background;
    JPanel panel;
    JLabel label,timeLabel;//time label use for display current time
    JTextField subject,providerName,hourOfEndTime,minuteOfEndTime,capacity;
    //	JTextArea comment;
    JButton save,cancel;
    Time timerThread; //for update time
    //****************************************************************************************************************
    //constructor
    public FrameForNewSeminar() {
        frame=new JFrame("ایجاد سمینار جدید");
        frame.setResizable(false);
        font=new Font("B Nazanin", Font.LAYOUT_RIGHT_TO_LEFT, 18);
        try {
            //use image as a background
            Background = new JLabel(new ImageIcon(ImageIO.read(new File("img\\f1.png"))));
            frame.setContentPane(Background);
            Image icon = Toolkit.getDefaultToolkit().getImage("img\\icon.png");
            frame.setIconImage(icon);
            panel=new JPanel();
            panel.setBackground(Color.white);
            panel.setBounds(90,60, 500, 320);


            timeLabel=new JLabel(new SimpleDateFormat("yyyy.MM.dd    HH:mm:ss").format(Calendar.getInstance().getTime()));
            timeLabel.setForeground(Color.white);
            timeLabel.setFont(new Font("", Font.LAYOUT_RIGHT_TO_LEFT, 16));
            timeLabel.setBounds(250,10,200,55);
            Background.add(timeLabel);

            timerThread=new Time(timeLabel);
            timerThread.start();//update time in timeLabel

            label=new JLabel("موضوع سمینار");
            label.setForeground(Color.black);
            label.setFont(font);
            label.setBounds(380,6,100,55);
            panel.add(label);

            //for getting subject of new seminar
            subject=new JTextField();
            subject.setFont(new Font("B Nazanin",Font.LAYOUT_RIGHT_TO_LEFT, 15));
            subject.setBounds(40,15,300,30);
            subject.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            panel.add(subject);

            label=new JLabel("ارائه دهنده سمینار");
            label.setForeground(Color.black);
            label.setFont(font);
            label.setBounds(355,70,150,55);
            panel.add(label);

            //for getting provider of new seminar
            providerName=new JTextField();
            providerName.setFont(new Font("B Nazanin",Font.LAYOUT_RIGHT_TO_LEFT, 15));
            providerName.setBounds(40,80,300,30);
            providerName.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            panel.add(providerName);

            label=new JLabel("زمان اتمام سمینار          دقیقه                ساعت");
            label.setForeground(Color.black);
            label.setFont(font);
            label.setBounds(180,130,600,55);
            panel.add(label);

            /*one usage of getting end time of seminar is that don't let add student to seminar */
            //for getting hour of end time of new seminar
            hourOfEndTime=new JTextField();
            hourOfEndTime.setFont(new Font("B Nazanin",Font.LAYOUT_RIGHT_TO_LEFT, 15));
            hourOfEndTime.setBounds(135,142,36,30);
            panel.add(hourOfEndTime);

            //for getting minute of end time of new seminar
            minuteOfEndTime=new JTextField();
            minuteOfEndTime.setFont(new Font("B Nazanin",Font.LAYOUT_RIGHT_TO_LEFT, 15));
            minuteOfEndTime.setBounds(235,142,36,30);
            panel.add(minuteOfEndTime);

            label=new JLabel("ظرفیت");
            label.setForeground(Color.black);
            label.setFont(font);
            label.setBounds(430,200,100,55);
            panel.add(label);

            //for getting capacity of seminar
            capacity=new JTextField();
            capacity.setFont(new Font("B Nazanin",Font.LAYOUT_RIGHT_TO_LEFT, 15));
            capacity.setBounds(380,210,36,30);
            panel.add(capacity);

            //the button for save information that entered
            save=new JButton("ذخیره اطلاعات");
            save.setFont(new Font("IranNastaliq", Font.LAYOUT_RIGHT_TO_LEFT,26));
            save.setBackground(new Color(0,100,130));
            save.setForeground(Color.white);
            save.setBounds(250,268,245,50);
            panel.add(save);

            //the button for cancel adding new seminar
            cancel=new JButton("لغو");
            cancel.setFont(new Font("IranNastaliq", Font.LAYOUT_RIGHT_TO_LEFT,26));
            cancel.setBackground(new Color(0,100,130));
            cancel.setForeground(Color.white);
            cancel.setBounds(5,268,245,50);

            panel.add(cancel);

            panel.setLayout(null);
            frame.add(panel);

        } catch (IOException e) {
            System.out.println("خطایی در برنامه رخ داده است");
        }
        frame.setSize(700, 500);
        frame.setLayout(null);
        frame.setVisible(true);
    }

}
