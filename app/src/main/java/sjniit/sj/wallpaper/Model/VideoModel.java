package sjniit.sj.wallpaper.Model;

import android.widget.VideoView;

public class VideoModel {
    private int id;
    private String videoUrl;
    private String userName;
    private String videoImage;
    private String videoDuration;
    private String qualityHd;
    private String qualitySd;
    private String downloadLink;

    public VideoModel(int id, String videoUrl, String userName, String videoImage, String videoDuration, String qualityHd, String qualitySd, String downloadLink) {
        this.id = id;
        this.videoUrl = videoUrl;
        this.userName = userName;
        this.videoImage = videoImage;
        this.videoDuration = videoDuration;
        this.qualityHd = qualityHd;
        this.qualitySd = qualitySd;
        this.downloadLink = downloadLink;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideoUrl(VideoView videoView) {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getQualityHd() {
        return qualityHd;
    }

    public void setQualityHd(String qualityHd) {
        this.qualityHd = qualityHd;
    }

    public String getQualitySd() {
        return qualitySd;
    }

    public void setQualitySd(String qualitySd) {
        this.qualitySd = qualitySd;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    public String getVideoImage() {
        return videoImage;
    }

    public void setVideoImage(String videoImage) {
        this.videoImage = videoImage;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getVideoDuration() {
        return videoDuration;
    }

    public void setVideoDuration(String videoDuration) {
        this.videoDuration = videoDuration;
    }
}