package com.example.funcionesfx;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import java.util.Locale;

public class HelloController {
    @FXML private TextField tfFuncion, tfVariables, tfValores;
    @FXML private Label lblResultado;
    @FXML private LineChart<Number, Number> grafica;

    @FXML public void Calcular(){
        String funcion=tfFuncion.getText().toLowerCase(Locale.ROOT);

        Expression expression = new ExpressionBuilder(funcion)
                .variables("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z")
                .build();

        String variablesF[]=tfVariables.getText().toLowerCase(Locale.ROOT).split(",");
        String valores[] =tfValores.getText().split(",");

        for(int i=0;i<=variablesF.length-1;i++){
            expression.setVariable(variablesF[i],Double.parseDouble(valores[i]));
        }

        if(valores.length==1){
            Expression exp = new ExpressionBuilder(funcion)
                    .variables(variablesF[0])
                    .build();
            XYChart.Series series = new XYChart.Series();
            for (int i=-100;i<=100;i++){

                exp.setVariable(variablesF[0],i);
                double numero=exp.evaluate();
                series.getData().add(new XYChart.Data(i, numero));
            }
            grafica.getData().add(series);
        }

        double result = expression.evaluate();

        lblResultado.setText(result+"");

    }

}