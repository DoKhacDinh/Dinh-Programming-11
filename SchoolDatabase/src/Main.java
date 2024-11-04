public class Main {
    public static void main(String[] args) {

        // Create a new school
        School school = new School("Colin", "3356 Hardy St", "HighSchool" );
        System.out.println(school); // Print out the school information

        // Add 3 teachers to the teacher's list
        school.addTeacher(new Teacher("Michael", "Jordan", "Math"));
        school.addTeacher(new Teacher("Lebron", "James", "History"));
        school.addTeacher(new Teacher("Cristiano", "Ronaldinho", "Physics"));
        System.out.println("List of Teachers: ");
        school.displayTeacher(); // Print out the list of teacher
        System.out.println("");

        // Add 10 students to the student's list
        school.addStudent(new Student("Leo", "Cocacola", "Six"));
        school.addStudent(new Student("Leo", "Pepsi", "Two"));
        school.addStudent(new Student("Leo", "Fanta", "Three"));
        school.addStudent(new Student("Leo", "Juice", "Five"));
        school.addStudent(new Student("Leo", "Superman", "Eleven"));
        school.addStudent(new Student("Leo", "Batman", "Ten"));
        school.addStudent(new Student("Leo", "Naruto", "Twelve"));
        school.addStudent(new Student("Leo", "Vancouver", "One"));
        school.addStudent(new Student("Leo", "Toronto", "Three"));
        school.addStudent(new Student("Leo", "America", "Seven"));
        System.out.println("List of Students: ");
        school.displayStudent(); // Print out the list of student
        System.out.println("");
        System.out.println("");

        // Remove 1 teacher and 2 students from the school lists
        // The first teacher and the first two students in their respective lists are deleted
        school.deleteTeacher(school.getTeachers().get(0)); // Remove the first teacher
        /*
        school.deleteTeacher: Call the deleteTeacher method on the school object
        school.getTeachers() returns the list of teachers
//      .get(0) get the first teacher from that list
//      Pass the first teacher to the deleteTeacher method to remove them from the list
        */

        school.deleteStudent(school.getStudents().get(0)); // Remove the first student
        /*
        school.deleteStudent: Call the deleteTeacher method on the school object
        school.getStudent() returns the list of teachers
//      .get(0) get the first student from that list
//      Pass the first student to the deleteStudent method to remove them from the list
        */
        school.deleteStudent(school.getStudents().get(0)); // Remove the new first student (after the deleting the previous one)

        // Print out both lists after modifying
        System.out.println("List of Teachers after removing: ");
        school.displayTeacher();
        System.out.println("");
        System.out.println("List of Students after removing: ");
        school.displayStudent();















    }
}
