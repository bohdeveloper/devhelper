package com.devhelper.repository;

import com.devhelper.entity.GenEntityField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenEntityFieldRepository extends JpaRepository<GenEntityField, Long> {
}
