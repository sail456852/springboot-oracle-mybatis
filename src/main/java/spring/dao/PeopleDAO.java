package spring.dao;

import spring.dto.People;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PeopleDAO{

    void add();

    void update();

    @Select("select * from people")
    List<People> listAll();
}
