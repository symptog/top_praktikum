package UserArea;

import processing.core.*;
import vialab.SMT.*;
import java.lang.Math.*;

public class UserArea extends Zone {
    private Integer col;
    PShape text = null, s = null;
    boolean active = false;
    Zone trackball = null;


    public UserArea(int i, int i1, int i2, int i3, Integer col) {
        super(i, i1, i2, i3);
        this.col = col;
    }

    @Override
    public void draw() {
        /* Wenn null, dann Rendern
         * Verhindert, dass die Fläche mit jedem Frame neu erstellt wird
         */
        if (s == null) {
            System.out.println("s ist null");
            s = createShape();
            s.beginShape();
            s.stroke(this.col);
            s.strokeWeight(2);
            s.fill(255);
            s.vertex(0, 0, 0, 0);
            s.vertex(this.getWidth(), 0, 1, 0);
            s.vertex(this.getWidth(), this.getHeight(), 1, 1);
            s.vertex(0, this.getHeight(), 0, 1);
            s.endShape(CLOSE);
        }
        if (text == null && !active) {
            System.out.println("text ist null");
            text = loadShape("resources/svg/test.svg");
            text.setFill(this.col);
            text.scale(0.7f);
        }

        shape(s);
        beginShape(POLYGON);
        shapeMode(CENTER);
        if (!active) {
            shape(text, this.getWidth() / 2, this.getHeight() / 2);
        }

        if (active) {
            kreuz(this.getWidth()/3*2, this.getHeight()/2, this.getHeight(), this.getHeight());
        }
    }

    //touch method
    @Override
    public void touch() {
        this.active = true;
        if (trackball == null) {
            this.trackball = new Trackball((this.getWidth() / 3*2)-(this.getHeight() / 5), (this.getHeight() / 2)-(this.getHeight() / 5), this.getHeight() / 3 * 2, this.getHeight() / 3 * 2);
        }
        this.add(trackball);
    } //touch down method
    @Override
    public void touchDown(Touch touch){} //touch up method
    @Override
    public void touchUp(Touch touch){} //touch moved method
    @Override
    public void touchMoved(Touch touch){}

    private void kreuz(int x, int y, int width, int height) {
        background(128,128,128);//legt Hintergrundfarbe fest - wenn nur einmal gezeichnet, dann läuft der Bildschirm voll
        stroke(0,0,0);//legt Randfarbe nachfolgender Formen fest
        fill(255,255,255);//legt Füllfarbe nachfolgender Formen fest
        ellipseMode(CENTER);
        ellipse(x, y, height/5*4, height/5*4);//Position, Position, Breite, Höhe
        int kreuz = (int) java.lang.Math.floor( java.lang.Math.sqrt((height/5*4)/2*(height/5*4))/2);
        line(x-kreuz, y-kreuz,x+kreuz, y+kreuz);//StartPosition, StartPosition, EndPosition, EndPosition
        line(x-kreuz, y+kreuz,x+kreuz, y-kreuz);
        ellipse(x, y, height/4*0.75f, height/4*0.75f);
    }

}
