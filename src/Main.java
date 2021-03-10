import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.print("Input File: ");
        Scanner input = new Scanner(System.in);
        String filename = input.next();
        Scanner scanFile = null;
        try {
            scanFile = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            System.out.println(input + " does not exist.");
        }

        MovieTheaterSeating seating = new MovieTheaterSeating();
        assert scanFile != null;
        while (scanFile.hasNextLine()) {
            String line = scanFile.nextLine().trim();
            String[] lines = line.split(" ");
            if (lines.length != 2) {
                throw new IllegalArgumentException();
            }
            String seatingAssignment = seating.getBestSeats(Integer.parseInt(lines[1]));
            System.out.print(lines[0] + " ");
            if (seatingAssignment.equals("")) {
                System.out.println("No spots available with that number of seats.");
            }
            System.out.println(seatingAssignment);
        }
        input.close();
        scanFile.close();
    }

}
