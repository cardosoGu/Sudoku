package service;

import model.Board;
import model.GameStatusEnum;
import model.Space;
import protocol.SpaceProtocol;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class BoardService {

    private final static int boardLimit = 9;
    private final Board board;

    public BoardService(Map<String, String> boardMap) {
        this.board = new Board(gameInit(boardMap));
    }

    public List<List<SpaceProtocol>> getSpaceProtocols() {
       return this.board.getBoard();
    }

    public void reset(){
        this.board.resetGame();
    }

    public boolean hasError(){
        return this.board.hasError();
    }

    public GameStatusEnum getStatus(){
        return board.getStatus();
    }

    public boolean gameIsFinished(){
        return this.board.finishGame();
    }



    private List<List<SpaceProtocol>> gameInit(final Map<String, String> boardMap) {
        List<List<SpaceProtocol>> spaces = new ArrayList<>();
        for (int row = 0; row < boardLimit; row++) {
            //add 9 list to List of List
            spaces.add(new ArrayList<>());
            for (int col = 0; col < boardLimit; col++) {
                //format row/col this model, and get value by the key in map
                var boardMapped = boardMap.get("%s,%s".formatted(row, col));
                //returned value of key map, so parse values to int and boolean
                var expected = Integer.parseInt(boardMapped.split(",")[0].trim());
                var fixed = Boolean.parseBoolean(boardMapped.split(",")[1].trim());
                spaces.get(row).add(new Space(expected, fixed));
            }
        }

        return spaces;
    }
}