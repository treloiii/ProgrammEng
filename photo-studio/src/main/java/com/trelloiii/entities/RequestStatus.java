package com.trelloiii.entities;

/**
 * Перечисление для описания статуса заявки
 * @author trelloiii
 */
public enum RequestStatus {
    /** Заявка обрабатывается*/
    PROCESSING,
    /** Заявка в процессе исполнения*/
    INPROGRESS,
    /** Все готово*/
    DONE
}
