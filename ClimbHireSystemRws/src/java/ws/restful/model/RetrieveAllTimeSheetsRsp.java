/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.model;

import entity.TimeSheetEntity;
import java.util.List;

/**
 *
 * @author rycan
 */
public class RetrieveAllTimeSheetsRsp {
    
    private List<TimeSheetEntity> timeSheets;

    public RetrieveAllTimeSheetsRsp() {
    }

    public RetrieveAllTimeSheetsRsp(List<TimeSheetEntity> timeSheets) {
        this.timeSheets = timeSheets;
    }

    public List<TimeSheetEntity> getTimeSheets() {
        return timeSheets;
    }

    public void setTimeSheets(List<TimeSheetEntity> timeSheets) {
        this.timeSheets = timeSheets;
    }
    
    
    
}
