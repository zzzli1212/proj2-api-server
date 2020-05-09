package com.service;

import com.common.ServerResponse;
import org.json.JSONObject;

public interface IRecommendService {
	ServerResponse<String> getRecommend(String term, String location, String latitude,
											String longitude, String price);
}
