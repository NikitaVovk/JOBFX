package backend;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

public class WordController extends INXSLXController {


    public WordController(int startRows, int endRows, FileInputStream fis, String path) throws IOException {
        super(fis);

        int numRow=startRows;
        XWPFDocument mainDocument=new XWPFDocument();
        while (numRow<=endRows) {
            XWPFDocument document = null;
            try {
              //  URL url = XSLXController.class.getClassLoader().getResource("okay.docx");
               // System.out.println(url.getPath());
                document = new XWPFDocument(OPCPackage.open("C:\\Program Files\\EasyJob\\"+ConfController.firma+"\\okay.docx"));
               // document = new XWPFDocument(OPCPackage.open(ConfController.firma+"/okay.docx"));

            } catch (InvalidFormatException e) {
                e.printStackTrace();
            }
            setNeededValues(numRow);
            setValues(document);
         //   createMainDocument(mainDocument,document);

            FileOutputStream fos = new FileOutputStream(path+"\\ready"+(numRow+1)+".docx");
            //FileOutputStream fos = new FileOutputStream(path+"/ready"+(numRow+1)+".docx");

            document.write(fos);
            fos.close();
            numRow++;
        }

//      //  FileOutputStream fos = new FileOutputStream(path+"\\ready"+(numRow+1)+".docx");
//             FileOutputStream fos = new FileOutputStream(path+"/ready"+(numRow+1)+".docx");
//
//        mainDocument.write(fos);
//        fos.close();


    }
    private void createMainDocument(XWPFDocument mainDoc,XWPFDocument doc){
        int i = 0;
        for (XWPFParagraph paragraph : doc.getParagraphs()){
            System.out.println(i+"    "+paragraph.getText());
          //  setText(i,mainDoc,paragraph.getText(),false);
            i++;
        }
    }

    private void setValues(XWPFDocument document){
        setText(3,document,"АКТ №"+numberAkt+" от "+date.getDate()+"."+(date.getMonth()+1)+"."+(date.getYear()+1900),true);
        setText(4,document,"К счету "+numberAkt+"/"+(date.getMonth()+1)+"/"+(date.getYear()-100)+dogovir(),false);
        setText(7,document,"Настоящий акт составлен в подтверждение выполнения грузовой автоперевозки автомобилем   "+numTrack+" по маршруту:",false);
        setText(10,document,getRoute(marszrut)+"-"+val+" EUR",false);
        setText(14,document,val+" EUR  ("+mo.num2str()+"евро)",false);
    }

    private void setText(int numParagraph,XWPFDocument document,String text,boolean isBold){
        XWPFRun run;
        run=document.getParagraphArray(numParagraph).createRun();
        run.setBold(isBold);
        run.setText(text);
    }
    private String getRoute(String route){
        if (route.contains("Київ")){
            route=route.replace("Київ","Киев (Украина)");
        }
        StringBuffer res=new StringBuffer(route);
        for (int i = 0;i<route.length();i++){
            if(res.charAt(i)=='-'){
                res.insert(i+1,"г. ");
            }
            if (i==0) res.insert(i,"г. ");
        }
        String result = new String(res);
        return result;

    }

}
