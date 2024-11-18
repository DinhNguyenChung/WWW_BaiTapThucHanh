package com.example.lab_week6.backend.DTO;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * DTO for {@link com.example.lab_week6.backend.models.PostComment}
 */
@Value
public class PostCommentDto implements Serializable {
    Long id;
    String title;
    Boolean published;
    LocalDateTime createdAt;
    LocalDateTime publishedAt;
    String content;
}