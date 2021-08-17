package com.github.tcwloy.auth.service;

import com.github.tcwloy.auth.model.pojo.TokenInfo;

public interface ITokenService {
    TokenInfo getTokenInfo(String token);
}
