package com.service.impl;

import com.common.ServerResponse;
import com.util.YelpFusionAPI;
import com.service.IRecommendService;
import org.json.JSONArray;
import org.springframework.stereotype.Service;

@Service("iRecommendService")
public class RecommendServiceImpl implements IRecommendService {

	@Override
	public ServerResponse<String> getRecommend(String term, String location, String latitude,
												   String longitude, String price) {

		if(location == null || location.length() == 0) {
			if(latitude == null || latitude.length() == 0 || longitude == null || longitude.length() == 0) {
				return ServerResponse.createByErrorMessage("You must specify either location or longitude and latitude");
			}
		}

		if(term == null || price == null || term.length() == 0 || price.length() == 0) {
			return ServerResponse.createByErrorMessage("You must specify term and price");
		}

		JSONArray myResponse;
		if(location == null || location.length() == 0) {
			myResponse = YelpFusionAPI.searchRestaurants(term, latitude, longitude, price);
		} else {
			myResponse = YelpFusionAPI.searchRestaurants(term, location, latitude, longitude, price);
		}

		if(myResponse == null) {
			return ServerResponse.createByErrorMessage("No restaurant found");
		}
		return ServerResponse.createBySuccess("Successfully found restaurants", myResponse.toString());
	}
}
