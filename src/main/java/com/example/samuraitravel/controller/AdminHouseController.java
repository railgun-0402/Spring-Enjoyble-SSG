package com.example.samuraitravel.controller;

import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.service.HouseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/houses")
public class AdminHouseController {
    private final HouseService houseService;

    /* Constructor */
    public AdminHouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @GetMapping
    public String index(@RequestParam(name = "keyword", required = false) String keyword,
                        @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                        Model model) {
        Page<House> housePage;

        if (keyword != null && !keyword.isEmpty()) {
            housePage = houseService.findHousesByNameLike(keyword, pageable);
        } else {
            housePage = houseService.findAllHouses(pageable);
        }

        model.addAttribute("housePage", housePage);
        model.addAttribute("keyword", keyword);

        return "admin/houses/index";
    }
}
