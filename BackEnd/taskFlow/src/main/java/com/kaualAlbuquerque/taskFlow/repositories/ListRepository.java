package com.kaualAlbuquerque.taskFlow.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kaualAlbuquerque.taskFlow.models.Lists;
import com.kaualAlbuquerque.taskFlow.models.projection.ListProjection;

@Repository
public interface ListRepository extends JpaRepository<Lists, Long> {
    List<ListProjection> findByUser_Id(Long id);
}
