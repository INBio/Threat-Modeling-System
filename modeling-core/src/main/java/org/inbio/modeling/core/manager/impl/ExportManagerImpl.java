/* Modeling - Application to model threats.
 *
 * Copyright (C) 2010  INBio ( Instituto Nacional de Biodiversidad )
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.inbio.modeling.core.manager.impl;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.draw.LineSeparator;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import org.inbio.modeling.core.dto.CategoryDTO;
import org.inbio.modeling.core.dto.GrassLayerDTO;
import org.inbio.modeling.core.dto.LayerDTO;
import org.inbio.modeling.core.layer.LayerType;
import org.inbio.modeling.core.manager.ExportManager;
import org.inbio.modeling.core.manager.LayerManager;

/**
 *
 * @author asanabria
 */
public class ExportManagerImpl implements ExportManager {

    private String mapsHome;
    private String tempDir;
    private LayerManager layerManager;
    private Locale currentLocale;


    @Override
    public FileInputStream exportImage(String layerName, long suffix) throws Exception{

        File f = new File(mapsHome+layerName+"_T_"+suffix+".png");

        FileInputStream fis = null;

        if(f.exists()){
            fis = new FileInputStream(f);
        }

        return fis;
    }

    @Override
    public FileInputStream exportShapefile(String layerName, long suffix) throws Exception {

        File f = new File(tempDir+layerName+"_T_"+suffix+".zip");
        FileInputStream fis = null;

        if(f.exists()){
            fis = new FileInputStream(f);
        }

        return fis;
    }

    @Override
    public Document exportPDF(Document document,
            Double resolution,
            String imageName,
            String limitLayerName,
            List<GrassLayerDTO>  layerList,
            long currentSessionId,
            Locale locale) throws Exception{


        // set the current locale globally
        this.currentLocale = locale;

        // Add metadata
        this.addMetadata(document);

        // Modeling threats report (Main title)
        this.addTitleOne(document, this.getI18nString("pdfReport.threatModel"));

        // Generated escenary (Sub title)
        this.addTitleTwo(document, this.getI18nString("pdfReport.proposedScenary"),true);
        this.addNewLine(document, 20);

        // Map and scale image
        this.addImageSection(document, imageName, currentSessionId);

        // General information of the scenario
        this.addTitleTwo(document,this.getI18nString("pdfReport.generalInfo"));

        // resolution information
        document.add(new Paragraph(this.getI18nString("pdfReport.resolution")+": " + resolution + " " + this.getI18nString("pdfReport.meters")));
        this.addNewLine(document, 1);

        // Limit layer
        document.add(new Paragraph(this.getI18nString("pdfReport.limitLayer") + " : " + limitLayerName));
        this.addNewLine(document, 1);

        document.newPage();

        // Layer sub-section
        this.addTitleTwo(document,this.getI18nString("pdfReport.layers"));

        Table table;

        LayerDTO layer  = null;
        List<CategoryDTO> categorys;

        for (GrassLayerDTO grassLayerDTO : layerList) {


            // layer name (title)
            this.addTitleThree(document, grassLayerDTO.getDisplayName());

            // Get the layer information
            layer = layerManager.getLayerByName(grassLayerDTO.getName());

            // create the table with the metadata information.
            table = this.create2columnTable();

            this.addTableHeader(table, this.getI18nString("pdfReport.metadata"));

            this.addTableRow(table, this.getI18nString("pdfReport.source"), layer.getSource());
            this.addTableRow(table, this.getI18nString("pdfReport.year"), layer.getYear());
            this.addTableRow(table, this.getI18nString("pdfReport.visualizationScale"), layer.getVizScale());
            this.addTableRow(table, this.getI18nString("pdfReport.dataScale"), layer.getDataScale());
            this.addTableRow(table, this.getI18nString("pdfReport.generationProcedure"), layer.getGenerationProcedure());
            this.addTableRow(table, this.getI18nString("pdfReport.description"), layer.getDescription());
            this.addTableRow(table, this.getI18nString("pdfReport.dataType"), grassLayerDTO.getType().toString());

            document.add(table);

            // Create the table with the infomation about the reclasification or parameters used
            // in the scenario generation process.
            table = this.create2columnTable();

            this.addTableHeader(table, this.getI18nString("pdfReport.layerDataConf"));

            // Print the intervals/category/Radio information
            categorys = grassLayerDTO.getCategories();

            if(grassLayerDTO.getType().equals(LayerType.AREA)) {
                for (CategoryDTO categoryDTO : categorys) {
                    if(categoryDTO != null){
                        table.addCell(categoryDTO.getValue());
                        table.addCell(categoryDTO.getDescription());
                    }
                }
            } else if (grassLayerDTO.getType().equals(LayerType.LINE)) {
                String minimal = "0";
                int counter = 2;

                table.addCell("1");
                table.addCell(this.getI18nString("pdfReport.lineMarker"));

                for (CategoryDTO categoryDTO : categorys) {

                    table.addCell(counter++ +" ");
                    table.addCell(minimal+ " - " + categoryDTO.getValue()+this.getI18nString("pdfReport.meters"));
                    minimal = categoryDTO.getValue();
                }
            } else{
                table.addCell(this.getI18nString("pdfReport.radio"));
                table.addCell(categorys.get(0).getValue() + this.getI18nString("pdfReport.meters"));
            }
            document.add(table);
        }


        String footerText[] = {
            this.getI18nString("pdfReport.footer1"),
            this.getI18nString("pdfReport.footer2"),
            this.getI18nString("pdfReport.footer3") };

        this.addDocumentFooter(document, footerText);

        return document;
    }

