package com.feng.com.rxjavade.app.bean;

import java.util.List;

/**
 * Created by WHF.Javas on 2017/8/22.
 */

public class DocsBean {
    /**
     * comment_count : 0
     * categ_name : مشاهير
     * firstName : الجرس
     * userPhoto : http://www.arabsada.com/uploads/profile_photos/593a62fd80135.jpg
     * create_time : 20170816152930
     * img_url : ["http://src.mysada.com/sada/file/jpg/220_144_sada12625168151502886573.jpg","http://src.mysada.com/sada/file/jpg/220_144_sada18592132071502886574.jpg","http://src.mysada.com/sada/file/jpg/220_144_sada5918938801502886576.jpg"]
     * dType : n
     * shareUrl : http://share.anawin.com/share/1469977
     * id : 1469977
     * title : هكذا علقت شيرين رضا على زواج طليقها عمرو دياب من صديقتها دينا الشربيني
     * recomId : 067d05607a0c41bdb4a2977b72436665
     * video_time : 1:10
     * like : 0
     * dislike : 0
     * video_url : utR4ao-S70s
     * total_view : 71
     */

    private String comment_count;
    private String categ_name;
    private String firstName;
    private String userPhoto;
    private String create_time;
    private String dType;
    private String shareUrl;
    private String id;
    private String title;
    private String recomId;
    private String video_time;
    private String like;
    private String dislike;
    private String video_url;
    private String total_view;
    private List<String> img_url;

    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }

    public String getCateg_name() {
        return categ_name;
    }

    public void setCateg_name(String categ_name) {
        this.categ_name = categ_name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getDType() {
        return dType;
    }

    public void setDType(String dType) {
        this.dType = dType;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRecomId() {
        return recomId;
    }

    public void setRecomId(String recomId) {
        this.recomId = recomId;
    }

    public String getVideo_time() {
        return video_time;
    }

    public void setVideo_time(String video_time) {
        this.video_time = video_time;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getDislike() {
        return dislike;
    }

    public void setDislike(String dislike) {
        this.dislike = dislike;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getTotal_view() {
        return total_view;
    }

    public void setTotal_view(String total_view) {
        this.total_view = total_view;
    }

    public List<String> getImg_url() {
        return img_url;
    }

    public void setImg_url(List<String> img_url) {
        this.img_url = img_url;
    }
}
