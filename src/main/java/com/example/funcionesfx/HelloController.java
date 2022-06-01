package com.example.funcionesfx;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import java.util.Locale;

public class HelloController {
    @FXML private TextField tfFuncion, tfVariables, tfValores;
    @FXML private Label lblResultado;
    @FXML private LineChart<Number, Number> grafica;

    @FXML void initialize(){
        tfFuncion.setOnKeyReleased(keyEvent -> {
            if(keyEvent.getCode() == KeyCode.ENTER) {
                tfVariables.requestFocus();
            }
        });
        tfVariables.setOnKeyReleased(keyEvent -> {
            if(keyEvent.getCode() == KeyCode.ENTER) {
                tfValores.requestFocus();
            }
        });
        tfValores.setOnKeyReleased(keyEvent -> {
            if(keyEvent.getCode() == KeyCode.ENTER) {
                Calcular();
            }
        });
    }//fin initialize

    @FXML public void Calcular(){
        try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Error");
            String funcion=tfFuncion.getText().toLowerCase(Locale.ROOT);

            String variablesF[]=tfVariables.getText().toLowerCase(Locale.ROOT).split(",");
            String valores[] =tfValores.getText().split(",");
            if(!funcion.equals("")){
                Expression expression = new ExpressionBuilder(funcion)
                        .variables("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z")
                        .build();

                if(!tfVariables.getText().equals("")){
                    if(valores.length==1){//graficar
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
                    }//fin graficar

                    if (!tfValores.getText().equals("")){//if valores vacios
                        for(int i=0;i<=variablesF.length-1;i++){
                            expression.setVariable(variablesF[i],Double.parseDouble(valores[i]));
                        }

                        double result = expression.evaluate();
                        lblResultado.setText(result+"");
                    }else {
                        alert.setContentText("El campo de los valores está vacío, recuerda separar los valores con comas (,).");
                        alert.show();
                    }

                }else {
                    alert.setContentText("El campo de las variables está vacío, recuerda separar las variables con comas (,).");
                    alert.show();
                }

            }else{

                alert.setContentText("El campo de la función está vacío.");
                alert.show();
            }


        } catch (NumberFormatException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Error");
            alert.setContentText(e+"");
            alert.show();
        }//catch

    }

}