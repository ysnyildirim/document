package com.yil.document.util;

import org.springframework.data.domain.Page;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public class Mapper<S extends Serializable, T extends Serializable> {
    private final Function<S, T> function;

    public Mapper(@NotNull Function<S, T> function) {
        this.function = function;
    }

    public List<T> map(@NotNull Collection<S> source) {
        List<T> list = new ArrayList<>();
        for (S s : source) {
            list.add(function.apply(s));
        }
        return list;
    }

    public PageDto<T> map(@NotNull Page<S> page) {
        PageDto<T> pageDto = new PageDto<>();
        pageDto.setCurrentPage(page.getNumber());
        pageDto.setTotalElements(page.getTotalElements());
        pageDto.setTotalPages(page.getTotalPages());
        List<T> content = new ArrayList<>();
        for (S entity : page.getContent())
            content.add(map(entity));
        pageDto.setContent(content);
        return pageDto;
    }

    public T map(S source) {
        return function.apply(source);
    }

}