    private void addDocumentFooter(Document d, String[] texts) throws DocumentException{

        Font smallFont = new Font(Font.TIMES_ROMAN, 12 , Font.BOLD);
        Font smallGreenFont = new Font(Font.TIMES_ROMAN, 12 , Font.BOLD, Color.GREEN);
        Font smallDarkGreenFont = new Font(Font.TIMES_ROMAN, 12 , Font.BOLD, new Color(135,194,53));

        Paragraph footer = new Paragraph();
        this.addLineSeparator(footer, 1);
        footer.setAlignment(Paragraph.ALIGN_CENTER);
        this.addEmptyLine(footer, 1);
        footer.add(new Paragraph(texts[0], smallFont));
        footer.add(new Paragraph(texts[1], smallGreenFont));
        footer.add(new Paragraph(texts[2], smallDarkGreenFont));
        this.addEmptyLine(footer, 1);
        d.add(footer);
    }

    private void addLineSeparator(Paragraph p, int number){
        LineSeparator sep = new LineSeparator();
        sep.setOffset(-5F);
        for (int i = 0; i < number; i++)
            p.add(sep);
    }

    private void addLineSeparator(Document d, int number) throws DocumentException{
        LineSeparator sep = new LineSeparator();
        sep.setOffset(-5F);
        for (int i = 0; i < number; i++)
            d.add(sep);
    }

    private void addEmptyLine(Paragraph p, int number){
        for (int i = 0; i < number; i++) {
            p.add(new Paragraph(" "));
        }
    }

    private void addNewLine(Paragraph p, int number){
        for (int i = 0; i < number; i++)
            p.add("\n");
    }

    private void addNewLine(Document d, int number) throws DocumentException{
        for (int i = 0; i < number; i++)
            d.add(new Paragraph("\n"));
    }

    private void addTableRow(Table t, String title, String value) throws BadElementException{

        // Fonts Definition
        Font boldFont = new Font(Font.TIMES_ROMAN, 12 , Font.BOLD);
        Cell cell = new Cell(new Chunk(title, boldFont));
        cell.setHeader(true);
        t.addCell(cell);
        t.addCell(value);
    }


    private Table create2columnTable() throws BadElementException{
        // Print the metadata information
        Table table = new Table(2);
        table.setBorderColor(Color.GRAY);
        table.setPadding(2);
        table.setSpacing(2);
        table.setBorderWidth(1);
        table.setTableFitsPage(true);

        return table;
    }

