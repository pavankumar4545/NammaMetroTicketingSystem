package com.metro.service;

import com.metro.entitiy.MetroEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ActiveUserService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void addActiveUser(MetroEntry entry) {
        redisTemplate.opsForHash().put("ACTIVE_USERS", entry.getUserId().toString(), entry);
    }

    public void removeActiveUser(Long userId) {
        redisTemplate.opsForHash().delete("ACTIVE_USERS", userId.toString());
    }

    public List<Object> getAllActiveUsers() {
        return redisTemplate.opsForHash().values("ACTIVE_USERS");
    }
}
