package com.example.DailyDistance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.DailyDistance.DTO.TotalDistance;
import com.example.DailyDistance.dao.DistanceDao;
import com.example.DailyDistance.model.DistanceModel;
import com.example.DailyDistance.service.DistanceService;


@RestController
public class DistanceController {
	
	@Autowired
	private DistanceDao distancedao;
	@Autowired
	private DistanceService distanceservice;
	
	@PostMapping("/postDate")
	public ResponseEntity<String> placeOrder(@RequestBody DistanceModel date) {
		try {
			if (null != date) {
				distancedao.save(date);
				return new ResponseEntity<>("Inserted!", HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>("Invalid request!", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Invalid request ", HttpStatus.BAD_REQUEST);
		}
		}
	
	
	@GetMapping("/getDate")
	public List<DistanceModel> getOrder() {
		return distancedao.findAll();
	}
	
	@PutMapping(path = "/timeUpdate")
	public double updatetime(@RequestParam(value="end_time", required = false) String end_time,@RequestParam(value="name", required = false) String name){
		if(end_time!=null && name!=null) {
			System.out.println("controller"+end_time);
		double d=distanceservice.getDistance(end_time, name);
		System.out.println(d);
		return d;
		}
		else {
			return 0;
		}	
	}
	
	
		@GetMapping(value="/buynow/{name}")
	  public TotalDistance getTotalDistance(@PathVariable(value="name") String name) { 
		   return distancedao.total(name);
		  }
	 

}
