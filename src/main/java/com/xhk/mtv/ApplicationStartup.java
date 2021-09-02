package com.xhk.mtv;

import com.xhk.mtv.entity.Account;
import com.xhk.mtv.entity.enums.Gender;
import com.xhk.mtv.entity.enums.LoginStatus;
import com.xhk.mtv.entity.enums.RegisterStatus;
import com.xhk.mtv.entity.enums.UserRole;
import com.xhk.mtv.repository.AccountRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {
    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    public ApplicationStartup(PasswordEncoder passwordEncoder, AccountRepository accountRepository) {
        this.passwordEncoder = passwordEncoder;
        this.accountRepository = accountRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
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
        } catch (Exception e) {
        }
    }
}
