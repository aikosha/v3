package com.me.chessview;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;

/** @author Patrick
 * 
 *  Creates and holds a list of piece objects*/


public class PieceController {

	public ArrayList<Piece> pieces = new ArrayList<Piece>();
	public ArrayList<Vector2> movPositions = new ArrayList<Vector2>();
	public ArrayList<Piece> removedW = new ArrayList<Piece>();
	public ArrayList<Piece> removedB = new ArrayList<Piece>();
	
	private int currentTeam = 0;
	private boolean moveMade = false;
	private float size = (Gdx.graphics.getWidth()/8);
	private Board board;
	private int removedCounterW = 0;
	private int removedCounterB = 0;
	
	private FileHandle[] img = {Gdx.files.internal("data/whiterook.png"),Gdx.files.internal("data/whiteknight.png"),
			Gdx.files.internal("data/whitebishop.png"), Gdx.files.internal("data/whitequeen.png"),
			Gdx.files.internal("data/whiteking.png"), Gdx.files.internal("data/whitepawn.png"),
			Gdx.files.internal("data/blackrook.png"),Gdx.files.internal("data/blackknight.png"),
			Gdx.files.internal("data/blackbishop.png"), Gdx.files.internal("data/blackqueen.png"),
			Gdx.files.internal("data/blackking.png"), Gdx.files.internal("data/blackpawn.png")};
	
	
	public PieceController(Board b)
	{
		populateBoard();
		initialisePieces();
		this.board = b;

	}

	public void populateBoard(){
		/*
		 * The following function populates the board with
		 * generic 'pieces', they are given only team and x,y positions
		 * the i for loop is for populating both 'sides' pieces
		 * the y loop is for y co-ordinates
		 * the x loop is for x co-ordinates
		 */
		Piece piece;
		
		int team = 0; //for whites

		for(float y = 0 ; y < 2 ; y++){
			for(float x = 0 ; x < 8 ; x++)
			{
				piece = new Piece(x * size, y * size);
				piece.setTeam(team);
				pieces.add(piece);
			}

		}

		team = 1;		//second iteration, so we are populating the black pieces
		
		for(float y = 7 ; y > 5 ; y--){
			for(float x = 0 ; x < 8 ; x++){
				piece = new Piece(x * size, y * size);
				piece.setTeam(team);
				pieces.add(piece);
			}

		}

	}

	public void initialisePieces(){

		int j = 0;
		/*
		 * The following code  iterates through the objects list and
		 * assigns them with the correct image from the image bank
		 */
		for(int i = 0 ; i < 16 ; i++){
			pieces.get(i).setImageId(img[j]);
			pieces.get(i).setType(j);//used for calculating possible moves for pieces

			if(i < 4) j++;
			if(i == 4) j = 3;
			if(i >= 4 && i < 7) j--;			
			if(i >= 7 && i < 16) j = 5;
		}
		
		j = 6;
		
		for(int i = 16 ; i < 32 ; i++){
			
			pieces.get(i).setImageId(img[j]);
			pieces.get(i).setType(j);//used for calculating possible moves for pieces
			
			if(i < 20) j++;
			if(i == 20) j = 8;
			if(i > 20 && i < 23) j--;
			if(i >= 23 && i < 32) j = 11;

		}

	}
	
	/*initialising moveblePos array numbers to zero*/
	public void initialiseMovPositions()
	{
		movPositions.clear();
	}
	

	
	/* Method to change turns */
	public int changeTurn()
	{
			if (currentTeam == 0 && moveMade )
			{
				currentTeam = 1;
				moveMade = false;
			}
			else if (currentTeam == 1 && moveMade)
			{
				currentTeam = 0;
				moveMade = false;
			}
			return currentTeam;
	}
	
