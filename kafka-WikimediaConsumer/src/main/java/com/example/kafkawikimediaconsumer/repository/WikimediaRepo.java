package com.example.kafkawikimediaconsumer.repository;

import com.example.kafkawikimediaconsumer.entity.WikimediaData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WikimediaRepo extends JpaRepository<WikimediaData,Long> {
}
