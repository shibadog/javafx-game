package com.github.shibadog.azugon.game.pane;

import java.net.URL;
import java.util.ResourceBundle;

import com.github.shibadog.azugon.game.model.CharactorState;
import com.github.shibadog.azugon.game.model.MapModel;
import com.github.shibadog.azugon.game.model.MapModel.Position;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class AzugonGame extends BorderPane implements Initializable {
    public CharactorState blackCat;
    public MapModel map;

    @FXML
    private Pane mainCanvas;
    @FXML
    private MainCanvas mainCanvasController;

    @FXML
    private BorderPane operation;
    @FXML
    private Operation operationController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        map = new MapModel(200, 200);
        int key = map.createCharactor();
        URL blackCatPath = getClass().getResource("/blackcat.png");
        blackCat = new CharactorState(blackCatPath, 32, 32, map.getPosition(key));
        operationController.setState(blackCat);
        mainCanvasController.addCharactor(blackCat);

        int whiteCatKey = map.createCharactor();
        URL whiteCatPath = getClass().getResource("/whitecat.png");
        Position pos = map.getPosition(whiteCatKey);
        pos.down();
        pos.right();
        pos.right();
        pos.down();
        pos.down();
        CharactorState whiteCat = new CharactorState(whiteCatPath, 32, 32, map.getPosition(whiteCatKey));
        mainCanvasController.addCharactor(whiteCat);
    }
}