package com.example.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Todo entity
 *
 * @author Daniel Granados
 * @version 1.0.0
 * @since 1.0.0
 */
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "todo")
public class Todo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "td_id")
    private Long id;

    @Column(name = "td_name", nullable = false, length = 100)
    private String name;

    @Column(name = "td_done", nullable = false)
    private Boolean done;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY,
            targetEntity = TodoList.class,
            optional = false)
    @JoinColumn(name = "tdlist_id")
    @JsonBackReference
    private TodoList todoList;
}
