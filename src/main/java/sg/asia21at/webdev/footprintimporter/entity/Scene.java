package sg.asia21at.webdev.footprintimporter.entity;

import java.util.Date;

public class Scene {
    private Integer id;

    private Date startTime;

    private Double lat;

    private Double lon;

    private Double cloudCover;

    private Double roll;

    private Double pitch;

    private Integer sceneNum;

    private Integer satellite;

    private Date endTime;

    private Object geom;

    private String stripId;

    private String folderName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(Double cloudCover) {
        this.cloudCover = cloudCover;
    }

    public Double getRoll() {
        return roll;
    }

    public void setRoll(Double roll) {
        this.roll = roll;
    }

    public Double getPitch() {
        return pitch;
    }

    public void setPitch(Double pitch) {
        this.pitch = pitch;
    }

    public Integer getSceneNum() {
        return sceneNum;
    }

    public void setSceneNum(Integer sceneNum) {
        this.sceneNum = sceneNum;
    }

    public Integer getSatellite() {
        return satellite;
    }

    public void setSatellite(Integer satellite) {
        this.satellite = satellite;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Object getGeom() {
        return geom;
    }

    public void setGeom(Object geom) {
        this.geom = geom;
    }

    public String getStripId() {
        return stripId;
    }

    public void setStripId(String stripId) {
        this.stripId = stripId == null ? null : stripId.trim();
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName == null ? null : folderName.trim();
    }
}