package Bomberman.UserArea.MenuButtons;

import Bomberman.Bomberman;
import vialab.SMT.Touch;
import vialab.SMT.Zone;

public class Submenu extends Zone {

    protected Bomberman bomberman;
    private boolean pop = false;
    private Integer side;
    private String button_name;
    private Checkbox checkbox_new;
    private Checkbox checkbox_close;
    private Checkbox checkbox_surr;

    public Submenu(int i, int i1, int i2, int i3, String name,  int position, Bomberman b) {
        super(i, i1, i2, i3);
        this.side = position;
        this.button_name = name;
        this.bomberman = b;

        if (side == 1){
            // button id: 1= close 2=new 3= surrender
            checkbox_surr = new Checkbox(- 2*this.getWidth(),0,this.getWidth()*2,this.getHeight()*4,"aufgeben", this.side, bomberman,3);
            checkbox_new = new Checkbox(- 2*this.getWidth(),- this.getHeight(),this.getWidth()*2,this.getHeight()*4,"neu starten", this.side, bomberman,2);
            checkbox_close = new Checkbox(- 2*this.getWidth(),- 2*this.getHeight(),this.getWidth()*2,this.getHeight()*4,"beenden", this.side, bomberman,1);
        }
        else{
            checkbox_surr = new Checkbox(this.getWidth(),0,this.getWidth()*2,this.getHeight()*4,"aufgeben", this.side, bomberman,3);
            checkbox_new = new Checkbox(this.getWidth(),- this.getHeight(),this.getWidth()*2,this.getHeight()*4,"neu starten", this.side, bomberman,2);
            checkbox_close = new Checkbox(this.getWidth(),- 2*this.getHeight(),this.getWidth()*2,this.getHeight()*4,"beenden", this.side, bomberman,1);
        }


    }
    @Override
    public void drag(){}

    @Override
    public void draw() {
        stroke(0, 0, 0);
        fill(192, 0, 0);
        rectMode(CORNER);
        rect(0, 0, this.getWidth(), this.getHeight());
        textAlign(CENTER);  textSize(15);   fill(0);
        text("" + button_name + "", this.getWidth() / 2, this.getHeight() / 3 * 2);
    }
    @Override
    public void touchDown(Touch touch){
        this.pop=!this.pop;
        if (this.pop) {
            if (this.button_name == "Neues Spiel"){
                this.add(checkbox_new);
            }
            if (this.button_name == "Beenden"){
                this.add(checkbox_close);
            }
            if (this.button_name == "Aufgeben"){
                this.add(checkbox_surr);
            }

        }
        else{
            clearChildren();
        }

    } //nur der Moment des Touches
}