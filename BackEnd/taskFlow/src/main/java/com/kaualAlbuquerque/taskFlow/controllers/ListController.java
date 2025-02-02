package com.kaualAlbuquerque.taskFlow.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kaualAlbuquerque.taskFlow.models.Lists;
import com.kaualAlbuquerque.taskFlow.models.projection.ListProjection;
import com.kaualAlbuquerque.taskFlow.services.ListService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/list")
@Validated
public class ListController {

    @Autowired
    private ListService listService;

    @GetMapping("/{id}")
    public ResponseEntity<Lists> findById(@PathVariable Long id) {
        Lists obj = this.listService.findListById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/user")
    public ResponseEntity<List<ListProjection>> findAllByUser() {
        List<ListProjection> objs = this.listService.findAllByUser();
        return ResponseEntity.ok().body(objs);
    }

    @PostMapping
    @Validated
    public ResponseEntity<ListProjection> create(@Valid @RequestBody Lists obj) {
        this.listService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<ListProjection> update(@Valid @RequestBody Lists obj, @PathVariable Long id) {
        obj.setId(id);
        this.listService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.listService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
