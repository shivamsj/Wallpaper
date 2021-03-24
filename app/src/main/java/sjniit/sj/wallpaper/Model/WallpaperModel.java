package sjniit.sj.wallpaper.Model;

public class WallpaperModel {

    private int id,heightImg,widthImg;

    private String originalUrl,mediumUrl,smallUrl,photographerName,portraitImg,landscapeImg,photographer_url;

    //Empty for Object creation
    public WallpaperModel() {
    }

    //Parameter cONSTRUCTOR
    public WallpaperModel(int id, int heightImg, int widthImg, String originalUrl, String mediumUrl, String smallUrl, String photographerName, String portraitImg, String landscapeImg ,String photographer_url) {
        this.id = id;
        this.heightImg = heightImg;
        this.widthImg = widthImg;
        this.originalUrl = originalUrl;
        this.mediumUrl = mediumUrl;
        this.smallUrl = smallUrl;
        this.photographerName = photographerName;
        this.portraitImg = portraitImg;
        this.landscapeImg = landscapeImg;
        this.photographer_url=photographer_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHeightImg() {
        return heightImg;
    }

    public void setHeightImg(int heightImg) {
        this.heightImg = heightImg;
    }

    public int getWidthImg() {
        return widthImg;
    }

    public void setWidthImg(int widthImg) {
        this.widthImg = widthImg;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getMediumUrl() {
        return mediumUrl;
    }

    public String getPhotographer_url() {
        return photographer_url;
    }

    public void setMediumUrl(String mediumUrl) {
        this.mediumUrl = mediumUrl;
    }

    public String getSmallUrl() {
        return smallUrl;
    }

    public void setSmallUrl(String smallUrl) {
        this.smallUrl = smallUrl;
    }

    public String getPhotographerName() {
        return photographerName;
    }

    public void setPhotographer_url(String photographer_url) {
        this.photographer_url = photographer_url;
    }

    public void setPhotographerName(String photographerName) {
        this.photographerName = photographerName;

    }

    public String getPortraitImg() {
        return portraitImg;
    }

    public void setPortraitImg(String portraitImg) {
        this.portraitImg = portraitImg;
    }

    public String getLandscapeImg() {
        return landscapeImg;
    }

    public void setLandscapeImg(String landscapeImg) {
        this.landscapeImg = landscapeImg;
    }
}
