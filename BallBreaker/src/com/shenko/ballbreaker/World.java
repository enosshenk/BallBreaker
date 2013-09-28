package com.shenko.ballbreaker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public abstract class World {
	
	public final int GRID_SIZE_X = 23;		// Pixel width per grid element
	public final int GRID_SIZE_Y = 14;		// Pixel height per grid element
	public final int GRID_WIDTH = 26;		// X num of grid elements
	public final int GRID_HEIGHT = 50;		// Y num of grid elements	
	
	public final int BAT_WIDTH = 128;		// Width of the unmodified bat
	public final int BAT_HEIGHT = 16;		// Height of the bat
	public final int BAT_Y = 800;			// Y level for the bat
	
	Brick[][] grid = new Brick[GRID_WIDTH][GRID_HEIGHT];
	Ball[] Balls = new Ball[3];
	Bat Bat = new Bat(this, 236);
	
	int InputX = 300;
	
	public World()
	{
		initWorld();
	}
	
	public void initWorld()
	{
	}
	
	public void Update(float DeltaTime)
	{
	    int i;
	    // Update balls
	    for (i=0; i < 3; i++)
	    {
	    	if (Balls[i] != null)
	    	{
	    		Balls[i].Update(DeltaTime);
	    	}
	    }
	    
	    // Update bat
	    InputX = Gdx.input.getX();
	    Bat.SetX(InputX);
	}
	
	public void BrickHit(int inX, int inY)
	{
		grid[inX][inY] = null;
	}
	
}
