package com.example.samuraitravel.repository;

import com.example.samuraitravel.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer> {
    public VerificationToken findByToken(String token);
}

