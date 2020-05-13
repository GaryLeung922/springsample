package com.xiaojiaqi.repository;

import com.xiaojiaqi.entity.Spitter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: liangjiaqi
 * @Date: 2020/3/26 1:08 PM
 */
@Repository
public interface SpitterRepository extends CrudRepository<Spitter, Integer> {
}
