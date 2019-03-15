package com.alexvak.booklibrary.payload;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BookResponse {

    private final String title;
    private final String bookDownloadUri;
    private final String fileType;
    private final long size;
}
