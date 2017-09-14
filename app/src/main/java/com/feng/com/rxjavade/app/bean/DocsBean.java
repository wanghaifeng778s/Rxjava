package com.feng.com.rxjavade.app.bean;

import java.util.List;

/**
 * Created by WHF.Javas on 2017/8/22.
 */

public class DocsBean {

    /**
     * comment_count : 0
     * categ_name :  مشاهير
     * firstName : الجرس
     * userPhoto : http: //www.arabsada.com/uploads/profile_photos/593a62fd80135.jpg
     * create_time : 20170816152930
     * img_url : ["http://n.sinaimg.cn/translate/20170912/J6WG-fykufii1168692.jpg","http://src.mysada.com/sada/file/jpg/220_144_sada18592132071502886574.jpg","http://src.mysada.com/sada/file/jpg/220_144_sada5918938801502886576.jpg"]
     * dType : n
     * shareUrl : http: //share.anawin.com/share/1469977
     *  id : 1469977
     * title :  当过高校院长、曾在安徽任职32年的他，任贵州省委政法委书记
     * recomId : 067d05607a0c41bdb4a2977b72436665
     * video_time : 1: 10
     *  like : 0
     *  dislike : 0
     * video_url :  utR4ao-S70s
     * total_view : 71
     * content : 也就是说，7月由安徽省委常委、秘书长同岗转任贵州省委常委、秘书长的唐承沛，已接班谌贻琴兼任省委政法委书记。谌贻琴9月6日已任贵州省委副书记、代省长。据了解，唐承沛还曾担任过合肥工业大学人事处处长、安徽农业技术师范学院院长等职
     */

    private String comment_count;
    private String categ_name;
    private String firstName;
    private String userPhoto;
    private long create_time;
    private String dType;
    private String shareUrl;
    private String id;
    private String title;
    private String recomId;
    private String video_time;
    private int like;
    private int dislike;
    private String video_url;
    private int total_view;
    private String content;
    private java.util.List<String> img_url;

    public String getdType() {
        return dType;
    }

    public void setdType(String dType) {
        this.dType = dType;
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

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public int getTotal_view() {
        return total_view;
    }

    public void setTotal_view(int total_view) {
        this.total_view = total_view;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getImg_url() {
        return img_url;
    }

    public void setImg_url(List<String> img_url) {
        this.img_url = img_url;
    }

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

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
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
}
