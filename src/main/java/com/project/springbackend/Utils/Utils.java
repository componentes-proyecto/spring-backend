package com.project.springbackend.Utils;

import com.project.springbackend.Domain.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.keygen.KeyGenerators;

public class Utils {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);

    public String encodePassword(String raw) {
        return this.encode(raw);
    }

    public boolean checkMatch(String encoded, String attempt) {
        return this.match(encoded, attempt);
    }

    public void cleanUser(User user) {
        user.setPassword("");
    }

    private String encode(String raw) {
        String result = encoder.encode(raw);
        return result;
    }

    private boolean match(String encoded, String attempt) {
        return encoder.matches(attempt, encoded);
    }
}
