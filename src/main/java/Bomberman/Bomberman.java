package Bomberman;

import processing.core.PImage;
import processing.core.PShape;

public class Bomberman {
    private String id;
    private Integer blockX, blockY;
    private Float x, y;
    private Field p;
    private Boolean inverted = false;
    PImage img;
    PShape man;

    public Bomberman(Field p, String id, Integer blockX, Integer blockY) {
        this.p = p;
        this.id = id;
        this.blockX = blockX;
        this.blockY = blockY;
    }

    public Bomberman(Field p, String id, Integer blockX, Integer blockY, Boolean inverted) {
        this.p = p;
        this.id = id;
        this.blockX = blockX;
        this.blockY = blockY;
        this.inverted = inverted;
    }

    public void setInverted(Boolean inverted) {
        this.inverted = inverted;
    }

    public void render() {
        img = p.loadImage(getClass().getResource("/img/" + id + ".png").toString());
        man = p.createShape();
        man.beginShape();
        man.noStroke();
        man.fill(255);
        man.texture(img);
        man.textureMode(PShape.NORMAL);
        man.vertex(0, 0, 0, 0);
        man.vertex(p.getBlock_size(), 0, 1, 0);
        man.vertex(p.getBlock_size(), p.getBlock_size(), 1, 1);
        man.vertex(0, p.getBlock_size(), 0, 1);
        man.endShape(PShape.CLOSE);
        if (inverted) {
            p.pushMatrix();
            man.rotateX(p.PI);
            man.translate(0, -p.getBlock_size());
            p.popMatrix();
        }

        this.x = p.getBlock(blockX, blockY).getPosX();
        this.y = p.getBlock(blockX, blockY).getPosY();

    }
    public void display() {
        updateBlock();
        p.shape(this.man, this.x, this.y, );
    }

    public void moveLeft() {
        if (blockX > 0 && p.getBlock(blockX, blockY).isWalkable() && x >= p.getBlock(blockX, blockY).getPosX()) {
                this.x -= 1;
        }
    }
    public void moveRight() {
        if (blockX < p.getHorizontal_blocks()-1 && p.getBlock(blockX+1, blockY).isWalkable() && x <= p.getBlock(blockX, blockY).getPosX()+p.getBlock_size()) {
                this.x += 1;
        }
    }
    public void moveUp() {
        if (blockY > 0 && p.getBlock(blockX, blockY).isWalkable() && y >= p.getBlock(blockX, blockY).getPosY()) {
                this.y -= 1;
        }
    }
    public void moveDown() {
        if (blockY < p.getVertical_blocks()-1 && p.getBlock(blockX, blockY+1).isWalkable() && y <= p.getBlock(blockX, blockY).getPosY()+p.getBlock_size()) {
                this.y += 1;
        }
    }
    public void dropBomb() {
        Block bomb_block = p.getBlock(blockX, blockY);
        bomb_block.setType(Items.BOMBE);
    }
    public void die() {}

    private void updateBlock() {
        blockX = (int) (x-p.getX_offset())/p.getBlock_size();
        blockY = (int) (y-p.getY_offset())/p.getBlock_size();
    }

}
