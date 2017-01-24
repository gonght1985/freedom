package com.zdsoft.freedom.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zdsoft.freedom.common.model.BaseEntity;

@Entity
@Table(name = "t_client_owner")
public class ClientOwner extends BaseEntity {

    private static final long serialVersionUID = -1328433676138239314L;

    /**
     * 对应客户
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Client client;

    /**
     * 授予人关联员工
     */
    @Column(length = 32, name = "grantor_code")
    private String grantorCd;

    /**
     * 授予人姓名【冗余】
     */
    @Column(length = 64, name = "grantor_name")
    private String grantorNm;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getGrantorCd() {
        return grantorCd;
    }

    public void setGrantorCd(String grantorCd) {
        this.grantorCd = grantorCd;
    }

    public String getGrantorNm() {
        return grantorNm;
    }

    public void setGrantorNm(String grantorNm) {
        this.grantorNm = grantorNm;
    }

}
