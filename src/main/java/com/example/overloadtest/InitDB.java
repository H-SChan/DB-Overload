package com.example.overloadtest;

import com.example.overloadtest.domain.main.Information;
import com.example.overloadtest.domain.main.InformationRepository;
import com.example.overloadtest.domain.sub.SubInfo;
import com.example.overloadtest.domain.sub.SubInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Component
public class InitDB {

    private final InitDBService initDBService;

    @Autowired
    public InitDB(InitDBService initDBService) {
        this.initDBService = initDBService;
    }

    @PostConstruct
    public void initDB() {
        initDBService.putTwentyThousands();
    }

    @Slf4j
    @Component
    @Transactional
    private static class InitDBService {
        private final InformationRepository informationRepository;
        private final SubInfoRepository subInfoRepository;

        @Autowired
        public InitDBService(InformationRepository informationRepository, SubInfoRepository subInfoRepository) {
            this.informationRepository = informationRepository;
            this.subInfoRepository = subInfoRepository;
        }

        public void putTwentyThousands() {
            if (informationRepository.count() > 0) return;
            for (int i = 1; i <= 100000; i++) {
                if (i % 7 == 0) {
                    // i가 7로 나눠질 때 main Information 생성 / 내용 -> divide7
                    Information divide7Information = new Information("divide7");

                    // i가 7로 나눠진다면 SubInfo 를 5개 추가 / 내용 -> i-j-uuid
                    for (int j = 0; j < 5; j++) {
                        SubInfo subInfoIn7 = new SubInfo(i + "-" + j + "-" + UUID.randomUUID());
                        divide7Information.addSubInfo(subInfoIn7);
                        subInfoRepository.save(subInfoIn7);
                    }
                    informationRepository.save(divide7Information);

                    log.info("divide7-{} saved", i);
                } else if (i % 5 == 0) {
                    // i가 5로 나눠질 때 main Information 생성 / 내용 -> divide7
                    Information divide5Information = new Information("divide5");

                    // i가 5로 나눠진다면 SubInfo 를 3개 추가 / 내용 -> i-j-uuid
                    for (int j = 0; j < 3; j++) {
                        SubInfo subInfoIn5 = new SubInfo(i + "-" + j + "-" + UUID.randomUUID());
                        divide5Information.addSubInfo(subInfoIn5);
                        subInfoRepository.save(subInfoIn5);
                    }
                    informationRepository.save(divide5Information);

                    log.info("divide5-{} saved", i);
                } else {
                    // 나머지 상황은 그냥 저장
                    Information dataInformation = new Information("not divided");
                    informationRepository.save(dataInformation);
                    log.info("data-{} saved", i);
                }
            }
        }
    }
}
