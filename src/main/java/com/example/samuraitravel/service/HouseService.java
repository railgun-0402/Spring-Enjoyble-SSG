package com.example.samuraitravel.service;

import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.form.HouseEditForm;
import com.example.samuraitravel.form.HouseRegisterForm;
import com.example.samuraitravel.repository.HouseRepository;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    /* Get Houses by ID */
    public Optional<House> findHouseById(Integer id) {
        return houseRepository.findById(id);
    }

    /* 民宿のレコード数を取得 */
    public long countHouses() {
        return houseRepository.count();
    }

    /* IDが最も大きい民宿を取得 */
    public House findFirstHouseByOrderByIdDesc() {
        return houseRepository.findFirstByOrderByIdDesc();
    }

    @Transactional
    public void deleteHouse(House house) {
        houseRepository.delete(house);
    }

    @Transactional
    public void createHouse(HouseRegisterForm houseRegisterForm) {
        House house = new House();
        MultipartFile imageFile = houseRegisterForm.getImageFile();

        if (!imageFile.isEmpty()) {
            String imageName = imageFile.getOriginalFilename();
            String hashedImageName = generateNewFileName(imageName);
            Path filePath = Paths.get("src/main/resources/static/storage/" + hashedImageName);
            copyImageFile(imageFile, filePath);
            house.setImageName(hashedImageName);
        }

        house.setName(houseRegisterForm.getName());
        house.setDescription(houseRegisterForm.getDescription());
        house.setPrice(houseRegisterForm.getPrice());
        house.setCapacity(houseRegisterForm.getCapacity());
        house.setPostalCode(houseRegisterForm.getPostalCode());
        house.setAddress(houseRegisterForm.getAddress());
        house.setPhoneNumber(houseRegisterForm.getPhoneNumber());

        houseRepository.save(house);
    }

    // UUIDで生成ファイル名を返す
    public String generateNewFileName(String imageName) {
        String[] fileNames = imageName.split("\\.");

        for (int i = 0; i < fileNames.length - 1; i++) {
            fileNames[i] = UUID.randomUUID().toString();
        }

        return String.join(".", fileNames);
    }

    // 画像ファイルを指定したファイルにコピーする
    public void copyImageFile(MultipartFile imageFile, Path filePath) {
        try {
            Files.copy(imageFile.getInputStream(), filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void updateHouse(HouseEditForm houseEditForm, House house) {
        MultipartFile imageFile = houseEditForm.getImageFile();

        if (!imageFile.isEmpty()) {
            String imageName = imageFile.getOriginalFilename();
            String hashedImageName = generateNewFileName(imageName);
            Path filePath = Paths.get("src/main/resources/static/storage/" + hashedImageName);
            copyImageFile(imageFile, filePath);
            house.setImageName(hashedImageName);
        }

        house.setName(houseEditForm.getName());
        house.setDescription(houseEditForm.getDescription());
        house.setPrice(houseEditForm.getPrice());
        house.setCapacity(houseEditForm.getCapacity());
        house.setPostalCode(houseEditForm.getPostalCode());
        house.setAddress(houseEditForm.getAddress());
        house.setPhoneNumber(houseEditForm.getPhoneNumber());

        houseRepository.save(house);
    }
}
