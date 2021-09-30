package com.example.like.adapter.repository;

import org.example.like.model.Like;
import org.example.like.port.repository.LikeRepository;
import org.springframework.stereotype.Component;

@Component
public class LikeRepositorySql implements LikeRepository {
    @Override
    public Long create(Like like) {
        return null;
    }
}
