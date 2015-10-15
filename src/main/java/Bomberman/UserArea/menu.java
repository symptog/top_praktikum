package Bomberman.UserArea;

import vialab.SMT.Touch;
import vialab.SMT.Zone;

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
        fill(192, 0, 0);//legt F�llfarbe nachfolgender Formen fest
        rectMode(CORNER);
        rect(-16, -16, this.getHeight()*5/2, this.getHeight()-3);
        textAlign(CENTER);  textSize(15);   fill(0);
        text("Menu", 25, 3);
        if (pop) {
            drawMenu();
            drawDialog();
        }
    }
    public void drawMenu() {
        // Neu
        stroke(0, 0, 0);
        fill(192, 50, 0);
        rectMode(CORNER);
        rect(-16, -48, this.getHeight()*5/2, this.getHeight()-3);
        textAlign(CENTER);  textSize(15);   fill(0);
        text("Neu", 25, -27);

        //Beenden
        stroke(0, 0, 0);
        fill(192, 50, 0);
        rectMode(CORNER);
        rect(-16, -80, this.getHeight()*5/2, this.getHeight()-3);
        textAlign(CENTER);  textSize(15);   fill(0);
        text("Beenden", 25, -61);

        // Aufgeben
        stroke(0, 0, 0);
        fill(192, 50, 0);
        rectMode(CORNER);
        rect(-16, -113, this.getHeight()*5/2, this.getHeight()-3);
        textAlign(CENTER);  textSize(15);   fill(0);
        text("Aufgeben", 25, -95);
    }
    public void drawDialog() {

        // Dialog Feld
        stroke(0, 0, 0);
        fill(200, 100, 50);
        rectMode(CORNER);
        rect(this.getWidth() * 3, -this.getHeight() * 5 / 2 - 5, this.getWidth() * 6, this.getHeight() * 3);
        textAlign(CENTER);  textSize(15);   fill(0);
        text("Willst du wirklich \n Aufgeben?", this.getWidth() * 6, -this.getHeight() * 2);

        // Ja
        stroke(0, 0, 0);
        fill(192, 150, 0);
        rectMode(CORNER);
        rect(this.getWidth() * 3, -this.getHeight() + 12, this.getWidth() * 2, this.getHeight());
        textAlign(CENTER);  textSize(15);   fill(0);
        text("Ja", this.getWidth()*4, -this.getHeight()*1/8);

        // Nein
        stroke(0, 0, 0);
        fill(191, 150, 0);
        rectMode(CORNER);
        rect(this.getWidth() * 7, -this.getHeight() + 12, this.getWidth() * 2, this.getHeight());
        textAlign(CENTER);  textSize(15);   fill(0);
        text("Nein", this.getWidth()*8, -this.getHeight()*1/8);
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