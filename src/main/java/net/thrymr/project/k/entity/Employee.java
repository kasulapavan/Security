package net.thrymr.project.k.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.thrymr.project.k.enums.RoleType;

import java.util.List;

@Entity
@Table(name = "employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee  {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String employeeName;
    @Column(name = "email", unique = true)

    private String email;
    @Enumerated(EnumType.STRING)

    private RoleType roleType;

    private String password;


    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "employee")
    private List<Attendance> attendanceList;
}
