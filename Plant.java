package indy;

import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

// creates a Plant.

public class Plant implements Buyable{
    private Ellipse _petal1;
    private Ellipse _petal2;
    private Ellipse _petal3;
    private Ellipse _petal4;
    private Ellipse _petal5;
    private Ellipse _petal6;
    private Ellipse _petal7;
    private Ellipse _petal8;
    private Ellipse _petal9;
    private Circle _middle;
    private Rectangle _stem;
    private Rectangle _pot;
    private Rectangle _potTop;
    private Ellipse _leaf1;
    private Rectangle _potTopShadow;
    private Pane _root;
    private Buyable[][] _pets;

    public Plant(Pane root, Buyable[][] pets){
        _pets = pets;
        _root = root;
        _petal1 = new Ellipse(12, 5);
        _petal1.setRotate(90);
        _petal1.setFill(this.switchCol());
        _petal2 = new Ellipse(12, 5);
        _petal2.setRotate(130);
        _petal2.setFill(this.switchCol());
        _petal3 = new Ellipse(12, 5);
        _petal3.setRotate(170);
        _petal3.setFill(this.switchCol());
        _petal4 = new Ellipse(12, 5);
        _petal4.setRotate(210);
        _petal4.setFill(this.switchCol());
        _petal5 = new Ellipse(12, 5);
        _petal5.setRotate(250);
        _petal5.setFill(this.switchCol());
        _petal6 = new Ellipse(12, 5);
        _petal6.setRotate(290);
        _petal6.setFill(this.switchCol());
        _petal7 = new Ellipse(12, 5);
        _petal7.setRotate(330);
        _petal7.setFill(this.switchCol());
        _petal8 = new Ellipse(12, 5);
        _petal8.setRotate(370);
        _petal8.setFill(this.switchCol());
        _petal9 = new Ellipse(12, 5);
        _petal9.setRotate(410);
        _petal9.setFill(this.switchCol());
        _middle = new Circle(6, Color.GOLD);
        _stem = new Rectangle(3, 40);
        _stem.setFill(Color.GREEN);
        _pot = new Rectangle(30, 40);
        _pot.setFill(Color.SADDLEBROWN);
        _leaf1 = new Ellipse(12, 5);
        _leaf1.setFill(Color.GREEN);
        _leaf1.setRotate(-45);
        _potTop = new Rectangle(40, 10);
        _potTop.setFill(Color.SADDLEBROWN);
        _potTopShadow = new Rectangle(30, 3);
        _potTopShadow.setFill(Color.rgb(94, 45, 11));

    }

    // enables the Petal colors to be randomly generated.

    public Color switchCol() {
        Color col = null;
        int rand_int = (int) (Math.random() * 15);
        switch (rand_int) {
            case 0:
                col = Color.DEEPSKYBLUE;
                break;
            case 1:
                col = Color.PINK;
                break;
            case 2:
                col = Color.TURQUOISE;
                break;
            case 3:
                col = Color.VIOLET;
                break;
            case 4:
                col = Color.ORANGE;
                break;
            case 5:
                col = Color.LIGHTSALMON;
                break;
            case 6:
                col = Color.LIGHTGREEN;
                break;
            case 7:
                col = Color.LIGHTCORAL;
                break;
            case 8:
                col = Color.PALETURQUOISE;
                break;
            case 9:
                col = Color.SPRINGGREEN;
                break;
            case 10:
                col = Color.MEDIUMORCHID;
                break;
            case 11:
                col = Color.MEDIUMPURPLE;
                break;
            case 12:
                col = Color.FUCHSIA;
                break;
            case 13:
                col = Color.AQUAMARINE;
                break;
            case 14:
                col = Color.DEEPPINK;
                break;
            default:
                col = Color.CYAN;
                break;
        }
        return col;
    }

    // the following methods are explained in the Pet superclass which also implements them.

    public int getPrice(){
        return 80;
    }

    public String getType(){
        return "Flower";
    }

