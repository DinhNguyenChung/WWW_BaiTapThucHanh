package com.example.lab_week6.backend.DTO;

import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.example.lab_week6.backend.models.Post}
 */
@Value
public class PostDto implements Serializable {
    Long id;
    String title;
    String metaTitle;
    String summary;
    Boolean published;
    Date createdAt;
    Date updatedAt;
    Date publishedAt;
    String content;
}