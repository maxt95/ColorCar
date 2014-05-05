package com.me.colorcar;

/**
 * Lines that the car must change colors to pass through
 * @author Nick Gilbert
 */
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Obstacle extends Sprite {

	private Random rn;
	private Color color;
	private String colorLabel;
	private boolean scoredOn;
	
	public Obstacle(Color color, String colorLabel, Texture tx) {
		super(tx);
		this.color = color;
		this.colorLabel = colorLabel;
		scoredOn = false;
		setColor(color);
		setX(0); setY(Gdx.graphics.getHeight());
		scale();
	}

	public void scale() {
		int deviceWidth = Gdx.graphics.getWidth();
		int deviceHeight = Gdx.graphics.getHeight();
		setSize(deviceWidth, deviceHeight / 20);
	}
	
	public void act() {
		translateY(-5f);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isScoredOn() {
		return scoredOn;
	}

	public void setScoredOn(boolean scoredOn) {
		this.scoredOn = scoredOn;
	}
	
	public String getColorLabel() {
		return colorLabel;
	}

	public void setColorLabel(String colorLabel) {
		this.colorLabel = colorLabel;
	}
}
