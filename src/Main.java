import model.Board;
import model.Space;
import protocol.SpaceProtocol;
import utils.BoardTemplate;
import utils.NumsFixedsAndExpecteds;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private final static Scanner scan = new Scanner(System.in);

    private static Board board;

    private static final String[] positionToSplit = new NumsFixedsAndExpecteds().getFixedDefaults().split(" ");

    private static BoardTemplate template;

    private final static int boardLimit = 9;

    public static void main(String[] args) {
        var positions = Stream.of(positionToSplit).collect(Collectors.toMap(
                k -> k.split(";")[0],
                v -> v.split(";")[1]
        ));

        var option = -1;

        while(true) {
            System.out.println("Select an option: ");
            System.out.println("1 - Init a new Game");
            System.out.println("2 - Insert a number");
            System.out.println("3 - Remove a number");
            System.out.println("4 - Display board");
            System.out.println("5 - Verify game status");
            System.out.println("6 - Reset game");
            System.out.println("7 - Finish game");
            System.out.println("8 - Exit");
            System.out.println("================================================================");

            option = scan.nextInt();

           switch (option) {
               case 1 -> gameInit(positions);
               case 2 -> addNumber();
               case 3 -> removeNumber();
               case 4 -> displayBoard();
               case 5 -> verifyStatus();
               case 6 -> resetGame();
               case 7 -> finishGame();
               case 8 -> System.exit(0);
           }


        }

    }

    private static void gameInit(Map<String, String> positions) {
        if(board != null){
            System.out.println("Game already started!");
        }

        List<List<SpaceProtocol>> spaces = new ArrayList<>();
        for(int row = 0; row < boardLimit; row++){
            //add 9 list to List of List
            spaces.add(new ArrayList<>());
            for (int col = 0; col < boardLimit; col++){
                //format row/col this model, and get value by the key in map
                var positionParam = positions.get("%s,%s".formatted(row,col));
                //returned value of key map, so parse values to int and boolean
                var expected = Integer.parseInt(positionParam.split(",")[0]);
                var fixed = Boolean.parseBoolean(positionParam.split(",")[1]);
                spaces.get(row).add(new Space(expected,fixed));
            }
        }
        board = new Board(spaces);
        template = new BoardTemplate(board);
        System.out.println("Game started!");
    }

    private static void addNumber() {

    }
    private static void finishGame() {

    }
    private static void resetGame() {

    }
    private static void verifyStatus() {
    }

    private static void displayBoard() {
        if(board == null){
            System.out.println("Please init the game");
        }
        template.printBoard();
    }

    private static void removeNumber() {
    }
}
