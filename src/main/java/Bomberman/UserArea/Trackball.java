package Bomberman.UserArea;

import vialab.SMT.*;

public class Trackball extends Zone {

    private int origin_x, origin_y;
    private Integer col;

    public Trackball(int i, int i1, int i2, int i3, int col) {
        super(i, i1, i2, i3);
        this.origin_x = i;
        this.origin_y = i1;
        this.col = col;
    }

    @Override
    public void draw() {
        stroke(0,220,0);//Rand des Objektes festlegen
        //fill(col);//F\u00fcllfarbe des Objektes festlegen
        fill(0,255,0);
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
        setX(origin_x);
        setY(origin_y);
    } //touch moved method
    /*
    @Override
    public void touchMoved(Touch touch){
        rst();
    }
    */
}
