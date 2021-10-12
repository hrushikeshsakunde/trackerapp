package com.service.tracker.mapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Base interface for object mapping
 *
 * @author Hrushikesh Sakunde
 * @since 12.10.20
 */

public interface Mapper<F, T> {

    T from(F from);

    default List<T> from(List<F> from) {
        return from.stream().map(this::from).collect(Collectors.toList());
    }

}
