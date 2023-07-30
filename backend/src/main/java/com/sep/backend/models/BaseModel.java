package com.sep.backend.models;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * The type Base model.
 */
@MappedSuperclass
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
@Data
public abstract class BaseModel {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "random_five_digit_id")
     @GenericGenerator(name = "random_five_digit_id", strategy = "com.sep.backend.utils.IDGenerator")
     @Column(name = "id", nullable = false, unique = true)
     private Integer id;
/**
 * The Created at.
 */
@Column(name = "created_at", nullable = false)
protected Date createdAt;

/**
 * The Updated at.
 */
@Column(name = "updated_at", nullable = false)
protected Date updatedAt;

/**
 * On create.
 */
@PrePersist
protected void onCreate() {
     updatedAt = createdAt = new Date(System.currentTimeMillis());
}

/**
 * On update.
 */
@PreUpdate
protected void onUpdate() {
     updatedAt = new Date(System.currentTimeMillis());
}

}
