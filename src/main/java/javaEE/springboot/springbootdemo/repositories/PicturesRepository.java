package javaEE.springboot.springbootdemo.repositories;

import javaEE.springboot.springbootdemo.entities.Pictures;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PicturesRepository extends JpaRepository<Pictures, Long> {
    List<Pictures> findAllByItemId(Long id);
}
