package com.kaualAlbuquerque.taskFlow.services;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kaualAlbuquerque.taskFlow.models.User;
import com.kaualAlbuquerque.taskFlow.models.dto.UserCreateDTO;
import com.kaualAlbuquerque.taskFlow.models.dto.UserUpdateDTO;
import com.kaualAlbuquerque.taskFlow.models.enums.ProfileEnum;
import com.kaualAlbuquerque.taskFlow.repositories.UserRepository;
import com.kaualAlbuquerque.taskFlow.security.UserSS;
import com.kaualAlbuquerque.taskFlow.services.exceptions.AuthorizationException;

import jakarta.validation.Valid;

@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    public User findUserById(Long id) {

        UserSS userSS = authenticated();
        if (!Objects.nonNull(userSS) || !userSS.hasRole(ProfileEnum.ADMIN) && !id.equals(userSS.getId()))
            throw new AuthorizationException("Access denied!");

        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException(
                "Usuário não encontrado! Id: " + id + ", Tipo: " + User.class.getName()));
    }

    @Transactional
    public User create(User obj) {
        obj.setId(null);
        obj.setPassword(this.bCryptPasswordEncoder.encode(obj.getPassword()));
        obj.setProfiles(Stream.of(ProfileEnum.USER.getCode()).collect(Collectors.toSet()));
        obj = this.userRepository.save(obj);
        return obj;
    }

    @Transactional
    public User update(User obj) {
        User newObj = findUserById(obj.getId());
        if (obj.getPassword() != null) {
            newObj.setPassword(obj.getPassword());
            newObj.setPassword(this.bCryptPasswordEncoder.encode(obj.getPassword()));
        }
        return this.userRepository.save(newObj);
    }

    public void delete(Long id) {
        findUserById(id);
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir pois há entidades relacionadas!");
        }
    }

    public static UserSS authenticated() {
        try {
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }

    public User fromDTO(@Valid UserCreateDTO obj) {
        User user = new User();
        user.setUsername(obj.getUsername());
        user.setPassword(obj.getPassword());
        return user;
    }

    public User fromDTO(@Valid UserUpdateDTO obj) {
        User user = new User();
        user.setId(obj.getId());
        user.setPassword(obj.getPassword());
        return user;
    }
}
