package javaEE.springboot.springbootdemo.repositories;

import javaEE.springboot.springbootdemo.entities.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CategoryRepository extends JpaRepository<Categories, Long> {


}
