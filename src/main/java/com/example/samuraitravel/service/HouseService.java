package com.example.samuraitravel.service;

import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.repository.HouseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseService {
    private final HouseRepository houseRepository;

    /* Constructor */
    public HouseService(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    /* Get All Houses Data */
    public Page<House> findAllHouses(Pageable pageable) {
        return houseRepository.findAll(pageable);
    }

    /* Get Hit Houses Data */
    public Page<House> findHousesByNameLike(String keyword, Pageable pageable) {
        return houseRepository.findByNameLike("%" + keyword + "%", pageable);
    }
}
