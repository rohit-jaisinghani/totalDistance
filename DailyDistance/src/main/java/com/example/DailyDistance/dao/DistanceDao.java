package com.example.DailyDistance.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.DailyDistance.DTO.TotalDistance;
import com.example.DailyDistance.model.DistanceModel;



@Repository
public interface DistanceDao  extends JpaRepository<DistanceModel, Integer>{
	
	 @Modifying
	 @Transactional
	 @Query("UPDATE DistanceModel c SET c.end_time = :end_time WHERE c.id = :id")
	public void updatetime(String end_time,int id);
	 
	 @Query("select new com.example.DailyDistance.model.DistanceModel(c.id,c.name,c.start_date,c.start_time,c.end_time,c.distance) from DistanceModel c where name=:name and distance=0")
		public List<DistanceModel> findbyname(String name);
	 
	 @Modifying
	 @Transactional
	 @Query("UPDATE DistanceModel c SET c.distance = :distance WHERE c.end_time = :end_time")
	public void updatedistance(double distance,String end_time);
	 
	 @Query("select new com.example.DailyDistance.model.DistanceModel(c.id,c.name,c.start_date,c.start_time,c.end_time,c.distance) from DistanceModel c where id=:id")
		public DistanceModel getStart(int id);
	 
	 @Query("select new com.example.DailyDistance.DTO.TotalDistance(sum(c.distance)) from DistanceModel c where name=:name")
		public TotalDistance total(String name);

}
