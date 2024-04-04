package org.example;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static spark.Spark.*;

public class MathService {

    public static void main(String[] args) {
        iniciarServidor();
        configurarArchivosEstaticos();
        establecerRutas();
    }

    private static void iniciarServidor() {
        int puerto = obtenerPuerto();
        port(puerto);
    }

    private static void configurarArchivosEstaticos() {
        staticFiles.location("/public");
    }

    private static void establecerRutas() {
        get("/hello", (req, res) -> "¡Hola!");

        get("/linear-search", (req, res) -> {
            String inputList = req.queryParams("inputlist");
            String value = req.queryParams("value");
            String respuesta = linearSearch(inputList, value);
            res.type("application/json");
            return respuesta;
        });

        get("/binary-search", (req, res) -> {
            String inputList = req.queryParams("inputlist");
            String value = req.queryParams("value");
            String respuesta = binarySearch(inputList, value);
            res.type("application/json");
            return respuesta;
        });
    }

    private static int obtenerPuerto() {
        String puerto = System.getenv("PUERTO");
        return (puerto != null) ? Integer.parseInt(puerto) : 4500;
    }

    private static String linearSearch(String inputListStr, String valueStr) {
        List<String> inputList = Arrays.asList(inputListStr.split(","));
        String output;
        if (inputList.contains(valueStr)) {
            output = "{\"operation\": \"linearSearch\", \"inputlist\": \"" + inputListStr +
                    "\", \"value\": \"" + valueStr + "\", \"output\": \"" + inputList.indexOf(valueStr) + "\"}";
        } else {
            output = "{\"operation\": \"linearSearch\", \"inputlist\": \"" + inputListStr +
                    "\", \"value\": \"" + valueStr + "\", \"output\": \"Value not found\"}";
        }
        return output;
    }

    private static String binarySearch(String inputListStr, String valueStr) {
        List<String> inputList = Arrays.asList(inputListStr.split(","));
        Collections.sort(inputList);
        int index = binarySearchRecursive(inputList, valueStr, 0, inputList.size() - 1);
        String output;
        if (index != -1) {
            output = "{\"operation\": \"binarySearch\", \"inputlist\": \"" + inputListStr +
                    "\", \"value\": \"" + valueStr + "\", \"output\": \"" + index + "\"}";
        } else {
            output = "{\"operation\": \"binarySearch\", \"inputlist\": \"" + inputListStr +
                    "\", \"value\": \"" + valueStr + "\", \"output\": \"Value not found\"}";
        }
        return output;
    }

    private static int binarySearchRecursive(List<String> inputList, String value, int left, int right) {
        if (right >= left) {
            int mid = left + (right - left) / 2;
            int compare = value.compareTo(inputList.get(mid));
            if (compare == 0) {
                return mid;
            }
            if (compare < 0) {
                return binarySearchRecursive(inputList, value, left, mid - 1);
            }
            return binarySearchRecursive(inputList, value, mid + 1, right);
        }
        return -1;
    }
}
