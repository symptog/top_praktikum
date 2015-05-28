package UserArea;

import processing.core.*;
import vialab.SMT.*;

public class Trackball extends Zone {

    private int origin_x, origin_y;
    private float crossX;
    private float crossY;
    //private PVector touchpoint;
    private UserArea parentzone;

    public Trackball(int i, int i1, int i2, int i3) {
        super(i, i1, i2, i3);

        origin_x = i;
        origin_y = i1;

        //this.parent = (UserArea)this.getParent();
        //this.crossX = parent.getCrossHeight()/2;
        //this.crossY = parent.getCrossHeight()/2;

    }

    @Override
    public void draw() {
        ellipseMode(CORNER);
        stroke(255,0,0);//Rand des Objektes festlegen
        fill(0,255,0);//Füllfarbe des Objektes festlegen
        ellipse(0 ,0 , this.getHeight()/2, this.getHeight()/2);

        /*
        float x = this.touchpoint.x - this.crossX;
        float y = this.touchpoint.y - this.crossY;
        PVector norm = new PVector(x, y);
        norm.normalize();

        if ()
        */

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
        /*
        Touch[] touches = this.getTouches();
        Touch touch1 = touches[0];
        this.touchpoint = touch1.getPositionVector();
        /*/

        this.parentzone = (UserArea)this.getParent();

        PVector v1 = new PVector(this.getWidth()/2, this.getHeight()/2);
        PVector v2 = new PVector(this.parentzone.getCrossHeight()/2, this.parentzone.getCrossHeight()/2);

        float r = Math.abs(this.parentzone.getCrossHeight() / 2 - this.getHeight()/2);

        float dist = v1.dist(v2);

        if(Math.abs(dist) <= r)
            rst();

    }
}
