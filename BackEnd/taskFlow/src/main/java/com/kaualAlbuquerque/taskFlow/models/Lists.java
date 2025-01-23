package com.kaualAlbuquerque.taskFlow.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = Lists.TABLE_NAME)
public class Lists {
    private static final String TABLE_NAME = "list";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(name = "name", length = 100, nullable = false)
    private String name;


    @OneToMany(mappedBy = "list", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;

    public Lists() {
    }

    public Lists(Long id, User user, String name, List<Task> tasks) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.tasks = tasks;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public List<Task> getTasks() {
        return this.tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Lists id(Long id) {
        setId(id);
        return this;
    }

    public Lists user(User user) {
        setUser(user);
        return this;
    }

    public Lists name(String name) {
        setName(name);
        return this;
    }

    public Lists tasks(List<Task> tasks) {
        setTasks(tasks);
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) // Mesma referência
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Lists other = (Lists) obj;

        return Objects.equals(this.id, other.id) &&
                Objects.equals(this.user, other.user) &&
                Objects.equals(this.name, other.name) &&
                Objects.equals(this.tasks, other.tasks);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        return result;
    }

}
