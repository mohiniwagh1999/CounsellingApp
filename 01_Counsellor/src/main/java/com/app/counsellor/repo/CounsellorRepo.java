package com.app.counsellor.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.counsellor.entity.Counsellor;

public interface CounsellorRepo  extends JpaRepository<Counsellor,Integer>{
	//select * from counsellor where email=:email
	public Counsellor findByEmail(String email);
	
	
	//select * from counsellor where email=: email and pwd=:pwd
	public Counsellor findByEmailAndPwd(String email,String pwd);

}
