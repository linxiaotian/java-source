package com.lxt.learnsource;

import com.lxt.learnsource.config.DoSomethingFacade;
import com.lxt.learnsource.starter.Klass;
import com.lxt.learnsource.starter.School;
import com.lxt.learnsource.starter.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class LearnSourceApplicationTests {

	@Autowired
	private DoSomethingFacade doSomethingFacade;

	@Autowired
	private School school;

	@Test
	void contextLoads() {
		doSomethingFacade.doSomething();
		List<Klass> klassList = school.getKlass();
		for (Klass klass : klassList) {
			List<Student> students = klass.getStudents();
			for(Student student :students) {
				System.out.println(student.toString());
			}
		}
	}

}
