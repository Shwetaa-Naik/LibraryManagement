package org.shweta.LibraryManagement.dtoRequests;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    private String bookNumber;

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
