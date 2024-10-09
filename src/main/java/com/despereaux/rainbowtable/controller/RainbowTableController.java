package com.despereaux.rainbowtable;

import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/rainbow")
public class RainbowTableController {

    private final Map<String, String> rainbowTable = new HashMap<>();

    // 비밀번호 해시 함수
    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] byteData = md.digest();

        StringBuilder sb = new StringBuilder();
        for (byte b : byteData) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    // Rainbow Table 생성
    @PostMapping("/create")
    public String createRainbowTable(@RequestBody String[] passwords) throws NoSuchAlgorithmException {
        for (String password : passwords) {
            String hashed = hashPassword(password);
            rainbowTable.put(hashed, password);
        }
        return "Rainbow Table created successfully!";
    }

    // 해시 값을 사용하여 비밀번호 찾기
    @GetMapping("/find/{hashedValue}")
    public String findPassword(@PathVariable String hashedValue) {
        return rainbowTable.getOrDefault(hashedValue, "Password not found.");
    }
}
