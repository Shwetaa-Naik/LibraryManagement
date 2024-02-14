package org.shweta.LibraryManagement.dtoRequests;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionRequest {


    private String studentPhoneNumber;

    private String bookNumber;

    private int money;
}
