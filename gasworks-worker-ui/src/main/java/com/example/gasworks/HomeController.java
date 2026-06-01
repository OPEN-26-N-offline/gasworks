package com.example.gasworks;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        // ログイン後はポータル画面（index.html）を表示する
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin() {
        // ログイン処理成功後、ルートパス（ポータル画面）へリダイレクト
        return "redirect:/";
    }

    @GetMapping("/map")
    public String map() {
        return "dummy_page";
    }

    @GetMapping("/notifications")
    public String notifications() {
        return "dummy_page";
    }

    @GetMapping("/sync")
    public String sync() {
        return "dummy_page";
    }

    @GetMapping("/emergency")
    public String emergency() {
        return "dummy_page";
    }

    @GetMapping("/settings")
    public String settings() {
        return "dummy_page";
    }
}
