package com.devhelper.repository;

import com.devhelper.entity.GenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenEntityRepository extends JpaRepository<GenEntity, Long> {
}
