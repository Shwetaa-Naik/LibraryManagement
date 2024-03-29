package org.shweta.LibraryManagement.dtoRequests;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.shweta.LibraryManagement.enums.BookType;
import org.shweta.LibraryManagement.modals.Author;
import org.shweta.LibraryManagement.modals.Book;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookCreateRequest {


    private String authorName;


    private String authorEmail;

    private String bookTitle;

    @NotBlank(message = "Shouldnt be blank")
    private String bookNumber;

    @NotBlank(message = "Please provde cost")
    private String bookCost;


    private BookType bookType;

    public Author toAuthor() {
        return Author.builder().
                authorName(this.authorName).
                email(this.authorEmail).
                build();
    }

    public Book toBook() {
        return Book.builder().
                bookTitle(this.bookTitle).
                bookNumber(this.bookNumber).
                bookCost(this.bookCost).
                type(this.bookType).
                build();
    }
}
