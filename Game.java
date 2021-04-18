package indy;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.LinkedList;

public class Game {
    private Pane gardenPane;
    private Pane buttonPane;
    private Garden _garden;
    private Pane storePane;
    private Store _store;
    private StoreInterface _si;
    private Pane interactPane;
    private Title _title;
    private boolean _titleNotCompressed;
    private int _intPercent;
    private int _counter;
    private int _titleXLoc;
    private int _titleYLoc;
    private Pet[][] _pets;
    private Pet[] _pet;
    private double _pet1XLoc;
    private double _pet2XLoc;
    private double _pet3XLoc;
    private double _pet4XLoc;
    private double _pet1YLoc;
    private double _pet2YLoc;
    private double _pet3YLoc;
    private double _pet4YLoc;
    private Button _verChanger;
    private Timeline _timeline;
    private LinkedList<Pet> _myPets;
    private String newName;
    private String newNewName;
    private String searchQuery;
    private Boolean _searchClicked = false;
    private Boolean _cannotChangeName = false;
    private Buyable[][] _shop;

    public Game(){
        _shop = new Buyable[3][2];
        this.setupGame();
        this.setupInitialPets();
    }

    public Pane getGardenPane(){
        return gardenPane;
    }

    public Pane getStorePane(){
        return storePane;
    }

    public Pane getInteractPane(){
        return interactPane;
    }

    public Pane getButtonPane() {return buttonPane;}

    public void setupGame(){
        gardenPane = new Pane();
        storePane = new Pane();
        interactPane = new Pane();
        _garden = new Garden(gardenPane);
        _garden.setLoc(30, 200);
        _store = new Store(storePane);
        _store.setLoc(620, 50);
        _title = new Title(interactPane);
        _title.setLoc(0, 0);
        _si = new StoreInterface(interactPane, _store.getShopPane());
        _si.setLoc(620,50);
        _titleNotCompressed = true;
        interactPane.addEventHandler(KeyEvent.KEY_PRESSED, new AllKeyEventsHandler());
        interactPane.setFocusTraversable(true);
        this.setupLoadingBarTimeline();
        _intPercent = _title.getPercent();
        _counter = 0;
        _titleXLoc = 318;
        _titleYLoc = 270;
        this.setupCounter();
        buttonPane = new Pane();
        buttonPane.relocate(30, 15);
        _verChanger = new Button("Static Version");
        _verChanger.setOnAction(new VersionChanger());
        _verChanger.setOpacity(0);
        buttonPane.getChildren().add(_verChanger);
        _myPets = new LinkedList<Pet>();
        newName = "";
        newNewName = "";
        searchQuery = "";
        _si.getSearchMyPetsCN().addEventHandler(MouseEvent.MOUSE_CLICKED, new SearchGetter());
        _si.getXCN1().addEventHandler(MouseEvent.MOUSE_CLICKED, new SearchDisappear());
        _si.getFirstCN().addEventHandler(MouseEvent.MOUSE_CLICKED, new FirstPage());
        _si.getSecondCN().addEventHandler(MouseEvent.MOUSE_CLICKED, new SecondPage());
        _si.getThirdCN().addEventHandler(MouseEvent.MOUSE_CLICKED, new ThirdPage());
    }

    private class FirstPage implements EventHandler<MouseEvent>{
        public void handle(MouseEvent event){

                for (int i = 0; i < 3; i++){
                    for (int j = 0; j < 2; j++){
                        _si.getShop()[i][j].setOpacity(0);
                        _si.getShop()[i][j] = null;
                    }
                }
                _si.fillShop(_si.getCat(), _si.getChicken(), _si.getCow(), _si.getDog(), _si.getFox(), _si.getGiraffe());

            for (int i = 0; i < 3; i++){
                for (int j = 0; j < 2; j++){
                    if (_si.getShop()[i][j] != null) {
                        _si.getShop()[i][j].setOpacity(1);
                    }
                }
            }
        }
    }

    private class SecondPage implements EventHandler<MouseEvent>{
        public void handle(MouseEvent event){
            for (int i = 0; i < 3; i++){
                for (int j = 0; j < 2; j++){
                    if (_si.getShop()[i][j] != null) {
                        _si.getShop()[i][j].setOpacity(0);
                        _si.getShop()[i][j] = null;
                    }
                }
            }
            _si.fillShop(_si.getOwl(), _si.getPenguin(), _si.getPig(), _si.getReindeer(), _si.getSheep(), _si.getTiger());

            for (int i = 0; i < 3; i++){
                for (int j = 0; j < 2; j++){
                    if (_si.getShop()[i][j] != null) {
                        _si.getShop()[i][j].setOpacity(1);
                    }
                }
            }
        }
    }

