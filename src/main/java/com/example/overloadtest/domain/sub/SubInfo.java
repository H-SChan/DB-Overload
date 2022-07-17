package com.example.overloadtest.domain.sub;

import com.example.overloadtest.domain.main.Information;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class SubInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_info_id", nullable = false)
    private Long idx;

    private String uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    private Information information;

    public SubInfo() {
    }

    public SubInfo(String uuid) {
        this.uuid = uuid;
    }

    public void setInformation(Information information) {
        if (this.information != null) {
            this.information.getSubInfos().remove(this);
        }
        this.information = information;
        information.getSubInfos().add(this);
    }

    @Override
    public String toString() {
        return "SubInfo{" +
                "idx=" + idx +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
