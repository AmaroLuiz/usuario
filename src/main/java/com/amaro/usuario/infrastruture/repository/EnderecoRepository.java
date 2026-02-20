package com.amaro.usuario.infrastruture.repository;

import com.amaro.usuario.infrastruture.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
