package com.sale_clothes.nhom11.service;

import java.util.List;

public interface IService<T,I> {
    T create(T dto); // Tạo mới

    List<T> getAll(); // Lấy tất cả

    T getById(I id); // Lấy theo ID

    void update(I id, T dto); // Cập nhật theo ID

    void delete(I id); // Xóa theo ID
}
