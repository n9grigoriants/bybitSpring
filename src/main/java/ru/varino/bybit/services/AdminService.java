package ru.varino.bybit.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.varino.bybit.repositories.AdminRepository;
import ru.varino.bybit.repositories.UserRepository;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

}
