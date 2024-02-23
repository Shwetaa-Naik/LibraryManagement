package org.shweta.LibraryManagement.dtoRequests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionRequest {

   /* @NotBlank(message = "should provide student contact number")
    private String studentPhoneNumber;*/

    private String bookNumber;

    @Positive(message = "amount should be greater than zero")
    private int money;
}
