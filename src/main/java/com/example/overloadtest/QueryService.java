package com.example.overloadtest;

import com.example.overloadtest.domain.main.Information;
import com.example.overloadtest.domain.main.InformationRepository;
import com.example.overloadtest.domain.sub.SubInfo;
import com.example.overloadtest.domain.sub.SubInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
public class QueryService {
    private final InformationRepository informationRepository;
    private final SubInfoRepository subInfoRepository;

    @Autowired
    public QueryService(InformationRepository informationRepository, SubInfoRepository subInfoRepository) {
        this.informationRepository = informationRepository;
        this.subInfoRepository = subInfoRepository;
    }

    @Transactional(readOnly = true)
    public void findAllByJoin(SubInfo subInfo) {
        Information information = informationRepository.findInformationBySubInfos(subInfo).orElseThrow(
                () -> new IllegalArgumentException("없음")
        );
        log.info(information.toString());
        information.getSubInfos().forEach(e -> log.info(e.toString()));
    }

    @Transactional(readOnly = true)
    public void findAllByWhere(SubInfo subInfo) {
        Information information = informationRepository.findBySubInfos(subInfo.getIdx()).orElseThrow(
                () -> new IllegalArgumentException("없음")
        );
        log.info(information.toString());
        information.getSubInfos().forEach(e -> log.info(e.toString()));
    }

    @Transactional(readOnly = true)
    public SubInfo getSubInfoByIdx(Long idx) {
        return subInfoRepository.findById(idx).orElseThrow();
    }
}
