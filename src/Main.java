import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
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
            String[] pieces = line.split(" ");
            if (!correctFormat(pieces)) {
                System.out.println("Error with at least one of the seats.");
            } else {
                System.out.print(pieces[0] + " ");
                String seatingAssignment = "";
                try {
                    seatingAssignment = seating.getBestSeats(Integer.parseInt(pieces[1]));
                } catch (IllegalArgumentException e) {
                    System.out.println("Cannot select a 0 or negative seat value.");
                    continue;
                }

                if (seatingAssignment.equals("")) {
                    System.out.println("No spots available with that number of seats.");
                }
                System.out.println(seatingAssignment);
            }
        }
        input.close();
        scanFile.close();
    }

    private static boolean correctFormat(String[] pieces) {
        if (pieces.length != 2) {
            return false;
        }

        if (pieces[0].length() != 4) {
            return false;
        }

        if (pieces[0].charAt(0) != 'R') {
            return false;
        }

        try {
            Integer.parseInt(pieces[0].substring(1));
        } catch (NumberFormatException e) {
            return false;
        }

        try {
            Integer.parseInt(pieces[1]);
        } catch(NumberFormatException e) {
            return false;
        }


        return true;
    }

}
