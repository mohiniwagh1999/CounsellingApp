package com.app.counsellor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.app.counsellor.dto.ViewEnqFilterRequest;
import com.app.counsellor.entity.Counsellor;
import com.app.counsellor.entity.Enquiry;
import com.app.counsellor.repo.CounsellorRepo;
import com.app.counsellor.repo.EnquiryRepo;

import io.micrometer.common.util.StringUtils;

@Service
public class EnquiryServiceImpl implements EnquiryService {
    //@Autowired
	private EnquiryRepo enqRepo;
	private CounsellorRepo counsellorRepo;
	
	
	//@Autowired(constructoe enjuction
	public EnquiryServiceImpl(EnquiryRepo enqRepo,CounsellorRepo counsellorRepo) {
		super();
		// TODO Auto-generated constructor stub
		this.enqRepo=enqRepo;
		this.counsellorRepo=counsellorRepo;
	}

	@Override
	public boolean addEnquiry(Enquiry enq, Integer counsellorId) throws Exception {
		// TODO Auto-generated method stub
		Counsellor counsellor = counsellorRepo.findById(counsellorId).orElse(null);
		if(counsellor==null)
		{
			throw new Exception("No Record Found");
		}
		
		enq.setCounsellor(counsellor);
		Enquiry save = enqRepo.save(enq);
		if(save.getEnqId()!=null)
		{
			return true;
		}
		return false;
	}

	@Override
	public List<Enquiry> getAllEnquiries(Integer counsellorId) {
		// TODO Auto-generated method stub
		return enqRepo.getEnquiriesByCounsellorId(counsellorId);
	}

	@Override
	public List<Enquiry> getEnquiriesWithFilter(ViewEnqFilterRequest filterReq,Integer counsellorId) {
		// TODO Auto-generated method stub
		//QBE(Query By Example =Dyanamic Query Preparation)
		
		Enquiry enq=new Enquiry();
		
		if(StringUtils.isNotEmpty(filterReq.getClassMode()))
		{
			enq.setClassMode(filterReq.getClassMode());
		}
		
		
		if(StringUtils.isNotEmpty(filterReq.getEnqStatus()))
		{
			enq.setClassMode(filterReq.getEnqStatus());
		}
		
		if(StringUtils.isNotEmpty(filterReq.getCourseName()))
		{
			enq.setClassMode(filterReq.getCourseName());
		}
		
		Counsellor c=counsellorRepo.findById(counsellorId).orElse(null);
		enq.setCounsellor(c);
		Example<Enquiry> of = Example.of(enq);
		List<Enquiry> all = enqRepo.findAll(of);
		
		return all;
	}

	@Override
	public Enquiry getEnquiryById(Integer enqId) {
		// TODO Auto-generated method stub
		return enqRepo.findById(enqId).orElse(null);
		
	}

}
