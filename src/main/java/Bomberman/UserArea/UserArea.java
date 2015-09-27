package Bomberman.UserArea;

//http://vialab.science.uoit.ca/smt/reference.php

import processing.core.*;
import vialab.SMT.*;
import Bomberman.*;

public class UserArea extends Zone {
    private Integer col;
    private PShape text = null, s = null;
    private boolean active = false;
    private Zone trackball = null;
    private Zone bombbutton = null;
    private float crossWidth;
    private float crossHeight;
    private int maxpos;
    private PVector touchpoint, v;
    private Integer org_x, org_y;
    private Float zero_x, zero_y ;
    private float abstand, rel_x, rel_y, last_rel_x, last_rel_y;
    protected Bomberman bomberman;
    private boolean init = false;

    public float getCrossWidth() {
        return crossWidth;
    }

    public float getCrossHeight() {
        return crossHeight;
    }

    public UserArea(int i, int i1, int i2, int i3, Integer col, Bomberman b) {
        super(i, i1, i2, i3);
        this.col = col;
        this.crossWidth = i2/5*4;
        this.crossHeight = i3/5*4;
        this.bomberman = b;
        //System.out.print("i: " + i + " " + "i1: " + i1 + " " + "i2: " + i2 + " " + "i3: " + i3 + " " + "crossWidth: " + crossWidth + " " + "crossHeight: " + crossHeight + "\n");
    }

    @Override
    public void draw() {

        maxpos = (int)this.getCrossHeight()/4;



        if(this.init) {

            kreuz((this.getWidth()/4*3 + this.getWidth() / 12), (this.getHeight()/2));
            bombe((this.getWidth() / 4 - this.getWidth() / 12), (this.getHeight() / 2));
            anzeige((this.getWidth() / 2 - this.getWidth() / 12), (this.getHeight()));

            this.touchpoint = new PVector(trackball.getX(), trackball.getY());  //get liefert globale Werte

            if(this.org_x == null) {            //Startposition des Trackballs speichern
                this.org_x = trackball.getX();
            }
            if(this.org_y == null) {
                this.org_y = trackball.getY();
            }

            //Todo: in den grenzen bleiben, mittelpunkt der box benutzen, nicht die ecke (radius addieren)
            abstand =  touchpoint.dist(new PVector(this.org_x, this.org_y));        //Abstand ermitteln klappt auch
            if (abstand > maxpos) {
                if (org_y<600) this.v = new PVector(org_x - touchpoint.x, org_y - touchpoint.y);    //Test ob obere Hälfte
                else this.v = new PVector(touchpoint.x-org_x, touchpoint.y-org_y);                  //oder untere Hälfte
                this.v.normalize();
                trackball.setX((this.getWidth() / 4 * 3) - (int)this.getCrossHeight()/4 + v.x * maxpos);       //Trackball lokal setzen
                trackball.setY((this.getHeight() / 2) - (int)this.getCrossHeight()/4 + v.y * maxpos);
                //System.out.print("+x: " + v.x + " +y: " + v.y + "\n");
            }
            touchpoint.rotate(45.0f);
            if (this.zero_x == null)
                this.zero_x = touchpoint.x;
            if (this.zero_y == null)
                this.zero_y = touchpoint.y;
            last_rel_x = rel_x;
            rel_x = touchpoint.x-this.zero_x;
            last_rel_y = rel_y;
            rel_y = touchpoint.y-this.zero_y;
            //if (last_rel_x != rel_x || last_rel_y != rel_y) {
                //System.out.print("+x: " + rel_x + " +y: " + rel_y + "\n");
                if (rel_x < 0 && rel_y < 0) {
                    bomberman.moveLeft();
                    bomberman.setDirection(Bomberman.LEFT);
                }
                else if (rel_x > 0 && rel_y < 0) {
                    bomberman.moveUp();
                    bomberman.setDirection(Bomberman.UP);
                }
                else if (rel_x > 0 && rel_y > 0) {
                    bomberman.moveRight();
                    bomberman.setDirection(Bomberman.RIGHT);
                }
                else if (rel_x < 0 && rel_y > 0) {
                    bomberman.moveDown();
                    bomberman.setDirection(Bomberman.DOWN);
                }
                else {
                    bomberman.setDirection(Bomberman.STOP);
                }
            if(!bomberman.isAlive())
            {

                background(10,128,256);
                stroke(0,0,0);
                fill(20, 50, 256);
                rect(0,0,this.getWidth(),this.getHeight());
                textAlign(CENTER);
                fill(0);
                text("Leider verloren", this.getWidth()/2, (this.getHeight()-6)/3-(this.getHeight()-6)/12);

            }
            //}
        }


    }

