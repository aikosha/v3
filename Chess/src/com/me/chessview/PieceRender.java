package com.me.chessview;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * 
 * @author Patrick
 *	This entire class can probably be deleted later on if not needed.
 *  We might rewrite this to deal with spritesheets so I will leave it here
 *  10/04/2014
 */

public class PieceRender{

		private Texture texture;	
		private Sprite sprite;
		private TextureRegion r;

	    ShapeRenderer debugRenderer = new ShapeRenderer();

	 

	    public PieceRender(){}

	    public TextureRegion renderTexture(Texture t) {

            
	    	
	    	texture = t;
			texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			r = new TextureRegion(texture, 0, 0, 32, 32);
			
			return r;

	    }

	}

