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

package org.inbio.modeling.core.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import org.inbio.modeling.core.dao.GrassDAO;
import org.inbio.modeling.core.dto.CategoryDTO;
import org.inbio.system.command.impl.SystemCommandExecutorImpl;

public class GrassDAOImpl extends BaseDAOImpl implements GrassDAO {

	/** Command Executor Instance. */
	SystemCommandExecutorImpl commandExecutor = null;

	/** Scripts (dependencies injected) */
	private String scriptHome;
	private String layerHome;
	private String configuration;
	private String importPNG;
	private String rasterization;
	private String mapAlgebra;
	private String exportPNG;
	private String getMinMax;
	private String vectorialReclasification;
	private String retrieveCategories;
	private String retrieveColumns;
	private String rasterReclasification;
	private String asignResolution;
	private String deleteGrassLocation;
	private String exportSHP;


	@Override
	public void configureEnvironment(String location, Long suffix) throws Exception   {

		int result = 0;
		List<String> commands = null;
		StringBuilder stdout = null;
		StringBuilder stderr = null;

		commands = new ArrayList<String>();

		// Arguments of the command
		commands.add(scriptHome+configuration);
		commands.add(location);
		commands.add(suffix.toString());

		logger.debug("Executing command: "+commands.toString());
		commandExecutor = new SystemCommandExecutorImpl(commands);
		// executes the command
		result = commandExecutor.executeCommand();
		// gets the output of the execution
		stdout = commandExecutor.getStandardOutput();
		stderr = commandExecutor.getStandardError();
		// Prints the output of the command for good or for bad.
		this.printThis(result, stdout, stderr);

	}

	@Override
	public void importLayer(String layerName, Long suffix) throws Exception {
		int result = 0;
		List<String> commands = null;
		StringBuilder stdout = null;
		StringBuilder stderr = null;
		commands = new ArrayList<String>();

		// Arguments of the command
		commands.add(scriptHome+importPNG);
		commands.add(layerHome+layerName);
		commands.add(suffix.toString());

		logger.debug("Executing command: "+commands.toString());
		commandExecutor = new SystemCommandExecutorImpl(commands);
		// executes the command
		result = commandExecutor.executeCommand();
		// gets the output of the execution
		stdout = commandExecutor.getStandardOutput();
		stderr = commandExecutor.getStandardError();
		// Prints the output of the command for good or for bad.
		this.printThis(result, stdout, stderr);
	}

	@Override
	public void executeRasterization(String layerName, Long suffix, String column) throws Exception{

		int result = 0;
		List<String> commands = null;
		StringBuilder stdout = null;
		StringBuilder stderr = null;
		commands = new ArrayList<String>();

		// Arguments of the command
		commands.add(scriptHome+rasterization);
		commands.add(layerName);
		commands.add(suffix.toString());
		commands.add(column);

		logger.debug("Executing command: "+commands.toString());
		commandExecutor = new SystemCommandExecutorImpl(commands);
		// executes the command
		result = commandExecutor.executeCommand();
		// gets the output of the execution
		stdout = commandExecutor.getStandardOutput();
		stderr = commandExecutor.getStandardError();
		// Prints the output of the command for good or for bad.
		this.printThis(result, stdout, stderr);
	}

	@Override
	public void executeWeightedSum(String layerName1, Double weight1, String layerName2, Double weight2, Long suffix, String outputName) throws Exception{
		int result = 0;
		List<String> commands = null;
		StringBuilder stdout = null;
		StringBuilder stderr = null;
		commands = new ArrayList<String>();

		commands = new ArrayList<String>();

		// Arguments of the command
		commands.add(scriptHome+mapAlgebra);
		commands.add(suffix.toString());
		commands.add(layerName1);
		commands.add(weight1.toString());
		commands.add(layerName2);
		commands.add(weight2.toString());
		commands.add(outputName);

		logger.debug("Executing command: "+commands.toString());
		commandExecutor = new SystemCommandExecutorImpl(commands);
		// executes the command
		result = commandExecutor.executeCommand();
		// gets the output of the execution
		stdout = commandExecutor.getStandardOutput();
		stderr = commandExecutor.getStandardError();
		// Prints the output of the command for good or for bad.
		this.printThis(result, stdout, stderr);
	}

