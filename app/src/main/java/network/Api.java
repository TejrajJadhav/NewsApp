package network;

import Entity.MainSource;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by Tejraj on 17-Mar-19.
 */

public interface Api {

    @GET("v2/sources")
    Observable<MainSource> getSources(@Query("apiKey") String api_key);


}
