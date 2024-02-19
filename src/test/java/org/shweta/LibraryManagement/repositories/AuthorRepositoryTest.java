package org.shweta.LibraryManagement.repositories;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shweta.LibraryManagement.modals.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class AuthorRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;

    private Author author;
    /*AuthorRepository is directly dependent upon db not on any other class
    * Hence we use dummy db H2 by adding its dependency
    * H2 is in memory DB*/

    @BeforeEach
    public void setup(){
        author=Author.builder().id(1).email("shweta@gmail.com").build();
       //h2 db
        authorRepository.save(author);
    }

    @Test
    public void testFindByEmail(){
       Author a= authorRepository.findByEmail("shweta@gmail.com");
        Assert.assertEquals(author.getEmail(),a.getEmail());
    }

}
