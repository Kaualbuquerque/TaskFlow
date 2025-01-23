package com.kaualAlbuquerque.taskFlow.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaualAlbuquerque.taskFlow.models.Lists;
import com.kaualAlbuquerque.taskFlow.models.Task;
import com.kaualAlbuquerque.taskFlow.repositories.TaskRepository;

import jakarta.transaction.Transactional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ListService listService;

    public Task findTaskById(Long id) {
        Optional<Task> task = this.taskRepository.findById(id);
        return task.orElseThrow(() -> new RuntimeException(
                "Tarefa não encontrada! Id: " + id + ", Tipo: " + Task.class.getName()));
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
}
