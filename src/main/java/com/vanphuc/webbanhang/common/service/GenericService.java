package com.vanphuc.webbanhang.common.service;

import com.vanphuc.webbanhang.common.model.BaseEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GenericService<T extends BaseEntity, D, I> {
    JpaRepository<T, I> getRepository();

    ModelMapper getMapper();

    default List<D> findAll(Class<D> clazz) {
        return getRepository().findAll().stream().map((model) ->
                getMapper().map(model, clazz)
        ).toList();
    }

    default Optional<D> findByID(I id, Class<D> clazz) {
        D dto = null;
        Optional<T> modelOptional = getRepository().findById(id);
        if (modelOptional.isPresent()) dto = getMapper().map(modelOptional.get(), clazz);
        return Optional.of(dto);
    }

    default T save(T entity) {
        return getRepository().save(entity);
    }

    default T update(T entity) {
        return getRepository().save(entity);
    }

    default void delete(T entity) {
        getRepository().delete(entity);
    }

    default void deleteByID(I id) {
        getRepository().deleteById(id);
    }
}
