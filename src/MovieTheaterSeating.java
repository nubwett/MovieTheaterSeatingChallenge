import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MovieTheaterSeating {
    private static final int rows = 10;
    private static final int columns = 20;
    private List<int[]> seating;
    private Character[] seatingArrangement;

    public MovieTheaterSeating() {
        List<int[]> seating = new ArrayList<>();
        Character[] seatingArrangement = new Character[]{'F', 'D', 'H', 'B', 'J'};
        for (int i = 0; i < seatingArrangement.length; i++) {
            seating.add(new int[columns]);
        }
        this.seating = seating;
        this.seatingArrangement = seatingArrangement;
    }

    /**
     *
     * @param numberOfSeats The number of seats that must be filled
     * @return the seats that have been filled in CSV format, or the empty
     *          string (""), if there no seats were able to be filled.
     */
    public String getBestSeats(int numberOfSeats) {
        String seats = "";

        if (numberOfSeats <= 0) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < rows/2; i++) {
            for (int j = 0; j < columns; j++) {
                if (j + numberOfSeats > columns) {
                    // if the number of seats needed to be placed won't fit the column
                    break;
                } else if (this.seating.get(i)[j] == 0) {
                    return parseToCSV(updateSeat(numberOfSeats, i, j));
                }
                // goes to the next seat if the seat is filled
            }
        }
        return seats;
    }

    private String updateSeat(int numberOfSeats, int row, int col) {
        String seats = "";
        int filledSeats = 0;
        int spacing = 1;
        // if the seat position isn't filled and there is space
        while (filledSeats != numberOfSeats) {
            this.seating.get(row)[col + filledSeats] = 1;
            // character then number.
            seats += this.seatingArrangement[row] + "" + (col + filledSeats + 1) + " ";
            filledSeats++;
        }
        // create a spacing of at least 3
        while (spacing <= 3 && col + numberOfSeats + spacing < columns) {
            this.seating.get(row)[col + numberOfSeats + spacing - 1] = 1;
            spacing++;
        }
        return seats;
    }

    /**
     * Converts the seats filled to be separated from spaces to commas
     * Example: F1 F2 converts to F1,F2
     * @param seatsWithSpaces a String representing the seats that have been filled,
     *                        separated by spaces
     * @return A new string that represents the seats that have been filled,
     *          separated by commas.
     */
    private String parseToCSV(String seatsWithSpaces) {
        return seatsWithSpaces.trim().replace(" ", ",");
    }

    public String seatsTakenToString() {
        String s = "";
        for (int i = 0; i < rows/2; i++) {
            for (int j = 0; j < columns; j++) {
                if (this.seating.get(i)[j] == 1) {
                    s += this.seatingArrangement[i] + "" + (j + 1) + " ";
                }
            }
        }
        return s.trim();
    }
}
