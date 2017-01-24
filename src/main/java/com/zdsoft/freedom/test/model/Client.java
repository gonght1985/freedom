package com.zdsoft.freedom.test.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.zdsoft.freedom.common.model.BaseEntity;

@Entity
@Table(name = "t_client")
public class Client extends BaseEntity {

    private static final long serialVersionUID = -3387903147519938652L;

    /**
     * 客户名称
     */
    @Column(length = 64, name = "client_name")
    private String clientNm;

    /**
     * 客户拥有者,orphanRemoval=true配置表明删除无关联的数据。级联更新子结果集时此配置最关键
     */
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<ClientOwner> crmClientOwners = new HashSet<ClientOwner>();

    public String getClientNm() {
        return clientNm;
    }

    public void setClientNm(String clientNm) {
        this.clientNm = clientNm;
    }

    public Set<ClientOwner> getCrmClientOwners() {
        return crmClientOwners;
    }

    public void setCrmClientOwners(Set<ClientOwner> crmClientOwners) {
        this.crmClientOwners = crmClientOwners;
    }

    public void removeClientOwner(String empCd) {
        for (ClientOwner cco : this.getCrmClientOwners()) {
            // 权限人
            if (cco.getGrantorCd().equals(empCd)) {
                this.getCrmClientOwners().remove(cco);
            }
        }
    }
}
