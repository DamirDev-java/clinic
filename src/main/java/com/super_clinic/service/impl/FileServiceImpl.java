package com.super_clinic.service.impl;

import com.super_clinic.entity.File;
import com.super_clinic.repository.FileRepository;
import com.super_clinic.service.BaseService;
import com.super_clinic.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.zip.ZipEntry;

@org.springframework.stereotype.Service
@Transactional
public class FileServiceImpl implements Service<Long, File> {

    @Autowired
    private FileRepository fileRepository;

    public void save(File f) {
        fileRepository.save(f);
    }

    @Override
    public Optional<File> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(File file) {

    }

    @Override
    public void update(File file) {

    }

    @Override
    public List<File> findAll() {
        return List.of();
    }

    @Override
    public Optional<File> findByUsername(String username) {
        return Optional.empty();
    }
}
