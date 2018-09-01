package com.ninjaclub.dashboard.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.ninjaclub.dashboard.constants.GameConstants;
import com.ninjaclub.dashboard.model.GameState;

public class GameStateUtil {

	GameState gameState;

	public GameStateUtil(GameState gameState) {
		super();
		this.gameState = gameState;
	}

	public void writeGameState() {
		System.out.println("Saving game state " +gameState.toString());
		FileOutputStream fout = null;
		ObjectOutputStream oos = null;
		try {
			fout = new FileOutputStream(GameConstants.FILE_PATH);
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

	public void writeGameStateJDK7() {
		System.out.println("Saving Game... ");
		File file = new File(GameConstants.FILE_PATH);
		file.delete();
		try (ObjectOutputStream oos = 
				new ObjectOutputStream(new FileOutputStream(GameConstants.FILE_PATH))) {
			oos.writeObject(gameState);
			System.out.println("Game saved!!");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public GameState readGameState(){
		GameState gameState = null;
		FileInputStream fin = null;
		ObjectInputStream ois = null;
		try {
			fin = new FileInputStream(GameConstants.FILE_PATH);
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

	public GameState readGameStateJDK7() {
		GameState gameState = null;
		try (ObjectInputStream ois 	= new ObjectInputStream(new FileInputStream(GameConstants.FILE_PATH))) {
			gameState = (GameState) ois.readObject();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return gameState;
	}

}
