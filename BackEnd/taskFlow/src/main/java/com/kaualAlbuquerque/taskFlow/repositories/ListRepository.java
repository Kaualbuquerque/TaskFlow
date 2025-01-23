package com.kaualAlbuquerque.taskFlow.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kaualAlbuquerque.taskFlow.models.Lists;

@Repository
public interface ListRepository extends JpaRepository<Lists, Long>{
    List<Lists> findByUser_Id(Long id);
}
