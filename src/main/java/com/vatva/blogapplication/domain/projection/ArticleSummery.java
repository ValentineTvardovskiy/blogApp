package com.vatva.blogapplication.domain.projection;

import java.time.Instant;

public interface ArticleSummery {
  Long getId();
  String getTitle();
  Instant getPublishedDate();
  String getAuthor();
}
