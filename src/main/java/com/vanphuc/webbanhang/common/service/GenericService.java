package com.vanphuc.webbanhang.common.service;

import com.vanphuc.webbanhang.common.model.BaseEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenericService<T extends BaseEntity, D, I> {
    JpaRepository<T, I> getRepository();

    ModelMapper getMapper();

    default List<D> findAll(Class<D> clazz) {
        return getRepository().findAll().stream().map((model) ->
                getMapper().map(model, clazz)
        ).toList();
    }

    default T save(T entity) {
        return getRepository().save(entity);
    }
}
