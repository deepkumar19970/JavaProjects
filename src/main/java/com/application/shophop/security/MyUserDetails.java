package com.application.shophop.security;

import com.application.shophop.model.User;
import org.hibernate.mapping.Array;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {
    private String password;
    private String  username;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private  boolean isCredencialsNonExpired;
    private boolean isEnabled;
    private List<GrantedAuthority> authorities;

    public MyUserDetails(User user) {
        this.username= user.getUsername();
        this.password= user.getPassword();
        this.isAccountNonExpired= user.isAccountNonExpired();
        this.isAccountNonLocked= user.isAccountNonLocked();
        this.isCredencialsNonExpired= user.isCredencialsNonExpired();
        this.isEnabled= user.isEnabled();
        this.authorities= Arrays.stream(user.getRole().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
