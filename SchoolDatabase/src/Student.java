/*
 Student class represent a student and information about the student's last and first name, and their grade
 as well as commands related to them
 */

public class Student {

    // Fields:
    private String firstName; // Declare student's first name
    private String lastName; // Declare student's last name
    private String grade; // Declare student's grade
    private int studentNum; // Declare student's unique id

    // Declare a count to set the unique id for each student
    // Using "static" to ensure every time we create a new car, this static variable will be incremented so each student will get a unique student's number.
    private static int counter = 1; // Student number counter start at 1

    // Getters and Setters: get or set the value of a specific instance
    public String getFirstname() {return firstName;}
    public void setFirstname(String firstname) {this.firstName = firstName;}
    public String getLastname() {return lastName;}
    public void setLastname(String lastname) {this.lastName = lastname;}
    public String getGrade() {return grade;}
    public void setGrade(String grade) {this.grade = grade;}
    public static int getCounter() {return counter;}
    public static void setCounter(int counter) {Student.counter = counter;}

    // Constructor:
    Student(String firstName, String lastName, String grade) { // Get input to create a student
        this.firstName = firstName; // Set first name of the student being created to the input first name
        this.lastName = lastName; // Set last name of the student being created to the input last name
        this.grade = grade; // Set the grade of the student being created to the input grade
        this.studentNum = counter; // Set the student's number of the student being created to the counter
        counter++; // ++ to increase the student number by 1 everytime we create a new student, ensuring each student has its unique number
    }

    // Method to String to return the information of the created student:
    @Override
    public String toString() {
        return "Name:" + this.firstName + " " + this.lastName + " Grade:" + this.grade + " " + "Student Number:" + this.studentNum;
    } // return the student's information following the required format

    // Testing:
    public static void main(String[] args) {
        Student student1 = new Student("Michael", "Do", "Six");
        Student student2 = new Student("Do", "Michael", "Ten");
        Student student3 = new Student("Dinh", "Do", "Twelve");

        System.out.println(student1);
        System.out.println(student2);
        System.out.println(student3);
    }
}
