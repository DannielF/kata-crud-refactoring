package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Todos DTO
 * DTO type Flat
 *
 * @author Daniel Granados
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodosDto implements Serializable {
    private Long id;
    private String name;
    private Boolean done;
    private Long todoListId;
    private String todoListName;
}
