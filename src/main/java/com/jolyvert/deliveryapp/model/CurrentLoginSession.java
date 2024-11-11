package com.jolyvert.deliveryapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CurrentLoginSession {

    @Id
    @Column(unique =  true)
    private Integer userId;

    private String uuid;

    private LocalDateTime localDateTime;




}
