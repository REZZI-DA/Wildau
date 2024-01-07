package wildau.web.live.chat.api.model;
import java.util.List;

public class SearchListOfEntitys {
    public String results;
    public List<UserEntity> data;


    public String getStatus() {
        return results;
    }

    public void setStatus(String results) {
        this.results = results;
    }
    public List<UserEntity> getData () {
        return data;  //TODO?   Ein Clonprizip hinzufügen gegen verlinkung?
    }

    public void setData(List<UserEntity> data) {
        this.data = data;   //TODO?   Ein Clonprizip hinzufügen gegen verlinkung?
    }
}
