package com.tenco.bank.dto;

import lombok.Data;

@Data
public class KakaoProfile {
    public Long id;
    public String connectedAt;
    public Properties properties;
    public KakaoAccount kakaoAccount;
    
    
}
