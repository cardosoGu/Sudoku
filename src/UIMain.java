import ui.custom.Scream.MainScream;
import ui.custom.frame.MainFrame;
import ui.custom.panel.MainPanel;
import utils.NumsFixedsAndExpecteds;

import javax.swing.*;
import java.awt.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class UIMain  {

    private static final String[] positionToSplit = new NumsFixedsAndExpecteds().getFixedDefaults().split(" ");

    public static void main(String[] args) {
        var gameConfig = Stream.of(positionToSplit).collect(Collectors.toMap(
                k -> k.split(";")[0].trim(),
                v -> v.split(";")[1].trim()
        ));
        var mainScreen = new MainScream(gameConfig);
        mainScreen.buildMainScreen();
    }
}
