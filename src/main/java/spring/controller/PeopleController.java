package spring.controller;


import spring.dao.PeopleDAO;
import spring.dto.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PeopleController {

    @Autowired
    private PeopleDAO peopleDAO;

    @ResponseBody
    @RequestMapping("/list")
    public List<People> listAll() {
        List<People> list = peopleDAO.listAll();
        return list;
    }

    @ResponseBody
    @RequestMapping("/test")
    public String testAPI() {
        return "test";
    }
}
