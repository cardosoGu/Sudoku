package model;

import java.util.Collection;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class Board {

    private List<List<Space>> board;
    private GameStatusEnum status = GameStatusEnum.NON_STARTED;



    public boolean hasError(){
        //non started cant have error
        if (status == GameStatusEnum.NON_STARTED) {
            return false;
        }
        //if some space nonnull is different of num expected, has an error
        else if(status == GameStatusEnum.INCOMPLETE || status == GameStatusEnum.COMPLETE){
            return board.stream().flatMap(Collection::stream).anyMatch(space -> nonNull(space.getActual()) && space.getActual() != space.getExpected());
        }
        return false;
    }

    public void setGameStatus(){
        // each space, different of fixed, e none null
        if( board.stream().flatMap(Collection::stream).noneMatch(space -> !space.isFixed() && nonNull(space.getActual()))){
             status = GameStatusEnum.NON_STARTED;
        }
        // some space is different of fixed, e is null
        //here i don't validate if all is null cause the first if alredy do it
        else if (board.stream().flatMap(Collection::stream).anyMatch(space -> !space.isFixed() && isNull(space.getActual()))){
             status = GameStatusEnum.INCOMPLETE;
        }
        //some space is null?
        else if (board.stream().flatMap(Collection::stream).noneMatch(space -> isNull(space.getActual()))){
             status = GameStatusEnum.COMPLETE;
        }
    }

    public boolean addBoard(int row, int col, int num){
        setGameStatus();
        if(status == GameStatusEnum.COMPLETE) finishGame();

        if(num < 1 || num > 9 || row < 1 || row > 9 || col < 1 || col > 9) return false;

        //The user didn't need to enter a row/column 0, and it would be understandable.
        int rowToFindOnArray = row -1;
        int colToFindOnArray = col -1;


        if(board.get(rowToFindOnArray).get(colToFindOnArray).isFixed()) return false;
        board.get(rowToFindOnArray).get(colToFindOnArray).setActual(num);
        return true;
    }

    public boolean clearValue(int row, int col){
        if(board.get(row).get(col).isFixed()) return false;
        board.get(row).get(col).cleanActual();
        return true;
    }

    public void resetGame(){
        board.stream().flatMap(Collection::stream).forEach(space -> space.cleanActual());
    }

    public boolean finishGame(){
        setGameStatus();
        return !hasError() && status == GameStatusEnum.COMPLETE;
    }

    public List<List<Space>> getBoard() {
        return board;
    }

    public GameStatusEnum getStatus() {
        return status;
    }


}
