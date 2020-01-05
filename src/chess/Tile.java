package me.pepe.games.chess;

import javax.swing.*;

public class Tile extends JButton {
	
	private String tile;
	private int X;
	private int Y;
	
	// Vars if it is a king
	private boolean canKingCastle;
	private boolean canDoShortCastling;
	private boolean canDoLongCastling;
	
	public Tile() {}
	
	public void setTile(String tile) {
		this.tile = tile;
	}
	
	public String getTile() {
		return this.tile;
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
