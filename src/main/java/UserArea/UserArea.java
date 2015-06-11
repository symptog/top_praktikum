package UserArea;

import processing.core.*;
import vialab.SMT.*;

public class UserArea extends Zone {
    private Integer col;
    private PShape text = null, s = null;
    private boolean active = false;
    private Zone trackball = null;
    private float crossWidth;
    private float crossHeight;
    private int maxpos;
    private PVector touchpoint;
    private Integer org_x;
    private Integer org_y;

    public float getCrossWidth() {
        return crossWidth;
    }

    public float getCrossHeight() {
        return crossHeight;
    }

    public UserArea(int i, int i1, int i2, int i3, Integer col) {
        super(i, i1, i2, i3);
        this.col = col;
        this.crossWidth = i2/5*4;
        this.crossHeight = i3/5*4;
    }

    @Override
    public void draw() {

        int joystickdm = this.getHeight()/2;
        maxpos = (int)this.getCrossHeight() - joystickdm;

        kreuz((this.getWidth() / 4*3), (this.getHeight() / 2));

        if(trackball != null) {
            this.touchpoint = new PVector(trackball.getX(), trackball.getY());
            System.out.print("x: " + trackball.getX() + " ");
            System.out.print("y: " + trackball.getY() + "\n");

            if(this.org_x == null) {
                this.org_x = trackball.getX();
            }
            if(this.org_y == null) {
                this.org_y = trackball.getY();
            }
            // Todo: in den grenzen bleiben, mittelpunkt der box benutzen, nicht die ecke (radius addieren)
            if (touchpoint.dist(new PVector(this.org_x, this.org_y)) > maxpos) {
                PVector v = new PVector(0 - touchpoint.x, 0 - touchpoint.y);
                v.normalize();
                trackball.setX(0 - v.x * maxpos);
                trackball.setY(0 - v.y * maxpos);
            }

        }

    }

    //touch method
    @Override
    public void touch() {
        if (trackball == null) {
            this.trackball = new Trackball((this.getWidth() / 4*3)-(int)this.getCrossHeight()/4, (this.getHeight() / 2)-(int)this.getCrossHeight()/4, (int)this.getCrossHeight()/2, (int)this.getCrossHeight()/2);
            this.add(trackball);
        }
    } //touch down method
    @Override
    public void touchDown(Touch touch){} //touch up method
    @Override
    public void touchUp(Touch touch){} //touch moved method
    @Override
    public void touchMoved(Touch touch){}

    private void kreuz(int x, int y) {
        background(128,128,128);//legt Hintergrundfarbe fest - wenn nur einmal gezeichnet, dann läuft der Bildschirm voll
        stroke(0,0,0);//legt Randfarbe nachfolgender Formen fest
        fill(255,255,255);//legt Füllfarbe nachfolgender Formen fest
        ellipseMode(CENTER);
        ellipse(x, y, this.getCrossHeight(),  this.getCrossHeight());//Position, Position, Breite, Höhe                //Außenkreis
        int kreuz = (int) Math.floor( Math.sqrt( this.getCrossHeight()/2* this.getCrossHeight())/2);                //
        line(x-kreuz, y-kreuz,x+kreuz, y+kreuz);                                                //loru
        line(x-kreuz, y+kreuz,x+kreuz, y-kreuz);                                                //luro
        ellipse(x, y, this.getHeight()/4*0.75f, this.getHeight()/4*0.75f);                                          //Innenkreis schwarz
    }

}
