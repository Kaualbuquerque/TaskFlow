package com.kaualAlbuquerque.taskFlow.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = Task.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Task {
    public static final String TABLE_NAME = "task";

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    @NotNull
    private User user;

    @Column(name = "title", nullable = false)
    @Size(min = 1, max = 100)
    @NotBlank
    private String title;

    @Column(name = "description", nullable = false)
    @Size(min = 1, max = 255)
    @NotBlank
    private String description;

    @Column(name = "priority", nullable = false)
    @Size(min = 3, max = 6)
    @NotBlank
    private String priority;

    @Column(name = "deadline", nullable = false)
    private LocalDate deadline;
}
