package com.shenko.ballbreaker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class WorldRenderer {
	boolean debugRender = false;
	
	World curWorld;
	private OrthographicCamera Camera;
	
	SpriteBatch Batch = new SpriteBatch();
	ShapeRenderer Renderer = new ShapeRenderer();
	
	Texture BrickTex = new Texture("data/brick.tga");
	Sprite BrickSprite = new Sprite(BrickTex);
	
	Texture BallTex = new Texture("data/ball.tga");
	Sprite BallSprite = new Sprite(BallTex);
	
	Texture BatTex = new Texture("data/bat.png");
	Sprite BatSprite = new Sprite(BatTex);
	
	public WorldRenderer(World World)
	{
		curWorld = World;
		Camera = new OrthographicCamera(curWorld.GRID_WIDTH, curWorld.GRID_HEIGHT);
		Camera.setToOrtho(true);		
	}

	public void render() 
	{
	    Batch.setProjectionMatrix(Camera.combined);
	    Batch.begin();
	    if (debugRender)
	    {
		    Renderer.setProjectionMatrix(Camera.combined);
		    Renderer.begin(ShapeType.Rectangle);
	    }
	    
	    // Render the brick grid
	    int x, y;
	    for (x=0; x < curWorld.GRID_WIDTH; x++)
	    {
	    	for (y=0; y < curWorld.GRID_HEIGHT; y++)
	    	{
	    		if (curWorld.grid[x][y] != null)
	    		{
	    			// Render sprite
	    			BrickSprite.setColor(curWorld.grid[x][y].color);
	    			Batch.setColor(curWorld.grid[x][y].color);
	    			Batch.draw(BrickSprite, 1 + curWorld.grid[x][y].Location.x, curWorld.grid[x][y].Location.y, curWorld.GRID_SIZE_X, curWorld.GRID_SIZE_Y);
	    		    if (debugRender)
	    		    {
	    		    	// Render bounds
	    		    	Renderer.rect(curWorld.grid[x][y].Location.x, curWorld.grid[x][y].Location.y, curWorld.GRID_SIZE_X, curWorld.GRID_SIZE_Y);
	    		    }
	    		}
	    	}
	    }
	    
	    int i;
	    // Render balls
	    for (i=0; i < 3; i++)
	    {
	    	if (curWorld.Balls[i] != null)
	    	{
	    		// Render sprite
	    		Batch.setColor(new Color(1, 1, 1, 1));
	    		Batch.draw(BallSprite, curWorld.Balls[i].Location.x, curWorld.Balls[i].Location.y, curWorld.Balls[i].BALLSIZE, curWorld.Balls[i].BALLSIZE);
	    	    if (debugRender)
	    	    {
	    	    	// Render bounds 
	    	    	Renderer.rect(curWorld.Balls[i].Bounds.getX(), curWorld.Balls[i].Bounds.getY(), curWorld.Balls[i].Bounds.getWidth(), curWorld.Balls[i].Bounds.getHeight());
	    	    }
	    	}
	    }
	    
	    // Render the bat
	    Batch.draw(BatSprite, curWorld.Bat.x, curWorld.BAT_Y);
	    // Render bat bounds
	    if (debugRender)
	    {
	    	Renderer.rect(curWorld.Bat.Bounds.getX(), curWorld.BAT_Y, curWorld.BAT_WIDTH, curWorld.BAT_HEIGHT);
	    }
	    
	    Batch.end();
	    if (debugRender)
	    {
	    	Renderer.end();
	    }
	}
}
