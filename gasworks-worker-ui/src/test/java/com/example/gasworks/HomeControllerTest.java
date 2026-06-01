package com.example.gasworks;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.containsString;

/**
 * 業務メニュー（Field Operations Dashboard）の機能テスト
 */
@WebMvcTest(HomeController.class)
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("業務メニュー画面が正常にレスポンスされ、正しいViewを返却すること")
    void testIndexPageRendering() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(content().string(containsString("業務メニュー")));
    }

    @Test
    @DisplayName("ブランドロゴとミッションステートメントが表示されていること")
    void testBrandingElements() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(content().string(containsString("GASWORKS")))
                .andExpect(content().string(containsString("安全なガスインフラの供給を通じて、")))
                .andExpect(content().string(containsString("豊かで持続可能な未来を支える。")));
    }

    @Test
    @DisplayName("仕様書に基づいた6つの主要メニューへのリンクが正しく配置されていること")
    void testDashboardMenuLinks() throws Exception {
        mockMvc.perform(get("/"))
                // 1. 担当顧客一覧
                .andExpect(content().string(containsString("担当顧客一覧")))
                .andExpect(content().string(containsString("href=\"/customers\"")))
                
                // 2. 巡回ルート
                .andExpect(content().string(containsString("巡回ルート")))
                .andExpect(content().string(containsString("href=\"/map\"")))
                
                // 3. お知らせ
                .andExpect(content().string(containsString("お知らせ")))
                .andExpect(content().string(containsString("href=\"/notifications\"")))
                .andExpect(content().string(containsString("badge rounded-pill bg-danger"))) // 通知バッジの存在
                
                // 4. 日報作成・同期
                .andExpect(content().string(containsString("日報作成・同期")))
                .andExpect(content().string(containsString("href=\"/sync\"")))
                .andExpect(content().string(containsString("提出済"))) // ステータスバッジの存在
                
                // 5. 緊急連絡
                .andExpect(content().string(containsString("緊急連絡")))
                .andExpect(content().string(containsString("href=\"/emergency\"")))
                
                // 6. 個人設定
                .andExpect(content().string(containsString("個人設定")))
                .andExpect(content().string(containsString("href=\"/settings\"")));
    }

    @Test
    @DisplayName("レスポンシブデザイン用のCSSクラスが適用されていること")
    void testResponsiveLayoutClasses() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(content().string(containsString("container-fluid px-md-5")))
                .andExpect(content().string(containsString("row g-4")))
                .andExpect(content().string(containsString("col-6")));
    }
}