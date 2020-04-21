package com.trelloiii.entities;

import javax.persistence.*;
import java.util.Date;
/**
 * Класс описыващий сущность заявка в базе данных.
 * Каждое поле класса - столбец в связанной таблице request
 * @author trelloiii
 */
@Table(name = "request")
@Entity
public class Request {
    /** Поле id*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**Время создания заявки */
    @Column(name = "time")
    private Date creationTime;
    /**
     * Текущий статус исполнения заявки
     * @see RequestStatus
     */
    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    /**
     * Шаблон выбранный в заявке
     * @see Pattern
     */
    @ManyToOne
    @JoinColumn(name="pattern_id")
    private Pattern pattern;
    /**
     * Исполнитель выбранный в заявке
     * @see Implementer
     */
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
