package Bomberman;

import Bomberman.UserArea.UserArea;
import processing.core.*;
import vialab.SMT.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Field extends PApplet {

	private Integer vertical_blocks = null;
	private Integer block_size = null;
	private Integer horizontal_blocks = null;
	private Integer x_offset = null;
	private Integer y_offset = null;
	private Properties prop;
	private Blocks blocks;
	private Bomberman b1, b2, b3, b4;

	public Field() {
		super();

		InputStream input = null;

		try {
			input = getClass().getResourceAsStream("/config.properties");
			System.out.print(input);

			this.prop = new Properties();
			this.prop.load(input);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		this.vertical_blocks = Integer.parseInt(prop.getProperty("vertical_blocks"));
	}

	public Block getBlock(Integer x, Integer y) {
		return blocks.getBlocks(x, y);
	}

	public Integer getVertical_blocks() {
		return vertical_blocks;
	}

	public Integer getBlock_size() {
		return block_size;
	}

	public Integer getHorizontal_blocks() {
		return horizontal_blocks;
	}

	public Integer getX_offset() {
		return x_offset;
	}

	public Integer getY_offset() {
		return y_offset;
	}

	public String getProp(String p) {
		return prop.getProperty(p);
	}

	public void setup() {

		size(1280, 720, SMT.RENDERER);
		SMT.init(this);

		block_size = height/(vertical_blocks+6);

		int panel_width = 8*block_size;
		int panel_height = 3*block_size;

		horizontal_blocks = width/block_size;
		if(horizontal_blocks%2 == 0) {
			horizontal_blocks--;
		}

		y_offset = (height - vertical_blocks*block_size)/2;
		x_offset = (width - horizontal_blocks*block_size)/2;

		this.blocks = new Blocks(this);
		this.blocks.render();

		b1 = new Bomberman(this, "blue", 0, 0, true);
		b1.render();
		Zone z1 = new UserArea(0, 0, panel_width, panel_height,color(255, 48, 48), b1);
		z1.translate(x_offset,0);
		z1.rotateAbout(PI,CENTER);

		b2 = new Bomberman(this, "blue", horizontal_blocks-1, 0, true);
		b2.render();
		Zone z2 = new UserArea(0, 0, panel_width, panel_height, color(255, 140, 0), b2);
		z2.translate(width-panel_width-x_offset,0);
		z2.rotateAbout(PI,CENTER);

		b3 = new Bomberman(this, "blue", 0, vertical_blocks-1, false);
		b3.render();
		Zone z3 = new UserArea(0, 0, panel_width, panel_height, color(30, 144, 255), b3);
		z3.translate(x_offset, height-panel_height);

		b4 = new Bomberman(this, "red", horizontal_blocks-1, vertical_blocks-1, false);
		b4.render();
		Zone z4 = new UserArea(0, 0, panel_width, panel_height, color(139, 0, 139), b4);
		z4.translate(width-panel_width-x_offset, height-panel_height);

		SMT.add(z1);
		SMT.add(z2);
		SMT.add(z3);
		SMT.add(z4);

	}

	public void draw() {
		background(255);
		//System.out.println(String.format( "fps: %.0f", this.frameRate));
		this.blocks.display();
		b1.display();
		b2.display();
		b3.display();
		b4.display();
	}




}