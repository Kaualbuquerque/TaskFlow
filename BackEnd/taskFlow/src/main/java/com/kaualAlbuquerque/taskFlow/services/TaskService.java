package com.kaualAlbuquerque.taskFlow.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaualAlbuquerque.taskFlow.models.Lists;
import com.kaualAlbuquerque.taskFlow.models.Task;
import com.kaualAlbuquerque.taskFlow.models.enums.ProfileEnum;
import com.kaualAlbuquerque.taskFlow.repositories.TaskRepository;
import com.kaualAlbuquerque.taskFlow.security.UserSS;
import com.kaualAlbuquerque.taskFlow.services.exceptions.AuthorizationException;

import jakarta.transaction.Transactional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ListService listService;

    @Autowired
    private UserService userService;

    public Task findTaskById(Long id) {
        Task task = this.taskRepository.findById(id).orElseThrow(() -> new RuntimeException(
                "Tarefa não encontrada! Id: " + id + ", Tipo: " + Task.class.getName()));

        UserSS userSS = userService.authenticated();
        if (Objects.isNull(userSS) || !userSS.hasRole(ProfileEnum.ADMIN) && !userHasTask(userSS, task))
            throw new AuthorizationException("Access denied!");

        return task;
    }

    public List<Task> findAllByListId(long listId) {
        List<Task> tasks = this.taskRepository.findByList_Id(listId);
        return tasks;
    }

    @Transactional
    public Task create(Task obj) {
        Lists list = this.listService.findListById(obj.getList().getId());
        obj.setId(null);
        obj.setList(list);
        obj = this.taskRepository.save(obj);
        return obj;
    }

    @Transactional
    public Task update(Task obj) {
        Task newObj = findTaskById(obj.getId());
        if (obj.getTitle() != null) {
            newObj.setTitle(obj.getTitle());
        }
        if (obj.getDescription() != null) {
            newObj.setDescription(obj.getDescription());
        }
        if (obj.getPriority() != null) {
            newObj.setPriority(obj.getPriority());
        }
        if (obj.getDeadline() != null) {
            newObj.setDeadline(obj.getDeadline());
        }
        newObj.setCompleted(obj.getCompleted());
        return this.taskRepository.save(newObj);
    }

    public void delete(Long id) {
        findTaskById(id);
        try {
            this.taskRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir pois há entidades relacionadas!");
        }
    }

    public Boolean userHasTask(UserSS userSS, Task task) {
        return task.getList().getUser().getId().equals(userSS.getId());
    }
}
