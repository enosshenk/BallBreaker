package com.shenko.ballbreaker;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "BallBreaker";
		cfg.useGL20 = false;
		cfg.width = 600;
		cfg.height = 850;
		
		new LwjglApplication(new BallBreaker(), cfg);
	}
}
