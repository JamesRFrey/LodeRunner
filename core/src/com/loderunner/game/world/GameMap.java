package com.loderunner.game.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GameMap {
	
	public void render (OrthographicCamera camera, SpriteBatch batch) {
		
	}
	public void update (float delta) {
		
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
	
	public abstract int getWidth();
	public abstract int getHeight();
	public abstract int getLayers();
}
