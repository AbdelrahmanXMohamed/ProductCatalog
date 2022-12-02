package com.example.product_catalog.entity;

import com.example.product_catalog.security.ApplicationUserRole;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Table
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    @Column(unique = true)
    private String email;
    private boolean accountExpired;
    private boolean accountLocked;
    private boolean credentialsExpired;
    private boolean enabled;
    @Enumerated(EnumType.STRING)
    private ApplicationUserRole applicationUserRole;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonManagedReference
    List<Orders> ordersList;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(ApplicationUserRole.CUSTOMER.name());
        return Collections.singleton(authority);
    }
    @Override
    public boolean isAccountNonExpired() {
        return !accountExpired;
    }
    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }
    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
