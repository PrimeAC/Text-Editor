package edt.core;

import java.util.*;
import java.lang.*;
 
/**
 *This class contains as atributes a title, one ArrayList of paragraphs and one ArrayList of sections <p>
 *has methods for getting the headline of a section, getting and setting a title, get the size, get content <p>
 *also methods for getting specific sections, add and remove sections and paragraphs <p>
 *
 *@author Afonso Caetano 82539 ; Bruno Santos 82053 ; João Correia 81990
*/
public class Section extends TextElement {

	/**
	* A string that stores the title of the given section
	*/
	protected String _title="";

	/**
	* This ArrayList stores all the paragraphs in the given section
	*/
	private List<Paragraph> _paragraph = new ArrayList<>();

	/**
	* This ArrayList stores all the sections in the given section
	*/
	private List<Section> _subsection = new ArrayList<>();

	/**
	 * Default constructor: false for all inputs.
	 */
	public Section() {

	}

	/**
	 * Overloaded constructor: Inputs receive same value.
	 * 
	 * @param title the input value.
	 * @see #Section() Section
	 */
	public Section(String title) {
		_title=title;
	}

	/**
	 * This methods returns the title between braces.
	 * This function checks if the atribute title is null and responds accordingly. 
	 *
	 * @return String - title between braces
	 */
	public String getHeadLine() {
		if (getKey() == null) {
			return "[] " + "{"+_title+"}";
		}
		else {
			return "["+getKey()+"] {"+_title+"}"; 
		}
	}

	/**
	 * This methods returns all titles.
	 * This function returns all the titles. 
	 *
	 * @return String - title between braces
	 */
	public String getAllTitles(int flag) {
		String titles="";

		if (getTitle() != "" && flag == 1) {
			titles += getHeadLine()+"\n";
		}

		if (_subsection != null) {
			for (Section i : _subsection) {
				titles += i.getAllTitles(1);
			}	
		}
		return titles;	
	}

	/**
	 * Sets the title of the section.
	 * This method receives a string and sets the section wtih the given title. 
	 *
	 * @param title - new title to be set
	 */
	public void setTitle(String title) {
		_title = title;
	}

	/**
	 * Returns the size of the given section.
	 * This methods counts the number of characters in all the subsections and paragraphs of the section. 
	 *
	 * @return int - section size in bytes
	 */
	public int getSize() {
		int total;

		total = 0;

		if (this.getTitle() != null) {
			total = this.getTitle().length();
		}

		for (Paragraph i : this._paragraph) {
			total += i.getSize();
		}

		for (Section i : this.getSubsections()) {
			total += i.getSize();	
		}
		return total;
	}

	/**
	 * Gets the title of the section.
	 * This method gets the title of the section. 
	 *
	 * @return String - section title
	 */
	public String getTitle() {
		if (_title == null) {
			return "";
		}

		return _title;
	}


	public String accept(VisitorContent visitor){
		return visitor.visit(this);
	}
	

	/**
	 * Gets the subsections of the section.
	 * This method returns an ArrayList with the subsections of the section. 
	 *
	 * @return List<Section> - array with sections
	 */
	public List<Section> getSubsections() {
		return _subsection;
	}

	/**
	 * Gets a specific subsection in the section.
	 * This method receives an index and returns the subsection in that index. 
	 *
	 * @param idx - index of the subsection
	 * @return Section - section associated with that index
	 */
	public Section getSection(int idx) {
		if (idx >= 0 && idx < this.getSubsections().size()){
			return _subsection.get(idx);
		}
		
		else {
			return null;
		}
	}

	/**
	 * Adds a subsection to the section.
	 * This methos receives an index and a section and inserts the given section in the respective index. 
	 *
	 * @param idx - index of the subsection
	 * @param sec - section to be added
	 */
	public void addSection(int idx, Section sec) {
		if (idx < 0 || idx > _subsection.size()) {
			_subsection.add(sec);
		}

		else {
			_subsection.add(idx,sec);
		}
	}

	/**
	 * Removes a subsection from a document.
	 * This method receives an index and a document and removes the subsection in the respective index. 
	 *
	 * @param idx - index of the section
	 * @param doc - document which a subsection is going to be removed
	 * @return boolean - result of the removal
	 */
	public boolean removeSection(int idx, Document doc) {
		if (getSubsections().size() > idx  && idx >= 0) {
			removeAllSubsections(this, doc);
			if (getSection(idx).isIndexed()) {
				doc.removeFromIndex(this.getSection(idx));
			}
		}
		return this.getSubsections().remove(this.getSection(idx));
	}

	/**
	 * Removes all the subsections and paragraphs from a section.
	 * This method receives an section and a document and removes all the subsections and paragraphs from the HashMap. 
	 *
	 * @param section - section
	 * @param doc - document which a subsection is going to be removed
	 */
	public void removeAllSubsections(Section section, Document doc) {

		for (Paragraph j : section.getParagraph()) {

			if(j.isIndexed()){

				doc.removeFromIndex(j);
			}
		}

		for (Section i : section.getSubsections()) {
			/*if (i.isIndexed()) {
				doc.removeFromIndex(i);
			}*/
			removeAllSubsections(i, doc);

		/*	for (Paragraph j : _paragraph) {

				if(j.isIndexed()){
					doc.removeFromIndex(j);
				}
			}
			removeAllSubsections(i, doc);
			if (i.isIndexed()) {
				doc.removeFromIndex(i);
			}*/
		}
	}

	/**
	 * Adds a paragraph to the section.
	 * This methos receives an index and inserts the given paragraph in the respective index. 
	 *
	 * @param idx - index of the paragraphs array
	 * @param par - paragraph to be added
	 */
	public void addParagraph(int idx, Paragraph par) {
		if (idx < 0 || idx > _paragraph.size()) {
			_paragraph.add(par);
		}

		else {
			_paragraph.add(idx,par);
		}
	}

	/**
	 * Removes a paragraph from a document.
	 * This method receives an index and a document and removes de paragraph in the respective index. 
	 *
	 * @param idx - index of the paragraphs array
	 * @param doc - document wich a paragrah is going to be removed
	 * @return boolean - result of the removal
	 */
	public boolean removeParagraph(int idx, Document doc) {
		if(idx>=0 && idx<this.getParagraph().size()){
		 	this.getParagraph().remove(this.getParagraph(idx));
		return true;
		}
		else {
			return false ;
		}
	}

	/**
	 * Gets a specific paragraph in the section.
	 * This method receives an index and returns the paragraph in that index. 
	 *
	 * @param idx - index of the paragraphs array
	 * @return Paragraph - paragraph associated with that index
	 */
	public List<Paragraph> getParagraph() {
		return _paragraph;
	}

	public Paragraph getParagraph(int idx) {
		if(idx>=0 && idx<this.getParagraph().size()){
		return _paragraph.get(idx);
		}
		else {
			return null ;
		}
	}

}