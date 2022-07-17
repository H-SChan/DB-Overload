package com.example.overloadtest.domain.main;

import com.example.overloadtest.domain.sub.SubInfo;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Information {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "information_id", nullable = false)
    private Long idx;

    private String divide;

    @OneToMany(mappedBy = "information", fetch = FetchType.EAGER)
    private List<SubInfo> subInfos = new ArrayList<>();

    public Information() {
    }

    public Information(String divide) {
        this.divide = divide;
    }

    public void addSubInfo(SubInfo subInfo) {
        subInfo.setInformation(this);
    }

    @Override
    public String toString() {
        return "Information{" +
                "idx=" + idx +
                ", divide='" + divide + '\'' +
                '}';
    }
}
