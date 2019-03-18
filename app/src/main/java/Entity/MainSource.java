package Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tejraj on 17-Mar-19.
 */

public class MainSource {
    public String status;
    public List<MainSourceData> sources = new ArrayList<>();

    public MainSource() {
    }

    public MainSource(String status, List<MainSourceData> mainSourceDataList) {
        this.status = status;
        this.sources = mainSourceDataList;
    }

    
}
