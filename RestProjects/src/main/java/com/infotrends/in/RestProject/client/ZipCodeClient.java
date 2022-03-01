package com.infotrends.in.RestProject.client;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.infotrends.in.RestProject.model.ZipCodeResponseModel;

//@EnableFeignClients
@FeignClient(url = "http://api.zippopotam.us")
//@Component
public interface ZipCodeClient {

	@PostMapping("/{countryCode}/{pincode}")
	public ZipCodeResponseModel getData(@PathVariable("countryCode") String countryCode, @PathVariable("pincode") String pincode);
}
