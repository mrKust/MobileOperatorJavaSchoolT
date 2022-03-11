package com.school.service;

import com.school.database.entity.Client;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientDetailService implements UserDetailsService {

    private final ServiceMVC clientService;

    public ClientDetailService(ServiceMVC<Client> clientService) {
        this.clientService = clientService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Client tmp = (Client) clientService.getByName(s);
        return Optional.ofNullable(tmp)
                .map(client-> User.withUsername(client.getEmailAddress())
                        .password(client.getPasswordLogIn())
                        .roles(client.getUserRole())
                        .build())
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }
}

