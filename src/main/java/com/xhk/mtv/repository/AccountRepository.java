package com.xhk.mtv.repository;

import com.xhk.mtv.entity.Account;
import com.xhk.mtv.entity.enums.RegisterStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByPhoneNumber(String phoneNumber);

    Optional<Account> findByPhoneNumberAndRegisterStatus(String phoneNumber, RegisterStatus registerStatus);

    Optional<Account> findByEmail(String email);

    Optional<Account> findByEmailAndRegisterStatus(String email, RegisterStatus registerStatus);
}
