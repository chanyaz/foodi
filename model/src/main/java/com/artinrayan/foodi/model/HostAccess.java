package com.artinrayan.foodi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by asus on 5/25/2017.
 */
@Entity
@Table(name = "HostAccess")
public class HostAccess implements Serializable{

    @Id
    @Column(name = "AccessId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hostAccessId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HostId", nullable = false)
    private Host host;

    @Temporal(TemporalType.DATE)
    @Column(name = "CreationDate")
    private Date creationDate;



    public Integer getHostAccessId() {
        return hostAccessId;
    }

    public void setHostAccessId(Integer hostAccessId) {
        this.hostAccessId = hostAccessId;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }


//    @Override
//    public String toString() {
//        return "HostAccessDetail{" +
//                "hostAccessId=" + hostAccessId +
//                host != null ? ", host=" + host.getHostId() : "" +
//                ", hostCountry='" + hostCountry + '\'' +
//                ", hostState='" + hostState + '\'' +
//                ", hostState='" + hostCity + '\'' +
//                ", hostAddress='" + hostAddress + '\'' +
//                ", hostPhoneNumber='" + hostPhoneNumber + '\'' +
//                ", hostWebSite='" + hostWebSite + '\'' +
//                ", latitude='" + latitude + '\'' +
//                ", longitude='" + longitude + '\'' +
//                ", creationDate='" + creationDate + '\'' +
//                '}';
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof HostAccess)) return false;
//
//        HostAccess that = (HostAccess) o;
//
//        if (hostAccessId != null ? !hostAccessId.equals(that.hostAccessId) : that.hostAccessId != null) return false;
//        return !(longitude != null ? !longitude.equals(that.longitude) : that.longitude != null);
//
//    }
//
//    @Override
//    public int hashCode() {
//        int result = hostAccessId != null ? hostAccessId.hashCode() : 0;
//        return result;
//    }
}
