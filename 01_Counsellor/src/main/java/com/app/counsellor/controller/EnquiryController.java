package com.app.counsellor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.counsellor.dto.DashboardResponse;
import com.app.counsellor.dto.ViewEnqFilterRequest;
import com.app.counsellor.entity.Counsellor;
import com.app.counsellor.entity.Enquiry;
import com.app.counsellor.service.CounsellorService;
import com.app.counsellor.service.EnquiryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;



@Controller
public class EnquiryController {

	
	//@Autowired
		private EnquiryService enqService;
		@Autowired
		public EnquiryController(EnquiryService enqService   ) {
			super();
			// TODO Auto-generated constructor stub
			this.enqService=enqService;
			///this. counsellerService=counsellerService;
		}
		
		@PostMapping("/view")
		public String filterenquiry( @ModelAttribute("viewfilter") ViewEnqFilterRequest viewRequest,HttpServletRequest req,Model model)
		{
			HttpSession session=req.getSession(false);
			Integer counsellorId=(Integer) session.getAttribute("counsellorId");
			List<Enquiry> enqList = enqService.getEnquiriesWithFilter(viewRequest, counsellorId);
			model.addAttribute("enquiries",enqList);
			
			return "view";
		}
		
		
      
	@GetMapping("/view") 
       public String getnquiries(HttpServletRequest req,Model model)
	{
			
			HttpSession session=req.getSession(false);
			Integer counsellorId=(Integer) session.getAttribute("counsellorId");
			
			 List<Enquiry> allEnquiries = enqService.getAllEnquiries(counsellorId);
			 model.addAttribute("enquiries",allEnquiries);
			 
			 ViewEnqFilterRequest filter=new ViewEnqFilterRequest();
			 model.addAttribute("viewfilter",filter);
		 
			
   	   return "view";
       }
		
		
		@GetMapping("/enquiry")       //fire url from broser
	     public String addEnquiry(Model model)       //send html page to view
	     {
			//sending data fron controller to ui mapping object to form
			
			Enquiry enobj=new Enquiry();
			model.addAttribute("enq",enobj);
			
			//index.html page
			
	    	 return "enquiry";
	     }
		
		@GetMapping("/edit")       //fire url from broser
	     public String editEnquiry(@RequestParam("enqId") Integer enqId ,Model model)       //send html page to view
	     {
			//sending data fron controller to ui mapping object to form
			
		Enquiry enquiry=enqService.getEnquiryById(enqId);
			model.addAttribute("enq",enquiry);
			
			//index.html page
			
	    	 return "enquiry";
	     }
		
		
		
		
		@PostMapping("/addenq")
		public String handleaddenquiry(@ModelAttribute("enq") Enquiry enquiry,
				HttpServletRequest req  ,Model model) throws Exception
		{
			//get existing session
			HttpSession session=req.getSession(false);
			Integer counsellorId=(Integer) session.getAttribute("counsellorId");
			
			boolean issaved = enqService.addEnquiry(enquiry,  counsellorId);
			if(issaved)
			{
				model.addAttribute("smsg","enquiry added sucessfully");
			}
			else
			{
				model.addAttribute("emsg","failed to add enquiry");
			}
			
		Enquiry enobj=new Enquiry();
		model.addAttribute("enq",enobj);
		return "enquiry";
		}
		
		
		
	

}
