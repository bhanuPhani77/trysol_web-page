package com.trysol.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trysol.model.TrysolWebApp;

public interface TrysolRepository extends JpaRepository<TrysolWebApp,Integer> {

}
