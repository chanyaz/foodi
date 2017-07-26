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
import java.util.HashSet;
import java.util.Set;

/**
 * Created by asus on 5/25/2017.
 */
@Entity
@Table(name = "Host")
@NamedQueries
    (
        {
            @NamedQuery(name=Host.GET_HOST_BY_ID, query=Host.GET_HOST_BY_ID_QUERY),
            @NamedQuery(name=Host.GET_HOST_BY_USER_ID, query=Host.GET_HOST_BY_USER_ID_QUERY),
            @NamedQuery(name=Host.GET_HOST_BY_HOST_ID, query=Host.GET_HOST_BY_HOST_ID_QUERY),
            @NamedQuery(name=Host.UPDATE_HOST_BY_ID, query=Host.UPDATE_HOST_BY_ID_QUERY)
        }
    )

public class Host implements Serializable{

    static final String GET_HOST_BY_ID_QUERY = "select DISTINCT(h) from Host h left join fetch h.hostAccesses where h.hostId = :id";
    public static final String GET_HOST_BY_ID = "GET_HOST_BY_ID";

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "host", orphanRemoval = true)
    @Fetch(FetchMode.JOIN)
//    @JsonIgnore
    private Set<HostAccess> hostAccesses = new HashSet<HostAccess>();


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

    public Set<HostAccess> getHostAccesses() {
        return hostAccesses;
    }

    public void setHostAccesses(Set<HostAccess> hostAccesses) {
        this.hostAccesses = hostAccesses;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
