package com.me.colorcar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
/**
 * Driver class for color car
 * @author Nick Gilbert
 * @version vDev
 */
public class ColorCarDemo implements ApplicationListener, GestureListener, InputProcessor {
    //Sprites
	private SpriteBatch batch;
    private Car car;
    
    //Camera and Dimensions
    private OrthographicCamera camera;
    private Rectangle viewport;
    private int deviceWidth, deviceHeight;
    public static final int VIRTUAL_WIDTH = 720;
    public static final int VIRTUAL_HEIGHT = 1280;
    public static final float VIRTUAL_ASPECT_RATIO = (float)VIRTUAL_HEIGHT/(float)VIRTUAL_WIDTH;
    
    //Input detection
    private GestureDetector gd;
    private InputMultiplexer im;
    
    //ArrayLists to hold objects
    private int score;
    private BitmapFont scoreFont;
    private Random rn = new Random();
    private int sleepCount = 0;
    private int chance = 25;
    private List<Obstacle> obstacles = new ArrayList<Obstacle>();
    
    @Override
    public void create() {        
        batch = new SpriteBatch();
        car = new Car(2);
        deviceWidth = Gdx.graphics.getWidth();
        deviceHeight = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(deviceWidth, deviceHeight);
        score = 0;
        scoreFont = new BitmapFont();
        im = new InputMultiplexer();
        gd = new GestureDetector(this);
        im.addProcessor(gd);
        im.addProcessor(this);
        Gdx.input.setInputProcessor(im);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        batch.begin();
        car.draw(batch);
        sleepCount--;
        if(rn.nextInt(chance) == 0) {
        	if(chance != 1)
        		chance--;
        	int color = rn.nextInt(3);
        	if(sleepCount <= 0) {
	        	switch(color) {
	        		case 0: obstacles.add(new Obstacle(Color.RED, "Red", new Texture(Gdx.files.internal("data/Neon Red.jpg"))));
	        		break;
	        		case 1: obstacles.add(new Obstacle(Color.BLUE, "Blue", new Texture(Gdx.files.internal("data/Neon Blue.jpg"))));
	        		break;
	        		case 2: obstacles.add(new Obstacle(Color.GREEN, "Green", new Texture(Gdx.files.internal("data/Neon Green.jpg"))));
	        		break;
	        	}
	        	sleepCount = 100;
        	}
        }
        for(int i = 0; i < obstacles.size(); i++) {
        	Obstacle obstacle = obstacles.get(i);
        	obstacle.act();
        	obstacle.draw(batch);
        	if(car.getBoundingRectangle().overlaps(obstacle.getBoundingRectangle()) && (obstacle.isScoredOn() == false)) {
        		if(car.getLabel().equals(obstacle.getColorLabel())) {
        			score++;		
        		}
        		else {
        			System.out.println("Game Over");
        		}
        		obstacle.setScoredOn(true);
        		
        	}
        	if(obstacles.get(i).getY() > deviceHeight) 
        		obstacles.remove(i);
        }
        scoreFont.setScale(3f);
        scoreFont.setColor(255.0f, 1.0f, 1.0f, 1.0f);
        scoreFont.draw(batch, Integer.toString(score), deviceWidth/2, deviceHeight); 
        batch.end();
    }

	@Override
	public void resize(int width, int height) {
	}

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return true;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
    	car.act();
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return true;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return true;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
    	/*
    	if((car.getX() >= 0 && deltaX < 0) || (car.getX() <= Gdx.graphics.getWidth()-car.getWidth() && deltaX > 0))
    		car.translateX(deltaX);
    	if(car.getX() < 0)
    		car.setX(0);
    	if(car.getX() > Gdx.graphics.getWidth()-car.getWidth())
    		car.setX(Gdx.graphics.getWidth()-car.getWidth());
    	*/
        return true;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return true;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
    	return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
    	return false;
    }

    @Override
    public boolean scrolled(int amount) {
    	return false;
    }

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		return false;
	}

}