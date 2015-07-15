package Bomberman.UserArea;

import vialab.SMT.*;

public class Trackball extends Zone {

    public Trackball(int i, int i1, int i2, int i3) {
        super(i, i1, i2, i3);
    }

    @Override
    public void draw() {
        stroke(255,0,0);//Rand des Objektes festlegen
        fill(0,255,0);//F\u00fcllfarbe des Objektes festlegen
        ellipseMode(CENTER);
        ellipse(this.getHeight()/2, this.getHeight()/2, this.getHeight(), this.getHeight());
    }
    //touch method
    @Override
    public void touch() {
        drag();
    } //gedrückte halten
    @Override
    public void touchDown(Touch touch){} //nur der Moment des Touches
    @Override
    public void touchUp(Touch touch){
        //setX(origin_x);
        //setY(origin_y);
    } //touch moved method
    /*
    @Override
    public void touchMoved(Touch touch){
        rst();
    }
    */
}
