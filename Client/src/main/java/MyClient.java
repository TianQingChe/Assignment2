import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import java.awt.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class MyClient implements Callable<CustomResponse> {
    private final int iterNum;
    private final String BASE_URI;
    private final int dayNumber;
    private final int userPopulation;
    private final int day = 1;
    private final CloseableHttpClient httpClient;

    private int requestCount = 0;
    private int successCount = 0;
    private List<Latency> latencyLst = new ArrayList<>();

    public MyClient(PoolingHttpClientConnectionManager connManager,String url, int iterNum, int dayNumber, int userPopulation){
        this.httpClient = HttpClients.custom().setConnectionManager(connManager).build();
        this.BASE_URI = url;
        this.iterNum = iterNum;
        this.dayNumber = dayNumber;
        this.userPopulation = userPopulation;
    }


    public CustomResponse call() throws IOException {

        for(int i = 0; i < iterNum; i++){
            System.out.println(i);
            int userId1 = 1 + ThreadLocalRandom.current().nextInt(userPopulation);
            int userId2 = 1 + ThreadLocalRandom.current().nextInt(userPopulation);
            int userId3 = 1 + ThreadLocalRandom.current().nextInt(userPopulation);
            int timeInterval1 = ThreadLocalRandom.current().nextInt(24);
            int timeInterval2 = ThreadLocalRandom.current().nextInt(24);
            int timeInterval3 = ThreadLocalRandom.current().nextInt(24);
            int stepCount1 = ThreadLocalRandom.current().nextInt(5001);
            int stepCount2 = ThreadLocalRandom.current().nextInt(5001);
            int stepCount3 = ThreadLocalRandom.current().nextInt(5001);

            String post1Str = BASE_URI + "/" + userId1 + "/" + day + "/" + timeInterval1 + "/" + stepCount1;
            String post2Str = BASE_URI + "/" + userId2 + "/" + day + "/" + timeInterval2 + "/" + stepCount2;
            String post3Str = BASE_URI + "/" + userId3 + "/" + day + "/" + timeInterval3 + "/" + stepCount3;

            String get1Str = BASE_URI + "/current/" + userId1;
            String get2Str = BASE_URI + "/single/" + userId2 + "/" + day;

            HttpPost post1 = new HttpPost(post1Str);
            HttpPost post2 = new HttpPost(post2Str);
            HttpGet get1 = new HttpGet(get1Str);
            HttpGet get2 = new HttpGet(get2Str);
            HttpPost post3 = new HttpPost(post3Str);

            execute(post1);
            execute(post2);
            execute(get1);
            execute(get2);
            execute(post3);

        }

//        try {
//            this.httpClient.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        this.connManager.shutdown();
        return new CustomResponse(requestCount,successCount,latencyLst);
    }

    private void execute(HttpUriRequest request){
        try {
            requestCount++;
            successCount++;
            long start = System.currentTimeMillis();

            CloseableHttpResponse response = this.httpClient.execute(request);
//            HttpEntity httpEntity = response.getEntity();
//            EntityUtils.consume(httpEntity);
            response.close();

            Latency latency = new Latency(start,System.currentTimeMillis() - start);
            latencyLst.add(latency);
        } catch (IOException e) {
            successCount--;
            e.printStackTrace();
        }
    }


}
