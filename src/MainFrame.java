import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainFrame {
    JFrame MainFrame=new JFrame("صفحه اصلی");
    JLabel Background = null;
    JButton createNewSeminar;
    JButton viewStudentsOfEachSeminar,search;
    JButton exit;
    JMenuBar menuBar;
    JMenu file,options;
    JMenuItem newSeminar,listStudent,searchStudent,close;
    Font font;
    //***************************************************************************************************************
    //constructor
    public MainFrame() {
        font=new Font("IranNastaliq", Font.CENTER_BASELINE, 36);//change font
        try {
            //using image as a background
            Background=new JLabel(new ImageIcon(ImageIO.read(new File("img\\main.jpg"))));
            MainFrame.setContentPane(Background);
            Background.setLayout(null);
            Image icon = Toolkit.getDefaultToolkit().getImage("img\\icon.png");
            MainFrame.setIconImage(icon);

            menuBar = new JMenuBar();
            file = new JMenu("فایل");
            options=new JMenu("امکانات");
            newSeminar=new JMenuItem("ایجاد سمینار جدید");
            listStudent=new JMenuItem("لیست اطلاعات دانشجویان هر جلسه");
            searchStudent=new JMenuItem("جستجوی  اطلاعات  یک دانشجو");
            close=new JMenuItem("خروج");

            file.add(newSeminar);
            file.addSeparator();
            file.add(close);

            options.add(listStudent);
            options.addSeparator();
            options.add(searchStudent);
            menuBar.add(file);
            menuBar.add(options);
            MainFrame.setJMenuBar(menuBar);

            createNewSeminar=new RoundButton("آغاز یک سمینار جدید");//roundButton is a class that make a round button
            createNewSeminar.setFont(font);
            createNewSeminar.setBackground(new Color(0,100,140));
            createNewSeminar.setForeground(Color.white);
            createNewSeminar.setBounds(1060, 280, 250, 250);
            createNewSeminar.setToolTipText("برای ایجاد سمینار جدید روی دکمه کلیک کنید");
            Background.add(createNewSeminar);

            viewStudentsOfEachSeminar=new RoundButton("مشاهده دانشجویان در هر سمینار");
            viewStudentsOfEachSeminar.setFont(font);
            viewStudentsOfEachSeminar.setBackground(new Color(0,100,140));
            viewStudentsOfEachSeminar.setForeground(Color.white);
            viewStudentsOfEachSeminar.setBounds(780, 280, 250, 250);
            viewStudentsOfEachSeminar.setToolTipText("برای مشاهده اطلاعات هر جلسه ازقبیل موضوع،ارائه دهنده سمینار ولیست تمام دانشجویان حاضر و... روی این دکمه کلیک کنید");
            Background.add(viewStudentsOfEachSeminar);

            search=new RoundButton("جستجوی اطلاعات دانشجو");
            search.setFont(font);
            search.setBackground(new Color(0,100,140));
            search.setForeground(Color.white);
            search.setBounds(500, 280, 250, 250);
            search.setToolTipText("برای مشاهده اطلاعات یک دانشجو روی دکمه کلیک کنید");
            Background.add(search);

            exit=new RoundButton("خروج از برنامه");
            exit.setFont(font);
            exit.setBackground(new Color(0,100,140));
            exit.setForeground(Color.white);
            exit.setBounds(220, 280, 250, 250);
            exit.setToolTipText("برای خروج از برنامه روی این دکمه کلیک نمایید");
            Background.add(exit);

        } catch (IOException e) {
            e.printStackTrace();
        }
        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainFrame.setSize(600, 600);
        MainFrame.pack();
        MainFrame.setVisible(true);
    }
}


