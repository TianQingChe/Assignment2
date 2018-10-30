import java.io.Serializable;

public class Latency implements Serializable {
    public long startTime;
    public long latency;
    public Latency(long startTime, long latency){
        this.startTime = startTime;
        this.latency = latency;
    }
}
