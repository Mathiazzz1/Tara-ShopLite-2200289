
package com.darwinruiz.shoplite.repositories;

import com.darwinruiz.shoplite.models.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class ProductRepository {
    private static final List<Product> DATA = new ArrayList<>();
    private static final AtomicLong ID = new AtomicLong(1000);

    static {
        DATA.add(new Product(nextId(), "Smartphone", 299.99));
        DATA.add(new Product(nextId(), "Tablet", 199.50));
        DATA.add(new Product(nextId(), "Smartwatch", 149.90));
        DATA.add(new Product(nextId(), "Audífonos Bluetooth", 49.99));
    }

    public static List<Product> findAll() {
        return Collections.unmodifiableList(DATA);
    }

    public static void save(Product p) {
        DATA.add(p);
    }

    public static long nextId() {
        return ID.incrementAndGet();
    }
}


//ZZZ