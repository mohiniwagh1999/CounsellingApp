package com.app.counsellor.service;

import java.util.List;

import com.app.counsellor.dto.ViewEnqFilterRequest;
import com.app.counsellor.entity.Counsellor;
import com.app.counsellor.entity.Enquiry;

public interface EnquiryService {
	
	public boolean addEnquiry(Enquiry enq, Integer counsellorId) throws Exception;
	
	public List<Enquiry> getAllEnquiries(Integer counsellorId);
	

	public Enquiry getEnquiryById(Integer enqId);
	
	List<Enquiry> getEnquiriesWithFilter(ViewEnqFilterRequest filterReq, Integer counsellorId);

}
