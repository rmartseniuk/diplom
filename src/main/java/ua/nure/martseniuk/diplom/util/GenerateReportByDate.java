package ua.nure.martseniuk.diplom.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ua.nure.martseniuk.diplom.domain.Account;
import ua.nure.martseniuk.diplom.domain.Currency;
import ua.nure.martseniuk.diplom.domain.Item;
import ua.nure.martseniuk.diplom.domain.TimePeriod;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Roman_Martseniuk.
 */
public class GenerateReportByDate {

    static Account account;

    static {
        Item item = new Item();
        item.setTitle("title1");
        item.setAmount(new BigDecimal("125"));
        item.setCurrency(Currency.USD);
        item.setPeriod(TimePeriod.MONTH);

        Item item1 = new Item();
        item1.setTitle("title2");
        item1.setAmount(new BigDecimal("126"));
        item1.setCurrency(Currency.UAH);
        item1.setPeriod(TimePeriod.MONTH);

        Item item2 = new Item();
        item2.setTitle("title3");
        item2.setAmount(new BigDecimal("127"));
        item2.setCurrency(Currency.EURO);
        item2.setPeriod(TimePeriod.MONTH);

        List<Item> list = new ArrayList<>();
        list.add(item);
        list.add(item1);
        list.add(item2);

        account = new Account();
        account.setIncomes(list);
        account.setExpenses(list);
    }

    public static byte[] generateReport(Account account) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Report");
        createHeaderRow(sheet);
        Row rowIncomes = sheet.createRow(2);
        Cell cellIncomes = rowIncomes.createCell(1);
        cellIncomes.setCellValue("Incomes");
        int rowCount = 2;

        for(Item item: account.getIncomes()){
            Row row = sheet.createRow(++rowCount);
            generate(row, item);
        }

        Row rowExpenses = sheet.createRow(++rowCount);
        Cell cellExpenses = rowExpenses.createCell(1);
        cellExpenses.setCellValue("Expenses");

        for(Item item: account.getExpenses()){
            Row row = sheet.createRow(++rowCount);
            generate(row, item);
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            workbook.write(bos);
        } finally {
            bos.close();
        }
        byte[] bytes = bos.toByteArray();

        return bytes;
    }

    private static void generate(Row row, Item item) {
        Cell cellTitle = row.createCell(1);
        cellTitle.setCellValue(item.getTitle());
        Cell cellAmount = row.createCell(2);
        cellAmount.setCellValue(item.getAmount().toString());
        Cell cellCurrency = row.createCell(3);
        cellCurrency.setCellValue(item.getCurrency().toString());
        Cell cellPeriod = row.createCell(4);
        cellPeriod.setCellValue(item.getPeriod().toString());
    }

//    public static void main(String[] args) {
//        try (FileOutputStream outputStream = new FileOutputStream("src/main/resources/JavaBooks.xlsx")) {
//            XSSFWorkbook workbook = generateReport(account);
//            workbook.write(outputStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private static Row createHeaderRow(XSSFSheet sheet) {
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        Font font = sheet.getWorkbook().createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 12);
        cellStyle.setFont(font);

        Row row = sheet.createRow(1);
        Cell cellTitle = row.createCell(1);
        cellTitle.setCellStyle(cellStyle);
        cellTitle.setCellValue("Title");

        Cell cellAmount = row.createCell(2);
        cellAmount.setCellStyle(cellStyle);
        cellAmount.setCellValue("Amount");

        Cell cellCurrency = row.createCell(3);
        cellCurrency.setCellStyle(cellStyle);
        cellCurrency.setCellValue("Currency");

        Cell cellPeriod = row.createCell(4);
        cellPeriod.setCellStyle(cellStyle);
        cellPeriod.setCellValue("Period");

        return row;
    }

}
