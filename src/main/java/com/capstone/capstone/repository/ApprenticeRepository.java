package com.capstone.capstone.repository;

import com.capstone.capstone.model.Entity.Apprentice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprenticeRepository extends JpaRepository<Apprentice, Integer> {
}
