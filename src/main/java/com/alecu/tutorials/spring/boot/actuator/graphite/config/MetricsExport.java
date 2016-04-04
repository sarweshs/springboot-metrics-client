package com.alecu.tutorials.spring.boot.actuator.graphite.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.ExportMetricWriter;
import org.springframework.boot.actuate.endpoint.SystemPublicMetrics;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.boot.actuate.metrics.reader.MetricReader;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ibm.springboot.metrics.export.MyMetricWriter;

@Component
public class MetricsExport {

    @Autowired private MetricReader repository;
    
    @Autowired private GaugeService gaugeService;
    
    @Autowired
    private SystemPublicMetrics systemPublicMetrics;

   /* private void registerSystemMetrics(MetricReader metricRegistry) {
        systemPublicMetrics.metrics().forEach(m -> {
            Gauge<Long> metricGauge = () -> m.getValue().longValue();
            metricRegistry.register(m.getName(), metricGauge);   
        });
    }*/

   // @Scheduled(fixedRate = 1000)
    private void exportMetrics() {
       // repository.findAll().forEach(this::saveMetric);
    	getMetricsExport().exportMetrics();
    }
    
   // @Bean 
    private com.ibm.springboot.metrics.export.MetricsExport getMetricsExport()
    {
    	return new com.ibm.springboot.metrics.export.MetricsExport(repository);
    }
    
    @Bean 
    @ExportMetricWriter
    private MyMetricWriter getWriter()
    {
    	//gaugeService.s
    	return new MyMetricWriter(systemPublicMetrics);
    }

    /*private void saveMetric(Metric<?> m) {
        System.out.println(String.format("%s = %s", m.getName(), m.getValue()));

        try {
            Socket socket = new Socket("104.236.218.223", 2003);
            OutputStream stream = socket.getOutputStream();
            PrintWriter out = new PrintWriter(stream, true);
            out.printf("%s %d %d%n", m.getName(), m.getValue().intValue(), System.currentTimeMillis() / 1000);
            out.close();
            socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        repository.reset(m.getName());
    }*/

}
