package Bomberman;

import processing.core.PImage;
import java.util.Random;

public class Blocks {

    private Field p;
    private Block[][] blocks;
    private PImage block;
    private PImage static_block;
    private PImage block_item;
    private PImage anzupgrade;
    private PImage lifeupgrade;
    private PImage rangeupgrade;
    private PImage speedupgrade;
    private Integer horizontal_blocks;
    private Integer vertical_blocks;
    

    public Blocks(Field p) {
        this.p = p;
        this.horizontal_blocks = p.getHorizontal_blocks();
        this.vertical_blocks = p.getVertical_blocks();
        this.blocks = new Block[horizontal_blocks][vertical_blocks];
        this.block = p.getImage(p.getProp("block_img"));
        this.static_block = p.getImage(p.getProp("static_block_img"));
        this.anzupgrade = p.getImage(p.getProp("anzupgrade_img"));
        this.lifeupgrade = p.getImage(p.getProp("lifeupgrade_img"));
        this.rangeupgrade = p.getImage(p.getProp("rangeupgrade_img"));
        this.speedupgrade = p.getImage(p.getProp("speedupgrade_img"));
        this.block_item = p.getImage(p.getProp("item_img"));
    }

    public Block getBlocks(Integer x, Integer y) {
        return blocks[x][y];
    }

    public PImage getBlock() {
        return block;
    }

    public PImage getStatic_block() {
        return static_block;
    }
    public PImage getanzupgrade() {
        return anzupgrade;
    }
    public PImage getlifeupgrade() {
        return lifeupgrade;
    }
    public PImage getrangeupgrade() {
        return rangeupgrade;
    }
    public PImage getspeedupgrade() {
        return speedupgrade;
    }

    public PImage getBlock_item() {
        return block_item;
    }

    public void render() {
        Random rand = new Random();
        int zahl;
        for (int i = 0; i < horizontal_blocks; i++) {
            for (int j = 0; j < vertical_blocks; j++) {
                this.blocks[i][j] = new Block(p, this, i * p.getBlock_size() + p.getX_offset(), j * p.getBlock_size() + p.getY_offset());
                if (i % 2 == 1 && j % 2 == 1) {
                    this.blocks[i][j].setType(Items.STATIC);
                } else {
                    zahl= rand.nextInt(60);
                    if(zahl<12) {
                        if (zahl == 0)
                            this.blocks[i][j].setType(Items.LIFEUPGRADE);
                        else if (zahl > 0 && zahl < 5)
                            this.blocks[i][j].setType(Items.COUNTUPGRADE);
                        else if (zahl > 4 && zahl < 9)
                            this.blocks[i][j].setType(Items.RANGEUPGRADE);
                        else if (zahl > 8 && zahl < 13)
                            this.blocks[i][j].setType(Items.SPEEDUPGRADE);
                        //this.blocks[i][j].setCovered(true);
                    }
                    else
                        this.blocks[i][j].setType(Items.BLOCK);
                }
            }
        }



        /*
        for (int i = 0; i < horizontal_blocks; i++) {
            for (int j = 0; j < vertical_blocks; j++) {
                this.blocks[i][j] = new Block(p, this, i * p.getBlock_size() + p.getX_offset(), j * p.getBlock_size() + p.getY_offset());
                if (i % 2 == 1 && j % 2 == 1) {
                    this.blocks[i][j].setType(Items.STATIC);
                } else {
                    this.blocks[i][j].setType(Items.BLOCK);
                }
            }
        }

        for (int i = 0; i < horizontal_blocks; i++) {
            this.blocks[i][0].setType(Items.EMPTY);
        }

        this.blocks[6][0].setType(Items.COUNTUPGRADE);
        this.blocks[6][0].setCovered(false);

        this.blocks[8][0].setType(Items.LIFEUPGRADE);
        this.blocks[8][0].setCovered(false);

        this.blocks[10][0].setType(Items.RANGEUPGRADE);
        this.blocks[10][0].setCovered(false);

        this.blocks[12][0].setType(Items.SPEEDUPGRADE);
        this.blocks[12][0].setCovered(false);

        this.blocks[8][1].setType(Items.EMPTY);
        this.blocks[8][2].setType(Items.EMPTY);
        this.blocks[10][1].setType(Items.EMPTY);
        this.blocks[10][2].setType(Items.EMPTY);

        */


        //oben links
        this.blocks[0][0].setType(Items.EMPTY);
        this.blocks[0][0].setCovered(false);
        this.blocks[0][1].setType(Items.EMPTY);
        this.blocks[0][1].setCovered(false);
        this.blocks[1][0].setType(Items.EMPTY);
        this.blocks[1][0].setCovered(false);
        //unten links
        this.blocks[0][vertical_blocks - 2].setType(Items.EMPTY);
        this.blocks[0][vertical_blocks - 2].setCovered(false);
        this.blocks[0][vertical_blocks - 1].setType(Items.EMPTY);
        this.blocks[0][vertical_blocks - 1].setCovered(false);
        this.blocks[1][vertical_blocks - 1].setType(Items.EMPTY);
        this.blocks[1][vertical_blocks - 1].setCovered(false);
        //unten rechts
        this.blocks[horizontal_blocks - 1][vertical_blocks - 2].setType(Items.EMPTY);
        this.blocks[horizontal_blocks - 1][vertical_blocks - 2].setCovered(false);
        this.blocks[horizontal_blocks - 1][vertical_blocks - 1].setType(Items.EMPTY);
        this.blocks[horizontal_blocks - 1][vertical_blocks - 1].setCovered(false);
        this.blocks[horizontal_blocks - 2][vertical_blocks - 1].setType(Items.EMPTY);
        this.blocks[horizontal_blocks - 2][vertical_blocks - 1].setCovered(false);
        //oben rechts
        this.blocks[horizontal_blocks - 1][0].setType(Items.EMPTY);
        this.blocks[horizontal_blocks - 1][0].setCovered(false);
        this.blocks[horizontal_blocks - 2][0].setType(Items.EMPTY);
        this.blocks[horizontal_blocks - 2][0].setCovered(false);
        this.blocks[horizontal_blocks - 1][1].setType(Items.EMPTY);
        this.blocks[horizontal_blocks - 1][1].setCovered(false);



        for (int i = 0; i < horizontal_blocks; i = i + 1) {
            for (int j = 0; j < vertical_blocks; j = j + 1) {
                this.blocks[i][j].render();
            }
        }


    }

    public void draw() {
        for (int i = 0; i < horizontal_blocks; i = i + 1) {
            for (int j = 0; j < vertical_blocks; j = j + 1) {
                this.blocks[i][j].display();
            }
        }
    }

    public static int randomInt(int low, int high) {
        return (int) (Math.random() * (high - low) + low);
    }
}
