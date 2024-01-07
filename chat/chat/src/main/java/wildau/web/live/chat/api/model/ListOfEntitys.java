package wildau.web.live.chat.api.model;
import java.util.List;

public class ListOfEntitys {
    public String status;
    public List<UserEntity> data;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public List<UserEntity> getData () {
        return data;  //TODO?   Ein Clonprizip hinzufügen gegen verlinkung?
    }

    public void setData(List<UserEntity> data) {
        this.data = data;   //TODO?   Ein Clonprizip hinzufügen gegen verlinkung?
    }
}
