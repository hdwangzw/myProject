package com.wzw.springboot.mapper;

import com.wzw.springboot.entity.Account;
import com.wzw.springboot.entity.AccountExample;

import org.springframework.stereotype.Repository;

/**
 * AccountMapper继承基类
 */
@Repository
public interface AccountMapper extends MyBatisBaseDao<Account, Integer, AccountExample> {
}