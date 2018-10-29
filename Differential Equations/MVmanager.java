package sample;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import static java.lang.Math.abs;
import javafx.scene.chart.XYChart.Data;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Cursor;

public class MVmanager {

    private double erf(double x) {
        double sign = x >= 0 ? 1 : -1;
        x = abs(x);

        double a1 = 0.254829592;
        double a2 = -0.284496736;
        double a3 = 1.421413741;
        double a4 = -1.453152027;
        double a5 = 1.061405429;
        double p = 0.3275911;

        double t = 1.0 / (1.0 + p * x);
        double y = 1.0 - (((((a5 * t + a4) * t) + a3) * t + a2) * t + a1) * t * Math.exp(-x * x);
        return sign * y;
    }

    private double diffEquation(double x, double y) {
        return 2*y*x + 5 - (x * x);
    }

    private double eqSolution(double x) {
        return (9.0 / 4.0) * Math.sqrt(Math.PI) * Math.pow(Math.E, x * x) * erf(x) + (Math.pow(Math.E, x * x)) + (x / 2.0);
    }

    public void printer(Stage stage1, double x0, double y0, double bigX, double h){

        NumberAxis x = new NumberAxis();
        NumberAxis y = new NumberAxis();

        LineChart<Number, Number> MyChart = new LineChart<Number, Number>(x,y);
        XYChart.Series functionSol,euler,eulerImproved,rungeKutta;

        XYChart.Series errorOfEuler,errorOfEulerImproved, errorOfRungeKutta;

        int n = (int) (Math.ceil((bigX-x0)/h));

        double[] xAxis = new double[n];

        double[] solvedFunction = new double[n];

        double[] eulerMethod = new double[n];
        double[] improvedEulerMethod = new double[n];
        double[] rungeMethod = new double[n];

        double[] eulerError = new double[n];
        double[] improvedEulerError = new double[n];
        double[] rungeKuttaError = new double[n];

        xAxis[0] = x0;
        solvedFunction[0] = y0;

        eulerMethod[0] = solvedFunction[0];
        improvedEulerMethod[0] = solvedFunction[0];
        rungeMethod[0] = solvedFunction[0];

        eulerError[0] = 0;
        improvedEulerError[0] = 0;
        rungeKuttaError[0] = 0;

        int i = 1;
        while(i < n){
            xAxis[i] = xAxis[i - 1] + h;
            i++;
        }


        double k0;
        double k1;
        double k0Runge;
        double k1Runge;
        double k2Runge;
        double k3Runge;
                
        for (int j = 1; j < n; j++)
        {
            solvedFunction[j] = eqSolution(xAxis[j]);

            eulerMethod[j] = eulerMethod[j-1] + h * diffEquation(xAxis[j-1],eulerMethod[j-1]);

            k0 = diffEquation(xAxis[j-1], improvedEulerMethod[j-1]);
            k1 = diffEquation(xAxis[j-1] + h,improvedEulerMethod[j-1] + h*k0);
            improvedEulerMethod[j] = improvedEulerMethod[j-1] + h * (k0 + k1) / 2;

            k0Runge = diffEquation(xAxis[j-1], rungeMethod[j-1]);
            k1Runge = diffEquation(xAxis[j-1] + h/2, rungeMethod[j-1] + h*k0/2);
            k2Runge = diffEquation(xAxis[j-1] + h/2, rungeMethod[j-1] + h*k1Runge/2);
            k3Runge = diffEquation(xAxis[j-1] + h, rungeMethod[j-1] + h*k2Runge);
            rungeMethod[j] = rungeMethod[j-1] + h*(k0Runge + 2*k1Runge + 2*k2Runge + k3Runge)/6;
        }

        int z = 1;
        while(z < n){
            eulerError[z] = abs(solvedFunction[z] - eulerMethod[z]);
            improvedEulerError[z] = abs(solvedFunction[z] - improvedEulerMethod[z]);
            rungeKuttaError[z] = abs(solvedFunction[z] - rungeMethod[z]);
            z++;
        }



        functionSol = new XYChart.Series();
        euler = new XYChart.Series();
        eulerImproved = new XYChart.Series();
        rungeKutta = new XYChart.Series();
        functionSol.setName("Function Solution");
        euler.setName("Euler Method");
        eulerImproved.setName("Improved Euler Method");
        rungeKutta.setName("Runge Kutta Method");

        errorOfEuler = new XYChart.Series();
        errorOfEuler.setName("errorOfEuler");
        errorOfEulerImproved = new XYChart.Series();
        errorOfEulerImproved.setName("errorOfEulerImproved");
        errorOfRungeKutta = new XYChart.Series();
        errorOfRungeKutta.setName("errorOfRungeKutta");


        //int s = 0;
        for (int s = 0; s < n; s++)
        {
            functionSol.getData().add(new XYChart.Data(xAxis[s],solvedFunction[s]));
            euler.getData().add(new XYChart.Data(xAxis[s],eulerMethod[s]));
            eulerImproved.getData().add(new XYChart.Data(xAxis[s],improvedEulerMethod[s]));
            rungeKutta.getData().add(new XYChart.Data(xAxis[s],rungeMethod[s]));
            errorOfEuler.getData().add(new XYChart.Data(xAxis[s],eulerError[s]));
            errorOfEulerImproved.getData().add(new XYChart.Data(xAxis[s],improvedEulerError[s]));
            errorOfRungeKutta.getData().add(new XYChart.Data(xAxis[s],rungeKuttaError[s]));
        }

        Scene scene = new Scene(MyChart,600,600);
        MyChart.getData().addAll(errorOfEuler,errorOfEulerImproved, errorOfRungeKutta);
        stage1.setScene(scene);

        stage1.show();

    }
}
