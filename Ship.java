public class Ship {
    private int filledPlace;
    private final int startingLength;
    private final String type;

    Ship(int fill, int length, String type) {
        this.filledPlace = fill;
        this.startingLength = length;
        this.type = type;
    }

    public int getCoordinates() {
        return this.filledPlace;
    }

    public void setCoordinates(int n) {
        this.filledPlace = n;
    }

    public int getLength() {
        return this.startingLength;
    }

    public String getChar() {
        return this.type;
    }
}
