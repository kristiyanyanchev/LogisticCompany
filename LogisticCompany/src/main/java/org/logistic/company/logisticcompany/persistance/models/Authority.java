package org.logistic.company.logisticcompany.persistance.models;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "authorities")
@IdClass(Authority.class)
public class Authority {
    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "username", nullable = false, referencedColumnName = "username")
    private User username;

    public Authority(String authority, User username) {
        this.authority = authority;
        this.username = username;
    }
    public Authority() {}

    @Id
    @Column(name = "authority", nullable = false, length = Integer.MAX_VALUE)
    private String authority;

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public User getUsername() {
        return username;
    }

    public void setUsername(User username) {
        this.username = username;
    }
}