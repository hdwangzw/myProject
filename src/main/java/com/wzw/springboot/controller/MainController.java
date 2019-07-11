package com.wzw.springboot.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wzw.springboot.entity.Account;
import com.wzw.springboot.service.AccountService;

@Controller
@RequestMapping("/")
public class MainController {
	
	@RequestMapping("index")
	public String index() {
		return "index";
	}
}
