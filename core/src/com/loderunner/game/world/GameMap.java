package com.loderunner.game.world;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.loderunner.game.entities.Entity;
import com.loderunner.game.entities.Player;

public abstract class GameMap {
	
	protected ArrayList<Entity> entities;
	
	public GameMap() {
		entities = new ArrayList<Entity>();
		entities.add(new Player(40, 128, this));
	}
	
	public void render (OrthographicCamera camera, SpriteBatch batch) {
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//Texture img = new Texture("jake_walk1.png");
		//batch.draw(img, 0f, 0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 0, 0, 3, 3);
		for(Entity entity : entities) {
			entity.render(batch);
		}
	}
	public void update (float delta) {
		for(Entity entity : entities) {
			entity.update(delta);
		}
	}
	
	public abstract void dispose();
	
	/**
	 * Gets a tile by pixel position within the game world at a specified layer
	 * @param layer
	 * @param x
	 * @param y
	 * @return
	 */
	public TileType getTileTypeByLocation(int layer, float x, float y) {
		return this.getTileTypeByCoordinate(layer, (int) (x / TileType.TILE_SIZE), (int) (y/ TileType.TILE_SIZE));
	}
	
	/**
	 * Gets a tile a its coordinate within the map at a specified layer
	 * @param layer
	 * @param col
	 * @param row
	 * @return
	 */
	public abstract TileType getTileTypeByCoordinate(int layer, int col, int row);
	
	/**
	 * 
	 * @param x: x coordinate of entity
	 * @param y: y coordinate of entity
	 * @param width: width of entity
	 * @param height: height of entity
	 * @return if collides with map
	 */
	public boolean doesRectCollideWithMap(float x, float y, int width, int height) {
		//check if outside of map
		if (x < 0 || y < 0 || x + width > getPixelWidth() || y + height > getPixelHeight()) {
			return true;
		}
		//check for collision with tiles that entity touches
		for(int row = (int) Math.floor(y / TileType.TILE_SIZE); row < Math.ceil((y + height) / TileType.TILE_SIZE); row++) {
			for(int col = (int) Math.floor(x / TileType.TILE_SIZE); col < Math.ceil((x + width) / TileType.TILE_SIZE); col++) {
				for(int layer = 0; layer < getLayers(); layer++) {
					TileType type = getTileTypeByCoordinate(layer, col, row);
					if (type != null && type.isCollidable()) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public abstract int getWidth();
	public abstract int getHeight();
	public abstract int getLayers();
	
	public int getPixelWidth() {
		return this.getWidth() * TileType.TILE_SIZE;
	}
	
	public int getPixelHeight() {
		return this.getHeight() * TileType.TILE_SIZE;
	}
}
