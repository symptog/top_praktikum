package Bomberman;

import processing.core.*;

public class Block {
    private Float posX, posY; // Position
    private Boolean visable = true;
    private Integer type = Items.EMPTY;
    private Boolean covered = false;

    private PShape block;

    private Field p;
    private Blocks b;

    Block(Field p, Blocks b, float x, float y) {
        block = p.createShape();
        this.posX = x;
        this.posY = y;
        this.p = p;
        this.b = b;
    }

    public Boolean getVisable() {
        return visable;
    }

    public void setVisable(Boolean visable) {
        this.visable = visable;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
        this.render();
    }

    public Boolean getCovered() {
        return covered;
    }

    public void setCovered(Boolean covered) {
        this.covered = covered;
        this.render();
    }

    public Boolean isWalkable() {
        return (type != Items.STATIC && type != Items.BLOCK && !covered&& type != Items.BOMBE);
    }

    public Float getPosX() {
        return posX;
    }

    public Float getPosY() {
        return posY;
    }

    private void drawBlock(PImage img) {
        block = p.createShape();
        block.beginShape();
        block.noStroke();
        block.fill(220f);
        block.texture(img);
        block.textureMode(PShape.NORMAL);
        block.vertex(0, 0, 0, 0);
        block.vertex(p.getBlock_size(), 0, 1, 0);
        block.vertex(p.getBlock_size(), p.getBlock_size(), 1, 1);
        block.vertex(0, p.getBlock_size(), 0, 1);
        block.endShape(PShape.CLOSE);
    }

    public void render() {

        PImage img;

        if (this.type == Items.BLOCK || this.covered) {
            img = b.getBlock();
        } else if (this.type == Items.STATIC) {
            img = b.getStatic_block();
        } else if (this.type == Items.EMPTY) {
            this.covered = false;
            img = null;
        } else if (this.type == Items.COUNTUPGRADE) {
            img = b.getanzupgrade();
        } else if (this.type == Items.LIFEUPGRADE) {
            img = b.getlifeupgrade();
        } else if (this.type == Items.RANGEUPGRADE) {
            img = b.getrangeupgrade();
        } else if (this.type == Items.SPEEDUPGRADE) {
            img = b.getspeedupgrade();
        } else {
            img = null; //
        }

        this.drawBlock(img);

    }

    public void display() {
        this.block.setVisible(this.visable);
        p.shape(this.block, this.posX, this.posY);
    }

}
