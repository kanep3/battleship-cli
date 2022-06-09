import java.util.*;
import static java.lang.Integer.parseInt;


// Creates all needed variables and arrays for further usage

public class Main {
    static Random random = new Random();
    static Scanner scanner = new Scanner(System.in);
    static int score = 0;
    static int radars = 4;
    static boolean a = false;
    static boolean b = false;
    static boolean d = false;
    static boolean p = false;
    static boolean s = false;
    static ArrayList<String> aliveShips = new ArrayList<>();
    static ArrayList<String> deadShips = new ArrayList<>();
    static Ship aircraft = new Ship(5, 5, "a");
    static Ship battleship = new Ship(4, 4, "b");
    static Ship destroyer = new Ship(3, 3, "d");
    static Ship submarine = new Ship(3, 3, "s");
    static Ship patrol = new Ship(2, 2, "p");

    static String[][] board = new String[][] {
            { "-", "-", "-", "-", "-", "-", "-", "-", "-", "-" },
            { "-", "-", "-", "-", "-", "-", "-", "-", "-", "-" },
            { "-", "-", "-", "-", "-", "-", "-", "-", "-", "-" },
            { "-", "-", "-", "-", "-", "-", "-", "-", "-", "-" },
            { "-", "-", "-", "-", "-", "-", "-", "-", "-", "-" },
            { "-", "-", "-", "-", "-", "-", "-", "-", "-", "-" },
            { "-", "-", "-", "-", "-", "-", "-", "-", "-", "-" },
            { "-", "-", "-", "-", "-", "-", "-", "-", "-", "-" },
            { "-", "-", "-", "-", "-", "-", "-", "-", "-", "-" },
            { "-", "-", "-", "-", "-", "-", "-", "-", "-", "-\n" }
        };


    public static void main(String[] args) {

        setBoard();
    }


    // Spawns the ships and adds them to the array list

    public static void setBoard() {
        aliveShips.add("Aircraft Carrier");
        aliveShips.add("Battleship");
        aliveShips.add("Submarine");
        aliveShips.add("Destroyer");
        aliveShips.add("Patrol Boat");
        spawnShip(aircraft);
        spawnShip(battleship);
        spawnShip(submarine);
        spawnShip(destroyer);
        spawnShip(patrol);
        hitOrMiss();
    }


    // Allows the player to choose if he wants to use the radar or simply shoot

    private static void hitOrMiss() {
        printBoard();
        while (true) {
            System.out.println("\nInput the coordinates");
            String playerShot = scanner.nextLine();
            if (playerShot.equalsIgnoreCase("quit")) {
                System.out.println("Bye :(");
                System.exit(0);
            } else if (playerShot.equalsIgnoreCase("radar")) {
                if (radars < 1) {
                    System.out.println("You ran out of radars.");
                } else {
                    System.out.println("Where to place the radar?");
                    String radarPlace = scanner.nextLine();
                    String[] radarString = radarPlace.split(" ");
                    try {
                        int[] radar;
                        radar = new int[]{parseInt(radarString[0]), parseInt(radarString[1])};
                        radar[0] = radar[0] - 1;
                        radar[1] = radar[1] - 1;
                        if (radar[0] > 9 || radar[1] > 9 || radar[0] < 0 || radar[1] < 0) {
                            System.out.println("Radar has to be within the map");
                        } else {
                            radarLaunch(radar[0], radar[1]);
                        }
                    } catch (Exception f) {
                        System.out.println("\nUse valid input");
                    }
                }

                // Displays score and victory if it's achieved

            } else {
                String[] shotPlace = playerShot.split(" ");
                try {
                    shotPlace[1] = String.valueOf(Integer.parseInt(shotPlace[1]) - 1);
                    shotPlace[0] = String.valueOf(Integer.parseInt(shotPlace[0]) - 1);
                    outputShot(Integer.parseInt(shotPlace[1]), Integer.parseInt(shotPlace[0]));
                } catch (Exception e) {
                    printBoard();
                    System.out.println("\nUse valid input");
                }
                System.out.println("Your score is " + score);
            }
            if (aliveShips.isEmpty()) {
                System.out.println("Victory!");
                replay();
                break;
            }
        }
    }


