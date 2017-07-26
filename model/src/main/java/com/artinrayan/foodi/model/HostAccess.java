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
@Table(name = "HostAccess", uniqueConstraints =
        { @UniqueConstraint(columnNames =
        { "Latitude", "Longitude" }) })
public class HostAccess implements Serializable{

    @Id
    @Column(name = "AccessId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hostAccessId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HostId", nullable = false)
    private Host host;

    @NotEmpty
    @Column(name = "HostCountry", nullable=false)
    private String hostCountry;

    @NotEmpty
    @Column(name = "HostState", nullable=false)
    private String hostState;

    @NotEmpty
    @Column(name = "HostCity", nullable=false)
    private String hostCity;

    @NotEmpty
    @Column(name = "HostAddress", nullable=false)
    private String hostAddress;

    @Column(name = "HostPhoneNumber")
    private String hostPhoneNumber;

    @Column(name = "HostWebSite")
    private String hostWebSite;

    @NotEmpty
    @Column(name = "Latitude", nullable=false)
    private String latitude;

    @NotEmpty
    @Column(name = "Longitude", nullable=false)
    private String longitude;

    @Temporal(TemporalType.DATE)
    @Column(name = "CreationDate")
    private Date creationDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hostAccess", cascade = CascadeType.REMOVE)
    @Fetch(FetchMode.JOIN)
    private Set<HostAccessFile> hostAccessFiles;


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

    public String getHostCountry() {
        return hostCountry;
    }

    public void setHostCountry(String hostCountry) {
        this.hostCountry = hostCountry;
    }

    public String getHostState() {
        return hostState;
    }

    public void setHostState(String hostState) {
        this.hostState = hostState;
    }

    public String getHostCity() {
        return hostCity;
    }

    public void setHostCity(String hostCity) {
        this.hostCity = hostCity;
    }

    public String getHostAddress() {
        return hostAddress;
    }

    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    }

    public String getHostPhoneNumber() {
        return hostPhoneNumber;
    }

    public void setHostPhoneNumber(String hostPhoneNumber) {
        this.hostPhoneNumber = hostPhoneNumber;
    }

    public String getHostWebSite() {
        return hostWebSite;
    }

    public void setHostWebSite(String hostWebSite) {
        this.hostWebSite = hostWebSite;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Set<HostAccessFile> getHostAccessFiles() {
        return hostAccessFiles;
    }

    public void setHostAccessFiles(Set<HostAccessFile> hostAccessFiles) {
        this.hostAccessFiles = hostAccessFiles;
    }

    @Override
    public String toString() {
        return "HostAccessDetail{" +
                "hostAccessId=" + hostAccessId +
                host != null ? ", host=" + host.getHostId() : "" +
                ", hostCountry='" + hostCountry + '\'' +
                ", hostState='" + hostState + '\'' +
                ", hostState='" + hostCity + '\'' +
                ", hostAddress='" + hostAddress + '\'' +
                ", hostPhoneNumber='" + hostPhoneNumber + '\'' +
                ", hostWebSite='" + hostWebSite + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", creationDate='" + creationDate + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HostAccess)) return false;

        HostAccess that = (HostAccess) o;

        if (hostAccessId != null ? !hostAccessId.equals(that.hostAccessId) : that.hostAccessId != null) return false;
        if (latitude != null ? !latitude.equals(that.latitude) : that.latitude != null) return false;
        return !(longitude != null ? !longitude.equals(that.longitude) : that.longitude != null);

    }

    @Override
    public int hashCode() {
        int result = hostAccessId != null ? hostAccessId.hashCode() : 0;
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        return result;
    }
}
