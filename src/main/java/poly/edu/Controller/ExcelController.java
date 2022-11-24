package poly.edu.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.FileOutputStream;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import poly.edu.DAO.ChiTietGiayDAO;
import poly.edu.DAO.GiayDAO;
import poly.edu.Entity.ChiTietGiay;
import poly.edu.Entity.Giay;
import poly.edu.Helper.ExcelHelper;
import poly.edu.Model.ResponseMessage;
import poly.edu.Service.ExcelService;

@Controller
public class ExcelController {
    @Autowired
    ExcelService fileService;

    @Autowired
    GiayDAO giayDAO;

    @Autowired
    ChiTietGiayDAO chiTietGiayDAO;

    @GetMapping("/admin/export")
    public String export() {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Giày");
        XSSFSheet sheet1 = workbook.createSheet("Chi tiết giày");
        int rowNumGiay = 0;
        int rowNum = 0;

        Row firstRow = sheet.createRow(rowNumGiay++);
        Cell firstCell1 = firstRow.createCell(0);
        firstCell1.setCellValue("Mã Giày");
        Cell firstCell2 = firstRow.createCell(1);
        firstCell2.setCellValue("Tên Giày");
        Cell firstCell3 = firstRow.createCell(2);
        firstCell3.setCellValue("Giá");
        Cell firstCell4 = firstRow.createCell(3);
        firstCell4.setCellValue("Ảnh");
        Cell firstCell5 = firstRow.createCell(4);
        firstCell5.setCellValue("Mô Tả");

        List<Giay> listGiay = giayDAO.findAll();
        List<ChiTietGiay> listChiTietGiay = chiTietGiayDAO.findAll();
        for (Giay giay : listGiay) {
            //giày
            Row row = sheet.createRow(rowNumGiay++);
            Cell cell1 = row.createCell(0);
            cell1.setCellValue(giay.getMag());
            Cell cell2 = row.createCell(1);
            cell2.setCellValue(giay.getTen());
            Cell cell3 = row.createCell(2);
            cell3.setCellValue(giay.getGia());
            Cell cell4 = row.createCell(3);
            cell4.setCellValue(giay.getAnh());
            Cell cell5 = row.createCell(4);
            cell5.setCellValue(giay.getMota());
        }

        Row firstRowCTG = sheet1.createRow(rowNum++);
        Cell firstCellCTG1 = firstRowCTG.createCell(0);
        firstCellCTG1.setCellValue("Mã Chi Tiết Giày");
        Cell firstCellCTG2 = firstRowCTG.createCell(1);
        firstCellCTG2.setCellValue("Mã Giày");
        Cell firstCellCTG3 = firstRowCTG.createCell(2);
        firstCellCTG3.setCellValue("Mã Loại Giày");
        Cell firstCellCTG4 = firstRowCTG.createCell(3);
        firstCellCTG4.setCellValue("Mã Size");
        Cell firstCellCTG5 = firstRowCTG.createCell(4);
        firstCellCTG5.setCellValue("Mã NSX");
        Cell firstCellCTG6 = firstRowCTG.createCell(5);
        firstCellCTG6.setCellValue("Số Lượng");
//        for (Giay giay : listGiay) {
        for (ChiTietGiay ctg : listChiTietGiay) {
//                if (giay.getMag()==ctg.getMag()){
            //chi tiết giày
            Row row = sheet1.createRow(rowNum++);
            Cell cell1 = row.createCell(0);
            cell1.setCellValue(ctg.getMactg());
            Cell cell2 = row.createCell(1);
            cell2.setCellValue(ctg.getMag());
            Cell cell3 = row.createCell(2);
            cell3.setCellValue(ctg.getMalg());
            Cell cell4 = row.createCell(3);
            cell4.setCellValue(ctg.getMas());
            Cell cell5 = row.createCell(4);
            cell5.setCellValue(ctg.getMansx());
            Cell cell6 = row.createCell(5);
            cell6.setCellValue(ctg.getSoluong());
//                }
//            }

        }
        try {
            FileOutputStream outputStream = new FileOutputStream("Giay.xlsx");
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/admin/giay/index";
    }

    @PostMapping("/admin/giay/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            FileInputStream excelFile = new FileInputStream(new File("Giay.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
            XSSFSheet datatypeSheet1 = workbook.getSheetAt(0);
            XSSFSheet datatypeSheet2 = workbook.getSheetAt(1);
            DataFormatter fmt = new DataFormatter();

            Iterator<Row> iterator1 = datatypeSheet1.iterator();
            Iterator<Row> iterator2 = datatypeSheet2.iterator();
            Row firstRowGiay = iterator1.next();
            Row firstRowCTG = iterator2.next();
            Cell firstCellGiay = firstRowGiay.getCell(0);
            Cell firstCellCTG = firstRowCTG.getCell(0);
            System.out.println(firstCellGiay.getStringCellValue());

            List<Giay> listGiay = new ArrayList<Giay>();
            List<ChiTietGiay> listCTG = new ArrayList<ChiTietGiay>();
            while (iterator1.hasNext()) {
                Row currentRow = iterator1.next();
                Giay giay = new Giay();
                giay.setMag(Integer.parseInt(fmt.formatCellValue(currentRow.getCell(0))));
                giay.setTen(currentRow.getCell(1).getStringCellValue());
                giay.setGia((int) currentRow.getCell(2).getNumericCellValue());
                giay.setAnh(currentRow.getCell(3).getStringCellValue());
                giay.setMota(currentRow.getCell(4).getStringCellValue());
                listGiay.add(giay);
            }
            while (iterator2.hasNext()) {
                Row currentRow = iterator2.next();
                ChiTietGiay ctg = new ChiTietGiay();
                ctg.setMactg(Integer.parseInt(fmt.formatCellValue(currentRow.getCell(0))));
                ctg.setMag((int) currentRow.getCell(1).getNumericCellValue());
                ctg.setMalg((int) currentRow.getCell(2).getNumericCellValue());
                ctg.setMas((int) currentRow.getCell(3).getNumericCellValue());
                ctg.setMansx((int) currentRow.getCell(4).getNumericCellValue());
                ctg.setSoluong((int) currentRow.getCell(5).getNumericCellValue());

                listCTG.add(ctg);
            }
            for (Giay giay : listGiay) {
                giayDAO.save(giay);
                System.out.println(giay);
            }
            System.out.println(firstCellCTG.getStringCellValue());
            for (ChiTietGiay ctg : listCTG) {
                chiTietGiayDAO.save(ctg);
                System.out.println(ctg);
            }
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/admin/giay/index";
    }
}
