package indy;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

// Creates a Chicken. Its methods are explained in the Pet superclass.

public class Chicken extends Pet{
    private Ellipse _eye1;
    private Ellipse _eye2;
    private Ellipse _pupil1;
    private Ellipse _pupil2;
    private Ellipse _beak;
    private Ellipse _wing1;
    private Ellipse _wing2;
    private Ellipse _waddle;
    private Ellipse _top1;
    private Ellipse _top2;
    private Ellipse _top3;
    private Buyable[][] _pets;
    private Ellipse _coverNode;
    private Pane _root;
    private double _originalYLoc;

    public Chicken(Pane root, Buyable[][] pets, String petName, String personality, String birthMonth, String favFood){
        super(root, pets, petName, personality, birthMonth, favFood);
        _root = root;
        _pets = pets;
        _eye1 = new Ellipse(Constants.EYE_RAD, Constants.EYE_RAD);
        _eye2 = new Ellipse(Constants.EYE_RAD, Constants.EYE_RAD);
        _eye1.setFill(Color.BLACK);
        _eye2.setFill(Color.BLACK);
        _pupil1 = new Ellipse(Constants.PUPIL_RAD, Constants.PUPIL_RAD);
        _pupil2 = new Ellipse(Constants.PUPIL_RAD, Constants.PUPIL_RAD);
        _pupil1.setFill(Color.WHITE);
        _pupil2.setFill(Color.WHITE);
        _beak = new Ellipse(Constants.BEAK_X, Constants.BEAK_Y);
        _beak.setFill(Color.ORANGE);
        _wing1 = new Ellipse(Constants.WING_X, Constants.WING_Y);
        _wing1.setFill(Color.GOLD);
        _wing1.setRotate(-13);
        _wing2 = new Ellipse(Constants.WING_X, Constants.WING_Y);
        _wing2.setFill(Color.GOLD);
        _wing2.setRotate(13);
        _waddle = new Ellipse(Constants.WADDLE_RAD, Constants.WADDLE_RAD);
        _waddle.setFill(Color.TOMATO);
        _top1 = new Ellipse(Constants.SMALL_TOP_X, Constants.SMALL_TOP_Y);
        _top1.setFill(Color.TOMATO);
        _top1.setRotate(-30);
        _top2 = new Ellipse(Constants.BIG_TOP_X, Constants.BIG_TOP_Y);
        _top2.setFill(Color.TOMATO);
        _top3 = new Ellipse(Constants.SMALL_TOP_X, Constants.SMALL_TOP_Y);
        _top3.setFill(Color.TOMATO);
        _top3.setRotate(30);
        _coverNode = new Ellipse(Constants.ANIMAL_BODY_X, Constants.ANIMAL_BODY_Y);
        _coverNode.setFill(Color.TRANSPARENT);

        root.getChildren().addAll(_eye1, _eye2, _pupil1, _pupil2, _waddle, _beak, _wing1, _wing2, _top1, _top2, _top3, _coverNode);
    }

    @Override
    public void removeFromPane(){
        _root.getChildren().removeAll(this.getBody(), _eye1, _eye2, _pupil1, _pupil2, _waddle, _beak, _wing1, _wing2, _top1,
                _top2, _top3, _coverNode);
    }

    @Override
    public void addToPane(){
        _root.getChildren().addAll(this.getBody(), _eye1, _eye2, _pupil1, _pupil2, _waddle, _beak, _wing1, _wing2, _top1,
                _top2, _top3, _coverNode);
    }

    @Override
    public Ellipse getNode(){
        return _coverNode;
    }

    @Override
    public void setOpacity(double x){
        this.getBody().setOpacity(x);
        _eye1.setOpacity(x);
        _eye2.setOpacity(x);
        _pupil1.setOpacity(x);
        _pupil2.setOpacity(x);
        _waddle.setOpacity(x);
        _beak.setOpacity(x);
        _wing1.setOpacity(x);
        _wing2.setOpacity(x);
        _top1.setOpacity(x);
        _top2.setOpacity(x);
        _top3.setOpacity(x);
    }

    @Override
    public Color getPetColor(){
        return Color.rgb(245, 234, 120);
    }

    @Override
    public void setLoc(double x, double y){
        if (_pets[(int) ((x-110)/130)][(int) ((y-290)/130)] == null) {
            super.setLoc(x, y);
            this.getBody().setCenterX(x);
            this.getBody().setCenterY(y);
            _eye1.setCenterX(x-15);
            _eye1.setCenterY(y-20);
            _eye2.setCenterX(x+15);
            _eye2.setCenterY(y-20);
            _pupil1.setCenterX(x-13);
            _pupil1.setCenterY(y-21);
            _pupil2.setCenterX(x+17);
            _pupil2.setCenterY(y-21);
            _beak.setCenterX(x);
            _beak.setCenterY(y-3);
            _wing1.setCenterX(x-38);
            _wing1.setCenterY(y+5);
            _wing2.setCenterX(x+38);
            _wing2.setCenterY(y+5);
            _waddle.setCenterX(x);
            _waddle.setCenterY(y+10);
            _top1.setCenterX(x-10);
            _top1.setCenterY(y-55);
            _top2.setCenterX(x);
            _top2.setCenterY(y-62);
            _top3.setCenterX(x+10);
            _top3.setCenterY(y-55);
            _coverNode.setCenterX(x);
            _coverNode.setCenterY(y);
            _originalYLoc = y;
        }
        else {
            this.setLoc(this.petXLoc(), this.petYLoc());
        }
    }

    @Override
    public void setBounceLoc(double x, double y){
        super.setBounceLoc(x, y);
        this.getBody().setCenterX(x);
        this.getBody().setCenterY(y);
        _eye1.setCenterX(x-15);
        _eye1.setCenterY(y-20);
        _eye2.setCenterX(x+15);
        _eye2.setCenterY(y-20);
        _pupil1.setCenterX(x-13);
        _pupil1.setCenterY(y-21);
        _pupil2.setCenterX(x+17);
        _pupil2.setCenterY(y-21);
        _beak.setCenterX(x);
        _beak.setCenterY(y-3);
        _wing1.setCenterX(x-38);
        _wing1.setCenterY(y+5);
        _wing2.setCenterX(x+38);
        _wing2.setCenterY(y+5);
        _waddle.setCenterX(x);
        _waddle.setCenterY(y+10);
        _top1.setCenterX(x-10);
        _top1.setCenterY(y-55);
        _top2.setCenterX(x);
        _top2.setCenterY(y-62);
        _top3.setCenterX(x+10);
        _top3.setCenterY(y-55);
        _coverNode.setCenterX(x);
        _coverNode.setCenterY(y);
    }

    @Override
    public String getType(){
        return "Chicken";
    }

    @Override
    public int getPrice() {
        return 20;
    }

    @Override
    public double getOYL(){
        return _originalYLoc;
    }
}
