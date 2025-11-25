package ui.custom.button;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ButtonCheckGameStatus extends JButton {

    public ButtonCheckGameStatus(ActionListener actionListener) {
        this.setText("Check Game");
        this.addActionListener(actionListener);
    }

}
