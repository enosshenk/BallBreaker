package com.shenko.ballbreaker;

import com.badlogic.gdx.math.Rectangle;

public class Bat {
	World curWorld;
	int x;
	Rectangle Bounds;
	
	public Bat(World inWorld, int inX)
	{
		x = inX;
		curWorld = inWorld;
		Bounds = new Rectangle(inX, inWorld.BAT_Y, inWorld.BAT_WIDTH, inWorld.BAT_HEIGHT);
	}
	
	public void SetX(int inX)
	{
		x = inX - 64;
		if (x < 0)
		{
			x = 0;
		}
		else if (x > 472)
		{
			x = 472;
		}
		Bounds.setX(x);
	}
	
	public int DeflectBall(int BallX)
	{
		int BatX = x + (curWorld.BAT_WIDTH / 2);
		return (int)((BallX - BatX) / 3.2);
	}
}


