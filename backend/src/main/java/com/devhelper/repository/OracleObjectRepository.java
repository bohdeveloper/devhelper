package com.devhelper.repository;

import com.devhelper.entity.OracleObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OracleObjectRepository extends JpaRepository<OracleObject, Long> {
}
