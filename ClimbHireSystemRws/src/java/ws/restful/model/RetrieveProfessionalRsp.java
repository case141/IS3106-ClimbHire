/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.model;

import entity.ProfessionalEntity;

/**
 *
 * @author rycan
 */
public class RetrieveProfessionalRsp {
    
    private ProfessionalEntity professionalEntity;

    public RetrieveProfessionalRsp() {
    }

    public RetrieveProfessionalRsp(ProfessionalEntity professionalEntity) {
        this.professionalEntity = professionalEntity;
    }

    public ProfessionalEntity getProfessionalEntity() {
        return professionalEntity;
    }

    public void setProfessionalEntity(ProfessionalEntity professionalEntity) {
        this.professionalEntity = professionalEntity;
    }
    
    
    
}
