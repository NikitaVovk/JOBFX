package backend;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

public class INXSLXController {
    int val;
    String marszrut;
    Date date;
    int numberAkt;
    String numTrack;
    Workbook wb;
    Sheet sh;
    fwMoney mo;
    INXSLXController(FileInputStream fis) throws IOException {
       wb = new XSSFWorkbook(fis);
         sh = wb.getSheetAt(1);
    }

    void setNeededValues (int numRow){
        this.val = (int) Math.round(sh.getRow(numRow).getCell(ConfController.val).getNumericCellValue()); //cell.getNumericCellValue();

//            row = sh.getRow(numRow);
//            cell = row.getCell(6);


        this.marszrut = sh.getRow(numRow).getCell(ConfController.marszrut).getStringCellValue();

//            row = sh.getRow(numRow);
//            cell = row.getCell(3);
        this.date = sh.getRow(numRow).getCell(ConfController.date).getDateCellValue();
        System.out.println(date);

//            row = sh.getRow(numRow);
//            cell = row.getCell(17);
        this.numberAkt = (int) sh.getRow(numRow).getCell(ConfController.numberAkt).getNumericCellValue();

        //№ Транспортного  средства: GDA26874/LCH17J7       Договор № 01/02-2018 от 22/01/2018
//            row = sh.getRow(numRow);
//            cell = row.getCell(4);
        this.numTrack = sh.getRow(numRow).getCell(ConfController.numTrack).getStringCellValue();
        System.out.println(numTrack);


         mo = new fwMoney(val);
        System.out.println(mo.num2str());


    }
    public String dogovir(){
        if (ConfController.firma.equals("AN1")||ConfController.firma.equals("Metro"))
            return "/TA";
        return "/NA";
    }
    public String dogovir2(){
        if (ConfController.firma.equals("AN1"))
            return "    Договор № 02/01-2015 от 02/01/2015";
        if (ConfController.firma.equals("AN"))
            return "       Договор № 01/10-2019 от 30/10/2019";


            return "    Договор № 04/01-2019 от 04/01/2019";

    }

}
