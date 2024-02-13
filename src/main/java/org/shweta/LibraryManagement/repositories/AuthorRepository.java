package org.shweta.LibraryManagement.repositories;

import org.shweta.LibraryManagement.modals.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends JpaRepository<Author,Integer> {

    //there are 3 ways to write down the query
    /*
    * 1)By providing @Query(value="") annotation
    * this query will directly run on db, hibernate will not take care of this query
    * */

    @Query(value = "select * from author where email = :email",nativeQuery = true)
    Author checkAuthor(String email);

    /*
    *2)Hibernate will run this query (HQL)
    * */
    @Query("select a from Author a where a.email =:email")
    Author checkAuthorWithoutNative(String email);

    /*
    * 3)No query at all, ask hibernate, for this we have to follow some
    * way of writing rules and hibernate
    * will create query
    * */
    Author findByEmail(String email);

    //Author findByNameAndEmail(String name, String email);

}