    // Asks the player if he wants to play again

    private static void replay() {
        System.out.println("Want to play again? (Y/N)");
        while (true) {
            String contain = scanner.nextLine();
            String choice;
            switch ((choice = contain.toLowerCase()).hashCode()) {
                case 1 -> {
                    if (!choice.equals("n"))
                        break;
                    System.out.println("Thanks for playing!");
                    System.exit(0);
                }
                case 2 -> {
                    if (!choice.equals("y"))
                        break;
                    System.out.println("Here we go!");
                    restart();
                }
            }
            System.out.println("Use valid input");
        }

    }


    // Checks if ships are not overlapping

    public static void spawnShip(Ship ship) {
        boolean shipPosition = random.nextBoolean();
        int[] placeShip = new int[2];
        while (true) {
            placeShip[0] = random.nextInt(10);
            placeShip[1] = random.nextInt(10);
            if (shipPosition) {
                if (9 - placeShip[0] >= ship.getLength()) {
                    boolean occupied = false;
                    int i;
                    for (i = placeShip[0] + 1; i <= placeShip[0] + ship.getLength(); i++) {
                        if (board[i][placeShip[1]].equals("a") ||
                            board[i][placeShip[1]].equals("b") ||
                            board[i][placeShip[1]].equals("d") ||
                            board[i][placeShip[1]].equals("p") ||
                            board[i][placeShip[1]].equals("s")) {
                            occupied = true;
                            break;
                        }
                    }
                    if (!occupied) {
                        for (i = placeShip[0] + 1; i <= placeShip[0] + ship.getLength(); i++)
                            board[i][placeShip[1]] = ship.getChar();
                        break;
                    }
                }
                continue;

            }

            // Checks if it doesnt go out of bounds

            if (9 - placeShip[1] >= ship.getLength()) {
                boolean occupied = false;
                int i;
                for (i = placeShip[1] + 1; i <= placeShip[1] + ship.getLength(); i++) {
                    if (board[placeShip[0]][i].equals("a") ||
                        board[placeShip[0]][i].equals("b") ||
                        board[placeShip[0]][i].equals("d") ||
                        board[placeShip[0]][i].equals("p") ||
                        board[placeShip[0]][i].equals("s")) {
                        occupied = true;
                        break;
                    }
                }
                if (!occupied) {
                    for (i = placeShip[1] + 1; i <= placeShip[1] + ship.getLength(); i++)
                        board[placeShip[0]][i] = ship.getChar();
                    break;
                }
            }
        }
    }


    // Talks to the player about his last shot

    public static void outputShot(int x, int y) {
        if (board[x][y].equals("M") || board[x][y].equals("H")) {
            printBoard();
            if (board[x][y].equals("M")) {
                System.out.println("\nYou already tried that spot!");
            }
        } else if (shipSink(x, y)) {
            board[x][y] = "H";
            printBoard();
            System.out.println("\nMy ship was hit!");
            score++;
        } else {
            board[x][y] = "M";
            printBoard();
            System.out.println("\nYou missed!");
            score--;
        }
        shipDestroyed();
        System.out.println("Alive ships: " + aliveShips);
        if (!deadShips.isEmpty())
            System.out.println("Dead ships: " + deadShips);
    }


    // Checks either the ship has been sunk

