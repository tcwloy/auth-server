package com.github.tcwloy.auth.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenInfo {
    private Long userId;

    private String token;

    private String userName;

    private String code;

    public TokenInfo(Long userId, String token, String userName) {
        this.userId = userId;
        this.token = token;
        this.userName = userName;
    }
}
