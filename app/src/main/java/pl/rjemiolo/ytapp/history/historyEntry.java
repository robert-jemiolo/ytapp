package pl.rjemiolo.ytapp.history;

public class HistoryEntry {
    private String id;
    private String date;
    private String video;

    public void setId( String id ){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setDate( String date ){
        this.date = date;
    }
    public String getDate(){
        return this.date;
    }
    public void setVideo( String video ){
        this.video = video;
    }
    public String getVideo(){
        return this.video;
    }
}
