package com.loderunner.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.loderunner.game.world.GameMap;

public abstract class Entity {
	protected Vector2 position;
	protected EntityType type;
	protected float velocityY = 0;
	protected GameMap map;
	protected boolean grounded = false;
	
	public Entity(float x, float y, EntityType type, GameMap map) {
		this.position = new Vector2(x, y);
		this.type = type;
		this.map = map;
	}
	
	public void update(float deltaTime, float gravity) {
		//gravity logic
		float newY = position.y;
		
		this.velocityY += gravity * deltaTime * getWeight();
		newY += this.velocityY * deltaTime;
		
		//System.out.println(map.doesRectCollideWithMap(position.x, newY, getWidth(), getHeight()));
		if (map.doesRectCollideWithMap(position.x, newY, getWidth(), getHeight())) {
			if(velocityY < 0) {
				this.position.y = (float)Math.floor(position.y);
				grounded = true;
			}
			this.velocityY = 0;
		} else {
			this.position.y = newY;
			grounded = false;
		}
	}
	
	public abstract void render(SpriteBatch batch);

	protected void moveX (float amount) {
		float newX = this.position.x + amount;
		if(!map.doesRectCollideWithMap(newX, position.y, getWidth(), getHeight())) {
			this.position.x = newX;
		}
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public float getX() {
		return position.x;
	}
	
	public float getY() {
		return position.y;
	}

	public EntityType getType() {
		return type;
	}
	
	public int getWidth() {
		return type.getWidth();
	}
	
	public int getHeight() {
		return type.getHeight();
	}
	
	public float getWeight() {
		return type.getWeight();
	}

	public boolean isGrounded() {
		return grounded;
	}
	
	
}
