import java.util.ArrayList;
import java.util.Calendar;

public class Seminar  {
    String subject;
    String providerName;
    Calendar startTime,endingTime;
    int capacity,remainingCapacity;
    ArrayList<Student> students=new ArrayList<Student>();

    //constructor for the time we create new seminar when reading data from a file
    public Seminar(String subject,String providerName,Calendar startTime,Calendar endingTime,int capacity,ArrayList<Student> students) {
        this.subject=subject;
        this.providerName=providerName;
        this.startTime=startTime;
        this.endingTime=endingTime;
        this.capacity=capacity;
        this.students=students;
    }
    //constructor for the time we create new seminar with information that user entered
    public Seminar(String subject,String providerName,Calendar endingTime,int capacity) {
        this.subject=subject;
        this.providerName=providerName;
        this.startTime=Calendar.getInstance();
        this.endingTime=endingTime;
        this.capacity=capacity;
        this.remainingCapacity=capacity;
    }
}
