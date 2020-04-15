package com.trelloiii.entities;

import javax.persistence.*;
import java.util.Date;

@Table(name = "request")
@Entity
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "time")
    private Date creationTime;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    @ManyToOne
    @JoinColumn(name="pattern_id")
    private Pattern pattern;

    @ManyToOne
    @JoinColumn(name = "implementer_id")
    private Implementer implementer;

    public int getId() {
        return id;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public Implementer getImplementer() {
        return implementer;
    }

    public void setImplementer(Implementer implementer) {
        this.implementer = implementer;
    }
}
