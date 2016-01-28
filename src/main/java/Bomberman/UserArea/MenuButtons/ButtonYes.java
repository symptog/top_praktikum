package Bomberman.UserArea.MenuButtons;

import Bomberman.Bomberman;
import vialab.SMT.Touch;
import vialab.SMT.Zone;

public class ButtonYes extends Zone {

    protected Bomberman bomberman;
    private Integer id;
    public Integer counter = 0;

    public ButtonYes(int i, int i1, int i2, int i3, Bomberman b, int button_id) {
        super(i, i1, i2, i3);
        id = button_id; // // button id: 1= close 2=new 3= surrender
        this.bomberman = b;
    }

    @Override
    public void draw() {
        stroke(0, 0, 0);
        fill(170, 60, 0);
        rectMode(CORNER);
        rect(0, 0, this.getWidth(), this.getHeight());
        textAlign(CENTER);  textSize(15);   fill(0);
        text("Ja", this.getWidth() / 2, this.getHeight() / 3 * 2);

    }

    @Override
    public void drag(){}

    @Override
    public void touchDown(Touch touch){
        switch (id) {
            case 1:
                    // close game
                     break;
            case 2:
                    // start new game
                    break;
            case 3:
                    // surrender
                if (bomberman.isAlive()){
                    bomberman.lostgame();
                    getParent().getParent().getParent().clearChildren();
                }
                    break;
            default:
                    break;
        }
    } //nur der Moment des Touches
}