package com.artinrayan.foodi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by asus on 5/25/2017.
 */
@Entity
@Table(name = "Host", uniqueConstraints =
        { @UniqueConstraint(columnNames =
                { "Latitude", "Longitude" }) })
@NamedQueries
    (
        {
//            @NamedQuery(name=Host.GET_HOST_BY_ID, query=Host.GET_HOST_BY_ID_QUERY),
            @NamedQuery(name=Host.GET_HOST_BY_USER_ID, query=Host.GET_HOST_BY_USER_ID_QUERY),
            @NamedQuery(name=Host.GET_HOST_BY_HOST_ID, query=Host.GET_HOST_BY_HOST_ID_QUERY),
            @NamedQuery(name=Host.UPDATE_HOST_BY_ID, query=Host.UPDATE_HOST_BY_ID_QUERY)
        }
    )

public class Host implements Serializable{

//    static final String GET_HOST_BY_ID_QUERY = "select DISTINCT(h) from Host h left join fetch h.hostAccesses where h.hostId = :id";
//    public static final String GET_HOST_BY_ID = "GET_HOST_BY_ID";

    static final String GET_HOST_BY_USER_ID_QUERY = "select DISTINCT(h) from Host h left join fetch h.user user WHERE user.id = :userId ";
    public static final String GET_HOST_BY_USER_ID = "GET_HOST_BY_USER_ID";

    static final String GET_HOST_BY_HOST_ID_QUERY = "select DISTINCT(h) from Host h WHERE h.hostId = :hostId ";
    public static final String GET_HOST_BY_HOST_ID = "GET_HOST_BY_HOST_ID";

    static final String UPDATE_HOST_BY_ID_QUERY = "UPDATE Host h SET h.hostName=:name where h.hostId = :id";
    public static final String UPDATE_HOST_BY_ID = "UPDATE_DEPARTMENT_BY_ID";

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "HostId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hostId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="UserId", nullable=false)
    @JsonIgnore
    private User user;

    @NotEmpty
    @Column(name = "HostName", nullable=false)
    private String hostName;

    @NotEmpty
    @Column(name = "HostDetail")
    private String hostDetail;

    @Temporal(TemporalType.DATE)
    @Column(name = "CreationDate")
    private Date creationDate;

    @Column(name = "Enabled", nullable=false)
    private Boolean enabled;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "host", cascade = CascadeType.REMOVE)
    @Fetch(FetchMode.SELECT)
    @JsonIgnore
    private Set<HostFile> hostFiles;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "host", orphanRemoval = true)
//    @Fetch(FetchMode.JOIN)
//    @JsonIgnore
//    private Set<HostAccess> hostAccesses = new HashSet<HostAccess>();


    public Integer getHostId() {
        return hostId;
    }

    public void setHostId(Integer hostId) {
        this.hostId = hostId;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostDetail() {
        return hostDetail;
    }

    public void setHostDetail(String hostDetail) {
        this.hostDetail = hostDetail;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Set<HostFile> getHostFiles() {
        return hostFiles;
    }

    public void setHostFiles(Set<HostFile> hostFiles) {
        this.hostFiles = hostFiles;
    }

    @Override
    public String toString() {
        return "Host{" +
                "hostId=" + hostId +
                ", user=" + user +
                ", hostName='" + hostName + '\'' +
                ", hostDetail='" + hostDetail + '\'' +
                ", creationDate=" + creationDate +
                ", enabled=" + enabled +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Host)) return false;

        Host host = (Host) o;

        if (hostId != null ? !hostId.equals(host.hostId) : host.hostId != null) return false;
        return !(user != null ? !user.equals(host.user) : host.user != null);

    }

    @Override
    public int hashCode() {
        int result = hostId != null ? hostId.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
