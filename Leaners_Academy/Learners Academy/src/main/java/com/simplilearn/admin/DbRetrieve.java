package com.simplilearn.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import com.simplilearn.models.Student;
import com.simplilearn.models.Subject;
import com.simplilearn.models.Teacher;
import com.simplilearn.models.Class;

public class DbRetrieve {

	private DataSource dataSource;

	public DbRetrieve(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Student> getStudents() {

		List<Student> students = new ArrayList<>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;

		try {
			connection = dataSource.getConnection();
			String sql = "SELECT * FROM students";
			statement = connection.createStatement();
			result = statement.executeQuery(sql);
			
			while (result.next()) {
				int id = result.getInt("id");
				String firstName = result.getString("fname");
				String lastName = result.getString("lname");
				int age = result.getInt("age");
				int aclass = result.getInt("class");
				Student tempStudent = new Student(id, firstName, lastName, age, aclass);
				students.add(tempStudent);

			}

		} catch (Exception e) {
		} finally {
			close(connection, statement, result);
		}
		return students;

	}

	public List<Teacher> getTeachers() {

		List<Teacher> teachers = new ArrayList<>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;

		try {
			connection = dataSource.getConnection();
			String sql = "SELECT * FROM teachers";
			statement = connection.createStatement();
			result = statement.executeQuery(sql);

			while (result.next()) {
				int id = result.getInt("id");
				String firstName = result.getString("fname");
				String lastName = result.getString("lname");
				int age = result.getInt("age");
				Teacher temp = new Teacher(id, firstName, lastName, age);
				teachers.add(temp);

			}

		} catch (Exception e) {
		} finally {
			close(connection, statement, result);
		}
		return teachers;

	}

	public List<Subject> getSubjects() {

		List<Subject> subjects = new ArrayList<>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;

		try {
			connection = dataSource.getConnection();
			String sql = "SELECT * FROM subjects";
			statement = connection.createStatement();
			result = statement.executeQuery(sql);

			while (result.next()) {
				int id = result.getInt("id");
				String name = result.getString("name");
				String shortcut = result.getString("shortcut");
				Subject temp = new Subject(id, name,shortcut);
				subjects.add(temp);
			}

		} catch (Exception e) {
		} finally {
			close(connection, statement, result);
		}
		return subjects;
	}

	public List<Class> getClasses() {

		List<Class> classes = new ArrayList<>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;

		try {
			connection = dataSource.getConnection();
			String sql = "SELECT * FROM classes";
			statement = connection.createStatement();
			result = statement.executeQuery(sql);
		
			while (result.next()) {
				int id = result.getInt("id");
				int section = result.getInt("section");
				int subject = result.getInt("subject");
				int teacher = result.getInt("teacher");
				String time = result.getString("time");
				Teacher tempTeacher = loadTeacher(teacher);
				Subject tempSubject = loadSubject(subject);
				String teacher_name = tempTeacher.getFname() + " " + tempTeacher.getLname();
				Class temp = new Class(id, section, teacher_name, tempSubject.getName(), time);
				classes.add(temp);

			}

		} catch (Exception e) {
		} finally {
			close(connection, statement, result);
		}
		return classes;

	}

	public Teacher loadTeacher(int teacherId) {

		Teacher theTeacher = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;

		try {
			connection = dataSource.getConnection();
			String sql = "SELECT * FROM teachers WHERE id = " + teacherId;
			statement = connection.createStatement();
			result = statement.executeQuery(sql);
			
			while (result.next()) {
				int id = result.getInt("id");
				String fname = result.getString("fname");
				String lname = result.getString("lname");
				int age = result.getInt("age");
				theTeacher = new Teacher(id, fname, lname, age);

			}

		} catch (Exception e) {
		} finally {
			close(connection, statement, result);
		}
		return theTeacher;

	}

	public Subject loadSubject(int subjectId) {
		Subject theSubject = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;

		try {
			connection = dataSource.getConnection();
			String sql = "SELECT * FROM subjects WHERE id = " + subjectId;
			statement = connection.createStatement();
			result = statement.executeQuery(sql);
			
			while (result.next()) {
				int id = result.getInt("id");
				String name = result.getString("name");
				String shortcut = result.getString("shortcut");

				theSubject = new Subject(id, name,shortcut);

			}

		} catch (Exception e) {
		} finally {
			close(connection, statement, result);
		}
		return theSubject;

	}

	public Class loadClass(int classId) {

		Class theClass = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;

		try {
			connection = dataSource.getConnection();
			String sql = "SELECT * FROM clasess WHERE id = " + classId;
			statement = connection.createStatement();
			result = statement.executeQuery(sql);
			
			while (result.next()) {
				int id = result.getInt("id");
				int section = result.getInt("section");
				int subject = result.getInt("subject");
				int teacher = result.getInt("teacher");
				String time = result.getString("time");

				Teacher tempTeacher = loadTeacher(teacher);
				Subject tempSubject = loadSubject(subject);

				String teacher_name = tempTeacher.getFname() + " " + tempTeacher.getLname();

			}

		} catch (Exception e) {
		} finally {
			close(connection, statement, result);
		}
		return theClass;

	}

	public List<Student> loadClassStudents(int classId) {

		List<Student> students = new ArrayList<>();

		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;

		try {
			connection = dataSource.getConnection();
			String sql = "SELECT * FROM students WHERE class = " + classId;
			statement = connection.createStatement();
			result = statement.executeQuery(sql);

			while (result.next()) {
				int id = result.getInt("id");
				String firstName = result.getString("fname");
				String lastName = result.getString("lname");
				int age = result.getInt("age");
				int aclass = result.getInt("class");
				Student tempStudent = new Student(id, firstName, lastName, age, aclass);
				students.add(tempStudent);
			}
		} catch (Exception e) {
		} finally {
			close(connection, statement, result);
		}
		return students;

	}

	private void close(Connection connection, Statement statement, ResultSet result) {

		try {
			if (result != null) {
				result.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
