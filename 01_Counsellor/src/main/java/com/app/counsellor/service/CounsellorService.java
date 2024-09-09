package com.app.counsellor.service;

import com.app.counsellor.dto.DashboardResponse;
import com.app.counsellor.entity.Counsellor;

public interface CounsellorService {
	
	
	public boolean registration(Counsellor Counsellor);
	
	public Counsellor login(String email,String pwd);
	
	
	public DashboardResponse getDashboardInfo(Integer counsellorId);

	public Counsellor findByEmail(String email);

}
