package me.pepe.games.chess;

import javax.swing.*;

public class Tile extends JButton {
	
	private String piece;
	private int X;
	private int Y;
	
	// Vars if it is a king
	private boolean canKingCastle;
	private boolean canDoShortCastling;
	private boolean canDoLongCastling;
	
	public Tile() {
		// To avoid NullPointerExcep
		this.piece = "-";
	}
	
	public boolean hasPiece() {
		return !piece.equals("null");
	}
	
	public void setPiece(String piece) {
		this.piece = piece;
		if(piece.equals("null")) {
			setIcon(null);
		} else {						
			setIcon(Chess.ICONS.get(this.piece));
		}
	}
	
	public String getPiece() {
		return this.piece;
	}
	
	public String getTeam() {
		return this.piece.substring(0, 1);
	}
	
	public void setLocX(int x) {
		this.X = x;
	}
	
	public int getLocX() {
		return this.X;
	}
	
	public void setLocY(int y) {
		this.Y = y;
	}
	
	public int getLocY() {
		return this.Y;
	}
}
