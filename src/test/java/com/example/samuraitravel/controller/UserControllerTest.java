package com.example.samuraitravel.controller;

import com.example.samuraitravel.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import org.springframework.transaction.annotation.Transactional;
import com.example.samuraitravel.entity.User;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("ci")
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Test
    public void 未ログインの場合は会員用の会員詳細ページからログインページにリダイレクトする() throws Exception {
        mockMvc.perform(get("/user"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithUserDetails("taro.samurai@example.com")
    public void ログイン済みの場合は会員用の会員詳細ページが正しく表示される() throws Exception {
        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/index"));
    }

    @Test
    public void 未ログインの場合会員用の編集ページからログインページにリダイレクトする() throws Exception {
        mockMvc.perform(get("/user/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithUserDetails("taro.samurai@example.com")
    public void ログイン済みの場合会員用の編集ページが表示される() throws Exception {
        mockMvc.perform(get("/user/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/edit"));
    }

    @Test
    @Transactional
    public void 未ログインの場合は会員を更新せずにログインページにリダイレクトする() throws Exception {
        mockMvc.perform(post("/user/update")
                        .with(csrf())
                        .param("name", "テスト氏名")
                        .param("furigana", "テストフリガナ")
                        .param("postalCode", "000-0000")
                        .param("address", "テスト住所")
                        .param("phoneNumber", "000-000-000")
                        .param("email", "test@example.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithUserDetails("taro.samurai@example.com")
    @Transactional
    public void ログイン済みの場合は会員更新後に会員用の会員詳細ページにリダイレクトする() throws Exception {
        User user = userService.findUserByEmail("taro.samurai@example.com");

        mockMvc.perform(post("/user/update")
                        .with(csrf())
                        .param("name", "テスト氏名")
                        .param("furigana", "テストフリガナ")
                        .param("postalCode", "000-0000")
                        .param("address", "テスト住所")
                        .param("phoneNumber", "000-000-000")
                        .param("email", "test@example.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user"));

        assertThat(user.getName()).isEqualTo("テスト氏名");
        assertThat(user.getFurigana()).isEqualTo("テストフリガナ");
        assertThat(user.getPostalCode()).isEqualTo("000-0000");
        assertThat(user.getAddress()).isEqualTo("テスト住所");
        assertThat(user.getPhoneNumber()).isEqualTo("000-000-000");
        assertThat(user.getEmail()).isEqualTo("test@example.com");
    }
}
