package com.kaualAlbuquerque.taskFlow.services;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kaualAlbuquerque.taskFlow.models.Lists;
import com.kaualAlbuquerque.taskFlow.models.User;
import com.kaualAlbuquerque.taskFlow.models.enums.ProfileEnum;
import com.kaualAlbuquerque.taskFlow.models.projection.ListProjection;
import com.kaualAlbuquerque.taskFlow.repositories.ListRepository;
import com.kaualAlbuquerque.taskFlow.security.UserSS;
import com.kaualAlbuquerque.taskFlow.services.exceptions.AuthorizationException;

@Service
public class ListService {

    @Autowired
    private ListRepository listRespository;

    @Autowired
    private UserService userService;

    public Lists findListById(Long id) {
        Lists list = this.listRespository.findById(id).orElseThrow(() -> new RuntimeException(
                "Lista não encontrada! Id: " + id + ", Tipo: " + Lists.class.getName()));

        UserSS userSS = userService.authenticated();
        if (Objects.isNull(userSS) || !userSS.hasRole(ProfileEnum.ADMIN) && !userHasList(userSS, list))
            throw new AuthorizationException("Access denied!");

        return list;
    }

    public List<ListProjection> findAllByUser() {
        UserSS userSS = userService.authenticated();
        if (Objects.isNull(userSS))
            throw new AuthorizationException("Access denied!");

        List<ListProjection> lists = this.listRespository.findByUser_Id(userSS.getId());
        return lists;
    }

    @Transactional
    public Lists create(Lists obj) {
        UserSS userSS = userService.authenticated();
        if (Objects.isNull(userSS))
            throw new AuthorizationException("Access denied!");

        User user = this.userService.findUserById(userSS.getId());
        obj.setId(null);
        obj.setUser(user);
        obj = this.listRespository.save(obj);
        return obj;
    }

    @Transactional
    public Lists update(Lists obj) {
        Lists newObj = findListById(obj.getId());
        if (obj.getName() != null) {
            newObj.setName(obj.getName());
        }
        return this.listRespository.save(newObj);
    }

    public void delete(Long id) {
        findListById(id);
        try {
            this.listRespository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir pois há entidades relacionadas!");
        }
    }

    public Boolean userHasList(UserSS userSS, Lists list) {
        return list.getUser().getId().equals(userSS.getId());
    }

}
