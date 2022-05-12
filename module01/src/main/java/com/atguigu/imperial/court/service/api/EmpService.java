package com.atguigu.imperial.court.service.api;

import com.atguigu.imperial.court.entity.Emp;

/**
 * @author lifeifan
 * @Description
 * @create 2022-05-11 23:44
 */
public interface EmpService {
    public Emp getEmpByLoginAccount(String loginAccount, String loginPassword);
}
