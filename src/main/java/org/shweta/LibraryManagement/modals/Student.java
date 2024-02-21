package org.shweta.LibraryManagement.modals;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.shweta.LibraryManagement.enums.StudentType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(length=20)
    private String studentName;

    private String address;

    @Column(length = 15, unique = true,nullable = false)
    private String phoneNumber;

    @Column(length = 30,unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private StudentType status;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    @OneToMany(mappedBy = "student")
    private List<Book> books;

    @OneToMany(mappedBy = "student")
    private List<Transaction> transactions;
}
