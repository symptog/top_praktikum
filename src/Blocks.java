import processing.core.PImage;

public class Blocks {

    private Field p;
    private Block[][] blocks;
    private PImage block;
    private PImage static_block;
    private PImage block_item;
    private Integer horizontal_blocks;
    private Integer vertical_blocks;
    

    public Blocks(Field p) {
        this.p = p;
        this.horizontal_blocks = p.getHorizontal_blocks();
        this.vertical_blocks = p.getVertical_blocks();
        this.blocks = new Block[horizontal_blocks][vertical_blocks];
        this.block = p.loadImage(p.getProp("block_img"));
        this.static_block = p.loadImage(p.getProp("static_block_img"));
        this.block_item = p.loadImage(p.getProp("item_img"));
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

    public PImage getBlock_item() {
        return block_item;
    }

    public void render() {
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

        this.blocks[6][6].setType(Items.BOMBE);
        this.blocks[6][6].setCovered(true);

        //oben links
        this.blocks[0][0].setType(Items.EMPTY);
        this.blocks[0][1].setType(Items.EMPTY);
        this.blocks[1][0].setType(Items.EMPTY);
        //unten links
        this.blocks[0][vertical_blocks - 2].setType(Items.EMPTY);
        this.blocks[0][vertical_blocks - 1].setType(Items.EMPTY);
        this.blocks[1][vertical_blocks - 1].setType(Items.EMPTY);
        //unten rechts
        this.blocks[horizontal_blocks - 1][vertical_blocks - 2].setType(Items.EMPTY);
        this.blocks[horizontal_blocks - 1][vertical_blocks - 1].setType(Items.EMPTY);
        this.blocks[horizontal_blocks - 2][vertical_blocks - 1].setType(Items.EMPTY);
        //oben rechts
        this.blocks[horizontal_blocks - 1][0].setType(Items.EMPTY);
        this.blocks[horizontal_blocks - 2][0].setType(Items.EMPTY);
        this.blocks[horizontal_blocks - 1][1].setType(Items.EMPTY);

        for (int i = 0; i < horizontal_blocks; i = i + 1) {
            for (int j = 0; j < vertical_blocks; j = j + 1) {
                this.blocks[i][j].render();
            }
        }

    }

    public void display() {
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
