package com.application.shophop.repository;

import com.application.shophop.model.AppUser;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

    @Repository
public interface AppUserRepository  extends JpaRepository<AppUser,Long> {

    public AppUser getAppUserByUseridAndAndPassword(String userid,String password);
}
