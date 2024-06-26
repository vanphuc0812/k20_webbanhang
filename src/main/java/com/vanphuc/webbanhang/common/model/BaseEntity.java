package com.vanphuc.webbanhang.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vanphuc.webbanhang.common.utils.DateTimeUtils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.experimental.UtilityClass;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder // để lớp con kế thừa
@MappedSuperclass //cho các lớp con kế thừa triển khai xuống DB để tạo bảng
@EntityListeners(AuditingEntityListener.class) // gọi để tự động khởi tạo đối tượng
public class BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = Columns.ID)
    protected UUID id;

    @Version
    @Column(name = Columns.VERSION)
    protected int version;

    @CreatedBy
    @Column(name = Columns.CREATED_BY)
    protected String createdBy;

    @CreatedDate
    @Column(name = Columns.CREATED_AT)
    @DateTimeFormat(pattern = DateTimeUtils.DATETIME_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeUtils.DATETIME_FORMAT)
    protected String createdAt;

    @LastModifiedBy
    @Column(name = Columns.LAST_MODIFIED_BY)
    protected String lastModifiedBy;

    @LastModifiedDate
    @Column(name = Columns.LAST_MODIFIED_AT)
    @DateTimeFormat(pattern = DateTimeUtils.DATETIME_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeUtils.DATETIME_FORMAT)
    protected String lastModifiedAt;

    @UtilityClass
    static class Columns {
        static final String ID = "ID";
        static final String VERSION = "VERSION";
        static final String CREATED_BY = "CREATED_BY";
        static final String CREATED_AT = "CREATED_AT";
        static final String LAST_MODIFIED_BY = "LAST_MODIFIED_BY";
        static final String LAST_MODIFIED_AT = "LAST_MODIFIED_AT";
    }
}
