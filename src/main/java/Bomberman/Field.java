package Bomberman;

import Bomberman.UserArea.UserArea;
import processing.core.*;
import vialab.SMT.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Field extends PApplet {

	private Integer vertical_blocks = null;
	private Integer block_size = null;
	private Integer horizontal_blocks = null;
	private Integer x_offset = null;
	private Integer y_offset = null;
	private Integer bombtimer = 120;
	private Properties prop;
	private Blocks blocks;
	private boolean won=false;
	public Bomberman b1, b2, b3, b4;
	public Plantedbomb[] bombfield= new Plantedbomb[20];
	public int[][] movefield=new int[20][7];
	PImage range, life, speed,count;
	PShape rangeIcon, lifeIcon, speedIcon, countIcon;
	private ConcurrentHashMap<String, PImage> ImageMap = new ConcurrentHashMap<String, PImage>();
	int diff=50;

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

		buildImageMap();

	}

	private String[] getResourceListing(Class clazz, String path) throws URISyntaxException, IOException {
		URL dirURL = clazz.getClassLoader().getResource(path);
		if (dirURL != null && dirURL.getProtocol().equals("file")) {
        /* A file path: easy enough */
			return new File(dirURL.toURI()).list();
		}

		if (dirURL == null) {
        /*
         * In case of a jar file, we can't actually find a directory.
         * Have to assume the same jar as clazz.
         */
			String me = clazz.getName().replace(".", "/")+".class";
			dirURL = clazz.getClassLoader().getResource(me);
		}

		if (dirURL.getProtocol().equals("jar")) {
        /* A JAR path */
			String jarPath = dirURL.getPath().substring(5, dirURL.getPath().indexOf("!")); //strip out only the JAR file
			JarFile jar = new JarFile(URLDecoder.decode(jarPath, "UTF-8"));
			Enumeration<JarEntry> entries = jar.entries(); //gives ALL entries in jar
			Set<String> result = new HashSet<String>(); //avoid duplicates in case it is a subdirectory
			while(entries.hasMoreElements()) {
				String name = entries.nextElement().getName();
				if (name.startsWith(path)) { //filter according to the path
					String entry = name.substring(path.length());
					int checkSubdir = entry.indexOf("/");
					if (checkSubdir >= 0) {
						// if it is a subdirectory, we just return the directory name
						entry = entry.substring(0, checkSubdir);
					}
					result.add(entry);
				}
			}
			return result.toArray(new String[result.size()]);
		}

		throw new UnsupportedOperationException("Cannot list files for URL "+dirURL);
	}

	private void buildImageMap() {

		String[] images_new = null;

		try {
			images_new = getResourceListing(getClass(), "img");
		} catch (URISyntaxException e) {
			System.out.print(e);
		} catch (IOException e) {
			System.out.print(e);
		}

		if(images_new != null && images_new.length > 0) {
			for (String image: images_new) {
				URL resource = getClass().getResource("/img/" + image);
				PImage pimage = loadImage(resource.toString());
				this.ImageMap.put(image, pimage);
			}
		}

	}

	public PImage getImage(String name) {
		PImage image = this.ImageMap.get(name);
		if(image == null) {
			//System.out.print("not chached\n");
			URL resource = getClass().getResource("/img/" + name);
			if(resource == null)
				return null;
			image = loadImage(resource.toString());
			this.ImageMap.put(name, image);
		}

		return image;
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
	private PShape renderIcon(PImage img) {
		PShape s = createShape();
		s.beginShape();
		s.noStroke();
		s.fill(255);
		s.texture(img);
		s.textureMode(PShape.NORMAL);
		s.vertex(0, 0, 0, 0);
		s.vertex(this.block_size, 0, 1, 0);
		s.vertex(this.block_size, this.block_size, 1, 1);
		s.vertex(0, this.block_size, 0, 1);
		s.endShape(PShape.CLOSE);
		return s;
	}

	public void setup() {

		//size(this.displayWidth, this.displayHeight, SMT.RENDERER);
		size(1180, 620, SMT.RENDERER);
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

		b1 = new Bomberman(this, "red", 0, 0, true);
		b1.render();
		Zone z1 = new UserArea(0, 0, panel_width, panel_height,color(255, 48, 48), b1, this);
		z1.translate(x_offset,0);
		z1.rotateAbout(PI,CENTER);

		b2 = new Bomberman(this, "orange", horizontal_blocks-1, 0, true);
		b2.render();
		Zone z2 = new UserArea(0, 0, panel_width, panel_height, color(255, 140, 0), b2, this);
		z2.translate(width-panel_width-x_offset,0);
		z2.rotateAbout(PI,CENTER);

		b3 = new Bomberman(this, "blue", 0, vertical_blocks-1, false);
		b3.render();
		Zone z3 = new UserArea(0, 0, panel_width, panel_height, color(30, 144, 255), b3, this);
		z3.translate(x_offset, height-panel_height);

		b4 = new Bomberman(this, "violett", horizontal_blocks-1, vertical_blocks-1, false);
		b4.render();
		Zone z4 = new UserArea(0, 0, panel_width, panel_height, color(139, 0, 139), b4, this);
		z4.translate(width-panel_width-x_offset, height-panel_height);

		SMT.add(z1);
		SMT.add(z2);
		SMT.add(z3);
		SMT.add(z4);



		for(int i =0; i<bombfield.length;i++)
		{
			bombfield[i]=new Plantedbomb(this);
		}

		for(int i=0;i<20;i++)
		{
			movefield[i][0]=0;
			movefield[i][1]=0;
			movefield[i][2]=0;
			movefield[i][3]=0;
			movefield[i][4]=0;
			movefield[i][5]=0;
			movefield[i][6]=0;
		}

		this.life =getImage(getProp("lifeupgrade_img"));

		this.range = getImage(getProp("rangeupgrade_img"));
		this.speed = getImage(getProp("speedupgrade_img"));
		this.count = getImage(getProp("anzupgrade_img"));

		this.rangeIcon = renderIcon(this.range);
		this.lifeIcon = renderIcon(this.life);
		this.speedIcon = renderIcon(this.speed);
		this.countIcon = renderIcon(this.count);

	}
	public void gametest() {
		if(b1.isAlive()&&!b2.isAlive()&&!b3.isAlive()&&!b4.isAlive()) {
			background(255, 48, 48);
			stroke(0, 0, 0);
			fill(255, 48, 48);
			rect(0, 0, this.displayWidth, this.displayHeight);
			textAlign(CENTER);
			fill(0);
			text("Spieler rot hat gewonnen", this.displayWidth / 2, (this.displayHeight - 6) / 3 - (this.displayHeight - 6) / 12);
			won=  true;

			//setup();

		}
		else if(!b1.isAlive()&&b2.isAlive()&&!b3.isAlive()&&!b4.isAlive()) {
			background(255, 140, 0);
			stroke(0, 0, 0);
			fill(255, 140, 0);
			rect(0, 0, this.displayWidth, this.displayHeight);
			textAlign(CENTER);
			fill(0);
			text("Spieler orange hat gewonnen", this.displayWidth / 2, (this.displayHeight - 6) / 3 - (this.displayHeight - 6) / 12);
			won= true;

		}
		else if(!b1.isAlive()&&!b2.isAlive()&&b3.isAlive()&&!b4.isAlive()) {
			background(30, 144, 255);
			stroke(0, 0, 0);
			fill(30, 144, 255);
			rect(0, 0, this.displayWidth, this.displayHeight);
			textAlign(CENTER);
			fill(0);
			text("Spieler blau hat gewonnen", this.displayWidth / 2, (this.displayHeight - 6) / 3 - (this.displayHeight - 6) / 12);
			won=  true;

		}
		else if(!b1.isAlive()&&!b2.isAlive()&&!b3.isAlive()&&b4.isAlive()) {
			background(139, 0, 139);
			stroke(0, 0, 0);
			fill(139, 0, 139);
			rect(0, 0, this.displayWidth, this.displayHeight);
			textAlign(CENTER);
			fill(0);
			text("Spieler violett hat gewonnen", this.displayWidth / 2, (this.displayHeight - 6) / 3 - (this.displayHeight - 6) / 12);
			won= true;

		}
		else if(!b1.isAlive()&&!b2.isAlive()&&!b3.isAlive()&&!b4.isAlive()&&b1.isPlaying()&&b2.isPlaying()&&b3.isPlaying()&&b4.isPlaying()) {
			background(0, 0, 0);
			stroke(0, 0, 0);
			fill(0, 0, 0);
			rect(0, 0, this.displayWidth, this.displayHeight);
			textAlign(CENTER);
			fill(255);
			text("Spielt zusammen, nicht nacheinander!", this.displayWidth / 2, (this.displayHeight - 6) / 3 - (this.displayHeight - 6) / 12);
			won = true;
		}
		else
			won= false;

	}


	public void setFloatingUpgrade(int upgrade , String id)
	{
		for(int i=0;i<20;i++)
		{
			if(movefield[i][0]==0)
			{
				movefield[i][0] = upgrade;
				if(id=="red")
				{
					movefield[i][1] = b1.getX().intValue();
					movefield[i][2] = b1.getY().intValue();
					movefield[i][3] = 4*block_size;
					movefield[i][4] = block_size;
					movefield[i][5] = -(movefield[i][1]-movefield[i][3])/diff;
					movefield[i][6] = -(movefield[i][2]-movefield[i][4])/diff;

				}
				else if(id=="orange")
				{
					movefield[i][1] = b2.getX().intValue();
					movefield[i][2] = b2.getY().intValue();
					movefield[i][3] = width-4*block_size;
					movefield[i][4] = block_size;
					movefield[i][5] = -(movefield[i][1]-movefield[i][3])/diff;
					movefield[i][6] = -(movefield[i][2]-movefield[i][4])/diff;
				}
				else if(id=="blue")
				{
					movefield[i][1] = b3.getX().intValue();
					movefield[i][2] = b3.getY().intValue();
					movefield[i][3] = 4*block_size;
					movefield[i][4] = height-block_size;
					movefield[i][5] = -(movefield[i][1]-movefield[i][3])/diff;
					movefield[i][6] = -(movefield[i][2]-movefield[i][4])/diff;
				}
				else if(id=="violett")
				{
					movefield[i][1] = b4.getX().intValue();
					movefield[i][2] = b4.getY().intValue();
					movefield[i][3] = width-4*block_size;
					movefield[i][4] = height-block_size;
					movefield[i][5] = -(movefield[i][1]-movefield[i][3])/diff;
					movefield[i][6] = -(movefield[i][2]-movefield[i][4])/diff;
				}
				i=20;
			}
		}
	}
	public void drawFloatingUpgrade()
	{
		for(int i=0;i<20;i++) {
			if (movefield[i][0] > 0) {
				if(movefield[i][0]==1) {
					shape(this.lifeIcon, movefield[i][1], movefield[i][2]);
				}

				if(movefield[i][0]==2) {
					shape(this.rangeIcon, movefield[i][1], movefield[i][2]);
				}
				if(movefield[i][0]==3) {
					shape(this.speedIcon, movefield[i][1], movefield[i][2]);
				}
				if(movefield[i][0]==4) {
					shape(this.countIcon, movefield[i][1], movefield[i][2]);
				}


				movefield[i][1] += movefield[i][5];
				movefield[i][2] += movefield[i][6];
				/*
				if (movefield[i][1] < movefield[i][3])
					movefield[i][1] = movefield[i][1] + movefield[i][5];
				else
					movefield[i][1] = movefield[i][1] - movefield[i][5];
				if(movefield[i][2] < movefield[i][4])
					movefield[i][2] = movefield[i][2] + movefield[i][6];
				else
					movefield[i][2] = movefield[i][2] - movefield[i][6];
					*/

				if (movefield[i][5] < 0 && movefield[i][6] < 0) { // linke obere Ecke=Ziel
					if(movefield[i][1]<movefield[i][3]) {
						movefield[i][1] = movefield[i][3];
					}
					if(movefield[i][2]<movefield[i][4]) {
						movefield[i][2] = movefield[i][4];
					}
				}
				else if (movefield[i][5] > 0 && movefield[i][6] > 0) { // rechts unten
					if(movefield[i][1]>movefield[i][3]) {
						movefield[i][1] = movefield[i][3];
					}
					if(movefield[i][2]>movefield[i][4]) {
						movefield[i][2] = movefield[i][4];
					}
				}
				else if (movefield[i][5] < 0 && movefield[i][6] > 0) { // links unten
					if(movefield[i][1]<movefield[i][3]) {
						movefield[i][1] = movefield[i][3];
					}
					if(movefield[i][2]>movefield[i][4]) {
						movefield[i][2] = movefield[i][4];
					}
				}
				else if (movefield[i][5] > 0 && movefield[i][6] < 0) { // rechts oben
					if(movefield[i][1]>movefield[i][3]) {
						movefield[i][1] = movefield[i][3];
					}
					if(movefield[i][2]<movefield[i][4]) {
						movefield[i][2] = movefield[i][4];
					}
				}


				if (movefield[i][1] == movefield[i][3]&& movefield[i][2] == movefield[i][4])
					movefield[i][0]=0;
			}


		}
	}




	public void draw() {
		background(255);
		//System.out.println(String.format("fps: %.0f\n", this.frameRate));
		bombtimer--;
		if (bombtimer == 0) {
			b1.increaseBombcount();
			b2.increaseBombcount();
			b3.increaseBombcount();
			b4.increaseBombcount();
			bombtimer = 120;
		}
		if (won)
			gametest();
		else {
			this.blocks.draw();

			//System.out.println(String.format( "%d:%d", this.b1.blockX,this.b1.blockY));


			Integer xred = b1.blockX;
			Integer yred = b1.blockY;
			Integer xorange = b2.blockX;
			Integer yorange = b2.blockY;

			Integer xblue = b3.blockX;
			Integer yblue = b3.blockY;


			Integer xviolett = b4.blockX;
			Integer yviolett = b4.blockY;


			for (int i = 0; i < bombfield.length; i++) {
				this.bombfield[i].draw(xred, yred, xorange, yorange, xblue, yblue, xviolett, yviolett);
			}
			if (b1.isAlive())
				b1.draw();
			if (b2.isAlive())
				b2.draw();
			if (b3.isAlive())
				b3.draw();
			if (b4.isAlive())
				b4.draw();
			drawFloatingUpgrade();
		}

	}
}