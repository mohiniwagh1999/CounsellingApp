package com.app.counsellor.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.counsellor.dto.DashboardResponse;
import com.app.counsellor.entity.Counsellor;
import com.app.counsellor.entity.Enquiry;
import com.app.counsellor.repo.CounsellorRepo;
import com.app.counsellor.repo.EnquiryRepo;

@Service
public class CounsellorServiceImpl implements CounsellorService {

	
	
//	@Autowired
	private CounsellorRepo counsellorRepo;
//	@Autowired
	private EnquiryRepo enqRepo;
	
	public CounsellorServiceImpl(CounsellorRepo counsellorRepo,EnquiryRepo enqRepo) {
		super();
		// TODO Auto-generated constructor stub
		
		this.counsellorRepo=counsellorRepo;
		this. enqRepo= enqRepo;
	}
	
	

	public Counsellor findByEmail(String email)
	{
		return counsellorRepo.findByEmail(email);
	}
	
	@Override
	public boolean registration(Counsellor Counsellor) {
		// TODO Auto-generated method stub
		Counsellor saved= counsellorRepo.save(Counsellor);
		if(null!=saved.getCounsellorId())
		{
			return true;
		}
		return false;
	}

	@Override
	public Counsellor login(String email, String pwd) {
		// TODO Auto-generated method stub
		
		Counsellor counsellor = counsellorRepo.findByEmailAndPwd(email, pwd);
		return counsellor;
	}
	
	
	

	@Override
	public DashboardResponse getDashboardInfo(Integer counsellorId) {
		// TODO Auto-generated method stub
		
		DashboardResponse response=new DashboardResponse();
		List<Enquiry> enqList = enqRepo.getEnquiriesByCounsellorId(counsellorId);
		
		int totalEnqs=enqList.size();
		
		
		
		int enrollEnqs=enqList.stream()
				.filter(e->e.getEnqStatus().equals("enrolled"))
				.collect(Collectors.toList())
				.size();
		
		int openEnqs=enqList.stream()
				.filter(e->e.getEnqStatus().equals("open"))
				.collect(Collectors.toList())
				.size();
		int lostEnqs=enqList.stream()
				.filter(e->e.getEnqStatus().equals("lost"))
				.collect(Collectors.toList())
				.size();
		
		response.setTotalEnqs(totalEnqs);
		response.setEnrolledEnqs(enrollEnqs);
		response.setLostEnqs(lostEnqs);
		response.setOpenEnqs(openEnqs);
		return response;
		
		
	}

}
