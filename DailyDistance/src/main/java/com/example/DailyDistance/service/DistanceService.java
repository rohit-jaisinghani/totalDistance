package com.example.DailyDistance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DailyDistance.dao.DistanceDao;
import com.example.DailyDistance.model.DistanceModel;

@Service
public class DistanceService {

	@Autowired
	private DistanceDao distancedao;

	public double getDistance(String end_time,String name) {
		System.out.println("service"+ end_time);
		List<DistanceModel> dm=distancedao.findbyname(name);
		System.out.println("service"+dm.toString());
		int id[]=new int[dm.size()];
		int i=0;
		for(DistanceModel dms:dm) {
			id[i]=dms.getId();
			i++;	
		}
		int min_id=id[0];
		for(int j=1;j<id.length;j++) {
			if(id[j]<min_id) {
				min_id=id[j];
			}
		}
		System.out.println(min_id);
		distancedao.updatetime(end_time,min_id);
		DistanceModel s1=distancedao.getStart(min_id);
		String s3=s1.getStart_time();
		String e=end_time.replaceAll(":","");
		int end=Integer.parseInt(e);
		String s=s3.replaceAll(":","");
		int start=Integer.parseInt(s);
		double min_distance=((end-start)*2)/10000;
	  distancedao.updatedistance(min_distance,end_time);
		return min_distance;
	}
	
	
}
