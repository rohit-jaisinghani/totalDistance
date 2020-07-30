package com.example.DailyDistance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DistanceModel {
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String start_date;
	private String start_time;
	private String end_time;
	private double distance;

}
