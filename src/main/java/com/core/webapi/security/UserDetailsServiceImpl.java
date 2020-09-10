package com.core.webapi.security;

import com.core.webapi.model.User;
import com.core.webapi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class UserDetailsServiceImpl  implements UserDetailsService {
    private static Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    private final UserRepository userRepository;
    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userRepository.getByUsername(username);
            logger.info("user: {}", user.getUsername());
            StringBuilder authorities = new StringBuilder();
            for (GrantedAuthority authority : user.getAuthorities()) {
                authorities.append(authority.getAuthority()).append(", ");
            }
            logger.info("authorities: [ {} ]", authorities.toString().replaceAll(", $", ""));
            return user;
        }
        catch(Exception ex) {
            throw new UsernameNotFoundException(username);
        }
    }
}
