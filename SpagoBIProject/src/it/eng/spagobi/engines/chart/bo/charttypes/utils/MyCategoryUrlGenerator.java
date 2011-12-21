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


package it.eng.spagobi.engines.chart.bo.charttypes.utils;

import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.jfree.chart.urls.StandardCategoryURLGenerator;
import org.jfree.data.category.CategoryDataset;

/** 
 *  * @author Giulio Gavardi
 *     giulio.gavardi@eng.it
 */

/** Extends StandardCategoryURLGenerator: the purpose of this class is to add a postfix in the case of document-composition and to substitute in the URL
 * serie anc category parameter, addedd by JFreeCharts function, with label chosen by the user
 */


public class MyCategoryUrlGenerator extends StandardCategoryURLGenerator{


	private String serieUrlLabel="series";
	private String categoryUrlLabel="catergory";
	private boolean document_composition=false;
	private static transient Logger logger=Logger.getLogger(MyCategoryUrlGenerator.class);
	private String URL=null;
	private String drillDocTitle = null;
	private String target = "self";

	public String getDrillDocTitle() {
		return drillDocTitle;
	}

	public void setDrillDocTitle(String drillDocTitle) {
		this.drillDocTitle = drillDocTitle;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	/* (non-Javadoc)
	 * @see org.jfree.chart.urls.StandardCategoryURLGenerator#generateURL(org.jfree.data.category.CategoryDataset, int, int)
	 */
	public String generateURL(CategoryDataset dataset, int series, int category) {
		logger.debug("IN");
		URL=new String();
		URL=super.generateURL(dataset, series, category);

		// URL contains the URL generated by the JFreeChart Library

		// take the serieUrlLabel, default is "series"
		if(serieUrlLabel==null || serieUrlLabel.equals("")){serieUrlLabel="series";}
		String serieToMove=replaceAndGetParameter("series=", serieUrlLabel,true);


		// take the categoryUrlLabel, default is "category"
		if(categoryUrlLabel==null || categoryUrlLabel.equals("")){categoryUrlLabel="category";}
		String categoryToMove=replaceAndGetParameter("category=", categoryUrlLabel,false);

		// this is the string to move inside PARAMETERS=
		String toMove=serieToMove+categoryToMove;

		// workaround (work-around): since JFreeChart converts white space into '+', this ruins the cross navigation url
		// therefore we substitute '+' with white space
		// TODO check what happens when the chart's template does not specify any categoryUrlName and seriesUrlName
		toMove = toMove.replaceAll(Pattern.quote("+") , " "); 


		// insert into PARAMETERS=
		/*if(!document_composition){
		String parameters=ObjectsTreeConstants.PARAMETERS;
		URL=URL.replaceAll(parameters+"=", parameters+"="+toMove);
		URL=URL+"');";
		}
		else{*/
		URL=URL+toMove;
		if(drillDocTitle!=null && target!=null && target.equalsIgnoreCase("tab")){
			URL +="','','"+drillDocTitle+"','tab";
		}else if(drillDocTitle!=null){
			URL +="','','"+drillDocTitle;
		}
		URL=URL+"');";
		logger.debug("Linked URL:"+URL);
		//}

		logger.debug("OUT");
		return URL;
	}

	/**
	 * Instantiates a new my category url generator.
	 */
	public MyCategoryUrlGenerator() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new my category url generator.
	 * 
	 * @param prefix the prefix
	 * @param seriesParameterName the series parameter name
	 * @param categoryParameterName the category parameter name
	 */
	public MyCategoryUrlGenerator(String prefix, String seriesParameterName,
			String categoryParameterName) {
		super(prefix, seriesParameterName, categoryParameterName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new my category url generator.
	 * 
	 * @param prefix the prefix
	 */
	public MyCategoryUrlGenerator(String prefix) {
		super(prefix);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets the serie url label.
	 * 
	 * @return the serie url label
	 */
	public String getSerieUrlLabel() {
		return serieUrlLabel;
	}

	/**
	 * Sets the serie url label.
	 * 
	 * @param serieUrlLabel the new serie url label
	 */
	public void setSerieUrlLabel(String serieUrlLabel) {
		this.serieUrlLabel = serieUrlLabel;
	}

	/**
	 * Gets the category url label.
	 * 
	 * @return the category url label
	 */
	public String getCategoryUrlLabel() {
		return categoryUrlLabel;
	}

	/**
	 * Sets the category url label.
	 * 
	 * @param categoryUrlLabel the new category url label
	 */
	public void setCategoryUrlLabel(String categoryUrlLabel) {
		this.categoryUrlLabel = categoryUrlLabel;
	}

	/**
	 * Checks if is document_composition.
	 * 
	 * @return true, if is document_composition
	 */
	public boolean isDocument_composition() {
		return document_composition;
	}

	/**
	 * Sets the document_composition.
	 * 
	 * @param document_composition the new document_composition
	 */
	public void setDocument_composition(boolean document_composition) {
		this.document_composition = document_composition;
	}


	private String replaceAndGetParameter(String toReplace, String replacer, boolean serie){
		// toReplace, series= or category=

		// Start index to substitute, check there is only one
		int startIndex=URL.indexOf(toReplace);
		int otherStart=URL.lastIndexOf(toReplace);
		if(startIndex!=otherStart){ //menas that there are more occurrence of the same parameter... ERROR
			logger.error("Too many occurrence of the same parameter defined in template");
			return null;
		}
		//end index of thing to substitute
		int endIndex=URL.indexOf('&', startIndex);

		// if there is no end index that is the end of the string
		if(endIndex==-1)endIndex=URL.length();
		String toMove=URL.substring(startIndex, endIndex);

		if(URL.contains("&amp;"+toMove)){
			URL=URL.replaceAll("&amp;"+toMove, "");
		}
		else{
			if(URL.contains("&"+toMove)){
				URL=URL.replaceAll("&"+toMove, "");
			}
		}
		if(URL.contains(toMove)){
			URL=URL.replaceAll(toMove, "");
		}
		if(URL.contains("?")){
			int indexQuestion=URL.indexOf('?');
			URL=URL.replace("?", "");
		}

		if(!replacer.equals("")){
			if(serie)
				toMove=toMove.replaceAll("series", replacer);
			else
				toMove=toMove.replaceAll("category", replacer);	
		}

		toMove=toMove.replaceAll("=", "%3D");
		toMove="%26"+toMove;


		return toMove;


	}

}
