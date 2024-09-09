package com.app.counsellor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.counsellor.dto.DashboardResponse;
import com.app.counsellor.entity.Counsellor;
import com.app.counsellor.service.CounsellorService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellorController {
	//@Autowired
	private CounsellorService counsellorService;
	@Autowired
	public CounsellorController(CounsellorService counsellorService) {
		super();
		// TODO Auto-generated constructor stub
		this.counsellorService=counsellorService;
	}


	
	
	@GetMapping("/")       //fire url from broser
     public String index(Model model)       //send html page to view
     {
		//sending data fron controller to ui mapping object to form
		
		Counsellor cobj=new Counsellor();
		model.addAttribute("counsellor",cobj);
		
		//index.html page
		
    	 return "index";
     }
	@PostMapping("/login")
	public String handlelogin(@ModelAttribute("counsellor") Counsellor counsellor ,HttpServletRequest req,Model model)
	{
		Counsellor c = counsellorService.login(counsellor.getEmail(), counsellor.getPwd());
		if(c==null)
		{
			model.addAttribute("emsg","Invalid Credential");
			return "index";
			
		}
		else
		{
		//valid login need to store id for session
		HttpSession session = req.getSession(true);
			session.setAttribute("counsellorId", c.getCounsellorId());
			
			
//			DashboardResponse dbobj = counsellorService.getDashboardInfo(c.getCounsellorId());
//			model.addAttribute("dashboardInfo", dbobj);
			return "redirect:/dashboard";
		}
	}
	
	
	@GetMapping("/dashboard") 
	public String getdashboard(HttpServletRequest req,Model model)
	{
		HttpSession session=req.getSession(false);
	Integer counsellorId=(Integer) session.getAttribute("counsellorId");
		DashboardResponse dbobj = counsellorService.getDashboardInfo(counsellorId);
		model.addAttribute("dashboardInfo", dbobj);
		return "dashboard";
		}
	
	
	@GetMapping("/register")    //fire url from broser
    public String registerpage(Model model)   //send html page to view
    {
	
		Counsellor cobj=new Counsellor();
		model.addAttribute("counsellor",cobj);
		return "register";
    }
	
	
	@PostMapping("/register")
	public String registerhandle( Counsellor counsellor,Model model)
	{
		
		Counsellor byemail=counsellorService.findByEmail(counsellor.getEmail());
		if(byemail!=null)
		{
			model.addAttribute("emsg","duplicate email");
			return "register";
		}
		boolean isregister = counsellorService.registration(counsellor);
		
		
		
		if(isregister)
		{
			model.addAttribute("smsg","register successfully");
		}
		else
		{
			model.addAttribute("emsg","register failed");
		}
		return "register";
	}
	
	
	
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest req)
	{
		HttpSession session = req.getSession(false);
		session.invalidate();
		return "redirect:/";
	}
	
}
