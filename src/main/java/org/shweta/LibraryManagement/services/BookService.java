package org.shweta.LibraryManagement.services;

import org.shweta.LibraryManagement.dtoRequests.BookCreateRequest;
import org.shweta.LibraryManagement.enums.BookType;
import org.shweta.LibraryManagement.enums.FilterType;
import org.shweta.LibraryManagement.enums.OperatorType;
import org.shweta.LibraryManagement.modals.Author;
import org.shweta.LibraryManagement.modals.Book;
import org.shweta.LibraryManagement.repositories.AuthorRepository;
import org.shweta.LibraryManagement.repositories.BookRepository;
import org.shweta.LibraryManagement.repositories.RedisDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private RedisDataRepository redisDataRepository;

    public Book addBook(BookCreateRequest bookCreateRequest) {
        //extract author email from bookCreateRequest
        //check with querying if author is present already in author table

       Author authorInDB= authorRepository.findByEmail(bookCreateRequest.getAuthorEmail());
        if(authorInDB == null){
            //create row inside table author
            authorInDB=authorRepository.save(bookCreateRequest.toAuthor());
        }
        //insert data into book table

        Book bookObj=bookCreateRequest.toBook();
        bookObj.setAuthor(authorInDB);
        Book book1 = bookRepository.save(bookObj);
        redisDataRepository.setBookToRedis(book1);
        return book1;
    }

    public List<Book> getBooks(FilterType filterType, OperatorType operator, String value) {
        //instead of creating different APIs for getting books on different filters
        //create single API with switch case according to filter criteria

        switch (operator){
            case EQUALS:
                switch (filterType){
                    case BOOK_NO:
                            List<Book> bookListByNumber=bookRepository.findByBookNumber(value);
                            /*List<Book> bookFromRedis=redisDataRepository.getBookFromRedisByBookNumber(value);
                                if(!bookFromRedis.isEmpty()){
                                    return bookFromRedis;
                                }
                                else{
                                    if(!bookListByNumber.isEmpty()){
                                        redisDataRepository.setBookToRedisByBookNumber(bookListByNumber.get(0));
                                        return bookListByNumber;
                                    }
                                }*/

                        return bookListByNumber;
                    case COST:
                        List<Book> bookListByCost=bookRepository.findByBookCost(value);
                        return bookListByCost;
                    case BOOKTYPE:
                        List<Book> bookListByType=bookRepository.findByType(BookType.valueOf(value));
                        return bookListByType;
                    case AUTHOR_NAME:
                        List<Book> bookListByAuthorName=bookRepository.findByAuthor_AuthorName(value);
                        return bookListByAuthorName;
                    //default://throw exception
                }
            case IN:
                switch (filterType){
                    case BOOK_NO:
                    case COST:
                    case BOOKTYPE:
                    case AUTHOR_NAME:
                   // default://throw exception

                }
            case LESS_THAN:
                switch (filterType){
                    case BOOK_NO:
                    case COST:
                    case BOOKTYPE:
                    case AUTHOR_NAME:
                    //default://throw exception
                }
            case GREATER_THAN:
                switch (filterType){
                    case BOOK_NO:
                    case COST:
                    case BOOKTYPE:
                    case AUTHOR_NAME:
                   // default://throw exception
                }
            case LESS_THAN_EQUALS:
                switch (filterType){
                    case BOOK_NO:
                    case COST:
                    case BOOKTYPE:
                    case AUTHOR_NAME:
                   // default://throw exception
                }
            default:return new ArrayList<>();

        }


    }

    public void saveUpdate(Book book){
        bookRepository.save(book);
    }
    public void updateStudentId(int studentId, int bookId) {
        bookRepository.saveUpdate(studentId,bookId);
    }
}