    public static boolean shipSink(int x, int y) {
        if (board[x][y].equals("a")) {
            aircraft.setCoordinates(aircraft.getCoordinates() - 1);
            return true;
        }
        if (board[x][y].equals("b")) {
            battleship.setCoordinates(battleship.getCoordinates() - 1);
            return true;
        }
        if (board[x][y].equals("d")) {
            destroyer.setCoordinates(destroyer.getCoordinates() - 1);
            return true;
        }
        if (board[x][y].equals("p")) {
            patrol.setCoordinates(patrol.getCoordinates() - 1);
            return true;
        }
        if (board[x][y].equals("s")) {
            submarine.setCoordinates(submarine.getCoordinates() - 1);
            return true;
        }
        return false;
    }


    // Checks if the ship has been destroyed

    public static void shipDestroyed() {
        if (aircraft.getCoordinates() == 0 && !a) {
            a = shipDestroyed(aircraft, "Aircraft Carrier");
        } else if (battleship.getCoordinates() == 0 && !b) {
            b = shipDestroyed(battleship, "Battleship");
        } else if (destroyer.getCoordinates() == 0 && !d) {
            d = shipDestroyed(destroyer, "Destroyer");
        } else if (patrol.getCoordinates() == 0 && !p) {
            p = shipDestroyed(patrol, "Patrol Boat");
        } else if (submarine.getCoordinates() == 0 && !s) {
            s = shipDestroyed(submarine, "Submarine");
        }
    }


    // Updates the ship Array List and score

    private static boolean shipDestroyed(Ship ship, String type) {
        if (ship.getCoordinates() == 0) {
            aliveShips.remove(type);
            deadShips.add(type);
            score += ship.getLength() * 2;
            System.out.println("You sank the " + type + ".\n");
            return true;
        }
        return false;
    }


    // Shows the area of radar targeted area and asks to confirm

    public static void radarLaunch(int x, int y) {
        String[][] radarCheck = new String[10][10];
        int i;
        for (i = 0; i < board.length; i++) {
            System.arraycopy(board[i], 0, radarCheck[i], 0, (board[i]).length);
        }
        for (i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                try {
                    radarCheck[i][j] = "x";
                } catch (Exception ignored) {}
            }
        }
        printBoard();
        System.out.println("Do you want to use radar? (Y/N)");


        // Performs check for ships around the chosen coordinate and outputs result

        if (scanner.nextLine().equalsIgnoreCase("y")) {
            radars--;
            boolean detected = false;
            for (int j = x - 1; j <= x + 1; j++) {
                for (int k = y - 1; k <= y + 1; k++) {
                    if (board[j][k].equals("a") ||
                        board[j][k].equals("b") ||
                        board[j][k].equals("d") ||
                        board[j][k].equals("p") ||
                        board[j][k].equals("s")) {
                        detected = true;
                        break;
                    }
                }
            }
            if (detected) {
                System.out.println("There's a ship in radars proximity!");
            } else {
                System.out.println("Nothing was detected");
            }
            System.out.println("You have " + radars + " radar(s) left");
        } else {
            System.out.println("Radar has been cancelled");
        }
    }


    // Resets the variables to the start of the game

    public static void restart() {
        score = 0;
        radars = 4;
        a = false;
        b = false;
        s = false;
        d = false;
        p = false;
        deadShips.clear();
        aliveShips.clear();
        aircraft.setCoordinates(5);
        battleship.setCoordinates(4);
        submarine.setCoordinates(3);
        destroyer.setCoordinates(3);
        patrol.setCoordinates(2);
        int i;
        for (i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++)
                board[i][j] = "-";
        }
        System.out.println("\n Starting a new game\n");
        setBoard();
    }


    // Prints each row and column of the board and hides the ships

    public static void printBoard() {
        for (String[] strings : board) {
            System.out.println();
            for (int col = 0; col < board.length; col++) {
                String pp = strings[col];

                if (pp.equals("a") ||
                   pp.equals("b") ||
                   pp.equals("d") ||
                   pp.equals("p") ||
                   pp.equals("s"))
                    System.out.print("-" + "  ");
                else {
                    System.out.print(pp + "  ");
                }
            }
        }
    }
}