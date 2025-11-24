package utils;

import model.Board;
import protocol.SpaceProtocol;

import java.util.List;

public class BoardTemplate {

    private static Board board;

    public BoardTemplate(Board board) {
        BoardTemplate.board = board;
    }

    public void printBoard() {
        List<List<SpaceProtocol>> spaces = board.getBoard();

        System.out.println("   1   2   3    4   5   6   7   8   9");
        System.out.println("  ┌───────────┬───────────┬───────────┐");
        for (int row = 0; row < 9; row++) {
            System.out.print((row + 1 ) + " │ ");
            for (int col = 0; col < 9; col++) {
                SpaceProtocol space = spaces.get(row).get(col);
                String value = space.getActual() == null ? " " : space.getActual().toString();
                System.out.printf(" %s ", value);

                if(col == 2 || col == 5){
                    System.out.print(" │ ");
                }
            }
            System.out.println(" │");

            if (row == 2 || row == 5) {
                System.out.println("  ├───────────┼───────────┼───────────┤");
            }
        }
        System.out.println("  └───────────┴───────────┴───────────┘");

    }
}
