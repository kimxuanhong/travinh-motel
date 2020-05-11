package com.xhk.mtv.oauth2;

import com.xhk.mtv.model.AbstractEntity;
import com.xhk.mtv.model.Account;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class OAuth2User extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    @Basic(optional = false)
    private String providerId;

    private String fullName;

    private String firstName;

    private String lastName;

    private String imageUrl;

    private String phoneNumber;

    private boolean verifyPhoneNumber;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastOptSentAt;

    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk__oauth2user__account"))
    private Account account;
}
