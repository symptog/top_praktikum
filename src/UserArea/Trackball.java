package UserArea;

import vialab.SMT.*;
import processing.core.*;

public class Trackball extends Zone {

    private int origin_x, origin_y;

    public Trackball(int i, int i1, int i2, int i3) {
        super(i, i1, i2, i3);

        origin_x = i;
        origin_y = i1;
    }

    @Override
    public void draw() {
        ellipseMode(CORNER);
        stroke(255,0,0);//Rand des Objektes festlegen
        fill(0,255,0);//Füllfarbe des Objektes festlegen
        ellipse(0,0,50,50);
    }

    //touch method
    @Override
    public void touch() {} //touch down method
    @Override
    public void touchDown(Touch touch){} //touch up method
    @Override
    public void touchUp(Touch touch){
        setX(origin_x);
        setY(origin_y);
    } //touch moved method
    @Override
    public void touchMoved(Touch touch){
        rst();
    }
}
