package com.artinrayan.foodi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by asus on 7/18/2017.
 */
@Entity
@Table(name = "HostAccessFile")
public class HostAccessFile implements Serializable{

    @Id
    @Column(name = "FileId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AccessId", nullable = false)
    @JsonIgnore
    private HostAccess hostAccess;

    @Column(name = "FileContent")
    private byte[] fileContent;

    @Column(name = "Thumbnail")
    private byte[] thumbnail;

    @Temporal(TemporalType.DATE)
    @Column(name = "CreationDate")
    private Date creationDate;

    @Column(name = "FileType")
    private String fileType;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public HostAccess getHostAccess() {
        return hostAccess;
    }

    public void setHostAccess(HostAccess hostAccess) {
        this.hostAccess = hostAccess;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }

    public byte[] getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(byte[] thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return "HostAccessFile{" +
                "id=" + id +
                ", fileContent=" + Arrays.toString(fileContent) +
                ", thumbnail=" + Arrays.toString(thumbnail) +
                ", creationDate=" + creationDate +
                ", fileType='" + fileType + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HostAccessFile)) return false;

        HostAccessFile that = (HostAccessFile) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (!Arrays.equals(fileContent, that.fileContent)) return false;
        if (!Arrays.equals(thumbnail, that.thumbnail)) return false;
        if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null) return false;
        return !(fileType != null ? !fileType.equals(that.fileType) : that.fileType != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + Arrays.hashCode(fileContent);
        result = 31 * result + Arrays.hashCode(thumbnail);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (fileType != null ? fileType.hashCode() : 0);
        return result;
    }
}