	/*
	 * Method to calculate available positions for chosen piece
	*/
	public void getMovPos(Piece p)
	{
		int inX = (int)(p.getPosition().x/size);
		int inY = (int)(p.getPosition().y/size);
		int newX;
		int newY;
		switch (p.getType())
		{
			case 'p':
				
				if (p.getTeam() == 0)
				{
					if (inX > 0)
					{
						if (pawnAttack (inX-1, inY+1))
						{
							newX = inX - 1;
							newY = inY + 1;
							movPositions.add(new Vector2(newX, newY));
						}
					}
					
					if (inX < 7)
					{
						if (pawnAttack (inX+1, inY+1))
						{
							newX = inX + 1;
							newY = inY + 1;
							movPositions.add(new Vector2(newX, newY));
						}
					}
				
					if ( !(pawnAttack (inX, inY+1)) )
					{
						newX = inX;
						newY = inY + 1;
						if (newY < 8)
						{
							movPositions.add(new Vector2(inX, newY));
						}
					}
				}
				
				else if (p.getTeam() == 1)
				{
					if ( inX > 0 )
					{
						if (pawnAttack (inX-1, inY-1))
						{
							newX = inX - 1;
							newY = inY - 1;
							movPositions.add(new Vector2(newX, newY));
						}
					}
					
					if ( inX < 7 )
					{
						if (pawnAttack (inX+1, inY-1))
						{
							newX = inX + 1;
							newY = inY - 1;
							movPositions.add(new Vector2(newX, newY));
						}
					}
					
					if ( !(pawnAttack (inX, inY-1)) )
					{
						newY = inY - 1;
						if (newY >= 0)
						{
							movPositions.add(new Vector2(inX, newY));
						}
					}
				}
				break;
				
			case 'k':	
				for (int i = -1; i < 2; i++)
				{
					for (int j = -1; j < 2; j++)
					{
						newX = inX + i;
						newY = inY + j;
						movPositions.add(new Vector2(newX, newY));
					
					}
				}
				break;
			case 'r':
				for (int i = -7; i < 8; i++)
				{
					if(i != 0)
					{
						newX = inX + i;
						if(newX > 0)
						{
							movPositions.add(new Vector2(newX, inY));
						}
						newY = inY + i;
						{
							movPositions.add(new Vector2(inX, newY));
						}
					}
				}
				break;
			case 'b':
				for(int i = -7; i < 8; i++)
				{
					for (int j = -7; j < 8; j++)
					{
						if (i != 0)
						{
							if ( i == j || i == -j )
							{
								newX = inX + i;
								newY = inY + j;
								if (newX > 0 || newY > 0)
								{
									movPositions.add(new Vector2(newX, newY));
								}
							}
						}
					}
				}
				break;
			case 'q':
				for (int i = -7; i < 8; i++)
				{
					if(i != 0)
					{
						newX = inX + i;
						if(newX > 0)
						{
							movPositions.add(new Vector2(newX, inY));
						}
						newY = inY + i;
						{
							movPositions.add(new Vector2(inX, newY));
						}
					}
				}

				for(int i = -7; i < 8; i++)
				{
					for (int j = -7; j < 8; j++)
					{
						if (i != 0)
						{
							if ( i == j || i == -j )
							{
								newX = inX + i;
								newY = inY + j;
								if (newX > 0 || newY > 0)
								{
									movPositions.add(new Vector2(newX, newY));
								}
							}
						}
					}
				}
				break;
			case 'n':
				for(int i = -1; i < 2; i++)
				{
					if(i != 0)
					{
						newX = inX + i;
						newY = inY + 2*i;
						movPositions.add(new Vector2(newX, newY));
						newY = inY - 2*i;
						movPositions.add(new Vector2(newX, newY));
					}	
				}
				for(int i = -1; i < 2; i++)
				{
					if(i != 0)
					{
						newX = inX + 2*i;
						newY = inY + i;
						movPositions.add(new Vector2(newX, newY));
						newY = inY - i;
						movPositions.add(new Vector2(newX, newY));
					}	
				}
				break;
			
		}
	}
	
	public void setMoveMade()
	{
		this.moveMade = true;
	}
	
	public void removePiece(Piece p)
	{
		int counter;
		if ( p.getTeam() == 0)
		{
			counter = removedW.size();
			p.setPosition( new Vector2((counter * size) + 2, 292));
			removedW.add(p);
		}
		else
		{
			counter = removedB.size();
			p.setPosition( new Vector2(counter * size, 340));
			removedB.add(p);
		}
	}
	
	public int getCurrentTeam()
	{
		return currentTeam;
	}
	
	public boolean pawnAttack (float x, float y)
	{
		if(board.tiles[(int) x][(int) y].isOccupied())
			{
				return true;
			}
		else
		{
			return false;
		}
	}
	
