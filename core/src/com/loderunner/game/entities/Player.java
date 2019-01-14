package com.loderunner.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.loderunner.game.world.GameMap;

public class Player extends Entity {

	private static final int SPEED = 80;
	private static final int JUMP_VELOCITY = 5;
	
	
	Texture image;
	
	public Player(float x, float y, GameMap map) {
		super(x, y, EntityType.PLAYER, map);
		image = new Texture("jake_walk1.png");
	}

	@Override
	public void update(float deltaTime, float gravity) {
		//jumping logic
		if (Gdx.input.isKeyPressed(Keys.SPACE) && grounded) {
			this.velocityY += JUMP_VELOCITY * getWeight();
		} else if (Gdx.input.isKeyPressed(Keys.SPACE) && !grounded && this.velocityY > 0) {
			//if spacebar is held, jump with stronger velocity
			this.velocityY += JUMP_VELOCITY * getWeight() * deltaTime;
		}
		
		super.update(deltaTime, gravity); //applies gravity
		
		//move left
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			moveX(-SPEED * deltaTime);
		}
		//move right
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			moveX(SPEED * deltaTime);
		}
	}
	
	@Override
	public void render(SpriteBatch batch) {
		//draw image at location and scale to width&height
		batch.draw(image, position.x, position.y, getWidth(), getHeight());
	}

}
