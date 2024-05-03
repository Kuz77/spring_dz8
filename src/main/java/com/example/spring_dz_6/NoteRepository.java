package com.example.spring_dz_6;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {
    Optional<Note> findById(Long id);
}
