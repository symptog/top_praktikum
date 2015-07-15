package Bomberman.UserArea;

import vialab.SMT.Touch;
import vialab.SMT.Zone;

/**
 * Created by tobias on 15.07.15.
 */
public class Bombbutton extends Zone {

    private UserArea parent;

    public Bombbutton(UserArea parent, int i, int i1, int i2, int i3) {
        super(i, i1, i2, i3);
        this.parent = parent;
    }

    @Override
    public void draw() {
        stroke(0, 0, 0);//legt Randfarbe nachfolgender Formen fest
        fill(192, 0, 0);//legt Füllfarbe nachfolgender Formen fest
        ellipseMode(CENTER);
        ellipse(0, 0, this.getHeight(), this.getHeight());        //Bombknopf
        textAlign(CENTER);  textSize(20);   fill(0);
    }
    //touch method
    @Override
    public void touch() {} //gedrückte halten
    @Override
    public void touchDown(Touch touch){
        parent.bomberman.dropBomb();
    } //nur der Moment des Touches
    @Override
    public void touchUp(Touch touch){} //touch moved method

}