    public void setLoc(double x, double y){
        if (_pets[(int) ((x-110)/130)][(int) ((y-290)/130)] == null) {
            _petal1.setCenterX(x);
            _petal1.setCenterY(y-40);
            _petal2.setCenterX(x+10);
            _petal2.setCenterY(y-38);
            _petal3.setCenterX(x+14);
            _petal3.setCenterY(y-28);
            _petal4.setCenterX(x+11);
            _petal4.setCenterY(y-18);
            _petal5.setCenterX(x+3);
            _petal5.setCenterY(y-14);
            _petal6.setCenterX(x-7);
            _petal6.setCenterY(y-14);
            _petal7.setCenterX(x-14);
            _petal7.setCenterY(y-21);
            _petal8.setCenterX(x-14);
            _petal8.setCenterY(y-30);
            _petal9.setCenterX(x-10);
            _petal9.setCenterY(y-38);
            _middle.setCenterX(x);
            _middle.setCenterY(y-28);
            _stem.setX(x-2);
            _stem.setY(y-12);
            _pot.setX(x-15);
            _pot.setY(y+15);
            _leaf1.setCenterX(x+9);
            _leaf1.setCenterY(y+6);
            _potTop.setX(x-20);
            _potTop.setY(y+15);
            _potTopShadow.setX(x-15);
            _potTopShadow.setY(y+25);
        }
        else{
            this.setLoc(this.petXLoc(), this.petYLoc());
        }
    }

    public void setBounceLoc(double x, double y){
        _petal1.setCenterX(x);
        _petal1.setCenterY(y-40);
        _petal2.setCenterX(x+10);
        _petal2.setCenterY(y-38);
        _petal3.setCenterX(x+14);
        _petal3.setCenterY(y-28);
        _petal4.setCenterX(x+11);
        _petal4.setCenterY(y-18);
        _petal5.setCenterX(x+3);
        _petal5.setCenterY(y-14);
        _petal6.setCenterX(x-7);
        _petal6.setCenterY(y-14);
        _petal7.setCenterX(x-14);
        _petal7.setCenterY(y-21);
        _petal8.setCenterX(x-14);
        _petal8.setCenterY(y-30);
        _petal9.setCenterX(x-10);
        _petal9.setCenterY(y-38);
        _middle.setCenterX(x);
        _middle.setCenterY(y-28);
        _stem.setX(x-2);
        _stem.setY(y-12);
        _pot.setX(x-15);
        _pot.setY(y+15);
        _leaf1.setCenterX(x+9);
        _leaf1.setCenterY(y+6);
        _potTop.setX(x-20);
        _potTop.setY(y+15);
        _potTopShadow.setX(x-15);
        _potTopShadow.setY(y+25);
    }

    public int petXLoc(){
        int loc = 0;
        int rand_int = (int) (Math.random() * 4);
        switch (rand_int){
            case 0:
                loc = 110;
                break;
            case 1:
                loc = 240;
                break;
            case 2:
                loc = 370;
                break;
            default:
                loc = 500;
                break;
        }
        return loc;
    }

    public int petYLoc(){
        int loc = 0;
        int rand_int = (int) (Math.random() * 4);
        switch (rand_int){
            case 0:
                loc = 290;
                break;
            case 1:
                loc = 420;
                break;
            case 2:
                loc = 550;
                break;
            default:
                loc = 680;
                break;
        }
        return loc;
    }

    public double getXLoc(){
        return _petal1.getCenterX();
    }
    public double getYLoc(){
        return _petal1.getCenterY()+40;
    }

    public void removeFromPane(){
        _root.getChildren().removeAll(_stem, _petal1, _petal2, _petal3, _petal4, _petal5, _petal6, _petal7, _petal8, _petal9, _middle,
                _pot, _leaf1, _potTop, _potTopShadow);
    }

    public void addToPane(){
        _root.getChildren().addAll(_stem, _petal1, _petal2, _petal3, _petal4, _petal5, _petal6, _petal7, _petal8, _petal9, _middle,
                _pot, _leaf1, _potTop, _potTopShadow);
    }

    // these methods don't need to be known by the Plant class but the Plant class must implement them.
    public Timeline getTimeline(){
        return null;
    }
    public double getOYL(){
        return 0;
    }
    public String getPetName(){
        return null;
    }
    public String getPetBirthMonth(){
        return null;
    }
    public String getPetPersonality(){
        return null;
    }
    public String getPetFavFood(){
        return null;
    }
    public Ellipse getNode(){
        return null;
    }
    public void addHat(double x, double y){}
    public void setOpacity(double x){}
}
