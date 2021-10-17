import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class FileIO {

    //this method load data that are informations of last seminars
    public void loadData(ArrayList<Seminar>  seminars){
        Scanner sc = null;
        String subject,provider;
        Calendar startTime,endTime;
        int capacity,numOfSeminars = 0;//num of seminars is a variable for knowing number of seminar we had for reading from file
        ArrayList<Student> students=new ArrayList<Student>();

        try {
            sc=new Scanner(new FileReader("data\\numOfSeminars.txt"));
            numOfSeminars=sc.nextInt();
        } catch (FileNotFoundException e1) {
            System.out.println("خطایی در برنامه رخ داده است1");
        }

        for(int i=0;i<numOfSeminars;i++) {
            students=new ArrayList<Student>();
            try {
                sc=new Scanner(new FileReader("data\\"+(i+1)+".txt"));//(i+1) determine that seminar is (i+1)th seminar
                subject=sc.nextLine();
                provider=sc.nextLine();
                startTime=new GregorianCalendar(sc.nextInt(),sc.nextInt(),sc.nextInt(),sc.nextInt(),sc.nextInt());
                endTime= new GregorianCalendar(sc.nextInt(),sc.nextInt(),sc.nextInt(),sc.nextInt(),sc.nextInt());
                capacity=sc.nextInt();
                sc.nextLine();

                //filling the students of seminar that we are reading from file
                while(sc.hasNext()) {
                    students.add(new Student(sc.nextLine(), sc.nextLine(), sc.nextLine(), sc.nextLine(), sc.nextLine(), sc.nextLine(),new GregorianCalendar(sc.nextInt(),sc.nextInt(),sc.nextInt(),sc.nextInt(),sc.nextInt())));
                    sc.nextLine();
                }
                //adding seminar to array of seminars
                seminars.add(new Seminar(subject, provider, startTime, endTime, capacity,students));
            } catch (FileNotFoundException e) {
                System.out.println("خطایی در برنامه رخ داده است");
            }finally {
                sc.close();
            }

        }

    }
    //*************************************************************************************************************************

    //this method write the information of each seminar to a distinct file
    public void saveData(ArrayList<Seminar>  seminars) {
        PrintWriter out=null;
        try {
            //for save the number of seminars that took place
            out=new PrintWriter(new FileWriter("data\\numOfSeminars.txt")) ;
            out.println(seminars.size());
            out.close();
        } catch (IOException e1) {
            System.out.println("خطایی در برنامه رخ داده است");
        }
        //saving and writing informations of each seminar in distinct file
        for(int i=0;i<seminars.size();i++) {
            try {
                out=new PrintWriter(new FileWriter("data\\"+(i+1)+".txt")) ;//(i+1)th seminar
                out.println(seminars.get(i).subject);
                out.println(seminars.get(i).providerName);
                out.println(seminars.get(i).startTime.get(Calendar.YEAR)+" "+seminars.get(i).startTime.get(Calendar.MONTH)+" "+seminars.get(i).startTime.get(Calendar.DATE)+" "+seminars.get(i).startTime.get(Calendar.HOUR)+" "+seminars.get(i).startTime.get(Calendar.MINUTE)+" "+seminars.get(i).endingTime.get(Calendar.YEAR)+" "+seminars.get(i).endingTime.get(Calendar.MONTH)+" "+seminars.get(i).endingTime.get(Calendar.DATE)+" "+seminars.get(i).endingTime.get(Calendar.HOUR)+" "+seminars.get(i).endingTime.get(Calendar.MINUTE)+" "+seminars.get(i).capacity);

                //writing informations of students of current seminar
                for(Student st:seminars.get(i).students) {
                    out.println(st.firstName+"\n"+st.lastName+"\n"+st.studentID+"\n"+st.grade+"\n"+st.major+"\n"+st.university);
                    out.println(st.timeOfEntry.get(Calendar.YEAR)+" "+st.timeOfEntry.get(Calendar.MONTH)+" "+st.timeOfEntry.get(Calendar.DATE)+" "+st.timeOfEntry.get(Calendar.HOUR)+" "+st.timeOfEntry.get(Calendar.MINUTE));
                }

            } catch (IOException e) {
                System.out.println("خطایی در برنامه رخ داده است");
            }finally {
                out.close();
            }


        }

    }
}
