package org.shweta.LibraryManagement.modals;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.shweta.LibraryManagement.enums.BookType;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(length=30)
    private String bookTitle;

    private String bookNumber;

    private String bookCost;

    @Enumerated(EnumType.ORDINAL)
    private BookType type;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("books")
    private Author author;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("books")
    private Student student;

    @OneToMany(mappedBy = "book")
    private List<Transaction> txns;


}
