package ui.custom.Scream;

import com.sun.tools.javac.Main;
import model.GameStatusEnum;
import service.BoardService;
import ui.custom.button.ButtonCheckGameStatus;
import ui.custom.button.ButtonReset;
import ui.custom.button.FinishGameButton;
import ui.custom.frame.MainFrame;
import ui.custom.panel.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class MainScream {
    private final static Dimension dimension = new Dimension(600, 600);

    private FinishGameButton finishGameButton;
    private ButtonReset buttonReset;
    private ButtonCheckGameStatus buttonCheckGameStatus;

    private final BoardService boardService;

    public MainScream(final Map<String, String> gameConfig) {
        this.boardService = new BoardService(gameConfig);
    }

    public void buildMainScreen(){
        JPanel mainPanel = new MainPanel(dimension);
        JFrame frame = new MainFrame(dimension, mainPanel);
        addResetButton(mainPanel);
        addCheckGameStatus(mainPanel);
        addFinishGameButton(mainPanel);
        frame.revalidate();
        frame.repaint();
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
            if(!gameStatus.equals(GameStatusEnum.NON_STARTED)){
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
        }
        });
        mainPanel.add(buttonReset);
    }

}
