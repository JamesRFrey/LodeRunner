package com.loderunner.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.loderunner.game.world.GameMap;

public class Player extends Entity {

	private static final int SPEED = 80;
	
	
	Texture image;
	
	public Player(float x, float y, GameMap map) {
		super(x, y, EntityType.PLAYER, map);
		image = new Texture("jake_walk1.png");
	}

	@Override
	public void update(float deltaTime) {
		//jumping logic
		if (falling) {
			this.velocityY -= getWeight();
		} else {
			this.velocityY = 0;
		}
		
		super.update(deltaTime); //applies gravity
		
		//move left
		if(Gdx.input.isKeyPressed(Keys.LEFT) && !falling) {
			moveX(-SPEED * deltaTime);
		}
		//move right
		if(Gdx.input.isKeyPressed(Keys.RIGHT) && !falling) {
			moveX(SPEED * deltaTime);
		}
	}
	
	@Override
	public void render(SpriteBatch batch) {
		//draw image at location and scale to width&height
		batch.draw(image, position.x, position.y, getWidth(), getHeight());
	}

}
