package com.kaualAlbuquerque.taskFlow.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kaualAlbuquerque.taskFlow.models.Lists;
import com.kaualAlbuquerque.taskFlow.models.User;
import com.kaualAlbuquerque.taskFlow.repositories.ListRepository;

@Service
public class ListService {

    @Autowired
    private ListRepository listRespository;

    @Autowired
    private UserService userService;

    public Lists findListById(Long id) {
        Optional<Lists> list = this.listRespository.findById(id);
        return list.orElseThrow(() -> new RuntimeException(
                "Lista não encontrada! Id: " + id + ", Tipo: " + Lists.class.getName()));
    }

    public List<Lists> findAllByUserId(Long userId){
        List<Lists> lists = this.listRespository.findByUser_Id(userId);
        return lists;
    }

    @Transactional
    public Lists create(Lists obj) {
        User user = this.userService.findUserById(obj.getUser().getId());
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

}
