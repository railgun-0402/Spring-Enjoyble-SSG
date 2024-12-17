package com.example.samuraitravel.controller;

import com.example.samuraitravel.entity.User;
import com.example.samuraitravel.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping
    public String index(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) {
        /*
         * AuthenticationPrincipal
         * ログイン中のユーザ情報が取得可
         */
        User user = userDetailsImpl.getUser();
        model.addAttribute("user", user);

        return "user/index";
    }
}
