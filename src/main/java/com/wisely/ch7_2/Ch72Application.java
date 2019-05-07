package com.wisely.ch7_2;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wisely.ch7_2.bean.Person;

@Controller
@SpringBootApplication
public class Ch72Application {

	@RequestMapping("/")
	public String index(Model model) {
		Person single = new Person("aa",11);
		List<Person> people = new ArrayList<Person>();
		Person single1 = new Person("xx",11);
		Person single2 = new Person("yy",22);
		Person single3 = new Person("zz",33);
		people.add(single1);
		people.add(single2);
		people.add(single3);
		model.addAttribute("singlePerson",single);
		model.addAttribute("people", people);
		return "index";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Ch72Application.class, args);
	}

}
