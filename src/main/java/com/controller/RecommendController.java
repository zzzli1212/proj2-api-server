package com.controller;

import com.service.IRecommendService;
import com.common.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/yelp-fusion/")
public class RecommendController {

	@Autowired
	private IRecommendService iRecommendService;


	@RequestMapping(value = "requestRecommend.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<String> getRecommend(String term, String location, String latitude,
												   String longitude, String price){
		System.out.println("location = " + location);
		System.out.println("latitude = " + latitude);
		System.out.println("longitude = " + longitude);
		ServerResponse<String> response = iRecommendService.getRecommend(term, location,latitude,
				longitude, price);

		return response;
	}

	@RequestMapping(value = "test.do")
	@ResponseBody
	public ServerResponse<String> getRecommend(){
		String str = "this is port";
		System.out.println(str);
		return ServerResponse.createBySuccess("successfully", str);
	}

}
