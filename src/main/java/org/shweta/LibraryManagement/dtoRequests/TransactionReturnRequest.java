package org.shweta.LibraryManagement.dtoRequests;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionReturnRequest {

    private String studentPhoneNumber;

    private String bookNumber;

    private String txnNumber;
}
