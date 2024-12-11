package com.sale_clothes.nhom11.service;

import java.util.List;

public interface IService <T>{
    T create(T dto); // Tạo mới
    List<T> getAll(); // Lấy tất cả
    T getById(int id); // Lấy theo ID
    T update(int id, T dto); // Cập nhật theo ID
    void delete(int id); // Xóa theo ID
}
