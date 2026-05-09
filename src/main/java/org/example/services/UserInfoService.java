package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.dto.RegisterDTO;
import org.example.entities.RoleEntity;
import org.example.entities.UserEntity;
import org.example.repositories.IRoleRepository;
import org.example.repositories.IUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInfoService implements UserDetailsService {
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(RegisterDTO dto) {
        if (userRepository.existsByUsername(dto.getUsername()))
            throw new RuntimeException("Логін вже зайнятий");
        if (userRepository.existsByEmail(dto.getEmail()))
            throw new RuntimeException("Email вже використовується");
        if (!dto.getPassword().equals(dto.getConfirmPassword()))
            throw new RuntimeException("Паролі не співпадають");

        RoleEntity role = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Роль не знайдена"));

        UserEntity user = new UserEntity();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.getRoles().add(role);

        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
