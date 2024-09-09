package com.app.counsellor.dto;

import lombok.Data;

@Data
public class DashboardResponse {
	
	
	private Integer totalEnqs;
	private Integer openEnqs;
	private Integer enrolledEnqs;
	private Integer lostEnqs;

}