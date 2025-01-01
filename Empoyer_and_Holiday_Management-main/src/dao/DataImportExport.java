package dao;

import model.EmployerModel.Employer;

import java.util.List;

public interface DataImportExport <T>{
    void importData (String fileName);
    void exportData (String fileName, List<T> data);

}
