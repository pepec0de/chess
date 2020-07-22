package me.pepe.games.chess;

import javax.swing.*;

public class Tile extends JButton {
	
	private String piece;
	private int X;
	private int Y;
	
	/* 
	 * Estudiar como poder hacer las comprobaciones para verificar un enroque (Castling)
	 * Short y Long (booleanos de abajo) son para detectar si esas torres ya han hecho un movimiento
	 * 
	 * SOLO SE PUEDE PRODUCIR EL ENROQUE SI LA TORRE Y EL REY NO HAN HECHO UN MOVIMIENTO PREVIO
	 * */
//	// Vars if it is a King
//	private boolean canKingCastle;
//	private boolean canDoShortCastling;
//	private boolean canDoLongCastling;
//	private boolean isFirstMove;
	private boolean isFirstMove;
	
	public Tile() {
		// To avoid NullPointerException
		this.piece = "";
		
	}
	
	public boolean hasPiece() {
		return !piece.equals("");
	}
	
	public void setPiece(String piece) {
		this.piece = piece;
		if(piece.equals("")) {
			setIcon(null);
		} else {						
			setIcon(Chess.ICONS.get(this.piece));
		}
	}
	
	public String getPiece() {
		return this.piece;
	}
	
	// Tomar el equipo de la pieza
	public String getTeam() {
		if (hasPiece()) {
			return this.piece.substring(0, 1);
		}
		return "";
	}
	
	// Tomar y establecer coordenadas
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
