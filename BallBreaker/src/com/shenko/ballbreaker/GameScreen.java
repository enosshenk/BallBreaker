package com.shenko.ballbreaker;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Vector2;

public class GameScreen implements Screen {
	
	World curWorld;
	WorldRenderer Renderer;
	
	@Override
	public void render(float delta) {
		
	    curWorld.Update(delta);
	    
	    // Render world
	    Gdx.gl.glClearColor(0, 0, 0, 1);
	    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);	
	    Renderer.render();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		curWorld = new TestWorld();	
		Renderer = new WorldRenderer(curWorld);
		render((float)0.1);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
