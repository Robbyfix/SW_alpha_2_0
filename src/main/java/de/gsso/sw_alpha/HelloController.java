package de.gsso.sw_alpha;

import de.gsso.sw_alpha.misc.keyevent.KeyPressEvent;
import de.gsso.sw_alpha.misc.keyevent.KeyReleaseEvent;
import de.gsso.sw_alpha.objects.Decoration;
import de.gsso.sw_alpha.objects.Ground;
import de.gsso.sw_alpha.objects.Player;
//import de.gsso.sw_alpha.resources.Img.Ground;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    private Player spieler;
    private Ground[] grounds;
    private Decoration[] decorations;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Pane canvas = new Pane();

    @FXML
    private Pane playerPane;

    @FXML
    private Pane QuickMenu;

    @FXML
    private Pane decorationPane;

    @FXML
    private Button lvl1;

    @FXML
    private Button MaMe;

    @FXML
    private Button Cont;

    @FXML
    private Button Settings;

    @FXML
    private Button Quit;

    @FXML
    private Button Ret;

    @FXML
    private Slider sliderVol;

    @FXML
    private ImageView Num1;

    @FXML
    private ImageView Num2;

    @FXML
    private ImageView Num3;

    @FXML
    public void handleFirstLevel() {
        if (spieler == null) {                            //Check, ob spieler nicht null ist
            spieler = new Player(canvas, playerPane, QuickMenu, decorationPane);               //Neuer Spieler instanziiert
            canvas.getScene().getRoot().setOnKeyPressed(new KeyPressEvent(spieler));
            canvas.getScene().getRoot().setOnKeyReleased(new KeyReleaseEvent(spieler));
        }
        spieler.getSpielerfig().setX(900); //Spawnposition
        spieler.getSpielerfig().setY(0);
        spieler.setStartPosY(0);
        spieler.setGoalPosX(4711);
        spieler.setAufBoden(false);
        lvl1.setOpacity(0);
        canvas.setVisible(true);
        playerPane.setVisible(true);
        spieler.start();

        grounds = new Ground[8];
        //grounds[0] = new Ground(canvas, "GrassRockHoz",1600,900);
        //grounds[1] = new Ground(canvas, "Lvl1_obst1",1300,700);
        //grounds[2] = new Ground(canvas, "Lvl1_obst1",1100,500);
        //grounds[3] = new Ground(canvas, "Lvl1_obst1",800,300);
        //grounds[4] = new Ground(canvas, "Lvl1_obst1",600,500);
        //grounds[5] = new Ground(canvas, "Lvl1_obst1",400,700);

        grounds[0] = new Ground(canvas, "5x8", 800, 800);
        grounds[1] = new Ground(canvas, "5x12", 300, 550);
        grounds[2] = new Ground(canvas, "3x10", -300, 600);
        grounds[3] = new Ground(canvas, "3x10", -700, 770);
        grounds[4] = new Ground(canvas, "8x9", -1400, 550);
        grounds[5] = new Ground(canvas, "5x12", -2000, 350);
        grounds[6] = new Ground(canvas, "8x12", -3000, 250);
        grounds[7] = new Ground(canvas, "5x12", -3800, 500);
    }

    public void handleMainMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(HelloApplication.class.getResource("Menu.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void handleContinue(){
        QuickMenu.setVisible(false);
        spieler.setqMenu(false);
        spieler.start();
    }

    public void handleOpenSettings(){
        MaMe.setVisible(false);
        Cont.setVisible(false);
        Settings.setVisible(false);
        Quit.setVisible(false);
        Ret.setVisible(true);

        sliderVol.setValue(spieler.getMediaPlayer().getVolume());
        sliderVol.setVisible(true);

        Num1.setVisible(true);

        Num2.setVisible(true);

        Num3.setVisible(true);

        if(sliderVol.getValue()<10) {
            einstellig();
        }
        else if(sliderVol.getValue()>10&&sliderVol.getValue()<100){
            zweistellig();
        }
        else{
            dreistellig();
        }
    }

    public void handleRefreshSettings(){
        if(sliderVol.getValue()<10){
            einstellig();
        }
        if(sliderVol.getValue()>10&&sliderVol.getValue()<100){
            zweistellig();
        }
        if(sliderVol.getValue()==100){
            dreistellig();
        }
        spieler.getMediaPlayer().setVolume(sliderVol.getValue());
    }

    public void handleCloseSettings(){
        MaMe.setVisible(true);
        Cont.setVisible(true);
        Settings.setVisible(true);
        Quit.setVisible(true);
        Ret.setVisible(false);
        sliderVol.setVisible(false);

        Num1.setVisible(false);

        Num2.setVisible(false);

        Num3.setVisible(false);
    }

    public void einstellig(){
        Num1.setVisible(true);

        Num2.setVisible(false);

        Num3.setVisible(false);

        setNums(false);

        Num1.setY(500);
        Num1.setX(1280);
    }

    public void zweistellig(){
        Num1.setVisible(true);

        Num2.setVisible(true);

        Num3.setVisible(false);

        setNums(true);

        Num1.setX(1304);
        Num1.setY(500);

        Num2.setX(1256);
        Num2.setY(500);
    }
    public void dreistellig(){
        Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/zero.png")));
        Num1.setVisible(true);

        Num2.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/zero.png")));
        Num2.setVisible(true);

        Num3.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/one.png")));
        Num3.setVisible(true);

        Num1.setX(1340);
        Num1.setY(500);

        Num2.setX(1292);
        Num2.setY(500);

        Num3.setX(1244);
        Num3.setY(500);
    }

    public void setNums(boolean twoNums){
        if(twoNums) {
            switch ((int) (sliderVol.getValue() / 10)) {
                case 1 -> Num2.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/one.png")));
                case 2 -> Num2.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/two.png")));
                case 3 -> Num2.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/three.png")));
                case 4 -> Num2.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/four.png")));
                case 5 -> Num2.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/five.png")));
                case 6 -> Num2.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/six.png")));
                case 7 -> Num2.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/seven.png")));
                case 8 -> Num2.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/eight.png")));
                case 9 -> Num2.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/nine.png")));
            }
            switch ((int) sliderVol.getValue() - (((int)sliderVol.getValue()/10) * 10)) {
                case 0 -> Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/zero.png")));
                case 1 -> Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/one.png")));
                case 2 -> Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/two.png")));
                case 3 -> Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/three.png")));
                case 4 -> Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/four.png")));
                case 5 -> Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/five.png")));
                case 6 -> Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/six.png")));
                case 7 -> Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/seven.png")));
                case 8 -> Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/eight.png")));
                case 9 -> Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/nine.png")));
            }
        }
        else{
            switch ((int) sliderVol.getValue()) {
                case 0 -> Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/zero.png")));
                case 1 -> Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/one.png")));
                case 2 -> Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/two.png")));
                case 3 -> Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/three.png")));
                case 4 -> Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/four.png")));
                case 5 -> Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/five.png")));
                case 6 -> Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/six.png")));
                case 7 -> Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/seven.png")));
                case 8 -> Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/eight.png")));
                case 9 -> Num1.setImage(new Image(HelloController.class.getClassLoader().getResourceAsStream("Img/UI/nine.png")));
            }
        }
    }

    public void handleQuit(ActionEvent event){
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}