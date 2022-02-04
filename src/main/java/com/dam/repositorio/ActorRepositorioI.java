package com.dam.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dam.entidades.Actor;

@Repository
public interface ActorRepositorioI extends JpaRepository<Actor,Long> {

	
	
}