	/* Method to check if there is another piece between chosen piece and its destination*/
	public boolean noPieceBetween(Piece p, float x, float y)
	{
		boolean noPiece = true;
		char ptype = p.getType();
		int xd = (int) (x/size);
		int yd = (int) (y/size);
		int xi = (int) (p.getPosition().x / size);
		int yi = (int) (p.getPosition().y / size);
		
		switch (ptype)
		{
			/* ROOK */
			case 'r':
				/*checking by x coordinate*/
				if(xi > xd)
				{
					for(int i = (xi - 1); i > xd; i--)
					{
						if(board.tiles[i][yd].isOccupied())
						{
							noPiece = false;
							break;
						}
					}
				}
				else if (xi < xd)
				{
					for(int i = (xi + 1); i < xd; i++)
					{
						if(board.tiles[i][yd].isOccupied())
						{
							noPiece = false;
							break;
						}
					}
				}
				/*checking by y coordinate*/
				if(yi > yd)
				{
					for(int i = (yi - 1); i > yd; i--)
					{
						if(board.tiles[xd][i].isOccupied())
						{
							noPiece = false;
							break;
						}
					}
				}
				else if (yi < yd)
				{
					for(int i = (yi + 1); i < yd; i++)
					{
						if(board.tiles[xd][i].isOccupied())
						{
							noPiece = false;
							break;
						}
					}
				}
				
			/* BISHOP */
			case 'b':
				if (xi < xd)
				{
					if( yi < yd)
					{
						for( int i = 1; i < xd-xi; i++)
						{
							if(board.tiles[xi+i][yi+i].isOccupied())
							{
								noPiece = false;
								break;
							}
						}
					}
					else if ( yi > yd)
					{
						for( int i = 1; i < xd-xi; i++)
						{
							if(board.tiles[xi+i][yi-i].isOccupied())
							{
								noPiece = false;
								break;
							}
						}
					}
				}
				else if(xi > xd)
				{
					if( yi > yd)
					{
						for( int i = 1; i < xi-xd; i++)
						{
							if(board.tiles[xi-i][yi-i].isOccupied())
							{
								noPiece = false;
								break;
							}
						}
					}
					else if ( yi < yd)
					{
						for( int i = 1; i < xi-xd; i++)
						{
							if(board.tiles[xi-i][yi+i].isOccupied())
							{
								noPiece = false;
								break;
							}
						}
					}
				}
				
			/* QUEEN */
			case 'q':
				/*checking if moving horizontally like rook*/
				if(xi > xd && yi == yd)
				{
					for(int i = (xi - 1); i > xd; i--)
					{
						if(board.tiles[i][yd].isOccupied())
						{
							noPiece = false;
							break;
						}
					}
				}
				else if (xi < xd && yi == yd)
				{
					for(int i = (xi + 1); i < xd; i++)
					{
						if(board.tiles[i][yd].isOccupied())
						{
							noPiece = false;
							break;
						}
					}
				}
				/*checking if moving vertically like rook*/
				if(yi > yd && xi == xd)
				{
					for(int i = (yi - 1); i > yd; i--)
					{
						if(board.tiles[xd][i].isOccupied())
						{
							noPiece = false;
							break;
						}
					}
				}
				else if (yi < yd && xi == xd)
				{
					for(int i = (yi + 1); i < yd; i++)
					{
						if(board.tiles[xd][i].isOccupied())
						{
							noPiece = false;
							break;
						}
					}
				}
				/* checking if moving diagonnaly like bishop */
				if (xi < xd && yi != yd)
				{
					if( yi < yd)
					{
						for( int i = 1; i < xd-xi; i++)
						{
							if(board.tiles[xi+i][yi+i].isOccupied())
							{
								noPiece = false;
								break;
							}
						}
					}
					else if ( yi > yd)
					{
						for( int i = 1; i < xd-xi; i++)
						{
							if(board.tiles[xi+i][yi-i].isOccupied())
							{
								noPiece = false;
								break;
							}
						}
					}
				}
				else if(xi > xd && yi != yd)
				{
					if( yi > yd)
					{
						for( int i = 1; i < xi-xd; i++)
						{
							if(board.tiles[xi-i][yi-i].isOccupied())
							{
								noPiece = false;
								break;
							}
						}
					}
					else if ( yi < yd)
					{
						for( int i = 1; i < xi-xd; i++)
						{
							if(board.tiles[xi-i][yi+i].isOccupied())
							{
								noPiece = false;
								break;
							}
						}
					}
				}
				
		}
		
		return noPiece;
	}
	
}