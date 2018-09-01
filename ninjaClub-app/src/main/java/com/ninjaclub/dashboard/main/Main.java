package com.ninjaclub.dashboard.main;

import java.util.InputMismatchException;

import com.ninjaclub.dashboard.model.Player;
import com.ninjaclub.dashboard.util.GameUtil;

public class Main {

	public static void main(String[] args) {
		Player player = new Player();
		Player enemy = new Player();
		GameUtil gameUtil = new GameUtil();
		try {
			
			gameUtil.start(player, enemy);
			
		}catch(InputMismatchException e) {
			System.out.println("Hey! I think you are not entering valid input. Something went wrong, we have to start again,\nAre you not following the steps? \n Hint: " +e.getMessage());
			System.exit(0);
		}
		catch(Exception e) {
			System.out.println("Something went wrong, we have to start again Are you not following the steps? \n Hint: " +e.getMessage());
			System.exit(0);
		}

	}

}
