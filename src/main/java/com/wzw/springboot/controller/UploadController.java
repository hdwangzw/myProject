package com.wzw.springboot.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.wzw.springboot.entity.Account;
import com.wzw.springboot.service.AccountService;

@Controller
@RequestMapping("/upload")
public class UploadController {
	
	//读取application文件里的属性
	@Value("${uploadFile}")
	private String uploadFile;
	
	@Autowired
	AccountService accountSrv;
	
	@RequestMapping("/profile")
	public String profile(MultipartFile filename,String password) {
		try {
			  File path = new File(ResourceUtils.getURL("classpath:").getPath());
		        File upload = new File(path.getAbsolutePath(), "static/uploads/");
		        System.out.println(upload.getAbsolutePath());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		return "account/profile";
	}

	@RequestMapping("/fileUploadController")
	public String fileUpload(MultipartFile filename,String password,HttpServletRequest request) {
		
		System.out.println("password：" + password);
		System.out.println("filename：" + filename.getOriginalFilename());

		
		 Account account = (Account) request.getSession().getAttribute("account");
		System.out.println("accountSeesion:"+account);
		
		try {
			File path = new File(ResourceUtils.getURL("classpath:").getPath());
			File upload = new File(path.getAbsolutePath(), "static/uploads/");// static/uploads/ 注意必须加 / 这个斜杠 否则报错 找不到路径
			System.out.println("upload：" + upload);
			// 转存
			//filename.transferTo(new File(upload + "/" + filename.getOriginalFilename())); //项目路径
			System.out.println("uploadFile:"+uploadFile);
			filename.transferTo(new File(uploadFile + filename.getOriginalFilename())); //本地路径 也就是 服务器路径
			System.out.println("uploadFile2:"+uploadFile + filename.getOriginalFilename());
			
			account.setPhoto(filename.getOriginalFilename());
			accountSrv.update(account);
		} catch (IllegalStateException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "account/profile";
		 
	}
}
