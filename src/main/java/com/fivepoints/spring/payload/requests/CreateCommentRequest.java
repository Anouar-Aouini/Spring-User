package com.fivepoints.spring.payload.requests;

import lombok.Data;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

@Data
public class CreateCommentRequest {
    @NotNull
    @Lob
    private String text;
}
