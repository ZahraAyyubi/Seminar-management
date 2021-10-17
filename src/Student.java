import java.util.Calendar;

public class Student {
	String firstName;
	String lastName;
	String studentID;
	String grade;
	String major;
	String university;
	Calendar timeOfEntry;
	
	//constructor for the time we create new seminar when reading data from a file
	public Student(String firstName,String lastName,String studentID,String grade,String major,String university,Calendar timeOfEntry) {
		this.firstName=firstName;
		this.lastName=lastName;
		this.studentID=studentID;
		this.grade=grade;
		this.major=major;
		this.university=university;
		this.timeOfEntry=timeOfEntry;
	}
	//constructor for the time we create new seminar with information that user entered
	public Student(String firstName,String lastName,String studentID,String grade,String major,String university) {
		this.firstName=firstName;
		this.lastName=lastName;
		this.studentID=studentID;
		this.grade=grade;
		this.major=major;
		this.university=university;
		this.timeOfEntry=Calendar.getInstance();
	}
}
