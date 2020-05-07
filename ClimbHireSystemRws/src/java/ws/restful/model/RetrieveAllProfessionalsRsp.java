/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.model;

import entity.ProfessionalEntity;
import java.util.List;

/**
 *
 * @author rycan
 */
public class RetrieveAllProfessionalsRsp {
    
    private List<ProfessionalEntity> professionals;

    public RetrieveAllProfessionalsRsp() {
    }

    public RetrieveAllProfessionalsRsp(List<ProfessionalEntity> professionals) {
        this.professionals = professionals;
    }

    public List<ProfessionalEntity> getProfessionals() {
        return professionals;
    }

    public void setProfessionals(List<ProfessionalEntity> professionals) {
        this.professionals = professionals;
    }
       
    
}