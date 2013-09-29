package com.shenko.ballbreaker;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Ball {
	World curWorld;
	Vector2 Location = new Vector2();
	Vector2 GridLocation = new Vector2();
	Vector2 Velocity = new Vector2();
	float Speed = 300;
	float XDiff;
	float YDiff;
	int id;
	boolean Launching = true;
	
	final int BALLSIZE = 12;
	final int BOUNDSSIZE = 8;
	Rectangle Bounds;
	
	public Ball(World inWorld, int inID, Vector2 SpawnLoc)
	{
		curWorld = inWorld;
		id = inID;
		Launching = true;
		Location = SpawnLoc;
		GridLocation.x = (float)Math.ceil(Location.x / curWorld.GRID_SIZE_X);
		GridLocation.y = (float)Math.ceil(Location.y / curWorld.GRID_SIZE_Y);
		
		Bounds = new Rectangle(Location.x + ((BALLSIZE - BOUNDSSIZE) / 2), Location.y + ((BALLSIZE - BOUNDSSIZE) / 2), BOUNDSSIZE, BOUNDSSIZE);		
	}
	
	public void LaunchBall(Vector2 Aim)
	{
		Aim.x -= 300;
		float AimRot = Aim.angle();
		Velocity.x = (float)(Math.cos(Math.toRadians(AimRot)) * Speed);
		Velocity.y = (float)(Math.sin(Math.toRadians(AimRot)) * Speed);
		Launching = false;
	}
	
	public void Update(float DeltaTime)
	{
		if (!Launching)
		{
			// Update location
			Location.x = Location.x + (Velocity.x * DeltaTime);
			Location.y = Location.y + (Velocity.y * DeltaTime);
			
			// Update bounds
			Bounds.setX(Location.x + ((BALLSIZE - BOUNDSSIZE) / 2));
			Bounds.setY( Location.y + ((BALLSIZE - BOUNDSSIZE) / 2));
			
			// Check for screen edge collision
			if (Location.x < 1)
			{
				Velocity.x *= -1;
				Location.x = 1;
			}
			else if (Location.x > 599 - BALLSIZE)
			{
				Velocity.x *= -1;
				Location.x = 599 - BALLSIZE;			
			}
			if (Location.y < 1)
			{
				Velocity.y *= -1;
				Location.y = 1;
			}
			else if (Location.y > curWorld.GUTTER_Y)
			{
				curWorld.BallInGutter(id);
			}
			
			// Check bat collision
			if (Bounds.overlaps(curWorld.Bat.Bounds))
			{
				// Bounce Y
				Velocity.y *= -1;
				if (Velocity.y > 0)
				{
					Location.y += 4;
				}
				else
				{
					Location.y -= 4;
				}			
				
				int DeflectAmount = curWorld.Bat.DeflectBall((int)(Location.x + (BALLSIZE / 2)));
				Velocity = Velocity.rotate((float)DeflectAmount);
			}
	
			// Check brick collision
			int x, y;
			for (x=0; x < curWorld.GRID_WIDTH; x++)
			{
				for (y=0; y < curWorld.GRID_HEIGHT; y++)
				{
		    		if (curWorld.grid[x][y] != null)
		    		{
		    			if (Bounds.overlaps(curWorld.grid[x][y].Bounds))
		    			{
		    				// Check for bounce direction
		    				XDiff = Bounds.getX() - curWorld.grid[x][y].Bounds.getX();
		    				YDiff = Bounds.getY() - curWorld.grid[x][y].Bounds.getY();
		    				
		    				if (Math.abs(XDiff) > Math.abs(YDiff))
		    				{
		    					// Bounce X
		    					Velocity.x *= -1;
		    					if (Velocity.x > 0)
		    					{
		    						Location.x += 4;
		    					}
		    					else
		    					{
		    						Location.x -= 4;
		    					}
		    				}
		    				else
		    				{
		    					// Bounce Y
		    					Velocity.y *= -1;
		    					if (Velocity.y > 0)
		    					{
		    						Location.y += 4;
		    					}
		    					else
		    					{
		    						Location.y -= 4;
		    					}
		    				}
		    				
		    				// Destroy brick
		    				curWorld.grid[x][y].hit();
		    			}
		    		}
				}
			}
		}
		else
		{
			// Update location on bat
			Location.y = curWorld.BAT_Y - curWorld.BAT_HEIGHT;
			Location.x = (curWorld.Bat.x + 64) - (BALLSIZE / 2); 
		}
		
	}
}
