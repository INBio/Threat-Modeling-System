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
import org.inbio.modeling.core.layer.LayerType;
import org.inbio.system.command.impl.SystemCommandExecutorImpl;

public class GrassDAOImpl extends BaseDAOImpl implements GrassDAO {

	/** Command Executor Instance. */
	SystemCommandExecutorImpl commandExecutor = null;

	/** Scripts (dependencies injected) */
	private String scriptHome;
	private String layerHome;
	private String configuration;
	private String exportPNG;
	private String exportSHP;
	private String rasterization;
	private String mapAlgebra;
	private String getMinMax;
	private String vectorialReclasification;
	private String retrieveCategories;
	private String retrieveColumns;
	private String rasterReclasification;
	private String asignResolution;
	private String deleteGrassLocation;
	private String importSHP;
	private String importWFS;
	private String retrieveType;
	private String asingBuffers;
	private String rename;
	private String setColorScale;


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
	public void importLayer(String shortOutputName, String uri, Long suffix) throws Exception {
		int result = 0;
		List<String> commands = null;
		StringBuilder stdout = null;
		StringBuilder stderr = null;
		commands = new ArrayList<String>();
		String importationScript = null;

		String[] uriComponents = null;
		String fullUri = null;
		uriComponents = uri.split(":");

		if(uriComponents[0].equals("http")){
			importationScript = scriptHome + importWFS;
			fullUri = uri;
		}else{ // uriComponents[0].equals("file:")
			importationScript = scriptHome + importSHP;
			fullUri = layerHome+uriComponents[1]+".shp";
		}

		// Arguments of the command
		commands.add(importationScript);
		commands.add(fullUri);
		commands.add(shortOutputName);
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
	public void rename(String layerName, Long suffix) throws Exception {
		int result = 0;
		List<String> commands = null;
		StringBuilder stdout = null;
		StringBuilder stderr = null;
		commands = new ArrayList<String>();

		// Arguments of the command
		commands.add(scriptHome+rename);
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
	public void executeRasterization(String layerName
									, Long suffix
									, String column)
									throws Exception{

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
	public void executeWeightedSum(String layerName1
									, Double weight1
									, String layerName2
									, Double weight2
									, Long suffix
									, String outputName)
									throws Exception{
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
	public String retrieveLayerType(String layerName, Long suffix)
		throws Exception{

		int result = 0;
		LayerType mapType = null;
		List<String> commands = null;
		StringBuilder stdout = null;
		StringBuilder stderr = null;
		commands = new ArrayList<String>();

		// Arguments of the command
		commands.add(scriptHome+retrieveType);
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
		stdout.deleteCharAt(stdout.length()-1);


		return stdout.toString();
	}

	@Override
	public String retrieveMinMaxValues(String layerName, Long suffix)
		throws Exception{

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

		return stdout.toString();

	}

	@Override
	public List<String> retrieveCategories(String layerName
											, String layerType
											, Long suffix)
											throws Exception{

		int result = 0;
		String temp = null;
		List<String> values = null;
		StringBuilder stdout = null;
		StringBuilder stderr = null;
		List<String> commands = null;
		commands = new ArrayList<String>();

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
		stderr = commandExecutor.getStandardError();
		stdout = commandExecutor.getStandardOutput();

		// Prints the output of the command for good or for bad.
		this.printThis(result, stdout, stderr);

		//Takes the output string and split it in a interval/category by Line
		StringTokenizer st = new StringTokenizer(new String(stdout), "\n");
		values = new ArrayList<String>();
		while(st.hasMoreTokens()){
			temp = st.nextToken();
			values.add(temp);
		}

		return values;
	}

	@Override
	public void executeReclassification(String layerName, Long suffix)
		throws Exception{

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
	public void asingResolution(Double resolution, Long suffix)
		throws Exception{

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
	public void asingBuffers(String layerName, String distances, int magicNumber, boolean reverted, Long suffix)
		throws Exception{

		int result = 0;
		List<String> commands = null;
		StringBuilder stdout = null;
		StringBuilder stderr = null;
		commands = new ArrayList<String>();

		// Arguments of the command
		commands.add(scriptHome+asingBuffers);
		commands.add(layerName);
		commands.add(distances);
		commands.add(magicNumber+"");
		commands.add(reverted+"");
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
	public List<String> retrieveColumns(String layerName, Long suffix)
		throws Exception {

		int result = 0;
		List<String> commands = null;
		StringBuilder stdout = null;
		StringBuilder stderr = null;
		List<String> values  = null;
		String temp = null;

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
		// Standar error
		stderr = commandExecutor.getStandardError();
		// gets the output of the execution
		stdout = commandExecutor.getStandardOutput();

		//parse the output
		values = new ArrayList<String>();

		StringTokenizer st = new StringTokenizer(stdout.toString(), "\n");

		while(st.hasMoreTokens()){
			temp = st.nextToken();
			values.add(temp);
		}

		// Prints the output of the command for good or for bad.
		this.printThis(result, stdout, stderr);

		return values;
	}

 	@Override
	public void executeVectorReclasification(String layerName
											, String column
											, Long suffix)
											throws Exception{

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
	/**
	 * @see org.inbio.modeling.core.dao.grassDAO#asingColorScale(String layerName , Long suffix)
	 */
	public void asingColorScale(String layerName , Long suffix) throws Exception{

		int result = 0;
		List<String> commands = null;
		StringBuilder stdout = null;
		StringBuilder stderr = null;
		commands = new ArrayList<String>();

		// Arguments of the command
		commands.add(scriptHome+setColorScale);
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
	public void deleteGRASSLocation(Long suffix){ }

	private void printThis(int exitCode
							, StringBuilder stdout
							, StringBuilder stderr){

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

	public String getRetrieveType() {
		return retrieveType;
	}

	public void setRetrieveType(String retrieveType) {
		this.retrieveType = retrieveType;
	}

	public String getAsingBuffers() {
		return asingBuffers;
	}

	public void setAsingBuffers(String asingBuffers) {
		this.asingBuffers = asingBuffers;
	}

	public String getRename() {
		return rename;
	}

	public void setRename(String rename) {
		this.rename = rename;
	}

	public String getSetColorScale() {
		return setColorScale;
	}

	public void setSetColorScale(String setColorScale) {
		this.setColorScale = setColorScale;
	}

	public String getGetMinMax() {
		return getMinMax;
	}

	public void setGetMinMax(String getMinMax) {
		this.getMinMax = getMinMax;
	}

	public String getImportSHP() {
		return importSHP;
	}

	public void setImportSHP(String importSHP) {
		this.importSHP = importSHP;
	}

	public String getImportWFS() {
		return importWFS;
	}

	public void setImportWFS(String importWFS) {
		this.importWFS = importWFS;
	}

	public String getLayerHome() {
		return layerHome;
	}

	public void setLayerHome(String layerHome) {
		this.layerHome = layerHome;
	}

	public String getExportSHP() {
		return exportSHP;
	}

	public void setExportSHP(String exportSHP) {
		this.exportSHP = exportSHP;
	}
}