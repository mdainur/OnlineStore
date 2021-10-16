package javaEE.springboot.springbootdemo.services;

import javaEE.springboot.springbootdemo.entities.Countries;
import javaEE.springboot.springbootdemo.entities.Roles;
import javaEE.springboot.springbootdemo.entities.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    Users getUserByEmail(String email);


    List<Users> getAllUsers();
    Users addUser(Users user);
    Users saveUser(Users user);
    Users getUser(Long id);
    void deleteUser(Users user);
    Users userCreate(Users user);


    List<Roles> getAllRoles();
    Roles addRole(Roles role);
    Roles saveRole(Roles role);
    Roles getRole(Long id);
    void deleteRole(Roles role);
}
