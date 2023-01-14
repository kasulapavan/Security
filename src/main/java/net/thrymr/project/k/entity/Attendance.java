package net.thrymr.project.k.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.thrymr.project.k.enums.AttendanceStatus;
import net.thrymr.project.k.service.EmployeeService;

import java.time.LocalDate;
@Entity
@Table(name = "attendance")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private AttendanceStatus attendanceStatus;

    @ManyToOne
    @JsonIgnore
    private Employee employee;
}
