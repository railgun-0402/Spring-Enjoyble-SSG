package com.example.samuraitravel.repository;

import com.example.samuraitravel.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House, Integer> {
}
