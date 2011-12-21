/*
 * SpagoBI, the Open Source Business Intelligence suite
 * � 2005-2015 Engineering Group
 *
 * This file is part of SpagoBI. SpagoBI is free software: you can redistribute it and/or modify it under the terms of the GNU
 * Lesser General Public License as published by the Free Software Foundation, either version 2.1 of the License, or any later version. 
 * SpagoBI is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details. You should have received
 * a copy of the GNU Lesser General Public License along with SpagoBI. If not, see: http://www.gnu.org/licenses/.
 * The complete text of SpagoBI license is included in the COPYING.LESSER file. 
 */
package it.eng.spagobi.kpi.model.metadata;

import it.eng.spagobi.commons.metadata.SbiHibernateModel;
// Generated 5-nov-2008 17.17.20 by Hibernate Tools 3.1.0 beta3



/**
 * SbiKpiModelResources generated by hbm2java
 */

public class SbiKpiModelResources  extends SbiHibernateModel {


    // Fields    
	
     private Integer kpiModelResourcesId;
     private SbiKpiModelInst sbiKpiModelInst;
     private SbiResources sbiResources;


    // Constructors

    /** default constructor */
    public SbiKpiModelResources() {
    this.kpiModelResourcesId = -1;
    }

    
    /** full constructor */
    public SbiKpiModelResources(Integer kpiModelResourcesId, SbiKpiModelInst sbiKpiModelInst, SbiResources sbiResources) {
        this.kpiModelResourcesId = kpiModelResourcesId;
        this.sbiKpiModelInst = sbiKpiModelInst;
        this.sbiResources = sbiResources;
    }
    

   
    // Property accessors

    public Integer getKpiModelResourcesId() {
        return this.kpiModelResourcesId;
    }
    
    public void setKpiModelResourcesId(Integer kpiModelResourcesId) {
        this.kpiModelResourcesId = kpiModelResourcesId;
    }

    public SbiKpiModelInst getSbiKpiModelInst() {
        return this.sbiKpiModelInst;
    }
    
    public void setSbiKpiModelInst(SbiKpiModelInst sbiKpiModelInst) {
        this.sbiKpiModelInst = sbiKpiModelInst;
    }

    public SbiResources getSbiResources() {
        return this.sbiResources;
    }
    
    public void setSbiResources(SbiResources sbiResources) {
        this.sbiResources = sbiResources;
    }
   








}
