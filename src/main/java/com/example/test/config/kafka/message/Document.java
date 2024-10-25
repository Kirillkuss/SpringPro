package com.example.test.config.kafka.message;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Document implements Serializable{

    private Long idDocument;
    private String typeDocument;
    private String seria;
    private String numar;
    private String snils;
    private String polis;

}