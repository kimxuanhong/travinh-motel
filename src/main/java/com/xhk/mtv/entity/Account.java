package com.xhk.mtv.entity;

import com.xhk.mtv.entity.enums.Gender;
import com.xhk.mtv.entity.enums.LoginStatus;
import com.xhk.mtv.entity.enums.RegisterStatus;
import com.xhk.mtv.entity.enums.UserRole;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Entity
public class Account extends AbstractEntity {
    @Column(unique = true)
    private String phoneNumber;
    @Column(unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    private String password;
    private String firstName;
    private String lastName;
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
