package com.wzw.springboot.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.wzw.springboot.entity.Account;
import com.wzw.springboot.service.AccountService;
import com.wzw.springboot.utils.RespStat;

@Controller
@RequestMapping("/account")
public class AccoutController {
	@Autowired
	AccountService accountSrv;
	
	@RequestMapping("/login")
	public String login() {
		return "account/login";
	}
	
	@RequestMapping("validataAccount")
	@ResponseBody
	public String validataAccount(String loginName,String password,HttpServletRequest request) {
		System.out.println("loginName"+loginName);
		System.out.println("password"+password);
		Account account = accountSrv.validataAccount(loginName,password);
		//判断登录用户是否存在
		if(account==null) {
			return "faild";
		}else {
			request.getSession().setAttribute("account", account);
			return "success";
		}
	}
	
	/*
	 * @RequestMapping("/list") public String list(Model
	 * map) { List<Account> accounts = accountSrv.findAll();
	 * System.out.println("accounts:"+accounts); map.addAttribute("accounts",
	 * accounts); return "account/list"; }
	 */
	
	@RequestMapping("/list")
	public String list(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize, Model map) {
		PageInfo<Account> pages = accountSrv.findByPage(pageNum,pageSize);
		map.addAttribute("pages", pages);
		return "account/list";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute("account");
		return "account/login";
	}
	
	@RequestMapping("/delById")
	@ResponseBody
	public RespStat delById(Integer userId) {
		System.out.println("userId："+userId);
		RespStat respStat = accountSrv.delById(userId);
		return respStat;
	}
}
