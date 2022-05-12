package com.atguigu.imperial.court.service.api;

import com.atguigu.imperial.court.entity.Memorials;

import java.util.List;

/**
 * @author lifeifan
 * @Description
 * @create 2022-05-12 9:11
 */
public interface MemorialsService {
    List<Memorials> getAllMemorialsDigest();

    Memorials getMemorialsDetailById(String memorialsId);

    void updateMemorialsStatusToRead(String memorialsId);

    void updateMemorialsFeedBack(String memorialsId, String feedbackContent);
}
