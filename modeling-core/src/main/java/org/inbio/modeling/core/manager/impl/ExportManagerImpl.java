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

import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
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
            long currentSessionId) throws Exception{

        // Fonts Definition
        Font titleFont = new Font(Font.TIMES_ROMAN, 24, Font.BOLD);
        Font subtitleFont = new Font(Font.TIMES_ROMAN, 16, Font.BOLD);
        Font layerNameFont = new Font(Font.TIMES_ROMAN, 14, Font.BOLD);
        Font boldFont = new Font(Font.TIMES_ROMAN, 12 , Font.BOLD);

        Font smallFont = new Font(Font.TIMES_ROMAN, 12 , Font.BOLD);
        Font smallGreenFont = new Font(Font.TIMES_ROMAN, 12 , Font.BOLD, Color.GREEN);
        Font smallDarkGreenFont = new Font(Font.TIMES_ROMAN, 12 , Font.BOLD, new Color(135,194,53));

        LineSeparator sep = new LineSeparator();
        sep.setOffset(-5F);
        
        // Add metadata
		document.addTitle("Modelado de amenazas");
		document.addSubject("Escenario propuesto");
		document.addKeywords("escenario,amenazas");
		document.addCreator("Software de modelado, INBio");

        // Modeling threats report (Main title)
        Paragraph mainTitle = new Paragraph();
		this.addEmptyLine(mainTitle, 1);
		mainTitle.add(new Paragraph("Modelo de amenazas", titleFont));
        this.addEmptyLine(mainTitle, 1);

        // Generated escenary (Sub title)
        Paragraph escenarySubtitle = new Paragraph();
        this.addEmptyLine(escenarySubtitle, 1);
        escenarySubtitle.add(new Chunk("Escenario Propuesto", subtitleFont));
        escenarySubtitle.add(sep);
        this.addEmptyLine(escenarySubtitle, 1);


        Image mapImage = Image.getInstance(mapsHome+imageName+"_T_"+currentSessionId+".png");
        mapImage.scaleToFit(640, 480);
        mapImage.setAlignment(Image.ALIGN_LEFT);

        // scale
        Image scaleImage = Image.getInstance(mapsHome+"scale.jpg");
        scaleImage.scaleToFit(30, 150);
        scaleImage.setAlignment(Image.ALIGN_RIGHT);

        Paragraph images = new Paragraph();
        images.setAlignment(Paragraph.ALIGN_RIGHT);

        //upper scale leyend
        images.add(new Chunk("High threat\n\n\n\n\n\n\n", new Font(Font.COURIER, 8, Font.BOLDITALIC)));
        // Map
        images.add(new Chunk(mapImage, 1F, 1F));
        // Scale
        images.add(new Chunk(scaleImage, 1F, 1F));
        // lower scale leyend
        images.add(new Chunk("\nLow threat\n",new Font(Font.COURIER, 8, Font.BOLDITALIC) ));

        // resolution information
        Paragraph resolutionSubtitle = new Paragraph();
        this.addEmptyLine(resolutionSubtitle, 1);
        resolutionSubtitle.add(new Paragraph("Infomación General", subtitleFont));
        this.addEmptyLine(resolutionSubtitle, 1);

        // resolution information
        Paragraph layerSubtitle = new Paragraph();
        this.addEmptyLine(layerSubtitle, 1);
        layerSubtitle.add(new Paragraph("Layers", subtitleFont));
        this.addEmptyLine(layerSubtitle, 1);

        // Layers
            // Metadata

        document.add(mainTitle);
        document.add(escenarySubtitle);
        this.addNewLine(document, 20);
        document.add(images);
        document.add(resolutionSubtitle);
        document.add(new Paragraph("Resolution: " + resolution + " meters"));
        this.addNewLine(document, 1);

        document.newPage();
        document.add(layerSubtitle);

        Table table;

        List<CategoryDTO> categorys;
        Paragraph layerName = null;
        Paragraph metadata = null;
        Paragraph layerDetail = null;
        Cell cell = null;
        LayerDTO layer  = null;

        for (GrassLayerDTO grassLayerDTO : layerList) {

            // Print the name of the layer.
            layerName = new Paragraph();
            this.addEmptyLine(layerName, 1);
            layerName.add(new Paragraph(grassLayerDTO.getDisplayName(), layerNameFont));

            // Print the metadata information
            table = new Table(2);

            table.setBorderColor(Color.GRAY);
            table.setPadding(2);
            table.setSpacing(2);
            table.setBorderWidth(1);

            layer = layerManager.getLayerByName(grassLayerDTO.getName());

            table.setTableFitsPage(true);

            cell = new Cell(new Chunk("Metadata", new Font(Font.TIMES_ROMAN, 15 , Font.BOLD)));
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
            cell.setColspan(2);
            cell.setHeader(true);
            table.addCell(cell);

            cell = new Cell(new Chunk("Source", boldFont));
            cell.setHeader(true);
            table.addCell(cell);
            table.addCell(layer.getSource());

            cell = new Cell(new Chunk("Year", boldFont));
            cell.setHeader(true);
            table.addCell(cell);
            table.addCell(layer.getYear());

            cell = new Cell(new Chunk("Visualization Scale", boldFont));
            cell.setHeader(true);
            table.addCell(cell);
            table.addCell(layer.getVizScale());

            cell = new Cell(new Chunk("Data Scale", boldFont));
            cell.setHeader(true);
            table.addCell(cell);
            table.addCell(layer.getDataScale());

            cell = new Cell(new Chunk("Generation Procedure", boldFont));
            cell.setHeader(true);
            table.addCell(cell);
            table.addCell(layer.getGenerationProcedure());

            cell = new Cell(new Chunk("Description", boldFont));
            cell.setHeader(true);
            table.addCell(cell);
            table.addCell(layer.getDescription());

            cell = new Cell(new Chunk("Data type", boldFont));
            cell.setHeader(true);
            table.addCell(cell);
            table.addCell(grassLayerDTO.getType().toString());

            document.add(layerName);
            document.add(sep);
            document.add(table);

                table = new Table(2);

                table.setBorderColor(Color.GRAY);
                table.setPadding(2);
                table.setSpacing(2);
                table.setBorderWidth(1);

                cell = new Cell(new Chunk("Layer data configuration", new Font(Font.TIMES_ROMAN, 15 , Font.BOLD)));
                cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
                cell.setColspan(2);
                cell.setHeader(true);
                table.addCell(cell);

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
                table.addCell(" - Line - ");

                for (CategoryDTO categoryDTO : categorys) {

                    table.addCell(counter++ +" ");
                    table.addCell(minimal+ " - " + categoryDTO.getValue()+"m");
                    minimal = categoryDTO.getValue();
                }
            } else{
                    table.addCell("Radio");
                    table.addCell(categorys.get(0).getValue() + "m");
            }
                document.add(table);
        }

        Paragraph footer = new Paragraph();
        footer.add(sep);
        footer.setAlignment(Paragraph.ALIGN_CENTER);
        this.addEmptyLine(footer, 1);
        footer.add(new Paragraph("El software utilizado para generar este reporte fue desarrollado por: ", smallFont));
        footer.add(new Paragraph(" El Instituto Nacional de Biodiversidad(INBio), Costa Rica", smallGreenFont));
        footer.add(new Paragraph(" y finaciado por la Red Interamericana de Información para la Biodiversidad (IABIN) ", smallDarkGreenFont));
        this.addEmptyLine(footer, 1);

        document.add(footer);

        return document;
    }

	private void addEmptyLine(Paragraph paragraph, int number) throws DocumentException{
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	private void addNewLine(Document document, int number) throws DocumentException {
		for (int i = 0; i < number; i++) {
			document.add(new Paragraph("\n"));
		}
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

    public static void main(String[] args){

       String FILE = "/home/asanabria/report.pdf";
       ExportManager em = new ExportManagerImpl();

        Document document = new Document();

        try {

            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            document =  em.exportPDF(document, 50D, "mapa", "limite", null, 1234);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LayerManager getLayerManager() {
        return layerManager;
    }

    public void setLayerManager(LayerManager layerManager) {
        this.layerManager = layerManager;
    }
}
