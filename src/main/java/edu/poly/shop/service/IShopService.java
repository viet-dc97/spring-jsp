/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.shop.service;

import java.util.Map;

public interface IShopService<T> {

    public void add(T t);

    public void update(T t);

    public void delete(T t);

    public void clear();

    public int getCount();

    public double totalPayment(T t);

    public double totalPayment();

    public Map<Integer, T> get();

    public T get(int id);
}
