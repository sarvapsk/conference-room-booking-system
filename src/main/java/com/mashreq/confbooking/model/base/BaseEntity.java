package com.mashreq.confbooking.model.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
public abstract class BaseEntity  implements Serializable {

    @Column(name = "created_on")
    protected LocalDateTime createdOn;

    @Column(name = "updated_on")
    protected LocalDateTime updatedOn;

    @Column(name = "created_by")
    protected String createdBy;

    @Column(name = "updated_by")
    protected String updatedBy;

    @PrePersist
    protected void onCreate() {
        createdOn = LocalDateTime.now();
        createdBy = ObjectUtils.isEmpty(createdBy) ? "System" : createdBy;

        updatedOn = LocalDateTime.now();
        updatedBy = ObjectUtils.isEmpty(updatedBy) ? "System" : updatedBy;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedOn = LocalDateTime.now();
        updatedBy = ObjectUtils.isEmpty(updatedBy) ? "System" : updatedBy;
    }
}

