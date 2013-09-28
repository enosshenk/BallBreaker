package com.shenko.ballbreaker;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Brick {
	
	World curWorld;
	Vector2 Location = new Vector2();
	int x;
	int y;
	int health = 1;
	Color color = new Color(1, 0, 0, 1);
	Rectangle Bounds;
	
	public Brick(World inWorld, int inX, int inY, Vector2 spawnLoc)
	{
		curWorld = inWorld;
		x = inX;
		y = inY;
		this.Location = spawnLoc;
		Bounds = new Rectangle(Location.x, Location.y, inWorld.GRID_SIZE_X, inWorld.GRID_SIZE_Y);
	}
	
	public void hit()
	{
		health -= 1;
		
		if (health <= 0)
		{
			curWorld.BrickHit(x, y);
		}
	}
}
