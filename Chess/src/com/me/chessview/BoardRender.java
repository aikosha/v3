package com.me.chessview;

import com.badlogic.gdx.Gdx;
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

public class BoardRender {

	private Texture texture;	
	private Sprite sprite;
	private TextureRegion r;
	private float size;
    ShapeRenderer debugRenderer = new ShapeRenderer();

 

    public BoardRender(){}

    public TextureRegion renderTexture(Texture t) {

    	size = (Gdx.graphics.getWidth()/8);
    	texture = t;
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		r = new TextureRegion(texture, 0, 0, size, size);
		
		return r;

    }


}

