package com.me.chessview;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
/**
 * 
 * @author Patrick
 * 
 * Our piece object. We will strip this down of dead code in the final version.
 */
public class Piece { 

	private Vector2 position;
	private int team;
	private Texture image;
	private boolean alive = true;
	private int[] positions;
	private char type;
	
	
	public Piece(float startX, float startY){
		
		position = new Vector2(startX, startY);
		
	}
	
	public Vector2 getPosition() {
		return position;
	}
	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Texture getImage() {
		return image;
	}
	public void setImageId(FileHandle imageId) {
		image = new Texture(imageId);
		
	}
	public int getTeam() {
		return team;
	}
	public void setTeam(int team) {
		this.team = team;
	}
	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	public int[] getPositions() {
		return positions;
	}
	public void setPositions(int[] positions) {
		this.positions = positions;
	}
	public void setType(int i)
	{
		
		if( i == 0 || i == 6)
			this.type = 'r';
		else if( i == 1 || i == 7)
			this.type = 'n';
		else if( i == 2 || i == 8)
			this.type = 'b';
		else if( i == 3 || i == 9)	
			this.type = 'q';
		else if( i == 4 || i == 10)
			this.type = 'k';
		else if( i == 5 || i == 11)
			this.type = 'p';
	}
	
	public char getType()
	{
		return type;
	}
	
}
