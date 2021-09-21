package com.lxt.learnsource;

import com.lxt.learnsource.config.DoSomethingFacade;
import com.lxt.learnsource.starter.Klass;
import com.lxt.learnsource.starter.School;
import com.lxt.learnsource.starter.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class LearnSourceApplicationTests {

	@Autowired
	private DoSomethingFacade doSomethingFacade;

	@Autowired
	private School school;

	@Autowired
	private DataSource ds1DataSource;

	@Autowired
	private DataSource ds2DataSource;

	@Test
	void contextLoads() throws SQLException {
//		doSomethingFacade.doSomething();
//		List<Klass> klassList = school.getKlass();
//		for (Klass klass : klassList) {
//			List<Student> students = klass.getStudents();
//			for(Student student :students) {
//				System.out.println(student.toString());
//			}
//		}

		ds1DataSource.getConnection().createStatement().execute("insert into student(id, name) value (12345,'12345')");
		ResultSet resultSet = ds2DataSource.getConnection().createStatement().executeQuery("select * from student");
		while (resultSet.next()) {
			int id = resultSet.getInt(1);
			String name = resultSet.getString(2);
			System.out.println("id=" + id + ",name=" + name);
		}
	}

}
