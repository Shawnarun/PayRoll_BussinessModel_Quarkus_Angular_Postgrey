package com.example.Repository;

import com.example.Entity.Business;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BusinessRepository implements PanacheRepository<Business> {
}
