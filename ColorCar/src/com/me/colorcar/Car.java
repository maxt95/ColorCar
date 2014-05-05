package com.me.colorcar;

import java.util.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Car extends Sprite{
	private List<Color> colors;
	private List<String> colorLabels;
	private int colorIndex;
	private int labelIndex;
	private String label;
	
	public Car(int difficulty) {
		super(new Texture(Gdx.files.internal("data/bird.jpg")));
		setX(Gdx.graphics.getWidth() / 3);
		setColors(difficulty);
		colorIndex = 0;
		scale();
		setColor(colors.get(colorIndex));
		setLabel(colorLabels.get(labelIndex));
	}
	
	public void scale() {
		float deviceWidth = Gdx.graphics.getWidth();
		float deviceHeight = Gdx.graphics.getHeight();
		float deviceAspectRatio = deviceWidth/deviceHeight;
		
		float scalar = deviceAspectRatio / ColorCarDemo.VIRTUAL_ASPECT_RATIO;
		
		setSize(getWidth()/scalar, getHeight()/scalar);
	}
	
	public void setColors(int difficulty) {
		colors = new ArrayList<Color>();
		colorLabels = new ArrayList<String>();
		colors.add(Color.RED);
		colorLabels.add("Red");
		colors.add(Color.BLUE);
		colorLabels.add("Blue");
		if(difficulty > 1) {
			colors.add(Color.GREEN);
			colorLabels.add("Green");
		}
		if(difficulty > 2) {
			colors.add(Color.YELLOW);
			colorLabels.add("Yellow");
		}
		if(difficulty > 3) {
			colors.add(Color.WHITE);
			colorLabels.add("White");
		}
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}

	public void act() {
		colorIndex++;
		labelIndex++;
		if(colorIndex == colors.size()) {
			colorIndex = 0;
			labelIndex = 0;
		}
		setColor(colors.get(colorIndex));
		setLabel(colorLabels.get(labelIndex));
	}
}