    private class ThirdPage implements EventHandler<MouseEvent>{
        public void handle(MouseEvent event){
            for (int i = 0; i < 3; i++){
                for (int j = 0; j < 2; j++){
                    if (_si.getShop()[i][j] != null) {
                        _si.getShop()[i][j].setOpacity(0);
                        _si.getShop()[i][j] = null;
                    }
                }
            }
            _si.fillShop(_si.getWalrus(), null, null, null, null, null);

            for (int i = 0; i < 3; i++){
                for (int j = 0; j < 2; j++){
                    if (_si.getShop()[i][j] != null) {
                        _si.getShop()[i][j].setOpacity(1);
                    }
                }
            }
        }
    }

    private class VersionChanger implements EventHandler<ActionEvent> {
        boolean isAnimated = true;
        public void handle(ActionEvent event) {
            if (isAnimated) {
                _timeline.stop();
                _pet[0].setBounceLoc(_pet1XLoc, _pet1YLoc);
                _pet[1].setBounceLoc(_pet2XLoc, _pet2YLoc);
                _pet[2].setBounceLoc(_pet3XLoc, _pet3YLoc);
                _pet[3].setBounceLoc(_pet4XLoc, _pet4YLoc);
                _verChanger.setText("Animated Version");
                isAnimated = false;
            }
            else {
                _timeline.play();
                _verChanger.setText("Static Version");
                isAnimated = true;
            }
        }
    }

    public Pet generatePet(){
        SpecsHelper sh = new SpecsHelper();
        Pet pet = null;
        int rand_int = (int) (Math.random() * 13);
        switch (rand_int) {
            case 0:
                pet = new Cat(interactPane, _pets, "Type+CLICK to name", sh.getAge(), sh.getBirthMonth(), sh.getFavFood());
                break;
            case 1:
                pet = new Chicken(interactPane, _pets, "Type+CLICK to name", sh.getAge(), sh.getBirthMonth(), sh.getFavFood());
                break;
            case 2:
                pet = new Cow(interactPane, _pets, "Type+CLICK to name", sh.getAge(), sh.getBirthMonth(), sh.getFavFood());
                break;
            case 3:
                pet = new Dog(interactPane, _pets, "Type+CLICK to name", sh.getAge(), sh.getBirthMonth(), sh.getFavFood());
                break;
            case 4:
                pet = new Fox(interactPane, _pets, "Type+CLICK to name", sh.getAge(), sh.getBirthMonth(), sh.getFavFood());
                break;
            case 5:
                pet = new Giraffe(interactPane, _pets, "Type+CLICK to name", sh.getAge(), sh.getBirthMonth(), sh.getFavFood());
                break;
            case 6:
                pet = new Owl(interactPane, _pets, "Type+CLICK to name", sh.getAge(), sh.getBirthMonth(), sh.getFavFood());
                break;
            case 7:
                pet = new Penguin(interactPane, _pets, "Type+CLICK to name", sh.getAge(), sh.getBirthMonth(), sh.getFavFood());
                break;
            case 8:
                pet = new Pig(interactPane, _pets, "Type+CLICK to name", sh.getAge(), sh.getBirthMonth(), sh.getFavFood());
                break;
            case 9:
                pet = new Reindeer(interactPane, _pets, "Type+CLICK to name", sh.getAge(), sh.getBirthMonth(), sh.getFavFood());
                break;
            case 10:
                pet = new Sheep(interactPane, _pets, "Type+CLICK to name", sh.getAge(), sh.getBirthMonth(), sh.getFavFood());
                break;
            case 11:
                pet = new Tiger(interactPane, _pets, "Type+CLICK to name", sh.getAge(), sh.getBirthMonth(), sh.getFavFood());
                break;
            default:
                pet = new Walrus(interactPane, _pets, "Type+CLICK to name", sh.getAge(), sh.getBirthMonth(), sh.getFavFood());
                break;
        }
        return pet;
    }

