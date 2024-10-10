import java.util.Scanner; // Import Scanner Class

public class Unit3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Create a scanner to gather users inputs

        int []arr = new int[]{1,2,3,4,5};
        // Instruction for the user
        System.out.println("            Welcome to Array modification program made by Do Khac Dinh");
        System.out.println("Your initial array is: [1,2,3,4,5]");
        System.out.println("Please enter 'add', 'delete' if you want to add or delete the last number of the array  ");
        System.out.println("             'change' to add a number to any position you want");
        System.out.println("User choice: ");


        while (true) { // while(true) will allow the loop run forever, until the user enters "q"
            String userChoice = scanner.nextLine(); // Gather user's input

            // Check if the users want to quit the program
            if (userChoice.equals("q")) {
                System.out.println("Thank you for using my program!");
                break; // This will end the loop if the user enters "q"
            }



            // Add last number to the existing array condition
            // If the user enters "add", this condition will be conducted
            if (userChoice.equals("add")) {
                System.out.print("Enter a number you want to add: ");
                while(true) { // while(true) will allow the loop run forever, until the user enter a valid input
                    String inputAddNumber = scanner.nextLine();  // Gather user's input
                    if (isNum(inputAddNumber)) { // Check if the user entered an integral. If not, it this condition will be fail and else will be conducted which will ask the user to re-enter their input.
                        // Integer.parseInt is used to transfer the user's input into an Integral. Because the initial array values are declared as an Integral but the user input is declared as a String
                        // Otherwise, the method "add" will not work
                        int x = Integer.parseInt(inputAddNumber);
                        // Method "add" is used
                        arr = add(arr,x); // Update the new values for array arr
                        arrayPrint(arr); // Print out the updated array
                        break;// This will end the loop after completing the adding task
                    }else { // If the user does not enter an integral number, this else will ask them to re-enter their input.
                        System.out.println("Invalid input, please enter a number!");

                    }
                }

            // Delete last number to the existing array condition
            // If the user enters "delete", this condition will be conducted
            } else if (userChoice.equals("delete")) {
                // Method "delete" is used
                 arr = delete(arr); // Update the new values for array arr
                arrayPrint(arr); // Print out the updated array

            // Condition for "change" method
            // If the user enters "add", this condition will be conducted
            } else if (userChoice.equals("change")) {
                System.out.print("Please enter a position (number only, from 1 to " + arr.length + ") you want to add: ");

                while (true) { // Set while(true) so the loop will run forever

                    /*
                    The logic here is if the user entered a non-integral number
                    The else(line85) will be repeatedly conducted until the user to enter an integral number
                    If the user entered an integral number, if condition (line 61) will be conducted
                    Method "isnNum" in if condition (line 61) is used to ensure the user's input is an integral number
                     */
                    String inputPosition = scanner.nextLine();  // Gather user's input

                    if (isNum(inputPosition)) {  // Ensuring user's input is an integral number

                        /*
                        Because inputPosition is declared as a String, but the if condition(line70), we compare the user's input with an integral
                        Therefore, we have to use "Integer.parseInt(inputPosition)" transform the input into an Integral. Otherwise, it will not work
                         */
                        int position = Integer.parseInt(inputPosition);

                        // Check position
                        /*
                        Because I set the position start from 1 to the end of the array. Therefore, if user enters an input that less than 1 or more than the size of the array,
                        if condition(line75) will be conducted and ask the user to re-enter their input.
                         */
                        if (position < 1 || position > arr.length) {
                                System.out.println("Invalid position, please enter a position from (1 to " + arr.length + ")");
                                continue; // Start again the loop (back to code line 60) and skip all the remaining steps if the user enters invalid input

                            } else { // After ensuring the user has entered a proper input, this "else" will ask them to enter a number they want to add
                                System.out.print("Please enter a number you want to add: ");

                                while(true) {  // This while loop has the function same as explained above
                                String inputNumber = scanner.nextLine();  // Gather user's input

                                    if (isNum(inputNumber)) { // Ensuring user's input is an integral
                                        int y = Integer.parseInt(inputNumber); // transform the user's input into an Integral

                                        // Method "change" is used to conduct
                                        // "position - 1" because the position in an array start from 0, but I ask the user to enter the position start from 1 (because most people think the first position is the position number 1).
                                        arr = change(arr, position - 1, y); // Update the new values for array arr

                                        arrayPrint(arr); // Print out the new array
                                        break; // Stop the loop (line82) after completing user's demand. Back to the loop (line52)
                                    } else {
                                        System.out.println("Invalid input, please enter a number!"); // Ask the user to re-enter their input if it is not an integral number
                                    }
                                }
                                break; // Stop the loop (line52) after completing the user's demand. Program will come back to code(line 16) to ask the user if they want to do so
                                }
                    } else { // If the user does not enter an integral number, this else will ask them to re-enter their input.
                        System.out.println("Invalid input, Please enter number from (1 to " + arr.length +"): ");
                    }
                }

            } else { // If the user does not follow the instruction, this else will ask them to re-enter their input.
                System.out.println("Invalid command, Please re-enter!");
            }
        }
    }





    // Method to add values to the end of an array.
    static int[] add(int[] arr, int x) {
        // Create a new array, length of the new array = length of initial array + 1
        // If you want to add, the size of the new array must be larger than 1 position than the initial array
        // Otherwise, you cannot add
        int[] brr = new int[arr.length + 1];
        // Copy the value from initial array to the new array
        for (int i = 0; i < arr.length; i++) {
            brr[i] = arr[i]; // Copy the values from initial array to the new array
        }
        // Add the value you want to add to the end of the array
        // Because when we copy the values from the initial array to the new array, the last position of the new array is empty
        // Therefore, we will take the last position be equal to x, which is the number that the user wants to add
        // Finally,the last position of the new array will be filled
        brr[brr.length - 1] = x;
        return brr; // Return the new array after all steps above, When we call this method, it will print the values inside the new array ( brr )
    }


    // Method to delete values from the end of an array
    static int[] delete(int[] arr) {
        //Create a new array, length of the new array =  length of initial array - 1
        // If you want to delete the last position, the size of the new array must be smaller than 1 position than the initial array ( following the requirement of the assessment)
        int[] brr = new int[arr.length - 1];

        //Copy the values from initial array to the new array except for the last position.
        // Because the new array has 1 position less than the initial array
        // arr.length - 1( the last position of the initial array)
        // When i < arr.length -1 , it means that it will copy every position from the initial array, except for the last one
        for (int i = 0; i < arr.length - 1 ; i++) {
            brr[i] = arr[i]; //Copy the values from initial array to the new array
        }
        return brr; // Return the new array after all steps above, When we call this method, it will print the values inside the new array ( brr )
    }


    // Insert values into an array at chosen index position
    static int[] change(int[] arr, int x, int y) {

        // Create a new array, length of the new array = length of initial array + 1
        // If you want to add, the size of the new array must be larger than 1 position than the initial array
        // Otherwise, you cannot add
        int[] brr = new int[arr.length + 1];

        // Copy the values from the initial array to the new array
        // Because to add a value to a specific position, we have to leave that position empty in the new array
        // We have x is the position that the user wants to add
        // Therefore, first we will copy the values before the position x from the initial array to the new array
        for (int i = 0; i < x; i++) {
            brr[i] = arr[i];
        }
        brr[x] = y; // Then, make the position x = y(the number that the user wants to add)

        for (int i = x; i < arr.length; i++) { // Finally, copy the rest of the value from the initial array to the new array
            // Because the position x = y already. As a result, we will copy the values start from the position after position x to the rest of the initial array.
            brr[i + 1] = arr[i];
        }
        return brr; // Return the new array after all steps above, When we call this method, it will print the values inside the new array ( brr )
    }


    // This method is used to print out the values inside the array
    static void arrayPrint(int[] arr) {
        System.out.print("         Your new Array is: [");
        for (int i = 0; i < arr.length; i++) { // Print out every value from the array
            System.out.print(arr[i]);
            if (i < arr.length - 1) { // To make it clear to read, every space between each value must have a comma, except for the last one. That's why we have arr.length - 1 to exclude the last value
                System.out.print(", ");
            }
        }
        System.out.println("]"); // Close bracket after printing all the values
        System.out.println("User Choice: "); // Ask the user to enter what they want to do again
    }


    // Method to check if a string is a numeric value
    /*
    This method takes a string as input and returns true if the string is a valid integer which can be positive or negative. It uses a regular expression to perform this check
    The regular expression "-?\\d+" works as follows:
     - "-?" allows for an optional negative sign at the beginning
     - "\\d+" requires at least one digit (0-9) to be present
     If the string matches this pattern, the method returns true
     Otherwise, it returns false
     */
    static boolean isNum(String str) {
        return str.matches("-?\\d+"); // Check if the string is a valid integer
    }
}







