package org.logistic.company.logisticcompany.persistance.repos;

import java.io.Serializable;
import java.util.Objects;

public class AuthorityPK implements Serializable {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    private String authority;

    public AuthorityPK() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthorityPK authPK)) return false;
        return username == authPK.username && Objects.equals(authority, authPK.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, authority);
    }
}