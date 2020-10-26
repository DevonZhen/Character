package com.bss.personal.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.bss.personal.domain.Item;

@Repository
public interface ItemRepository extends BaseRepository<Item, Long>{

}
