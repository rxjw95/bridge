package com.assign.search.integration;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
public class SpringIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void 올바르지_않은_파라미터로_검색하면_BadRequest() throws Exception {
        mvc.perform(get("/blog/search")
                .accept(APPLICATION_JSON)
                .param("query", "")
            )
            .andExpectAll(
                status().isBadRequest(),
                jsonPath("$.message").value("적절하지 않은 요청 값입니다."),
                jsonPath("$.fieldViolations").isArray(),
                jsonPath("$.fieldViolations[0].field").value("query"),
                jsonPath("$.fieldViolations[0].reason").value("질의어는 필수값입니다.")
            )
            .andDo(print());

    }

    @Test
    void 올바른_파라미터로_검색하면_블로그_검색_결과를_반환한다() throws Exception {
        mvc.perform(get("/blog/search")
                .accept(APPLICATION_JSON)
                .param("query", "keyword")
            )
            .andExpectAll(
                status().isOk(),
                jsonPath("$.documents").isArray(),
                jsonPath("$.pageInfo").isMap(),
                jsonPath("$.pageInfo.totalCount").exists(),
                jsonPath("$.pageInfo.pageableCount").exists(),
                jsonPath("$.pageInfo.isLastPage").exists()
            )
            .andDo(print());
    }

    @Test
    void 인기_키워드를_조회하면_최대_10개의_키워드와_빈도수를_반환한다() throws Exception {
        mvc.perform(get("/blog/keyword/hot")
                .accept(APPLICATION_JSON))
            .andExpectAll(
                status().isOk(),
                jsonPath("$").isArray()
            )
            .andDo(print());
    }

}
