package com.shenko.ballbreaker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class TestWorld extends World
{
	public void initWorld()
	{
		Pixmap LevelPixMap = new Pixmap(Gdx.files.internal("data/level3.png"));
		int x, y;
		for (x=0; x < GRID_WIDTH; x++)
		{
			for (y=0; y < GRID_HEIGHT; y++)
			{
				if (LevelPixMap.getPixel(x, y) != Color.rgba8888(0,0,0,1))
				{
					grid[x][y] = new Brick(this, x, y, new Vector2(x * GRID_SIZE_X, y * GRID_SIZE_Y));
					Color.rgba8888ToColor(grid[x][y].color, LevelPixMap.getPixel(x, y));
					BricksLeft += 1;
				}
			}
		}
		
		Background = new Texture("data/backdrop4.png");
		
		super.initWorld();
	}
}
