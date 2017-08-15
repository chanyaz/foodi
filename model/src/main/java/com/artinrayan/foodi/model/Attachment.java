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
@Table(name = "Attachment")
public class Attachment implements Serializable{

    @Id
    @Column(name = "AttachmentId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HostId", nullable = false)
    @JsonIgnore
    private Host host;

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

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
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
        return "Attachment{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", fileType='" + fileType + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Attachment)) return false;

        Attachment attachment = (Attachment) o;

        if (id != null ? !id.equals(attachment.id) : attachment.id != null) return false;
        if (!Arrays.equals(fileContent, attachment.fileContent)) return false;
        if (creationDate != null ? !creationDate.equals(attachment.creationDate) : attachment.creationDate != null)
            return false;
        return !(fileType != null ? !fileType.equals(attachment.fileType) : attachment.fileType != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + Arrays.hashCode(fileContent);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (fileType != null ? fileType.hashCode() : 0);
        return result;
    }
}
