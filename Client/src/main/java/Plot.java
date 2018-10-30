import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Plot extends ApplicationFrame {
    public static void main(String[] args) {
        int[] buckets = getBuckets("object4.adt");
        Plot chart = new Plot(
                "Throughtput Chart" ,
                "Overall Throughput 256 threads 100 iterations",buckets);
        chart.pack( );
        RefineryUtilities.centerFrameOnScreen( chart );
        chart.setVisible( true );
    }

    public Plot( String applicationTitle , String chartTitle,int[] buckets) {
        super(applicationTitle);
        JFreeChart lineChart = ChartFactory.createLineChart(
                chartTitle,
                "Times","Requests",
                createDataset(buckets),
                PlotOrientation.VERTICAL,
                true,true,false);

        ChartPanel chartPanel = new ChartPanel( lineChart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
        setContentPane( chartPanel );
    }

    private static DefaultCategoryDataset createDataset(int[] buckets) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        for(int i = 0; i < buckets.length; i++){
            dataset.addValue(buckets[i],"requests",i+"");
        }
        return dataset;
    }

    private static int[] getBuckets(String path){
        File file = new File(path);
        List<Latency> latencies = new ArrayList<>();

        try (ObjectInputStream out = new ObjectInputStream(new FileInputStream(file))) {
            Latency[] obj = (Latency[]) out.readObject();
            latencies = Arrays.asList(obj);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        List<Long> times = new ArrayList<>();

        for(Latency latency : latencies){
            times.add(latency.startTime);
        }

        Collections.sort(times);

        long start = times.get(0);
        long end = times.get(times.size() - 1);
        long width = end - start + 1;

        int seconds = (int)width / 1000;

        int[] buckets = new int[seconds+1];

        for(long time : times){
            int index = (int)((time - start) / 1000);
            buckets[index]++;
        }

        return buckets;
    }
}