	@Override
	public void exportAsImage(Long suffix, String outputName) throws Exception{

		int result = 0;
		List<String> commands = null;
		StringBuilder stdout = null;
		StringBuilder stderr = null;
		commands = new ArrayList<String>();
		commands = new ArrayList<String>();

		// Arguments of the command
		commands.add(scriptHome+exportPNG);
		commands.add(suffix.toString());
		commands.add(outputName);

		logger.debug("Executing command: "+commands.toString());
		commandExecutor = new SystemCommandExecutorImpl(commands);
		// executes the command
		result = commandExecutor.executeCommand();
		// gets the output of the execution
		stdout = commandExecutor.getStandardOutput();
		stderr = commandExecutor.getStandardError();
		// Prints the output of the command for good or for bad.
		this.printThis(result, stdout, stderr);
	}

	@Override
	public void retrieveMinMaxValues(String layerName, Long suffix) throws Exception{
		int result = 0;
		List<String> commands = null;
		StringBuilder stdout = null;
		StringBuilder stderr = null;
		commands = new ArrayList<String>();

		// Arguments of the command
		commands.add(scriptHome+getMinMax);
		commands.add(layerName);
		commands.add(suffix.toString());

		logger.debug("Executing command: "+commands.toString());
		commandExecutor = new SystemCommandExecutorImpl(commands);
		// executes the command
		result = commandExecutor.executeCommand();
		// gets the output of the execution
		stdout = commandExecutor.getStandardOutput();
		stderr = commandExecutor.getStandardError();
		// Prints the output of the command for good or for bad.
		this.printThis(result, stdout, stderr);

	}

	@Override
	public List<CategoryDTO> retrieveCategories(String layerName, String layerType, Long suffix) throws Exception{

		int result = 0;
		List<String> commands = null;
		StringBuilder stdout = null;
		StringBuilder stderr = null;
		commands = new ArrayList<String>();


		List<CategoryDTO> intervals = new ArrayList<CategoryDTO>();
		String temp = null;
		String[] tarray = null;
		CategoryDTO category = null;
		List<String> values = null;


		// Arguments of the command
		commands.add(scriptHome+retrieveCategories);
		commands.add(layerName);
		commands.add(suffix.toString());
		commands.add(layerType);

		logger.debug("Executing command: "+commands.toString());
		commandExecutor = new SystemCommandExecutorImpl(commands);
		// executes the command
		result = commandExecutor.executeCommand();
		// gets the output of the execution
		stdout = commandExecutor.getStandardOutput();

		// Prints the output of the command for good or for bad.
		this.printThis(result, stdout, stderr);

		StringTokenizer st = new StringTokenizer(new String(stdout), "\n");

		while(st.hasMoreTokens()){

			temp = st.nextToken();
			tarray = temp.split(":");

			category = new CategoryDTO();
			category.setValue(tarray[0]);
			category.setDescription(tarray[1]);

			intervals.add(category);
		}

		stderr = commandExecutor.getStandardError();

		return intervals;
	}

	@Override
	public void executeReclassification(String layerName, Long suffix) throws Exception{

		int result = 0;
		List<String> commands = null;
		StringBuilder stdout = null;
		StringBuilder stderr = null;
		commands = new ArrayList<String>();

		// Arguments of the command
		commands.add(scriptHome+rasterReclasification);
		commands.add(layerName);
		commands.add(suffix.toString());

	stdout = commandExecutor.getStandardOutput();
		stderr = commandExecutor.getStandardError();
		logger.debug("Executing command: "+commands.toString());
		commandExecutor = new SystemCommandExecutorImpl(commands);
		// executes the command
		result = commandExecutor.executeCommand();
		// gets the output of the execution
		stdout = commandExecutor.getStandardOutput();
		stderr = commandExecutor.getStandardError();
		// Prints the output of the command for good or for bad.
		this.printThis(result, stdout, stderr);
	}

	@Override
	public void asingResolution(Double resolution, Long suffix) throws Exception{

		int result = 0;
		List<String> commands = null;
		StringBuilder stdout = null;
		StringBuilder stderr = null;
		commands = new ArrayList<String>();

		// Arguments of the command
		commands.add(scriptHome+asignResolution);
		commands.add(resolution.toString());
		commands.add(suffix.toString());


		logger.debug("Executing command: "+commands.toString());
		commandExecutor = new SystemCommandExecutorImpl(commands);
		// executes the command
		result = commandExecutor.executeCommand();
		// gets the output of the execution
		stdout = commandExecutor.getStandardOutput();
		stderr = commandExecutor.getStandardError();
		// Prints the output of the command for good or for bad.
		this.printThis(result, stdout, stderr);

	}

