package fi.jyu.dropboxer.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Metadata {

    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("hash")
    @Expose
    private String hash;
    @SerializedName("bytes")
    @Expose
    private Long bytes;
    @SerializedName("thumb_exists")
    @Expose
    private Boolean thumbExists;
    @SerializedName("rev")
    @Expose
    private String rev;
    @SerializedName("modified")
    @Expose
    private String modified;
    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("is_dir")
    @Expose
    private Boolean isDir;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("root")
    @Expose
    private String root;
    @SerializedName("contents")
    @Expose
    private List<Metadata> contents = null;
    @SerializedName("revision")
    @Expose
    private Long revision;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Long getBytes() {
        return bytes;
    }

    public void setBytes(Long bytes) {
        this.bytes = bytes;
    }

    public Boolean getThumbExists() {
        return thumbExists;
    }

    public void setThumbExists(Boolean thumbExists) {
        this.thumbExists = thumbExists;
    }

    public String getRev() {
        return rev;
    }

    public void setRev(String rev) {
        this.rev = rev;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getIsDir() {
        return isDir;
    }

    public void setIsDir(Boolean isDir) {
        this.isDir = isDir;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public List<Metadata> getContents() {
        return contents;
    }

    public void setContents(List<Metadata> contents) {
        this.contents = contents;
    }

    public Long getRevision() {
        return revision;
    }

    public void setRevision(Long revision) {
        this.revision = revision;
    }

}