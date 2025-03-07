package com.upp.spring6webapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private UUID id;
    private String email;
    private Integer version;
    private String name;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
