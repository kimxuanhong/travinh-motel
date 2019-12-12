package com.xhk.mtv;

import com.xhk.mtv.model.Account;
import com.xhk.mtv.model.enums.Gender;
import com.xhk.mtv.model.enums.LoginStatus;
import com.xhk.mtv.model.enums.RegisterStatus;
import com.xhk.mtv.model.enums.UserRole;
import com.xhk.mtv.repository.AccountRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {
    private final AccountRepository accountRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public ApplicationStartup(BCryptPasswordEncoder passwordEncoder, AccountRepository accountRepository) {
        this.passwordEncoder = passwordEncoder;
        this.accountRepository = accountRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        //dummy account user
        Account user = new Account();
        user.setEmail("xuanhongkim@gmail.com");
        user.setPhoneNumber("84335898022");
        user.setFirstName("Hong");
        user.setLastName("Xuan");
        user.setPassword(passwordEncoder.encode("111111"));
        user.setLoginStatus(LoginStatus.LOGOUT);
        user.setRegisterStatus(RegisterStatus.COMPLETED);
        user.setUserRole(UserRole.ROLE_USER);
        user.setGender(Gender.MALE);
        accountRepository.saveAndFlush(user);

        //dummy account user
        Account admin = new Account();
        admin.setEmail("kimxuanhong@outlook.com");
        admin.setPhoneNumber("84335898023");
        admin.setFirstName("Xuan Hong");
        admin.setLastName("Kim");
        admin.setPassword(passwordEncoder.encode("111111"));
        admin.setLoginStatus(LoginStatus.LOGOUT);
        admin.setRegisterStatus(RegisterStatus.COMPLETED);
        admin.setUserRole(UserRole.ROLE_ADMIN);
        admin.setGender(Gender.MALE);
        accountRepository.saveAndFlush(admin);
    }
}
