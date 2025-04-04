package com.zocode.event.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "event_company_logins")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventCompanyLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    private boolean active;

}

