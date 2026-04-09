package com.devhelper.service;

import com.devhelper.entity.Snippet;
import com.devhelper.repository.SnippetRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SnippetService {

    private final SnippetRepository repository;

    public SnippetService(@NonNull SnippetRepository repository) {
        this.repository = repository;
    }

    public List<Snippet> findAll() {
        return repository.findAll();
    }

    @NonNull
    public Optional<Snippet> findById(@NonNull Long id) {
        return repository.findById(id);
    }

    @NonNull
    public Snippet save(@NonNull Snippet Snippet) {
        return repository.save(Snippet);
    }

    public void deleteById(@NonNull Long id) {
        repository.deleteById(id);
    }
}
