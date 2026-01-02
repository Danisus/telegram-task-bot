package org.example.taskbot.entities;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "tasks")
public class Task {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private String description;
    private boolean completed = false;
    @Column(nullable = false, unique = true)
    private int position;

}
