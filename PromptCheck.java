package mini_proj;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class PromptCheck {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public String getStringInput(Scanner scanner, String prompt) {
        String input;
        while(true){
            System.out.println(prompt);
            input=scanner.nextLine();
            if(input.isEmpty()){
                System.out.println("input cannot be empty");
            }else{
                return input;
            }
        }
    }

    public boolean getTrueFalse(Scanner scanner,String prompt){
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim(); // Trim to handle leading/trailing spaces

            if (input.isEmpty()) {
                System.out.println("Input cannot be empty");
            } else if (input.equalsIgnoreCase("y")) {
                return true; // Return true immediately if input is "y"
            } else if (input.equalsIgnoreCase("n")) {
                return false; // Return false immediately if input is "n"
            } else {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
            }
        }
    }

    public LocalDateTime getDateTimeInput(Scanner scanner, String prompt) {
        LocalDateTime dateTime = null;
        while (dateTime == null) {
            System.out.println(prompt);
            String dateTimeInput = scanner.nextLine();
            try {
                dateTime = LocalDateTime.parse(dateTimeInput, formatter); //convert string to datetime format
            } catch (DateTimeException e) {
                System.out.println("invalid date/time format");
            }
        }
        return dateTime;
    }

    public boolean setComplete(Scanner scanner, String prompt) {
        String input = "";
        while (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n")) {
            System.out.println(prompt);
            input = scanner.nextLine();
        }
        if (input.equalsIgnoreCase("y")) {
            return true;
        } else {
            return false;
        }
    }


}
