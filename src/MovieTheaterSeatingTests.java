
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MovieTheaterSeatingTests {
    private static final int rows = 10;
    private static final int columns = 20;
    private MovieTheaterSeating seats;

    @Before
    public void createSeats() {
        this.seats = new MovieTheaterSeating();
    }

    @Test
    public void SampleSeatsTakenTest() {
        // F1 F2 taken, followed by 3 empty seats, F3 F4 F5
        Assert.assertEquals(seats.getBestSeats(2), "F1,F2");
        // F6 F7 F8 F9 taken, followed by 3 empty seats, F10 F11 F12
        Assert.assertEquals(seats.getBestSeats(4), "F6,F7,F8,F9");
        // F13, F14, F15, F16 taken, followed by 3 empty seats, F17 F18 F19
        Assert.assertEquals(seats.getBestSeats(4), "F13,F14,F15,F16");
        // D1 D2 D3  taken, followed by 3 empty seats, D4 D5 D6
        Assert.assertEquals(seats.getBestSeats(3), "D1,D2,D3");
        Assert.assertEquals(seats.seatsTakenToString(),
                "F1 F2 F3 F4 F5 F6 F7 F8 F9 F10 F11 F12 F13 F14 F15 F16 F17 F18 F19 D1 D2 D3 D4 D5 D6");
    }

    @Test
    public void TestOneLargeSize() {
        Assert.assertEquals(seats.getBestSeats(100), "");
    }

    @Test (expected = IllegalArgumentException.class)
    public void NegativeSize() {
        seats.getBestSeats(-1);
    }

    @Test
    public void SkipOneRowComeBack() {
        Assert.assertEquals(seats.getBestSeats(1), "F1");
        seats.getBestSeats(20);
        Assert.assertEquals(seats.getBestSeats(1), "F5");
    }

    @Test
    public void completelyFillUpSeats() {
        for (int i = 0; i < rows/2; i++) {
            seats.getBestSeats(20);
        }
        Assert.assertEquals(seats.getBestSeats(1), "");
    }

    @Test
    public void addNoSpacing() {
        seats.getBestSeats(16);
        Assert.assertEquals(seats.getBestSeats(1), "F20");
        Assert.assertEquals(seats.getBestSeats(1), "D1");
    }
}
