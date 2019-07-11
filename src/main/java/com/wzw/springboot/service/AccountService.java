package com.wzw.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wzw.springboot.entity.Account;
import com.wzw.springboot.entity.AccountExample;
import com.wzw.springboot.mapper.AccountMapper;
import com.wzw.springboot.utils.RespStat;

@Service
public class AccountService {

	@Autowired
	AccountMapper accountMapper;
	
	public List<Account> findAll() {
		AccountExample example = new AccountExample();
		return accountMapper.selectByExample(example );
	}

	public Account validataAccount(String loginName, String password) {
		AccountExample example = new AccountExample();
		example.createCriteria()
		.andLoginNameEqualTo(loginName)
		.andPasswordEqualTo(password);
		List<Account> selectByExample = accountMapper.selectByExample(example);
		return selectByExample.size()==0 ? null : selectByExample.get(0);
	}

	public PageInfo<Account> findByPage(int pageNum, int pageSize) {
		System.out.println("pageNum:"+pageNum);
		System.out.println("pageSize:"+pageSize);
		//分页插件
		PageHelper.startPage(pageNum, pageSize);
		AccountExample example = new AccountExample();
		//PageInfo<>(list,10) 表示下面分页栏最多显示10个 多了往下10个里排  以此类推
		PageInfo<Account> pageInfo = new PageInfo<>(accountMapper.selectByExample(example),10);
		return pageInfo;
	}

	public RespStat delById(Integer userId) {
		int deleteByPrimaryKey = accountMapper.deleteByPrimaryKey(userId);
		System.out.println("deleteByPrimaryKey:"+deleteByPrimaryKey);
		if(deleteByPrimaryKey==0) {
			return RespStat.getInstance().bulid("500", "删除失败！");
		}else {
			return RespStat.getInstance().bulid("200", "删除成功！");
		}
	}

	public void update(Account account) {
		try {
			accountMapper.updateByPrimaryKey(account);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
