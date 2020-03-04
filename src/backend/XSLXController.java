package backend;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

public class XSLXController extends INXSLXController {

    public XSLXController(int startRows, int endRows, FileInputStream fis,String pathToSave) throws IOException {
        super(fis);
     //   URL url = XSLXController.class.getClassLoader().getResource("okay.xlsx");

        Workbook wb1 = new XSSFWorkbook("C:\\Program Files\\EasyJob\\"+ConfController.firma+"\\okay.xlsx");
        //Workbook wb1 = new XSSFWorkbook(ConfController.firma+"/okay.xlsx");

        for (int i = 0; i < endRows - startRows; i++)
            wb1.cloneSheet(0);

        int counterSheet=0;
        int numRow=startRows;
        while (numRow<=endRows) {
            setNeededValues(numRow);

            ArrayList<String> strList = getRoute(marszrut + "-");

            setValuesIntoNewXSLX((XSSFWorkbook) wb1,strList,counterSheet);
            FileOutputStream fos = new FileOutputStream(pathToSave+"\\done.xlsx");
           // FileOutputStream fos = new FileOutputStream(pathToSave+"/done.xlsx");

            wb1.write(fos);
            fos.close();
            counterSheet++;
            numRow++;
        }
        fis.close();

    }

    private void setValuesIntoNewXSLX(XSSFWorkbook wb1,ArrayList<String> strList,int sheet){
        setValue(val, (XSSFWorkbook) wb1, 15, 6, sheet);
        setValue("по маршруту: " + getCountries(strList), (XSSFWorkbook) wb1, 14, 1, sheet);
        setValue(getCities(strList), (XSSFWorkbook) wb1, 15, 1, sheet);
        setValue("№" + numberAkt + "/" + (date.getMonth() + 1) + "/" + (date.getYear() - 100) + dogovir(), (XSSFWorkbook) wb1, 8, 3, sheet);
        setValue("№ Транспортного  средства: " + numTrack + dogovir2(), (XSSFWorkbook) wb1, 27, 0, sheet);
        setValue("К выплате:  " + val + " EUR", (XSSFWorkbook) wb1, 25, 0, sheet);
        setValue("Прописью:  " + mo.num2str() + " EUR", (XSSFWorkbook) wb1, 26, 0, sheet);
        setValue("Дата: " + (date.getDate()) + " " + getStringMonth(date.getMonth()) + " " + (date.getYear() + 1900) + " года", (XSSFWorkbook) wb1, 7, 6, sheet);
        //setFormulas("G16",(XSSFWorkbook) wb1,27,6,sheet);
    }



    //String str = route.substring(route.indexOf("(")+1,route.indexOf(")"));
    private static String getCountries(ArrayList<String> str){
        String res="";
        for(String s : str){

            if (s.contains("(")){
                s= s.substring(s.indexOf("(")+1,s.indexOf(")"));
                if (!res.contains(s))
                    res=res+s+" - ";
            }
            else if (s.contains("Київ"))
                res=res+"Украина"+" - ";
        }

        return res.substring(0,res.length()-3);
    }

    private static String getCities(ArrayList<String> str) {
        String res = "";
        for (String s : str) {
            if (s.contains("(")) {
                s = s.substring(0, s.indexOf("("));
                res=res+s+" - ";
            }
            else if (s.contains("Київ"))
                res=res+"Киев"+" - ";
            else res=res+s+" - ";
        }
        return res.substring(0,res.length()-3);
    }

    private static ArrayList<String> getRoute(String route){
        ArrayList<String> list = new ArrayList<>();
        while(route.contains("-")){
            list.add(route.substring(0,route.indexOf("-")));
            route=route.substring(route.indexOf("-")+1);
            // System.out.println(route);
        }
        return list;
    }
    private static int oblicz (String str, String znak){
        int i=0;
        while (str.contains(znak)){
            //System.out.println("counting...");
            i++;
            str=str.replace(znak,"");
        }
        return i;
    }
    public static void setFormulas (String formula, XSSFWorkbook wb, int row, int cell, int sheet){
        wb.getSheetAt(sheet).getRow(row).getCell(cell).setCellFormula(formula);
    }

    public static void setValue (int value,XSSFWorkbook wb, int row,int cell,int sheet){
        wb.getSheetAt(sheet).getRow(row).getCell(cell).setCellValue(value);
    }
    public static void setValue (String value,XSSFWorkbook wb, int row,int cell,int sheet){
        wb.getSheetAt(sheet).getRow(row).getCell(cell).setCellValue(value);
    }
    private static String getStringMonth(int mnth){
        String month="";
        switch (mnth){
            case 0:
                month = "января";
                break;
            case 1:
                month = "февраля";
                break;
            case 2:
                month = "марта";
                break;
            case 3:
                month = "апреля";
                break;
            case 4:
                month = "мая";
                break;
            case 5:
                month = "июня";
                break;
            case 6:
                month = "июля";
                break;
            case 7:
                month = "августа";
                break;
            case 8:
                month = "сентября";
                break;
            case 9:
                month = "октября";
                break;
            case 10:
                month = "ноября";
                break;
            case 11:
                month = "декабря";
                break;
        }
        return month;
    }
}
