package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner{

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Loading data...**************");

        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ADMIN"));

        Role adminRole = roleRepository.findByRole("ADMIN");
        Role userRole = roleRepository.findByRole("USER");

        User bob = new User("bob@bob.com", "password", "Bob", "Bobberson", true, "bob");
        bob.setRoles(Arrays.asList(userRole));
        bob.setPassword(passwordEncoder.encode(bob.getPassword()));
        bob.setEnabled(true);
        userRepository.save(bob);

        User alice = new User("alice@alice.com", "password", "Alice", "Allison", true, "alice");
        alice.setRoles(Arrays.asList(userRole));
        alice.setPassword(passwordEncoder.encode(alice.getPassword()));
        alice.setEnabled(true);
        userRepository.save(alice);

        User admin = new User("admin@admin.com", "password", "Admin", "User", true, "admin");
        admin.setRoles(Arrays.asList(adminRole));
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        userRepository.save(admin);

        System.out.println("Data loaded...**************");
    }

}
