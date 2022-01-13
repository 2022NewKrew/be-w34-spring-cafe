package com.kakao.cafe.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class UserAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String inputUsername = authentication.getName();
        String inputPassword = (String) authentication.getCredentials();

        UserDetails userDetailsByDB = userDetailsService.loadUserByUsername(inputUsername);
        if (!passwordEncoder.matches(inputPassword, userDetailsByDB.getPassword())) {
            throw new BadCredentialsException(inputUsername);
        }
        return new UsernamePasswordAuthenticationToken(userDetailsByDB.getUsername(), null, userDetailsByDB.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
