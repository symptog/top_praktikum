package Bomberman.UserArea;

import vialab.SMT.Touch;
import vialab.SMT.Zone;
import vialab.SMT.*;

public class menu extends Zone {

    private UserArea parent;
    private boolean pop = false;

    public menu(UserArea parent, int i, int i1, int i2, int i3) {
        super(i, i1, i2, i3);
        this.parent = parent;
    }
    @Override
    public void draw() {
        stroke(0, 0, 0);//legt Randfarbe nachfolgender Formen fest
        fill(192, 0, 0);//legt Füllfarbe nachfolgender Formen fest
        rectMode(CORNER);
        rect(-16, -15, this.getHeight()*5/2, this.getHeight()-3);
        textAlign(CENTER);  textSize(15);   fill(0);
        text("Menue", 25, 5);
        if (pop) {
            drawMenu();
            drawdialog(5);
        }
    }
    public void drawMenu() {
        // Neues Spiel
        stroke(0, 0, 0);//legt Randfarbe nachfolgender Formen fest
        fill(192, 50, 0);//legt Füllfarbe nachfolgender Formen fest
        rectMode(CORNER);
        rect(-16, -47, this.getHeight()*5/2, this.getHeight()-3);
        textAlign(CENTER);  textSize(15);   fill(0);
        text("Neu", 25, - 27);

        //Beenden
        stroke(0, 0, 0);//legt Randfarbe nachfolgender Formen fest
        fill(192, 50, 0);//legt Füllfarbe nachfolgender Formen fest
        rectMode(CORNER);
        rect(-16, -79, this.getHeight()*5/2, this.getHeight()-3);
        textAlign(CENTER);  textSize(15);   fill(0);
        text("Beenden", 25, -60);

        // Aufgeben
        stroke(0, 0, 0);//legt Randfarbe nachfolgender Formen fest
        fill(192, 50, 0);//legt Füllfarbe nachfolgender Formen fest
        rectMode(CORNER);
        rect(-16, -111, this.getHeight()*5/2, this.getHeight()-3);
        textAlign(CENTER);  textSize(15);   fill(0);
        text("Aufgeben", 25, -90);
    }
    public void drawdialog(int auswahl){
        //ganzes Feld
        stroke(0, 0, 0);//legt Randfarbe nachfolgender Formen fest
        fill(192, 50, 0);//legt Füllfarbe nachfolgender Formen fest
        rectMode(CORNER);
        rect(this.getWidth()*3, -this.getHeight()*3, this.getHeight()*5, this.getHeight()*3);
        textAlign(CENTER);  textSize(15);   fill(0);
        text("Willst du wirklich \n Aufgegeben ?", this.getWidth()*6, -this.getHeight()*2);

        // aufgeben - ja
        stroke(0, 0, 0);//legt Randfarbe nachfolgender Formen fest
        fill(192, 100, 0);//legt Füllfarbe nachfolgender Formen fest
        rectMode(CORNER);
        rect(this.getWidth()*3, -this.getHeight(), this.getHeight()*2, this.getHeight());
        textAlign(CENTER);  textSize(15);   fill(0);
        text("Ja", this.getWidth()*4, -this.getHeight()+22);

        // aufgeben - nein
        stroke(0, 0, 0);//legt Randfarbe nachfolgender Formen fest
        fill(192, 100, 0);//legt Füllfarbe nachfolgender Formen fest
        rectMode(CORNER);
        rect(this.getWidth()*7, -this.getHeight(), this.getHeight()*2, this.getHeight());
        textAlign(CENTER);  textSize(15);   fill(0);
        text("Nein", this.getWidth()*8, -this.getHeight()+22);
    }


    //touch method
    @Override
    public void touch() {}
    @Override
    public void touchDown(Touch touch){
        pop=!pop;
    } //nur der Moment des Touches
    @Override
    public void touchUp(Touch touch){} //touch moved method

}