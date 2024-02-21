package org.shweta.LibraryManagement.repositories;

import org.shweta.LibraryManagement.modals.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RedisDataRepository {

    @Autowired
    RedisTemplate<String ,Object> redisTemplate;

    private final String BOOK_KEY="book:";


    public void setBookToRedis(Book book){

        setBookToRedisByAuthorName(book);
        setBookToRedisByBookNumber(book);
        setBookToRedisByBookType(book);
    }

    public void setBookToRedisByAuthorName(Book book){
        redisTemplate.opsForList().leftPush(BOOK_KEY+book.getAuthor().getId(),book);
    }

    public void setBookToRedisByBookNumber(Book book){
       // redisTemplate.opsForValue().set(BOOK_KEY+book.getBookNumber(),book);
            redisTemplate.opsForList().leftPush(BOOK_KEY+book.getBookNumber(),book);

    }

    public void setBookToRedisByBookType(Book book){
        redisTemplate.opsForList().leftPush(BOOK_KEY+book.getType(),book);
    }

    public List<Book> getBookFromRedisByBookNumber(String value) {
       return (List<Book>)(Object)redisTemplate.opsForList().range(BOOK_KEY+value,0,-1);

    }
}
