package model;
import dao.HolidayDAO;
import enums.*;
import dao.*;
import enums.HolidayType;
import model.EmployerModel.Employer;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.time.LocalDate;

public class HolidayLogic {

    private HolidayDAO dao;

    public HolidayLogic (HolidayDAO dao) {
        this.dao = dao;
    }

    public boolean addHoliday(int id, int employer_id, String fullName, LocalDate startDate, LocalDate endDate, HolidayType holidayType) {

        if (!isValidDates(startDate, endDate)) return false;

        int days =  dao.getHolidayDays(employer_id);
        if (days == -1) return false;

        System.out.println("days: " + days);
        if (getDateDifferenceInDays(startDate, endDate) > days) return false;

        return dao.add( new model.Holiday(
                id,
                employer_id,
                fullName,
                startDate,
                endDate,
                holidayType
        ));
    }

    public boolean updateHoliday(int id, int employer_id, String fullName, LocalDate startDate, LocalDate endDate, HolidayType holidayType) {

        if (!isValidDates(startDate, endDate)) return false;

        int days =  dao.getHolidayDays(employer_id);
        if (days == -1) return false;

        System.out.println("days: " + days);
        if (getDateDifferenceInDays(startDate, endDate) > days) return false;

        return dao.update(new model.Holiday(
                id,
                employer_id,
                fullName,
                startDate,
                endDate,
                holidayType
        ));
    }

    public boolean deleteHoliday(int id) {
        return dao.delete(id);
    }

    public List<model.Holiday> getAllHolidays() {
        return dao.getAll();
    }

    private boolean isValidDates(LocalDate startDate, LocalDate endDate) {

        return endDate.isAfter(startDate);
    }

    private int getDateDifferenceInDays(LocalDate startDate, LocalDate endDate) {
        return (int) java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate);
    }
    private boolean checkFileExists(File file){
        if(!file.exists()){
            throw new IllegalArgumentException("le fichier n'existe pas :" + file.getPath());
        }
        return true;
    }

    private boolean checkIsFile(File file){
        if(!file.isFile()){
            throw new IllegalArgumentException("le chemin specifie n'est pas un fichier :" + file.getPath());
        }
        return true;
    }

    private boolean checkIsReadable(File file){
        if(!file.canRead()){
            throw new IllegalArgumentException("le fichier n'est pas lisible :" + file.getPath());
        }
        return true;
    }

    public void importData(String FileName) {
        File file = new File(FileName);
        checkFileExists(file);
        checkIsFile(file);
        checkIsReadable(file);
        dao.importData(FileName);
    }

    public void exportData(String FileName , List<model.Holiday> data) throws IOException {
        File file = new File(FileName);
        checkFileExists(file);
        checkIsFile(file);
        dao.exportData(FileName, data);
    }
}