    public void getOriginalLocs(){
        _pet1XLoc = _pet[0].getXLoc();
        _pet2XLoc = _pet[1].getXLoc();
        _pet3XLoc = _pet[2].getXLoc();
        _pet4XLoc = _pet[3].getXLoc();
        _pet1YLoc = _pet[0].getYLoc();
        _pet2YLoc = _pet[1].getYLoc();
        _pet3YLoc = _pet[2].getYLoc();
        _pet4YLoc = _pet[3].getYLoc();
    }

    public void setupInitialPets(){
        _pets = new Pet[4][4];
        _pet = new Pet[4];
        for (int i = 0; i < 4; i++){
            _pet[i] = this.generatePet();
            _pet[i].setOpacity(0);
            _pet[i].setLoc(this.petXLoc(), this.petYLoc());
            _myPets.add(i, _pet[i]);
            _pets[(int) ((_pet[i].getXLoc()-110)/130)][(int) ((_pet[i].getYLoc()-290)/130)] = _pet[i];
        }
        this.getOriginalLocs();
        this.setupPetMover();
        this.setupHandlers();
    }

    public void setupHandlers(){
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                if (_pets[i][j] != null) {
                    SpecsGetter sg = new SpecsGetter(_pets[i][j]);
                    _pets[i][j].getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, sg);
                    _pets[i][j].getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, sg);
                    _pets[i][j].getNode().addEventHandler(MouseEvent.MOUSE_EXITED, new SpecsDisappear());
                }
            }
        }
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

    public void setupLoadingBarTimeline() {
        KeyFrame kf = new KeyFrame(Duration.millis(90), new LoadingBarFiller());
        Timeline timeline = new Timeline(kf);
        timeline.setCycleCount(25);
        timeline.play();
    }

    public void setupCounter() {
        KeyFrame kf2 = new KeyFrame(Duration.millis(1), new Counter());
        Timeline timeline = new Timeline(kf2);
        timeline.setCycleCount(2500);
        timeline.play();
    }

    public void setupPetMover(){
        KeyFrame kf3 = new KeyFrame(Duration.millis(45), new PetMover());
        Timeline timeline = new Timeline(kf3);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        _timeline = timeline;
    }

    private class Counter implements EventHandler<ActionEvent>{
        public void handle(ActionEvent event){
            _counter ++;
            if (_counter == 2500){
                _title.getLoadingBar().setOpacity(0);
                _title.getLoadingBG().setOpacity(0);
                _title.getPercentLabel().setOpacity(0);
                _title.getPressSpace().setOpacity(1);
            }
        }
    }

    private class PetMover implements EventHandler<ActionEvent>{
        boolean _direction = true;

        public void handle(ActionEvent event){
            for (int i = 0; i < 4; i++){
                    if (_direction) {
                        _pet[i].setBounceLoc(_pet[i].getXLoc(), _pet[i].getYLoc() + 2);
                    } else {
                        _pet[i].setBounceLoc(_pet[i].getXLoc(), _pet[i].getYLoc() - 2);
                    }
            }
            if ((_pet[0].getYLoc() < (_pet1YLoc - 2)) && (_pet[1].getYLoc() < (_pet2YLoc - 4)) && (_pet[2].getYLoc() < (_pet3YLoc - 6))
            && (_pet[3].getYLoc() < (_pet4YLoc - 8))){
                _direction = !_direction;
            }

            if ((_pet[0].getYLoc() > (_pet1YLoc + 2)) && (_pet[1].getYLoc() > (_pet2YLoc + 3)) && (_pet[2].getYLoc() > (_pet3YLoc + 4))
                    && (_pet[3].getYLoc() > (_pet4YLoc + 5))){
                _direction = !_direction;
            }
        }
    }

    private class TitleMover implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            _title.getBG().setWidth(_title.getBG().getWidth() - 120);
            _title.setBGXLoc(_title.getBGXLoc() + 120);
            _title.getTitle().setRotate(_title.getTitle().getRotate()+9);
            _titleXLoc +=49.5;
            _titleYLoc +=3;
            _title.setTitleLoc(_titleXLoc + 49.5, _titleYLoc + 3);
        }
    }

    private class LoadingBarFiller implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            _title.getLoadingBar().setWidth(_title.getLoadingBar().getWidth()+12);
            _intPercent +=4;
            _title.getPercentLabel().setText(_intPercent + "%");
        }
    }

    private class SearchGetter implements EventHandler<MouseEvent>{
        public void handle (MouseEvent event){
            _si.setSearchOpacity(1);
            _store.getCat().setOpacity(0);
            _store.getChicken().setOpacity(0);
            _store.getCow().setOpacity(0);
            _store.getDog().setOpacity(0);
            _store.getFox().setOpacity(0);
            _store.getGiraffe().setOpacity(0);
            _store.getOwl().setOpacity(0);
            _store.getPenguin().setOpacity(0);
            _store.getPig().setOpacity(0);
            _store.getReindeer().setOpacity(0);
            _store.getSheep().setOpacity(0);
            _store.getTiger().setOpacity(0);
            _store.getWalrus().setOpacity(0);
            _store.getPetNamed().setOpacity(0);
            _si.getTypeToSearch().setText("Type a pet's name to search");
            searchQuery = "";
            _searchClicked = true;
        }
    }

    private class SearchDisappear implements EventHandler<MouseEvent>{
        public void handle(MouseEvent event){
            _si.setSearchOpacity(0);
            _searchClicked = false;
        }
    }


    private class SpecsGetter implements EventHandler<MouseEvent> {
        private Pet _pet;
        private SpecsGetter(Pet pet) {
            _pet = pet;
        }
            public void handle (MouseEvent event){
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (_pets[i][j] != null) {
                            _store.getSpecsPane().setOpacity(1);
                            _store.getMyName().setText(_pet.getPetName());
                            _store.getMyAge().setText(_pet.getPetAge());
                            _store.getMyBirthMonth().setText(_pet.getPetBirthMonth());
                            _store.getMyFavFood().setText(_pet.getPetFavFood());
                            AllKeyEventsHandler akeh = new AllKeyEventsHandler();
                            _pet.getNode().addEventHandler(KeyEvent.KEY_TYPED, akeh);

                            if (newName.length() > 1) {
                                newNewName = newName;
                                newName = "";
                                _pet.setPetName(newNewName);
                                _store.getPetNamed().setOpacity(1);
                                _pet.setIsNamed();
                            }

                            if (_pet.getType().equals("cat")){
                                _store.getCat().setOpacity(1);
                            }
                            if (_pet.getType().equals("chicken")){
                                _store.getChicken().setOpacity(1);
                            }
                            if (_pet.getType().equals("cow")){
                                _store.getCow().setOpacity(1);
                            }
                            if (_pet.getType().equals("dog")){
                                _store.getDog().setOpacity(1);
                            }
                            if (_pet.getType().equals("fox")){
                                _store.getFox().setOpacity(1);
                            }
                            if (_pet.getType().equals("giraffe")){
                                _store.getGiraffe().setOpacity(1);
                            }
                            if (_pet.getType().equals("owl")){
                                _store.getOwl().setOpacity(1);
                            }
                            if (_pet.getType().equals("penguin")){
                                _store.getPenguin().setOpacity(1);
                            }
                            if (_pet.getType().equals("pig")){
                                _store.getPig().setOpacity(1);
                            }
                            if (_pet.getType().equals("reindeer")){
                                _store.getReindeer().setOpacity(1);
                            }
                            if (_pet.getType().equals("sheep")){
                                _store.getSheep().setOpacity(1);
                            }
                            if (_pet.getType().equals("tiger")){
                                _store.getTiger().setOpacity(1);
                            }
                            if (_pet.getType().equals("walrus")){
                                _store.getWalrus().setOpacity(1);
                            }
                        }
                    }
                }
            }
    }

    private class SpecsDisappear implements EventHandler<MouseEvent> {
        public void handle(MouseEvent event) {
            _store.getSpecsPane().setOpacity(0);
            _store.getCat().setOpacity(0);
            _store.getChicken().setOpacity(0);
            _store.getCow().setOpacity(0);
            _store.getDog().setOpacity(0);
            _store.getFox().setOpacity(0);
            _store.getGiraffe().setOpacity(0);
            _store.getOwl().setOpacity(0);
            _store.getPenguin().setOpacity(0);
            _store.getPig().setOpacity(0);
            _store.getReindeer().setOpacity(0);
            _store.getSheep().setOpacity(0);
            _store.getTiger().setOpacity(0);
            _store.getWalrus().setOpacity(0);
            _store.getPetNamed().setOpacity(0);
        }
    }

    private class SpecsClose implements EventHandler<MouseEvent>{
        private Pet _pet;
        private SpecsClose(Pet pet){
            _pet = pet;
        }
        public void handle(MouseEvent event){
            _store.getSpecsPane().setOpacity(0);
            _store.getCat().setOpacity(0);
            _store.getChicken().setOpacity(0);
            _store.getCow().setOpacity(0);
            _store.getDog().setOpacity(0);
            _store.getFox().setOpacity(0);
            _store.getGiraffe().setOpacity(0);
            _store.getOwl().setOpacity(0);
            _store.getPenguin().setOpacity(0);
            _store.getPig().setOpacity(0);
            _store.getReindeer().setOpacity(0);
            _store.getSheep().setOpacity(0);
            _store.getTiger().setOpacity(0);
            _store.getWalrus().setOpacity(0);
            _store.getPetNamed().setOpacity(0);
                _si.setCloseOpacity(0);
                _cannotChangeName = false;
                _pet.getNode().addEventHandler(MouseEvent.MOUSE_EXITED, new SpecsDisappear());
        }
    }

    private class AllKeyEventsHandler implements EventHandler<KeyEvent> {
        public void setupTitleBGTimeline() {
            KeyFrame kf1 = new KeyFrame(Duration.millis(25), new TitleMover());
            Timeline timeline = new Timeline(kf1);
            timeline.setCycleCount(10);
            timeline.play();
        }

        public void handle (KeyEvent event){
            if ((_titleNotCompressed) && (_counter == 2500)) {
                if (event.getCode() == KeyCode.SPACE) {
                    this.setupTitleBGTimeline();
                    _title.getPressSpace().setOpacity(0);
                    _titleNotCompressed = false;
                    _verChanger.setOpacity(1);
                    for (int i = 0; i < 4; i++){
                        _pet[i].setOpacity(1);
                    }
                    for (int i = 0; i < 3; i++){
                        for (int j = 0; j < 2; j++){
                            _si.getShop()[i][j].setOpacity(1);
                        }
                    }
                    _si.setOpacity(1);
                }
            }

            if ((event.getCode() == KeyCode.A) || (event.getCode() == KeyCode.B) || (event.getCode() == KeyCode.C)
                    || (event.getCode() == KeyCode.D) || (event.getCode() == KeyCode.E) || (event.getCode() == KeyCode.F)
                    || (event.getCode() == KeyCode.G) || (event.getCode() == KeyCode.H) || (event.getCode() == KeyCode.I)
                    || (event.getCode() == KeyCode.J) || (event.getCode() == KeyCode.K) || (event.getCode() == KeyCode.L)
                    || (event.getCode() == KeyCode.M) || (event.getCode() == KeyCode.N) || (event.getCode() == KeyCode.O)
                    || (event.getCode() == KeyCode.P) || (event.getCode() == KeyCode.Q || (event.getCode() == KeyCode.R)
                    || (event.getCode() == KeyCode.S) || (event.getCode() == KeyCode.T) || (event.getCode() == KeyCode.U)
                    || (event.getCode() == KeyCode.V) || (event.getCode() == KeyCode.W) || (event.getCode() == KeyCode.X)
                    || (event.getCode() == KeyCode.Y) || (event.getCode() == KeyCode.Z))) {

                if (_searchClicked) {
                    searchQuery = searchQuery + event.getText();
                    _si.getTypeToSearch().setText(searchQuery);

                    if (searchQuery != null && searchQuery.length() > 1 && !searchQuery.equals("Type+CLICK to name")) {
                        for (int i = 0; i < 4; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (_pets[i][j] != null) {
                                    if (searchQuery.equals(_pets[i][j].getPetName())) {
                                        _cannotChangeName = true;
                                        _si.setSearchOpacity(0);
                                        _searchClicked = false;
                                        _store.getSpecsPane().setOpacity(1);
                                        _store.getMyName().setText(_pets[i][j].getPetName());
                                        _store.getMyAge().setText(_pets[i][j].getPetAge());
                                        _store.getMyBirthMonth().setText(_pets[i][j].getPetBirthMonth());
                                        _store.getMyFavFood().setText(_pets[i][j].getPetFavFood());

                                        if (_pets[i][j].getType().equals("cat")){
                                            _store.getCat().setOpacity(1);
                                        }
                                        if (_pets[i][j].getType().equals("chicken")){
                                            _store.getChicken().setOpacity(1);
                                        }
                                        if (_pets[i][j].getType().equals("cow")){
                                            _store.getCow().setOpacity(1);
                                        }
                                        if (_pets[i][j].getType().equals("dog")){
                                            _store.getDog().setOpacity(1);
                                        }
                                        if (_pets[i][j].getType().equals("fox")){
                                            _store.getFox().setOpacity(1);
                                        }
                                        if (_pets[i][j].getType().equals("giraffe")){
                                            _store.getGiraffe().setOpacity(1);
                                        }
                                        if (_pets[i][j].getType().equals("owl")){
                                            _store.getOwl().setOpacity(1);
                                        }
                                        if (_pets[i][j].getType().equals("penguin")){
                                            _store.getPenguin().setOpacity(1);
                                        }
                                        if (_pets[i][j].getType().equals("pig")){
                                            _store.getPig().setOpacity(1);
                                        }
                                        if (_pets[i][j].getType().equals("reindeer")){
                                            _store.getReindeer().setOpacity(1);
                                        }
                                        if (_pets[i][j].getType().equals("sheep")){
                                            _store.getSheep().setOpacity(1);
                                        }
                                        if (_pets[i][j].getType().equals("tiger")){
                                            _store.getTiger().setOpacity(1);
                                        }
                                        if (_pets[i][j].getType().equals("walrus")){
                                            _store.getWalrus().setOpacity(1);
                                        }

                                        _pets[i][j].getNode().removeEventHandler(MouseEvent.MOUSE_EXITED, new SpecsDisappear());
                                        _si.setCloseOpacity(1);
                                            _si.getXCN1().addEventHandler(MouseEvent.MOUSE_CLICKED, new SpecsClose(_pets[i][j]));
                                    }
                                }
                            }
                        }
                    }
                }

                if (!_searchClicked && !_cannotChangeName) {

                    newName = newName + event.getText();
                    _store.getMyName().setText(newName);
                }
            }
        }
    }

    public class SpecsHelper {
        public String getAge() {
            String age = null;
            int rand_int = (int) (Math.random() * 13);
            switch (rand_int) {
                case 0:
                    age = "1";
                    break;
                case 1:
                    age = "2";
                    break;
                case 2:
                    age = "3";
                    break;
                case 3:
                    age = "4";
                    break;
                case 4:
                    age = "5";
                    break;
                case 5:
                    age = "6";
                    break;
                case 6:
                    age = "7";
                    break;
                case 7:
                    age = "8";
                    break;
                case 8:
                    age = "9";
                    break;
                case 9:
                    age = "10";
                    break;
                case 10:
                    age = "11";
                    break;
                case 11:
                    age = "12";
                    break;
                default:
                    age = "13";
                    break;
            }
            return age;
        }

        public String getBirthMonth() {
            String month = null;
            int rand_int = (int) (Math.random() * 12);
            switch (rand_int) {
                case 0:
                    month = "January (Capricorn)";
                    break;
                case 1:
                    month = "February (Aquarius)";
                    break;
                case 2:
                    month = "March (Pisces)";
                    break;
                case 3:
                    month = "April (Aries)";
                    break;
                case 4:
                    month = "May (Taurus)";
                    break;
                case 5:
                    month = "June (Gemini)";
                    break;
                case 6:
                    month = "July (Cancer)";
                    break;
                case 7:
                    month = "August (Leo)";
                    break;
                case 8:
                    month = "September (Virgo)";
                    break;
                case 9:
                    month = "October (Libra)";
                    break;
                case 10:
                    month = "November (Scorpio)";
                    break;
                default:
                    month = "December (Sagittarius)";
                    break;
            }
            return month;
        }

        public String getFavFood() {
            String food = null;
            int rand_int = (int) (Math.random() * 12);
            switch (rand_int) {
                case 0:
                    food = "Pasta";
                    break;
                case 1:
                    food = "Acai Bowl";
                    break;
                case 2:
                    food = "Dumplings";
                    break;
                case 3:
                    food = "Ramen";
                    break;
                case 4:
                    food = "Vegan Ice Cream";
                    break;
                case 5:
                    food = "Tofu";
                    break;
                case 6:
                    food = "Tomato Soup";
                    break;
                case 7:
                    food = "Veggie Burger";
                    break;
                case 8:
                    food = "Sushi";
                    break;
                case 9:
                    food = "Mashed Potatoes";
                    break;
                case 10:
                    food = "Fruit Salad";
                    break;
                default:
                    food = "Channa Masala";
                    break;
            }
            return food;
        }
    }

}
