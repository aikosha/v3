package com.me.chessview;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
/**
 * 
 * @author Patrick
 * We will either replace this with the real android main or rewrite it,
 * should be ok to replace it once we have the correct packages and imports
 * 
 */
public class Main {
	public static void main(String[] args) {
		
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Chess_View";
		cfg.width = 256;
		cfg.height = 256;
		
		new LwjglApplication(new ChessView(), cfg);
	}
}
