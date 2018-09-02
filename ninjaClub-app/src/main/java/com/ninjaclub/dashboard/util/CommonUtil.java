package com.ninjaclub.dashboard.util;

import java.util.Scanner;

import com.ninjaclub.dashboard.constants.MenuConstants;
import com.ninjaclub.dashboard.model.enums.ResponseCode;
import com.ninjaclub.dashboard.service.common.nao.ResultNAO;

public class CommonUtil {

	public final Scanner sc; // = new Scanner(System.in);
	
	 public CommonUtil()
     {
        this(new Scanner(System.in));
     }

	 CommonUtil(Scanner scanner)
     {
         this.sc  = scanner;
     }

     public String getInput() {

         return sc.nextLine();
     }

	public ResultNAO validateUserInputIsInt() {
		ResultNAO result = new ResultNAO();
		String userInput;
		userInput = sc.nextLine();
		if(userInput.matches("\\d+")) {
			result.setData(Integer.parseInt(userInput));
			result.setCode(ResponseCode.SUCCESS);
			return result;
		}
		else {

			System.out.println(MenuConstants.INVALID_CHOICE);
			result.setCode(ResponseCode.ERROR);
			return validateUserInputIsInt();

		}

	}
}
