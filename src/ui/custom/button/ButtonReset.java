package ui.custom.button;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ButtonReset extends JButton {
    public ButtonReset(ActionListener actionListener) {
        this.setText("Reset Game");
        this.addActionListener(actionListener);
    }
}
