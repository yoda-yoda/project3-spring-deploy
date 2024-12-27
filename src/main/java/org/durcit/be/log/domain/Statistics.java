package org.durcit.be.log.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "statistics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_users", nullable = false)
    private Long totalUsers;

    @Column(name = "total_posts", nullable = false)
    private Long totalPosts;

    @Column(name = "total_logs", nullable = false)
    private Long totalLogs;

    @Column(name = "created_at", nullable = false, unique = true)
    private LocalDate createdAt;

}
