package indy;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

// creates a Penguin. Its methods are explained in the Pet superclass.

public class Penguin extends Pet{
    private Ellipse _eye1;
    private Ellipse _eye2;
    private Ellipse _pupil1;
    private Ellipse _pupil2;
    private Ellipse _stomach;
    private Ellipse _eyeback1;
    private Ellipse _eyeback2;
    private Ellipse _beak;
    private Buyable[][] _pets;
    private Ellipse _coverNode;
    private Pane _root;
    private double _originalYLoc;


    public Penguin(Pane root, Buyable[][] pets, String petName, String personality, String birthMonth, String favFood){
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
        _stomach = new Ellipse(Constants.STOMACH_X, Constants.STOMACH_Y);
        _stomach.setFill(Color.WHITE);
        _eyeback1 = new Ellipse(Constants.EYE_BACK_X, Constants.EYE_BACK_Y);
        _eyeback1.setFill(Color.WHITE);
        _eyeback1.setRotate(-20);
        _eyeback2 = new Ellipse(Constants.EYE_BACK_X, Constants.EYE_BACK_Y);
        _eyeback2.setFill(Color.WHITE);
        _eyeback2.setRotate(20);
        _beak = new Ellipse(Constants.BEAK_X, Constants.BEAK_Y);
        _beak.setFill(Color.ORANGE);
        _coverNode = new Ellipse(Constants.ANIMAL_BODY_X, Constants.ANIMAL_BODY_Y);
        _coverNode.setFill(Color.TRANSPARENT);

        root.getChildren().addAll(_eyeback1, _eyeback2, _eye1, _eye2, _pupil1, _pupil2, _stomach, _beak, _coverNode);
    }

    @Override
    public void removeFromPane(){
        _root.getChildren().removeAll(this.getBody(), _eyeback1, _eyeback2, _eye1, _eye2, _pupil1, _pupil2, _stomach, _beak, _coverNode);
    }

    @Override
    public void addToPane(){
        _root.getChildren().addAll(this.getBody(), _eyeback1, _eyeback2, _eye1, _eye2, _pupil1, _pupil2, _stomach, _beak, _coverNode);
    }

    @Override
    public Ellipse getNode(){
        return _coverNode;
    }

    @Override
    public void setOpacity(double x){
        this.getBody().setOpacity(x);
        _eyeback1.setOpacity(x);
        _eyeback2.setOpacity(x);
        _eye1.setOpacity(x);
        _eye2.setOpacity(x);
        _pupil1.setOpacity(x);
        _pupil2.setOpacity(x);
        _stomach.setOpacity(x);
        _beak.setOpacity(x);
    }

    @Override
    public Color getPetColor(){
        return Color.BLACK;
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
            _stomach.setCenterX(x);
            _stomach.setCenterY(y+22);
            _eyeback1.setCenterX(x-20);
            _eyeback1.setCenterY(y-15);
            _eyeback2.setCenterX(x+20);
            _eyeback2.setCenterY(y-15);
            _beak.setCenterX(x);
            _beak.setCenterY(y-7);
            _coverNode.setCenterX(x);
            _coverNode.setCenterY(y);
            _originalYLoc = y;
        }
        else {
            this.setLoc(this.petXLoc(), this.petYLoc());
        }
    }

    @Override
    public double getOYL(){
        return _originalYLoc;
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
        _stomach.setCenterX(x);
        _stomach.setCenterY(y+22);
        _eyeback1.setCenterX(x-20);
        _eyeback1.setCenterY(y-15);
        _eyeback2.setCenterX(x+20);
        _eyeback2.setCenterY(y-15);
        _beak.setCenterX(x);
        _beak.setCenterY(y-7);
        _coverNode.setCenterX(x);
        _coverNode.setCenterY(y);
    }

    @Override
    public String getType(){
        return "Penguin";
    }

    @Override
    public int getPrice() {
        return 60;
    }
}
