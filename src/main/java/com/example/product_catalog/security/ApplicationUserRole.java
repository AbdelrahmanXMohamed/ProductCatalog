package com.example.product_catalog.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.example.product_catalog.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    CUSTOMER(Sets.newHashSet());
//    ADMIN(Sets.newHashSet(CUSTOMER_READ, CUSTOMER_WRITE, PRODUCT_READ, PRODUCT_WRITE));
    private final Set<ApplicationUserPermission> permissions;
    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
}