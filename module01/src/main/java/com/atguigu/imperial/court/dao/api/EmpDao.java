package com.atguigu.imperial.court.dao.api;

import com.atguigu.imperial.court.entity.Emp;

/**
 * @author lifeifan
 * @Description
 * @create 2022-05-11 20:46
 */
public interface EmpDao {
    public Emp selectEmpByLoginAccount(String loginAccount, String encodedLoginPassword);
}
