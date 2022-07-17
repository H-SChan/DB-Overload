package com.example.overloadtest.query;

import com.example.overloadtest.QueryService;
import com.example.overloadtest.domain.sub.SubInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class QueryTest {

    @Autowired
    QueryService queryService;

    @Test
    @Transactional
    void join() {
        SubInfo subInfo = queryService.getSubInfoByIdx(120000L);

        long beforeTime = System.currentTimeMillis();

        queryService.findAllByJoin(subInfo);

        long afterTime = System.currentTimeMillis();
        long secDiffTime = (afterTime - beforeTime);
        System.out.println("경과 시간(ms) : " + secDiffTime);
    }

    @Test
    @Transactional
    void where() {
        SubInfo subInfo = queryService.getSubInfoByIdx(120000L);

        long beforeTime = System.currentTimeMillis();

        queryService.findAllByWhere(subInfo);

        long afterTime = System.currentTimeMillis();
        long secDiffTime = (afterTime - beforeTime);
        System.out.println("경과 시간(ms) : " + secDiffTime);
    }

}
