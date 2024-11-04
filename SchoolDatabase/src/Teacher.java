
/*
 Teacher class represents a teacher and information about the teacher's last and first name, and their subject
 as well as commands related to them
 */

public class Teacher {
    // Field:
   private String firstName; // Declare teacher's first name
   private String lastname; // Declare teacher's last name
   private String subject; // Declare teacher's subject


    // Constructor:
    Teacher(String firstName, String lastname, String subject){ // Get input to create a teacher
        this.firstName = firstName; // Set first name of the teacher being created to the input first name
        this.lastname = lastname; // Set last name of the teacher being created to the input last name
        this.subject = subject; // Set subject of the teacher being created to the input subject
    }

    // Getters and Setters: get or set the value of a specific instance
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }

    // Method to String to return the information of the created teacher:
    @Override
    public String toString(){
        return "Name:" + firstName + " " + lastname + " Subject:" + subject; // return the teacher's information following the required format
    }

    // Testing:
    public static void main(String[] args) {
        Teacher teacher1 = new Teacher("Michael", "Do", "Math");
        Teacher teacher2 = new Teacher("Do", "Michael", "History");

        System.out.println(teacher1);
        System.out.println(teacher2);
    }

}