    //touch method
    @Override
    public void touch() {
        if (!this.init) {
            this.trackball = new Trackball((this.getWidth() / 4*3)-(int)this.getCrossHeight()/4, (this.getHeight() / 2)-(int)this.getCrossHeight()/4, (int)this.getCrossHeight()/2, (int)this.getCrossHeight()/2);  //lokal
            this.add(trackball);
            this.bombbutton = new Bombbutton(this, (this.getWidth() / 4), (this.getHeight() / 2), (int)this.getCrossHeight()*3/4, (int)this.getCrossHeight()*3/4);
            this.add(bombbutton);
            this.bomberman.setAlive();
            this.bomberman.render();
            this.init = true;

        }

    } //touch down method
    @Override
    public void touchDown(Touch touch){} //touch up method
    @Override
    public void touchUp(Touch touch){} //touch moved method
    @Override
    public void touchMoved(Touch touch){}

    private void kreuz(int x, int y) {
        background(128, 128,128);//legt Hintergrundfarbe fest - wenn nur einmal gezeichnet, dann läuft der Bildschirm voll
        stroke(0,0,0);//legt Randfarbe nachfolgender Formen fest
        fill(col);//legt Füllfarbe nachfolgender Formen fest
        ellipseMode(CENTER);
        ellipse(x, y, this.getCrossHeight(), this.getCrossHeight());//Position, Position, Breite, Höhe                //Außenkreis
        int kreuz = (int) Math.floor( Math.sqrt( this.getCrossHeight()/2* this.getCrossHeight())/2);                //
        line(x - kreuz, y - kreuz, 1, x + kreuz, y+kreuz,1);                                                //loru
        line(x-kreuz, y+kreuz,1,x+kreuz, y-kreuz,1);                                                //luro
        ellipse(x, y, this.getHeight()/4*0.75f, this.getHeight()/4*0.75f);                                          //Innenkreis schwarz
    }

    private void bombe(int x, int y) {
        stroke(0, 0, 0);//legt Randfarbe nachfolgender Formen fest
        fill(192, 0, 0);//legt Füllfarbe nachfolgender Formen fest
        ellipseMode(CENTER);
        ellipse(x, y, this.getCrossHeight(), this.getCrossHeight());        //Bombknopf
        textAlign(CENTER);  textSize(20);   fill(0);
        //text("Bombe", this.getCrossHeight(), this.getCrossHeight());      //vielleicht bekommst du ja eine zentrale Positionierung hin ;)
    }

    private void anzeige(int x, int y) {
        stroke(0, 0, 0);//legt Randfarbe nachfolgender Formen fest
        fill(255, 255, 255);//legt Füllfarbe nachfolgender Formen fest
        rect(x, 3, this.getWidth() / 6, y - 6);         //Anzeigen
        line(x, y/3, x+this.getWidth()/6, y/3);    //Trennlinie 1
        line(x, 2*y/3, x+this.getWidth()/6, 2*y/3);    //Trennlinie 2
        textAlign(CENTER);
        fill(0);
        text(bomberman.getBombcount()+" / "+bomberman.getMaxbombcount(), this.getWidth()/2, (y-6)/3-(y-6)/12);
        text(bomberman.getRange(), this.getWidth()/2, (y-6)/3*2-(y-6)/12);
        text(bomberman.getLife(), this.getWidth()/2, (y-6)-(y-6)/12);
    }

}
