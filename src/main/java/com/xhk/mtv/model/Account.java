package com.xhk.mtv.model;

import com.xhk.mtv.model.enums.Gender;
import com.xhk.mtv.model.enums.LoginStatus;
import com.xhk.mtv.model.enums.RegisterStatus;
import com.xhk.mtv.model.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@Entity
public class Account extends AbstractEntity {
    @Column(unique = true)
    private String phoneNumber;
    @Column(unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    private String password;
    private String firstName = "";
    private String lastName = "";
    private String avatarUrl;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private LoginStatus loginStatus;
    @Enumerated(EnumType.STRING)
    private RegisterStatus registerStatus;
    private String registrationToken;
    @Column(columnDefinition = "TEXT")
    private String address;

    public String getFullName() {
        return lastName.concat(" ").concat(firstName).trim();
    }
}
