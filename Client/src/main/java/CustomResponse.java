import java.util.List;

public class CustomResponse {
    int requestCount;
    int successCount;
    List<Latency> latencyLst;

    public CustomResponse(int requestCount, int successCount, List<Latency> latencyLst){
        this.requestCount = requestCount;
        this.successCount = successCount;
        this.latencyLst = latencyLst;
    }
}
