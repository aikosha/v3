package com.me.chessview;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
/**
 * 
 * @author Patrick
 * 
 * Our tile object.
 * We will strip this down in the final version and cut dead code.
 *
 */
public class Tile {

	static final float SIZE = 1f;
	private boolean isOccupied = false;

	Vector2 position = new Vector2();
	Texture texture;
	private Piece piece = new Piece(0,0);

	public Tile(Vector2 pos, FileHandle img) {

		this.position = pos;
		texture = new Texture(img);
	}


	public Vector2 getPosition() {
		
		return position;
	}
	
	public void setOccupied(Piece p)
	{
		this.isOccupied = true;
		this.piece = p;
		
	}
	
	public boolean isOccupied()
	{
		return isOccupied;
		
	}
	
	public void setFree()
	{
		this.isOccupied = false;
	}
	
	public Piece getPiece()
	{
		return piece;
	}
}

