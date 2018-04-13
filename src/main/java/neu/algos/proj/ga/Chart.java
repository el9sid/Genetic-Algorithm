package neu.algos.proj.ga;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.stage.Stage;

public class Chart extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Generation-Fitness Chart");

        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("Generation Number");
        yAxis.setLabel("Fitness Score");

        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);

        lineChart.setTitle("Timetable fitness chart");

        Scene scene = new Scene(lineChart, 800, 600);

        Series series = plotData();
        lineChart.getData().add(series);

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private Series plotData() {
        Series series = new Series();
        series.getData().add(new XYChart.Data<>(1,3));

        return series;
    }
}
