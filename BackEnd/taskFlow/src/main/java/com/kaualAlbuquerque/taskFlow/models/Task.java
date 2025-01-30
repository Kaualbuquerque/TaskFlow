package com.kaualAlbuquerque.taskFlow.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = Task.TABLE_NAME)
public class Task {

    public static final String TABLE_NAME = "task";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "list_id", referencedColumnName = "id", nullable = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    private Lists list;

    @Column(name = "title", length = 15, nullable = false)
    @NotBlank
    @Size(min = 2, max = 15)
    private String title;

    @Column(name = "description", length = 255, nullable = false)
    @NotNull
    @Size(max = 255)
    private String description;

    @Column(name = "deadline", length = 10, nullable = false)
    private LocalDate deadline;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority", length = 6, nullable = false)
    private Priority priority;

    @Column(name = "completed", nullable = false)
    private boolean completed;


    public Task() {
    }

    public Task(Long id, Lists list,  String title, String description, LocalDate deadline, Priority priority, boolean completed) {
        this.id = id;
        this.list = list;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.completed = completed;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Lists getList() {
        return this.list;
    }

    public void setList(Lists list) {
        this.list = list;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return this.deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public Priority getPriority() {
        return this.priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public boolean getCompleted() {
        return this.completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Task id(Long id) {
        setId(id);
        return this;
    }

    public Task list(Lists list) {
        setList(list);
        return this;
    }

    public Task title(String title) {
        setTitle(title);
        return this;
    }

    public Task description(String description) {
        setDescription(description);
        return this;
    }

    public Task deadline(LocalDate deadline) {
        setDeadline(deadline);
        return this;
    }

    public Task priority(Priority priority) {
        setPriority(priority);
        return this;
    }

    public Task completed(boolean completed) {
        setCompleted(completed);
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) // Mesma referência
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Task other = (Task) obj;

        return Objects.equals(this.id, other.id) &&
                Objects.equals(this.title, other.title) &&
                Objects.equals(this.description, other.description) &&
                Objects.equals(this.deadline, other.deadline) &&
                Objects.equals(this.priority, other.priority) &&
                Objects.equals(this.completed, other.completed)&&
                Objects.equals(this.list, other.list);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        return result;
    }

    public enum Priority {
        LOW, MEDIUM, HIGH
    }
}
