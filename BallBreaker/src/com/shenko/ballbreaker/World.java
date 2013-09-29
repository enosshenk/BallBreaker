package com.shenko.ballbreaker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public abstract class World {
	
	public final int GRID_SIZE_X = 23;		// Pixel width per grid element
	public final int GRID_SIZE_Y = 14;		// Pixel height per grid element
	public final int GRID_WIDTH = 26;		// X num of grid elements
	public final int GRID_HEIGHT = 50;		// Y num of grid elements	
	
	public final int BAT_WIDTH = 128;		// Width of the unmodified bat
	public final int BAT_HEIGHT = 16;		// Height of the bat
	public final int BAT_Y = 800;			// Y level for the bat
	public final int GUTTER_Y = 810;		// Y level of the ball gutter region
	public final int BAT_ZONE_Y = 700;		// Start of bat touch zone
	
	public boolean Launching = true;		// If true, ball is locked to paddle and must be launched
	public Vector2 LaunchSource = new Vector2();
	public Vector2 LaunchTarget = new Vector2(300, 300);
	
	public int Score = 0;
	public int BricksLeft;
	public int BallsLeft;
	
	Brick[][] grid = new Brick[GRID_WIDTH][GRID_HEIGHT];
	Ball[] Balls = new Ball[3];
	Texture Background;
	Bat Bat = new Bat(this, 236);
	
	int InputX = 300;
	
	public World()
	{
		initWorld();
	}
	
	public void SetBalls(int inBalls)
	{
		BallsLeft = inBalls;
	}
	
	public void BallInGutter(int ballID)
	{
		BallsLeft -= 1;
		Balls[ballID] = null;
		if (Balls[0] == null && Balls[1] == null && Balls[2] == null)
		{
			// No more balls in play
			Launching = true;
			Balls[0] = new Ball(this, 0, new Vector2(300, 700));
		}
	}
	
	public void initWorld()
	{
		Balls[0] = new Ball(this, 0, new Vector2(300, 700));
		Launching = true;
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
	    if (!Launching && Gdx.input.getY() > BAT_ZONE_Y)
	    {
		    InputX = Gdx.input.getX();
		    Bat.SetX(InputX);
	    }
	    else if (Launching && Gdx.input.getY() > BAT_ZONE_Y)
	    {
		    InputX = Gdx.input.getX();
		    Bat.SetX(InputX);
	    }
	    else if (Launching &&  Gdx.input.getY() < BAT_ZONE_Y)
	    {
	    	LaunchTarget.x = Gdx.input.getX();
	    	LaunchTarget.y = Gdx.input.getY();
	    }
	    
	    if (Launching)
	    {
		    LaunchSource.x = InputX;
		    LaunchSource.y = Balls[0].Location.y - (Balls[0].BALLSIZE / 2);	    	
	    }	
	    
	    if (Launching && Gdx.input.justTouched())
	    {
	    	if (Gdx.input.getY() < BAT_ZONE_Y)
	    	{
	    		Balls[0].LaunchBall(LaunchTarget);
	    		Launching = false;
	    	}
	    }
	}
	
	public void BrickHit(int inX, int inY)
	{
		grid[inX][inY] = null;
		Score += 10;
	}
	
}
