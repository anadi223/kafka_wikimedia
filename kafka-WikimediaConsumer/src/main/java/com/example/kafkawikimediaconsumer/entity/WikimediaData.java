package com.example.kafkawikimediaconsumer.entity;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Clob;

@Entity
@Data
@Table(name = "wikimedia_data")
public class WikimediaData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "LONGTEXT",length = 16777215)
    private String wikiEventData;
}
