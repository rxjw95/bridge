package com.assign.search.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.Objects;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

@Getter
@EqualsAndHashCode
public class KeywordSearchRequest {

    @NotBlank(message = "질의어는 필수값입니다.")
    private final String query;

    @Range(min = 1, max = 50, message = "1~50 사이의 값을 입력해주세요. [기본 값: 1]")
    private final Integer page;

    @Range(min = 1, max = 50, message = "1~50 사이의 값을 입력해주세요. [기본 값: 10]")
    private final Integer size;

    @Pattern(regexp = "accuracy|recency", message = "accuracy(정확도순) 또는 recency(최신순)으로 정렬할 수 있습니다. [기본 값: accuracy]")
    private final String sort;

    public KeywordSearchRequest(String query, Integer page, Integer size, String sort) {
        this.query = query;
        this.page = Objects.requireNonNullElse(page, 1);
        this.size = Objects.requireNonNullElse(size, 10);
        this.sort = Objects.requireNonNullElse(sort, "accuracy");
    }

}
