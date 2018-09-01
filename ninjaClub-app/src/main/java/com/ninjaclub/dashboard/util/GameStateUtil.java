package com.ninjaclub.dashboard.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.ninjaclub.dashboard.model.GameState;

public class GameStateUtil {

	GameState gameState;
	
	public GameStateUtil(GameState gameState) {
		super();
		this.gameState = gameState;
	}
	
	public void readGameState() {
		
	}
	
	public void writeGameState() {
		System.out.println("Saving game state " +gameState.toString());
		FileOutputStream fout = null;
		ObjectOutputStream oos = null;

		try {

			fout = new FileOutputStream("C:\\NinjaClub\\gamestate.ser");
			oos = new ObjectOutputStream(fout);
			oos.writeObject(gameState);

			System.out.println("Saved!!");

		} catch (Exception ex) {

			ex.printStackTrace();

		} finally {

			if (fout != null) {
				try {
					fout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	public void writeGameStateJDK7(GameState gameState) {

		try (ObjectOutputStream oos = 
				new ObjectOutputStream(new FileOutputStream("c:\\temp\\address2.ser"))) {

			oos.writeObject(gameState);
			System.out.println("Done");

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public GameState deserialzeAddress(String filename)throws IOException,
    ClassNotFoundException {

		GameState gameState = null;

		FileInputStream fin = null;
		ObjectInputStream ois = null;

		try {

			fin = new FileInputStream(filename);
			ois = new ObjectInputStream(fin);
			gameState = (GameState) ois.readObject();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			if (fin != null) {
				try {
					fin.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

		return gameState;

	}

	public GameState readGameStateJDK7(String filename) {

		GameState gameState = null;

		try (ObjectInputStream ois 
			= new ObjectInputStream(new FileInputStream(filename))) {

			gameState = (GameState) ois.readObject();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return gameState;

	}



}
