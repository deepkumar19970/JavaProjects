package com.application.shophop.services;

import com.application.shophop.model.AppUser;
import com.application.shophop.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {

    @Autowired
    AppUserRepository appUserRepository;

    public AppUser getAppUserByUseridAndAndPassword(String userid,String password){
        return appUserRepository.getAppUserByUseridAndAndPassword(userid,password);
    }


}
