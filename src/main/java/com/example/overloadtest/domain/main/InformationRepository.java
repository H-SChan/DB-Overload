package com.example.overloadtest.domain.main;

import com.example.overloadtest.domain.sub.SubInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface InformationRepository extends JpaRepository<Information, Long> {
    Optional<Information> findInformationBySubInfos(SubInfo subInfos);

    @Query(value = "select * from information i where i.information_id = (select information_information_id from sub_info s where s.sub_info_id = ?)", nativeQuery = true)
    Optional<Information> findBySubInfos(Long subInfoIdx);
}
