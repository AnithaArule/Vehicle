package com.utils;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by anitha on 02/09/2017.
 */
public class ExcelUtils {
    private static XSSFSheet ExcelWSheet;

    private static XSSFWorkbook ExcelWBook;

    private static XSSFCell Cell;

    private static XSSFRow Row;

    static  {
        try {
            ExcelUtils.setExcelFile(Constants.Path_TestData + Constants.File_TestData, "Sheet1");
        }catch (Exception e){
            //TODO
        }
    }
    //This method is to set the File path and to open the Excel file, Pass Excel Path and Sheet name as Arguments to this method

    public static void setExcelFile(String Path, String SheetName)throws Exception{
        try{
            //Open the Excel File
            FileInputStream ExcelFile = new FileInputStream(Path);

            //Access the required test data sheet
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
        }
            catch(Exception e){
                throw(e);
            }
    }

    public static String  getCellData(int RowNum, int ColNum)throws Exception{

        try{
            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
            String CellData = Cell.getStringCellValue();
            return CellData;
        }catch (Exception e){

            return"";
        }
    }

    public static void setCellData(String Result, int RowNum, int ColNum)throws Exception{
        try{
            Row = ExcelWSheet.getRow(RowNum);
            Cell = Row.getCell(ColNum,Row.RETURN_BLANK_AS_NULL);
            if(Cell == null){
                Cell = Row.createCell(ColNum);
                Cell.setCellValue(Result);
            }
            else {
                Cell.setCellValue(Result);
            }
            FileOutputStream fileOut = new FileOutputStream(Constants.Path_TestData + Constants.File_TestData);

            ExcelWBook.write(fileOut);

            fileOut.flush();

            fileOut.close();

            }catch (Exception e) {
            throw (e);
        }
        }


    }


