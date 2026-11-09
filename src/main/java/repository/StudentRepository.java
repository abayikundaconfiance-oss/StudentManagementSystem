
package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.config.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    public void save(Student student) throws SQLException {

        String sql = "INSERT INTO students (full_name, age, email) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, student.getFullName());
            stmt.setInt(2, student.getAge());
            stmt.setString(3, student.getEmail());

            stmt.executeUpdate();
        }
    }

    public List<Student> findAll() throws SQLException {

        List<Student> students = new ArrayList<>();

        String sql = "SELECT * FROM students";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getInt("age"),
                        rs.getString("email")
                ));
            }
        }

        return students;
    }
}

