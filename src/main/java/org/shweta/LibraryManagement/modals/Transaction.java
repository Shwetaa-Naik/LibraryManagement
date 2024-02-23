package org.shweta.LibraryManagement.modals;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.shweta.LibraryManagement.enums.TransactionType;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String txnNumber;

    @ManyToOne
    @JoinColumn
   // @JsonIgnore
    @JsonIgnoreProperties("transactions")
    private Student student;

    @ManyToOne
    @JoinColumn
    //@JsonIgnore
    @JsonIgnoreProperties("txns")
    private Book book;

    //adv amount taken by user
    private int paidAmount;

    @Enumerated(EnumType.STRING)
    private TransactionType status;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;
}
