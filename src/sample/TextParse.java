package sample;

//сделать пословный разбор

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextParse {

    public static void parseText(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        String line = "";
        String name;
        String action;
        String importance;
        String probability;
        int flag = 0;
        int number = 0;
        while (sc.hasNext()) {
            name = sc.nextLine();
            line = name.replaceAll("\n", "");
            line = line.replaceAll(" ", "");
            if (line.equals("strength")) {
                flag = 1;
                number = 0;
                name = sc.nextLine();
            } else if (line.equals("weakness")) {
                flag = 2;
                number = 0;
                name = sc.nextLine();
            } else if (line.equals("opportunities")) {
                flag = 3;
                number = 0;
                name = sc.nextLine();
            } else if (line.equals("threats")) {
                flag = 4;
                number = 0;
                name = sc.nextLine();
            }
            action = sc.nextLine();
            importance = sc.nextLine();
            probability = sc.nextLine();
            name = name.replaceAll(" \n", "");
            action = action.replaceAll(" \n", "");
            importance = importance.replaceAll(" \n", "");
            probability = probability.replaceAll(" \n", "");
            if (flag == 1) {
                number++;
                ExcelParse.strength.add(new Data(number, name, action, importance, probability,
                        Double.parseDouble(importance) * Double.parseDouble(probability)));
            }
            if (flag == 2) {
                number++;
                ExcelParse.weakness.add(new Data(number, name, action, importance, probability,
                        Double.parseDouble(importance) * Double.parseDouble(probability)));
            }
            if (flag == 3) {
                number++;
                ExcelParse.opportunities.add(new Data(number, name, action, importance, probability,
                        Double.parseDouble(importance) * Double.parseDouble(probability)));
            }
            if (flag == 4) {
                number++;
                ExcelParse.threats.add(new Data(number, name, action, importance, probability,
                        Double.parseDouble(importance) * Double.parseDouble(probability)));
            }
        }
        for (int i = 0; i < ExcelParse.strength.size(); i++) {
            ExcelParse.SPower += ExcelParse.strength.get(i).getPower();
        }
        for (int i = 0; i < ExcelParse.opportunities.size(); i++) {
            ExcelParse.OPower += ExcelParse.opportunities.get(i).getPower();
        }
        for (int i = 0; i < ExcelParse.weakness.size(); i++) {
            ExcelParse.WPower += ExcelParse.weakness.get(i).getPower();
        }
        for (int i = 0; i < ExcelParse.threats.size(); i++) {
            ExcelParse.TPower += ExcelParse.threats.get(i).getPower();
        }
    }

}
