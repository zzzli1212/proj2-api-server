package com.service.impl;

import com.common.ServerResponse;
import com.service.IP2pService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

@Service("iP2pService")
public class P2pServiceImpl implements IP2pService {

	final String environmentRoot = System.getProperty("user.dir");
	final String filePath = environmentRoot + "/P2pID.txt";
	//final String filePath = "/Users/wendazheng/Documents/IntelliJ/workplace/YelpRecommedationService/P2pID.txt";

	@Override
	public ServerResponse<String> getP2pID(String inputID) throws IOException {
		File file = new File(filePath);
		if(!file.exists()) {
			file.createNewFile();
		}

		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String str = "";
		StringBuilder sb = new StringBuilder(" " + inputID);
		if((str = br.readLine()) != null) {
			String[] strArray = str.split(" ");
			List<String> list = Arrays.asList(strArray);
			if(list.contains(inputID)) {
				return ServerResponse.createByErrorMessage("Input ID is invalid: Duplicate ID Found");
			}
			sb.append(" " + str);
		}
		br.close();

		FileWriter fileWriter = new FileWriter(filePath, false);
		fileWriter.write(sb.toString().trim());
		fileWriter.close();
		if(str == null || str.length() == 0){
			return ServerResponse.createByErrorMessage("There is No Online People");
		}

		return ServerResponse.createBySuccess("Successfully Found Other Online People", str);
	}

	@Override
	public ServerResponse<String> deleteByID(String inputID) throws IOException {
		File file = new File(filePath);
		if(!file.exists()) {
			return ServerResponse.createByErrorMessage("No File Found. Please first create file");
		}

		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String str = "";
		StringBuilder sb = new StringBuilder();
		if((str = br.readLine()) != null) {
			String[] strArray = str.split(" ");
			List<String> list = new LinkedList<String>(Arrays.asList(strArray));
			if(!list.contains(inputID)) {
				return ServerResponse.createByErrorMessage("No Input ID Found in the File");
			}
			list.remove(inputID);
			for(String ele : list) {
				sb.append(" " + ele);
			}
		}
		br.close();

		FileWriter fileWriter = new FileWriter(filePath, false);
		fileWriter.write(sb.toString().trim());
		fileWriter.close();

		return ServerResponse.createBySuccess("Successfully Deleted ID: " + inputID);
	}

	@Override
	public ServerResponse<String> deleteAllID() throws IOException {
		File file = new File(filePath);
		if(file.delete()) {
			return ServerResponse.createBySuccess("File deleted successfully");
		}
		else {
			return ServerResponse.createByErrorMessage("File Not Exist; Failed to delete the file");
		}
	}
}
