package Bomberman.UserArea;
/*
import vialab.SMT.*;

public class Menu extends Zone {


    public Menu(UserArea parent, int i, int i1, int i2, int i3) {
        super(i, i1, i2, i3);
        this.parent = parent;

        PieMenuZone menu = new PieMenuZone("PieMenu", 400, 400, 355);
        add(menu);
        menu.add("Beenden");
        menu.add("NeuesSpiel");
        menu.add("Aufgeben");

        menu.addSubmenu("Beenden","Ja");
        menu.addSubmenu("Beenden","Nein");
        menu.addSubmenu("Aufgeben","Ja");
        menu.addSubmenu("Aufgeben","Nein");
        menu.addSubmenu("NeuesSpiel","Ja");
        menu.addSubmenu("NeuesSpiel","Nein");

        ButtonZone b = new ButtonZone("ShowHide","Menu");
        //b.deactivated = true;
        add(b);
    }

    void pressShowHide() {
        PieMenuZone m = SMT.get("PieMenu", PieMenuZone.class);
        m.setVisible(!m.isVisible());
    }

    @Override
    public void draw() {
        background(0);
    }

}
*/

import vialab.SMT.*;

public class Menu extends Zone {


    private UserArea parent;
    private boolean pop = false;
    private boolean popclose = false;
    private boolean popnew = false;
    private boolean popsurr = false;
    private ButtonZone button_new;
    private ButtonZone button_surr;
    private ButtonZone button_close;
    private ButtonZone button_yes;
    private ButtonZone button_no;
    private ButtonZone button_check;

    public Menu(UserArea parent, int i, int i1, int i2, int i3) {
        super(i, i1, i2, i3);
        this.parent = parent;

        this.button_close = new ButtonZone( "close", 0,-this.getHeight(),this.getWidth(),this.getHeight(), "Beenden");
        this.button_new = new ButtonZone( "new", 0,-this.getHeight()*2,this.getWidth(),this.getHeight(), "Neustarten");
        this.button_surr = new ButtonZone( "surr", 0,-this.getHeight()*3,this.getWidth(),this.getHeight(), "Aufgeben");

        this.button_check = new ButtonZone ("check", this.getWidth(),-this.getHeight()*3,this.getWidth()*2,this.getHeight()*3, "Willst du wirklich?");
        this.button_yes = new ButtonZone( "yes", this.getWidth(),0,this.getWidth(),this.getHeight(), "Ja");
        this.button_no = new ButtonZone( "no",  this.getWidth()*2,0,this.getWidth(),this.getHeight(), "Nein");

    }

    @Override
    public void draw() {
        stroke(0, 0, 0);
        fill(192, 0, 0);
        rectMode(CORNER);
        rect(0, 0, this.getWidth(), this.getHeight());
        textAlign(CENTER);  textSize(15);   fill(0);
        text("Menu", this.getWidth() / 2, this.getHeight() / 3 * 2);

    }


    //touch method
    @Override
    public void touch() {}
    @Override
    public void touchDown(Touch touch){

        this.pop=!this.pop;
        if (this.pop) {
            this.add(button_close);
            this.add(button_new);
            this.add(button_surr);

        }

        else {
            clearChildren();
        }
    } //nur der Moment des Touches
    @Override
    public void touchUp(Touch touch){} //touch moved method

    void pressclose(){
        System.out.print("Beenden");
        this.popclose=!this.popclose;
        if(this.popclose){
            if (this.popsurr){
                this.remove(button_check);
            }
            this.add(button_yes);
            this.add(button_no);
            this.add(button_check);
        }

    }
    void presssurr(){
        System.out.print("Aufgeben");
        this.popsurr=!this.popsurr;
        this.add(button_yes);
        this.add(button_no);
        this.add(button_check);
    }
    void pressnew(){
        System.out.print("Neustarten");
        this.popnew=!this.popnew;
        this.add(button_yes);
        this.add(button_no);
        this.add(button_check);
    }
}

