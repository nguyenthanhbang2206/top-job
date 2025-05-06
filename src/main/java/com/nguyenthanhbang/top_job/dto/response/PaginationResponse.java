package com.nguyenthanhbang.top_job.dto.response;

import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaginationResponse<T>{
    private Pagination pagination;
    private List<T> items;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Pagination {
        private int page;
        private int size;
        private int totalPages;
        private long totalItems;
    }
}
