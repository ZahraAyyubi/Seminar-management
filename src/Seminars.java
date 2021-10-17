import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

//Manage all seminars and add actions to component is done in this class
public class Seminars {
    ArrayList<Seminar> seminars;
    FileIO fileIO;//for reading and writing in formation in seminars into files
    MainFrame mainframe;
    final int MAX_CAPACITY=100;//max capacity that a seminar can have

    //*********************************************************************************************************************
    //Constructor
    public Seminars() {
        seminars=new ArrayList<Seminar>();
        fileIO=new FileIO();
        fileIO.loadData(seminars);

        mainframe=new MainFrame();
        //action listener of createNewSeminar button in main frame
        mainframe.createNewSeminar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                createNewSeminar();

            }
        });
        //action listener of viewStudentsOfEachSeminar button in main frame
        mainframe.viewStudentsOfEachSeminar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                listStudentsOfEachSeminar();
            }
        });
        //action listener of search button in main frame
        mainframe.search.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                searchInformationOfAStudentInAllSeminars();
            }
        });
        //action listener of exit button in main page
        mainframe.exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {//showing a dialog
                int a = JOptionPane.showConfirmDialog(mainframe.MainFrame,"قصد خارج شدن دارید؟");
                if (a == JOptionPane.YES_OPTION) {
                    mainframe.MainFrame.setDefaultCloseOperation(mainframe.MainFrame.EXIT_ON_CLOSE);
                    fileIO.saveData(seminars);
                    System.exit(0);
                }

            }
        });
        //action of newSeminar that is a menuItem
        mainframe.newSeminar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                createNewSeminar();
            }
        });
        //action of close that is a menuItem
        mainframe.close.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int a = JOptionPane.showConfirmDialog(mainframe.MainFrame,"قصد خارج شدن دارید؟");
                if (a == JOptionPane.YES_OPTION) {
                    mainframe.MainFrame.setDefaultCloseOperation(mainframe.MainFrame.EXIT_ON_CLOSE);
                    fileIO.saveData(seminars);
                    System.exit(0);
                }

            }
        });
        //action of listStudent that is a menuItem
        mainframe.listStudent.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                listStudentsOfEachSeminar();
            }
        });
        //action of searchStudent that is a menuItem
        mainframe.searchStudent.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                searchInformationOfAStudentInAllSeminars();

            }
        });

    }
    //in this method check if user entered true information add a new seminar to seminar then open a frame that user can add students in it
    public void createNewSeminar() {
        FrameForNewSeminar frame=new FrameForNewSeminar();
        Calendar time=Calendar.getInstance();//using year and month of current time for end time of seminar

        //Action listener of save button in frame for add new seminar
        frame.save.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {

                //Checking that the user has entered the requested information and correctness of hour an minute of end time
                if(frame.subject.getText().isEmpty() || frame.providerName.getText().isEmpty()|| frame.hourOfEndTime.getText().isEmpty() || frame.minuteOfEndTime.getText().isEmpty() || frame.capacity.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frame.frame,"!لطفا تمام فیلد ها را پر کنید","Alert",JOptionPane.WARNING_MESSAGE);
                }else if(Integer.parseInt(frame.hourOfEndTime.getText())>23 || Integer.parseInt(frame.hourOfEndTime.getText())<0 || Integer.parseInt(frame.minuteOfEndTime.getText())>59 ||Integer.parseInt(frame.minuteOfEndTime.getText())<0){

                    JOptionPane.showMessageDialog(frame.frame,"!لطفا مقادیری که برای ساعت و دقیقه وارد میکنید درست باشند","Alert",JOptionPane.WARNING_MESSAGE);
                }else{
                    //checking  capacity  entered for not being  more than max capacity of salon
                    if(Integer.parseInt(frame.capacity.getText())>MAX_CAPACITY)
                        JOptionPane.showMessageDialog(frame.frame,"!ظرفیت بیش از ظرفیت سالن می باشد","Alert",JOptionPane.WARNING_MESSAGE);
                    else {
                        seminars.add(new Seminar(frame.subject.getText(), frame.providerName.getText(),new GregorianCalendar(time.get(Calendar.YEAR),time.get(Calendar.MONTH),time.get(Calendar.DATE),Integer.parseInt(frame.hourOfEndTime.getText()),Integer.parseInt(frame.minuteOfEndTime.getText())),Integer.parseInt(frame.capacity.getText())));
                        JOptionPane.showMessageDialog(frame.frame," سمینار "+" "+seminars.get(seminars.size()-1).subject+" ایجاد شد.");
                        frame.frame.setVisible(false);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e1) {
                            System.out.println("خطایی در برنامه رخ داده است");
                        }
                        addStudents();
                    }
                }
            }

        });
        //action listener of cancel button in frame for create new seminar
        frame.cancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                frame.frame.setVisible(false);
                frame.timerThread.stop();
            }
        });


    }

    //in this method check if user entered true information add a new student to seminar students
    public void addStudents() {
        FrameForAddStudents frame=new FrameForAddStudents();
        //display information of current seminar
        frame.subjectOfSeminar.setText("موضوع:"+seminars.get(seminars.size()-1).subject);
        frame.providerOfSeminar.setText("ارائه دهنده:"+seminars.get(seminars.size()-1).providerName);
        frame.endTime.setText("زمان اتمام:"+new SimpleDateFormat("HH:mm").format(seminars.get(seminars.size()-1).endingTime.getTime()));
        frame.remainingCapacity.setText(" ظرفیت باقی مانده "+seminars.get(seminars.size()-1).remainingCapacity);
        frame.numOfStudentsInSeminar.setText("تعداد دانشجویان حاضر "+seminars.get(seminars.size()-1).students.size());

        //action listener of add student button in frame for add new student to current seminar
        frame.addStudent.addActionListener(new ActionListener() {

            public boolean checkExistence() {//checking that student with this information have not entered the current seminar before
                for(Student st:seminars.get(seminars.size()-1).students) {
                    if(st.firstName.equals(frame.firstName.getText()) && st.lastName.equals(frame.lastName.getText()) && st.studentID.equals(frame.studentID.getText())) {
                        JOptionPane.showMessageDialog(frame.frame,"دانشجو با مشخصات وارد شد ه پیش تر وارد سمینار شده است","Alert",JOptionPane.WARNING_MESSAGE);
                        frame.firstName.setText("");
                        frame.lastName.setText("");
                        frame.studentID.setText("");
                        frame.major.setText("");
                        frame.university.setText("");
                        return true;
                    }
                }
                return false;

            }
            @Override
            public void actionPerformed(ActionEvent e) {


                if(seminars.get(seminars.size()-1).remainingCapacity>0) {//Check that the capacity has not already been completed

                    //Checking that the requested information is entered
                    if(frame.firstName.getText().isEmpty() || frame.lastName.getText().isEmpty()|| frame.studentID.getText().isEmpty() || frame.major.getText().isEmpty() || frame.grades.getSelectedIndex()==-1 || frame.university.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(frame.frame,"!لطفا تمام فیلد ها را پر کنید","Alert",JOptionPane.WARNING_MESSAGE);
                    }else if(Calendar.getInstance().compareTo(seminars.get(seminars.size()-1).endingTime)>0)//Check that the seminar is not over
                        JOptionPane.showMessageDialog(frame.frame,"مدت زمان تعیین شده برای ورود به سمینار به اتمام رسیده است!","Alert",JOptionPane.WARNING_MESSAGE);
                    else if(!checkExistence()){
                        //create new student then add to students of current seminar
                        seminars.get(seminars.size()-1).students.add(new Student(frame.firstName.getText(),frame.lastName.getText(),frame.studentID.getText(),(String) frame.grades.getItemAt(frame.grades.getSelectedIndex()),frame.major.getText(), frame.university.getText()));
                        seminars.get(seminars.size()-1).remainingCapacity--;
                        //clear previous information in fields
                        frame.firstName.setText("");
                        frame.lastName.setText("");
                        frame.studentID.setText("");
                        frame.major.setText("");
                        frame.university.setText("");
                        //display remaining capacity and number of students in current seminar
                        frame.remainingCapacity.setText(" ظرفیت باقی مانده "+seminars.get(seminars.size()-1).remainingCapacity);
                        frame.numOfStudentsInSeminar.setText("تعداد دانشجویان حاضر "+seminars.get(seminars.size()-1).students.size());
                    }
                }
                else {
                    JOptionPane.showMessageDialog(frame.frame,"ظرفیت تکمیل است","Alert",JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        //action listener of listStudentsOfCurrentSeminar button in frame for add new student
        frame.listStudentsOfCurrentSeminar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                listStudentsOfCurrentSeminar();
            }
        });

        //
        frame.search.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                searchInformationOfAStudentInCurrentSeminar();
            }
        });
        frame.endingTheSeminar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                frame.frame.setVisible(false);
                fileIO.saveData(seminars);
                frame.timerThread.stop();
            }
        });
    }

    public void listStudentsOfCurrentSeminar() {
        FrameforListStudentsOfCurrentSeminar frame= new FrameforListStudentsOfCurrentSeminar();
        frame.buildTable(seminars.get(seminars.size()-1));
    }

    //listing students of each seminar that we want
    public void listStudentsOfEachSeminar() {
        FrameForListStudents frame=new FrameForListStudents(seminars);//action listener is in FrameForListStudents class
    }

    //search information of a student with having her name and id in current seminar
    public void searchInformationOfAStudentInCurrentSeminar() {
        FrameForSearchAStudent frame=new FrameForSearchAStudent();

        //action listener of search button in frame for search student
        frame.search.addActionListener(new ActionListener() {
            int index=-1;//A variable to hold the intended student index in students of current seminar

            @Override
            public void actionPerformed(ActionEvent e) {
                //Checking that the requested information is entered
                if(frame.firstName.getText().isEmpty()|| frame.lastName.getText().isEmpty() || frame.studentID.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frame.frame,"!لطفا تمام فیلد ها را پر کنید","Alert",JOptionPane.WARNING_MESSAGE);
                }
                else {
                    for(int i=0;i<seminars.get(seminars.size()-1).students.size();i++) {
                        if(seminars.get(seminars.size()-1).students.get(i).firstName.equals(frame.firstName.getText()) && seminars.get(seminars.size()-1).students.get(i).lastName.equals(frame.lastName.getText()) && seminars.get(seminars.size()-1).students.get(i).studentID.equals(frame.studentID.getText())) {
                            index=i;
                            break;
                        }

                    }
                }


                if(index==-1)
                    JOptionPane.showMessageDialog(frame.frame,"! دانشجویی با این مشخصات در سمینار حضور ندارد !","Alert",JOptionPane.WARNING_MESSAGE);
                else
                    frame.displayInformation(index,seminars.get(seminars.size()-1));//this method is in the FrameForSearchAStudent class that display information of intended student
            }

        });

    }

    //search information of a student with having her name and id in all seminars and display her/him info and seminars that she/he have took place in them
    public void searchInformationOfAStudentInAllSeminars() {
        FrameForSearchAStudent frame=new FrameForSearchAStudent();
        ArrayList<Seminar> seminarsThatTookPlace=new ArrayList<Seminar>();//for holding seminars that intended student have took place in them

        //action listener of search button in frame for search student in all seminars
        frame.search.addActionListener(new ActionListener() {
            int index=-1;//A variable to hold the intended student index in students of current seminar

            @Override
            public void actionPerformed(ActionEvent arg0) {
                //Checking that the requested information is entered
                if(frame.firstName.getText().isEmpty()|| frame.lastName.getText().isEmpty() || frame.studentID.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frame.frame,"!لطفا تمام فیلد ها را پر کنید","Alert",JOptionPane.WARNING_MESSAGE);
                }
                else {
                    for(Seminar seminar:seminars) {//search intended student in all seminars
                        for(int i=0;i<seminar.students.size();i++) {
                            if(seminar.students.get(i).firstName.equals(frame.firstName.getText()) && seminar.students.get(i).lastName.equals(frame.lastName.getText()) && seminar.students.get(i).studentID.equals(frame.studentID.getText())) {
                                index=i;
                                seminarsThatTookPlace.add(seminar);
                                break;
                            }

                        }
                    }


                    if(index==-1)
                        JOptionPane.showMessageDialog(frame.frame,"! دانشجویی با این مشخصات در هیچ سمیناری شرکت نکرده است !","Alert",JOptionPane.WARNING_MESSAGE);
                    else
                        frame.displayInformation(index,seminarsThatTookPlace);//this method is in the FrameForSearchAStudent class that display information of intended student and  seminars that she/he have took place in them
                }
            }
        });
    }
}

