package thanhnotes.com.notetaker.models;

/**
 * Created by nguyenbathanh on 4/5/16.
 */
public class NewsItem {
    private String newsTitle;
    private String newsThumb;
    private String newsContentHTML;
    private String newsURL;

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsThumb() {
        return newsThumb;
    }

    public void setNewsThumb(String newsThumb) {
        this.newsThumb = newsThumb;
    }

    public String getNewsContentHTML() {
        return newsContentHTML;
    }

    public void setNewsContentHTML(String newsContentHTML) {
        this.newsContentHTML = newsContentHTML;
    }

    public String getNewsURL() {
        return newsURL;
    }

    public void setNewsURL(String newsURL) {
        this.newsURL = newsURL;
    }
}