	@Override
	public HashMap<String, String> retrieveColumns(String layerName, Long suffix) throws Exception {
		int result = 0;
		List<String> commands = null;
		StringBuilder stdout = null;
		StringBuilder stderr = null;
		String temp = null;
		String[] data = null;

		commands = new ArrayList<String>();
		HashMap<String, String> columns;

		// Arguments of the command
		commands.add(scriptHome+retrieveColumns);
		commands.add(layerName);
		commands.add(suffix.toString());


		logger.debug("Executing command: "+commands.toString());
		commandExecutor = new SystemCommandExecutorImpl(commands);
		// executes the command
		result = commandExecutor.executeCommand();
		// gets the output of the execution
		stdout = commandExecutor.getStandardOutput();

		//parse the output
		columns = new HashMap<String, String>();

		StringTokenizer st = new StringTokenizer(stdout.toString(), "\n");

		while(st.hasMoreTokens()){
			temp = st.nextToken();

			data = temp.split(":");
			columns.put(data[0], data[1]);
		}

		stderr = commandExecutor.getStandardError();
		// Prints the output of the command for good or for bad.
		this.printThis(result, stdout, stderr);

		return columns;
	}

 	@Override
	public void executeVectorReclasification(String layerName, String column, Long suffix) throws Exception{

		int result = 0;
		List<String> commands = null;
		StringBuilder stdout = null;
		StringBuilder stderr = null;
		commands = new ArrayList<String>();

		// Arguments of the command
		commands.add(scriptHome+vectorialReclasification);
		commands.add(layerName);
		commands.add(column);
		commands.add(suffix.toString());

		logger.debug("Executing command: "+commands.toString());
		commandExecutor = new SystemCommandExecutorImpl(commands);
		// executes the command
		result = commandExecutor.executeCommand();
		// gets the output of the execution
		stdout = commandExecutor.getStandardOutput();
		stderr = commandExecutor.getStandardError();
		// Prints the output of the command for good or for bad.
		this.printThis(result, stdout, stderr);
	}



	@Override
	public void deleteGRASSLocation(Long suffix){ }

	private void printThis(int exitCode, StringBuilder stdout, StringBuilder stderr){
		System.out.println("Exit code: " + exitCode);
		System.out.println("stdout: ");
		System.out.println(stdout);
		System.out.println("stderr: ");
		System.out.println(stderr);
		System.out.println(" #-> ***************************************** <-#");
	}

	/* getters & setters */
	public String getAsignResolution() {
		return asignResolution;
	}

	public void setAsignResolution(String asignResolution) {
		this.asignResolution = asignResolution;
	}

	public String getConfiguration() {
		return configuration;
	}

	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}

	public String getDeleteGrassLocation() {
		return deleteGrassLocation;
	}

	public void setDeleteGrassLocation(String deleteGrassLocation) {
		this.deleteGrassLocation = deleteGrassLocation;
	}

	public String getExportPNG() {
		return exportPNG;
	}

	public void setExportPNG(String exportPNG) {
		this.exportPNG = exportPNG;
	}

	public String getExportSHP() {
		return exportSHP;
	}

	public void setExportSHP(String exportSHP) {
		this.exportSHP = exportSHP;
	}

	public String getGetMinMax() {
		return getMinMax;
	}

	public void setGetMinMax(String getMinMax) {
		this.getMinMax = getMinMax;
	}

	public String getImportPNG() {
		return importPNG;
	}

	public void setImportPNG(String importPNG) {
		this.importPNG = importPNG;
	}

	public String getLayerHome() {
		return layerHome;
	}

	public void setLayerHome(String layerHome) {
		this.layerHome = layerHome;
	}

	public String getMapAlgebra() {
		return mapAlgebra;
	}

	public void setMapAlgebra(String mapAlgebra) {
		this.mapAlgebra = mapAlgebra;
	}

	public String getRasterReclasification() {
		return rasterReclasification;
	}

	public void setRasterReclasification(String rasterReclasification) {
		this.rasterReclasification = rasterReclasification;
	}

	public String getRasterization() {
		return rasterization;
	}

	public void setRasterization(String rasterization) {
		this.rasterization = rasterization;
	}

	public String getRetrieveCategories() {
		return retrieveCategories;
	}

	public void setRetrieveCategories(String retrieveCategories) {
		this.retrieveCategories = retrieveCategories;
	}

	public String getScriptHome() {
		return scriptHome;
	}

	public void setScriptHome(String scriptHome) {
		this.scriptHome = scriptHome;
	}

	public String getVectorialReclasification() {
		return vectorialReclasification;
	}

	public void setVectorialReclasification(String vectorialReclasification) {
		this.vectorialReclasification = vectorialReclasification;
	}

	public String getRetrieveColumns() {
		return retrieveColumns;
	}

	public void setRetrieveColumns(String retrieveColumns) {
		this.retrieveColumns = retrieveColumns;
	}
}
