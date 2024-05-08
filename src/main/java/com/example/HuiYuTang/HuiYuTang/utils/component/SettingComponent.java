package com.example.HuiYuTang.HuiYuTang.utils.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SettingComponent {
	
	@Value("${spring.security.encrypt.key}")
    private String encryptionKey;

    public String getEncryptionKey() {
        return encryptionKey;
    }

}
