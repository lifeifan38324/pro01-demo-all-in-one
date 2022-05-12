package com.atguigu.imperial.court.dao.api;

import com.atguigu.imperial.court.entity.Memorials;

import java.util.List;

/**
 * @author lifeifan
 * @Description
 * @create 2022-05-11 20:50
 */
public interface MemorialsDao {
    List<Memorials> selectAllMemorialsDigest();

    Memorials selectMemorialsById(String memorialsId);

    void updateMemorialsStatusToRead(String memorialsId);

    void updateMemorialsFeedBack(String memorialsId, String feedbackContent);
}
