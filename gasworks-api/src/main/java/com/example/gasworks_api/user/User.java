package com.example.gasworks_api.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data                  // ゲッター、セッター、toString、equals、hashCode を全自動生成
@NoArgsConstructor     // 引数なしの標準コンストラクタを生成
@AllArgsConstructor    // すべてのフィールドを引数に持つコンストラクタを生成
public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}