package kadr25.model;

public class AdvancedSearch {
    private String keyword;
    private String dateFrom;
    private String dateTo;
    private Integer  userId;
    private Integer likeT;
    private Integer viewT;
    private Integer  CatId;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getLikeT() {
        return likeT;
    }

    public void setLikeT(Integer likeT) {
        this.likeT = likeT;
    }

    public Integer getViewT() {
        return viewT;
    }

    public void setViewT(Integer viewT) {
        this.viewT = viewT;
    }

    public Integer getCatId() {
        return CatId;
    }

    public void setCatId(Integer catId) {
        CatId = catId;
    }
}