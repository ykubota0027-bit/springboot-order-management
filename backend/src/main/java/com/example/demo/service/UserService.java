package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.request.UserCreateRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse create(UserCreateRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("このメールアドレスはすでに登録されています");
        }
        User user = new User(request.getName(), request.getEmail());
        User savedUser = userRepository.save(user);
        return toResponse(savedUser);
    }

    public List<UserResponse> findAll() {
        return userRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public UserResponse findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return toResponse(user);
    }

    public UserResponse toResponse(User user) {
        return new UserResponse(user.getUserId(), user.getName(), user.getEmail());
    }

    // Put
    public UserResponse update(Long id, UserCreateRequest req) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // email重複チェック
        if (userRepository.existsByEmail(req.getEmail()) && !user.getEmail().equals(req.getEmail())) {
            throw new IllegalArgumentException("メールアドレスが重複しています");
        }

        user.setName(req.getName());
        user.setEmail(req.getEmail());
        return toResponse(user);
    }

    // Delete
    public void delete(Long id) {
        // 存在チェック
        userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Uesr not found"));

        userRepository.deleteById(id);
    }

}
