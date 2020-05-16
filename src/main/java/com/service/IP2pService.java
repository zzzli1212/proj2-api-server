package com.service;

import com.common.ServerResponse;

import java.io.IOException;

public interface IP2pService {
	ServerResponse<String> getP2pID(String inputID) throws IOException;
	ServerResponse<String> deleteByID(String inputID) throws IOException;
	ServerResponse<String> deleteAllID() throws IOException;
}
