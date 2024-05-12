package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entity.CartaoEntity;

@ApplicationScoped
public class CartaoRepository implements PanacheRepository<CartaoEntity> {
}
