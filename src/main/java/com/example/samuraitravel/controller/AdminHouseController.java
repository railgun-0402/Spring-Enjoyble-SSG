package com.example.samuraitravel.controller;

import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.service.HouseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/houses")
public class AdminHouseController {
    private final HouseService houseService;

    /* Constructor */
    public AdminHouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @GetMapping
    public String index(Model model) {
        List<House> houses = houseService.findAllHouses();
        model.addAttribute("houses", houses);

        return "admin/houses/index";
    }
}
