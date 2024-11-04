import java.util.ArrayList; // Import ArrayList Class

// School class represents a school and contains important information and commands related to its teachers and students.
public class School {

    // Fields:
    private ArrayList<Teacher> teachers; // Declare an array to hold a list of teachers
    private ArrayList<Student> students; // Declare an array to hold a list of students
    private String schoolName; // Declare school name
    private String schoolAddress; // Declare school address
    private String schoolType; // Declare school type

    // Constructor:
    School(String schoolName, String schoolAddress, String schoolType){ // Get input for the school
        this.schoolName = schoolName; // Set the name of the school being created to the input name
        this.schoolAddress = schoolAddress; // Set the address of the school being created to the input address
        this.schoolType = schoolType; // Set school type of the school being created to the input school type
        this.teachers =  new ArrayList<>(); // Create a new array as a list of teachers
        this.students=  new ArrayList<>(); // Create a new array as a list of students
    }

    // Method to String to return the information of the created student:
    public String toString() {
        return this.schoolName + " " + this.schoolType + " " + this.schoolAddress;
    } // return the student's information following my format

    // Getters and Setters: get or set the value of a specific instance
    public ArrayList<Teacher> getTeachers() {return teachers;}
    public void setTeachers(ArrayList<Teacher> teachers) {this.teachers = teachers;}
    public ArrayList<Student> getStudents() {return students;}
    public void setStudents(ArrayList<Student> students) {this.students = students;}
    public String getSchoolName() {return schoolName;}
    public void setSchoolName(String schoolName) {this.schoolName = schoolName;}
    public String getSchoolAddress() {return schoolAddress;}
    public void setSchoolAddress(String schoolAddress) {this.schoolAddress = schoolAddress;}
    public String getSchoolType() {return schoolType;}
    public void setSchoolType(String schoolType) {this.schoolType = schoolType;}


    // Method to add a student to the students list
    // Input: Student student: an instance of the Student class containing student information
    public void addStudent(Student student){
        students.add(student); // Adds the student to the students list
    }

    // Method to add a teacher to the teacher list
    // Input: Teacher teacher: an instance of the Teacher class containing teacher information
    public void addTeacher(Teacher teacher){
        teachers.add(teacher); // Adds the teacher to the students list
    }

    // Method to remove a student from the students list
    // Input: Student student: an instance of the Student class containing student information that you want to remove
    public void deleteStudent(Student student){
        students.remove(student); // Removes the student to the students list
    }

    // Method to remove a teacher from the teachers list
    // Input: Teacher teacher: an instance of the Teacher class containing teacher information that you want to remove
    public void deleteTeacher(Teacher teacher){
        teachers.remove(teacher); // Removes the teacher to the students list
    }

    // Method to display the teachers list
    public void displayTeacher(){
        for (int i=0; i<teachers.size(); i++){ // Loop through each teacher in the teachers list by index
            System.out.println(teachers.get(i)); // Print out each teacher in the list
            // teachers.get(): to get the student from the index i in the teachers array
        }
    }

    // Method to display the students list
    public void displayStudent(){
        for (int i=0; i<students.size(); i++){ // Loop through each student in the teachers list by index
            System.out.println(students.get(i)); // Print out each teacher in the list
            // students.get(): to get the student from the index i in the students array
        }
    }
}
