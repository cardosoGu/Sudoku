package ui.custom.Scream;


import model.GameStatusEnum;

import protocol.SpaceProtocol;
import service.BoardService;
import service.EventEnum;
import service.NotifierService;
import ui.custom.button.ButtonCheckGameStatus;
import ui.custom.button.ButtonReset;
import ui.custom.button.FinishGameButton;
import ui.custom.frame.MainFrame;
import ui.custom.input.NumberText;
import ui.custom.panel.MainPanel;
import ui.custom.panel.SudokuSector;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;


public class MainScream {
    private final static Dimension dimension = new Dimension(600, 600);

    private FinishGameButton finishGameButton;
    private ButtonReset buttonReset;
    private ButtonCheckGameStatus buttonCheckGameStatus;

    private final BoardService boardService;
    private final NotifierService notifierService;

    public MainScream(final Map<String, String> gameConfig) {
        this.boardService = new BoardService(gameConfig);
        this.notifierService = new NotifierService();
    }

    public void buildMainScreen(){
        JPanel mainPanel = new MainPanel(dimension);
        JFrame frame = new MainFrame(dimension, mainPanel);
        for (int r = 0; r < 9; r+=3) {
            var endRow = r + 2;
            for (int c = 0; c < 9; c+=3) {
                var endCol = c + 2;
                var spaces = getSpacesFromSector(boardService.getSpaceProtocols(), c, endCol, r, endRow);
                mainPanel.add(generateSection(spaces));
            }
        }
        addResetButton(mainPanel);
        addCheckGameStatus(mainPanel);
        addFinishGameButton(mainPanel);
        frame.revalidate();
        frame.repaint();
    }

    private List<SpaceProtocol> getSpacesFromSector(List<List<SpaceProtocol>> spaces, final int initCol, final int endCol, final int initRow, final int endRow) {
        List<SpaceProtocol> spacesFromSector = new ArrayList<>();
        for(int r = initRow; r <= endRow; r++){
            for(int c = initCol; c <= endCol; c++){
                spacesFromSector.add(spaces.get(r).get(c));
            }
        }
    return spacesFromSector;
    }

    private JPanel generateSection(final List<SpaceProtocol> spaces){
        List<NumberText> fields = new ArrayList<>(spaces.stream().map(NumberText::new).toList());
        fields.forEach(n -> notifierService.subscriber(EventEnum.CLEAR_SPACE, n));
        return new SudokuSector(fields);
    }
    private void addFinishGameButton(JPanel mainPanel) {
        finishGameButton = new FinishGameButton(e -> {
        if(boardService.gameIsFinished()){
            JOptionPane.showMessageDialog(null, "Game has been finished, congratulations!");
            buttonReset.enable(false);
            finishGameButton.enable(false);
            buttonCheckGameStatus.enable(false);

        }
        else{
            JOptionPane.showMessageDialog(null, "Seen like something went wrong! fix and to finish game!");
        }
        });
        mainPanel.add(finishGameButton);
    }

    private void addCheckGameStatus(JPanel mainPanel) {
        buttonCheckGameStatus = new ButtonCheckGameStatus(e -> {
            var hasError = boardService.hasError();
            var gameStatus = boardService.getStatus();
            var message = switch (gameStatus) {
                case NON_STARTED -> "Game non started yet";
                case INCOMPLETE -> "Game incomplete, please fill the empty fields";
                case COMPLETE -> "Game FullFilled";

            };
            if(gameStatus.equals(GameStatusEnum.COMPLETE)){
                message += hasError ? " but wrong, please fix" : " and right! congratulations!";
            }
            JOptionPane.showMessageDialog(null, message);
        });
        mainPanel.add(buttonCheckGameStatus);

    }

    private void addResetButton(JPanel mainPanel) {
        buttonReset = new ButtonReset(e -> {
        var dialogResult = JOptionPane.showConfirmDialog(
                null,
                "really wish reset the game??",
                        "Reset Game",
                JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
        );
        if(dialogResult == 0){
            boardService.reset();
            notifierService.notifier(EventEnum.CLEAR_SPACE);
        }
        });
        mainPanel.add(buttonReset);
    }

}