    private void addMetadata(Document d){

        // Add metadata
        d.addTitle(this.getI18nString("pdfReport.metadataTitle"));
        d.addSubject(this.getI18nString("pdfReport.metadataSubject"));
        d.addKeywords(this.getI18nString("pdfReport.metadataKeywords"));
        d.addCreator(this.getI18nString("pdfReport.metadataCreator"));
    }

    private void addTitleOne(Document d, String text) throws DocumentException{

        Font titleFont = new Font(Font.TIMES_ROMAN, 24, Font.BOLD);

        Paragraph p = new Paragraph();
        this.addEmptyLine(p, 1);
        p.add(new Paragraph(text, titleFont));
        this.addEmptyLine(p, 1);
        d.add(p);
    }

    private void addTitleTwo(Document d, String text) throws DocumentException{

        Font subtitleFont = new Font(Font.TIMES_ROMAN, 16, Font.BOLD);

        Paragraph p = new Paragraph();
        this.addEmptyLine(p, 1);
        p.add(new Paragraph(text, subtitleFont));
        this.addEmptyLine(p, 1);
        d.add(p);
    }

    private void addTitleTwo(Document d, String text, boolean underline) throws DocumentException{

        Font subtitleFont = new Font(Font.TIMES_ROMAN, 16, Font.BOLD);

        Paragraph p = new Paragraph();
        this.addEmptyLine(p, 1);
        p.add(new Paragraph(text, subtitleFont));

        if(underline)
            this.addLineSeparator(p, 1);

        this.addEmptyLine(p, 1);
        d.add(p);
    }

    private void addTitleThree(Document d, String text) throws DocumentException{
        Font layerNameFont = new Font(Font.TIMES_ROMAN, 14, Font.BOLD);
        Paragraph p = new Paragraph();
        this.addEmptyLine(p, 1);
        p.add(new Paragraph(text, layerNameFont));
        this.addLineSeparator(d, 1);
        d.add(p);
    }


    private void addTableHeader(Table t, String text) throws BadElementException{
        Chunk chunk = new Chunk(text, new Font(Font.TIMES_ROMAN, 15 , Font.BOLD));
        Cell cell = new Cell(chunk);
        cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
        cell.setHeader(true);
        cell.setColspan(2);
        t.addCell(cell);

    }

    private void addImageSection(Document d, String mapName, Long currentSessionId) throws BadElementException, MalformedURLException, IOException, DocumentException{


        Image mapImage = Image.getInstance(mapsHome+mapName+"_T_"+currentSessionId+".png");
        mapImage.scaleToFit(550, 413);
        mapImage.setAlignment(Image.ALIGN_LEFT);

        // scale
        Image scaleImage = Image.getInstance(mapsHome+"scale.jpg");
        scaleImage.scaleToFit(30, 150);
        scaleImage.setAlignment(Image.ALIGN_RIGHT);

        Paragraph images = new Paragraph();
        images.setAlignment(Paragraph.ALIGN_RIGHT);

        //upper scale leyend
        images.add(new Chunk(this.getI18nString("pdfReport.scaleUpper")+ "\n\n\n\n\n\n\n", new Font(Font.COURIER, 8, Font.BOLDITALIC)));
        // Map
        images.add(new Chunk(mapImage, 1F, 1F));
        // Scale
        images.add(new Chunk(scaleImage, 1F, 1F));
        // lower scale leyend
        images.add(new Chunk("\n"+this.getI18nString("pdfReport.scaleLower")+"\n",new Font(Font.COURIER, 8, Font.BOLDITALIC) ));

        d.add(images);
    }

    private String getI18nString(String key){

        Locale l = this.getCurrentLocale();

        String text =
                (String)ResourceBundle.getBundle("org.inbio.modeling.core.i18n.pdf", l).getObject(key);

        return text;
    }

    /*
     * ==================================================================
     */

    public String getMapsHome() {
        return mapsHome;
    }

    public void setMapsHome(String mapsHome) {
        this.mapsHome = mapsHome;
    }

    public String getTempDir() {
        return tempDir;
    }

    public void setTempDir(String tempDir) {
        this.tempDir = tempDir;
    }

    public LayerManager getLayerManager() {
        return layerManager;
    }

    public void setLayerManager(LayerManager layerManager) {
        this.layerManager = layerManager;
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }
}
