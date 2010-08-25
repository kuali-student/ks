package org.apache.torque.mojo;

import static org.apache.commons.io.FileUtils.*;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SchemaMorpher extends Morpher {
	private static final Log log = LogFactory.getLog(SchemaMorpher.class);

	// Ant impex has kfs hard coded
	String defaultSchemaName = "kfs";
	// Token we can look for to help identify this schema XML file as an Impex file generated by the Ant process
	String defaultSchemaToken = "name=\"" + defaultSchemaName + "\"";
	// Ant impex is hard coded to database.dtd
	String defaultDTDString = "\"database.dtd\"";
	// The Kuali database.dtd
	String newDTDString = "\"http://www.kuali.org/dtd/database.dtd\"";
	// Ant impex comment
	String defaultComment = "<!-- Autogenerated by KualiTorqueJDBCTransformTask! -->";
	// Ant impex comment
	String newComment = "<!-- Autogenerated by the Maven Impex Plugin -->";

	String newSchemaName;

	public SchemaMorpher() {
		this(null, null);
	}

	public SchemaMorpher(MorphRequest morphRequest, String newSchemaName) {
		super();
		this.newSchemaName = newSchemaName;
		this.morphRequest = morphRequest;
	}

	/**
	 * Attempt to determine if this content is from an Ant Impex XML export
	 */
	protected boolean isAntImpexSchemaXML(String contents) {
		if (contents == null) {
			return false;
		}
		if (contents.indexOf(defaultSchemaToken) == -1) {
			return false;
		}
		if (contents.indexOf(defaultDTDString) == -1) {
			return false;
		}
		if (contents.indexOf(defaultComment) == -1) {
			return false;
		}
		// All 3 tokens we know about were present in the String
		// Pretty good chance it is content from an Ant Impex export
		return true;
	}

	/**
	 * Return true if we need to morph this file
	 */
	protected boolean isMorphNeeded(String content) {
		// Look for the DTD the Maven Impex Plugin uses
		int pos = content.indexOf(newDTDString);

		if (pos == -1) {
			// It isn't there so we should morph
			return true;
		} else {
			// It is already there, we are good to go
			return false;
		}
	}

	/**
	 * Morph an Ant Impex XML file into a Maven Impex Plugin XML file
	 */
	protected String morph(String contents, String schemaName) {
		contents = StringUtils.replace(contents, defaultDTDString, newDTDString);
		contents = StringUtils.replace(contents, defaultComment, newComment);
		return StringUtils.replace(contents, "name=\"" + defaultSchemaName + "\">", "name=\"" + schemaName + "\">");
	}

	@Override
	public void executeMorph(String encoding) throws IOException {
		log.info("hhhdalsdflasdjf;laskjdf;alsdj;f");
		log.info("hhhdalsdflasdjf;laskjdf;alsdj;f");
		log.info("hhhdalsdflasdjf;laskjdf;alsdj;f");
		log.info("hhhdalsdflasdjf;laskjdf;alsdj;f");
		log.info("hhhdalsdflasdjf;laskjdf;alsdj;f");
		log.info("hhhdalsdflasdjf;laskjdf;alsdj;f");
		// Read the "old" schema XML file into a string
		String contents = readFileToString(morphRequest.getOldFile(), encoding);

		// Check it to see if it was created by Ant
		if (!isAntImpexSchemaXML(contents)) {
			log.warn("Unable to determine if this is an export from Ant Impex");
		}

		// May not need to morph
		if (isMorphNeeded(contents)) {
			contents = morph(contents, newSchemaName);
		} else {
			log.info("No morphing needed");
		}

		// Write the schema to the file system
		writeStringToFile(morphRequest.getNewFile(), contents, encoding);
	}

	public String getDefaultSchemaName() {
		return defaultSchemaName;
	}

	public void setDefaultSchemaName(String defaultSchemaName) {
		this.defaultSchemaName = defaultSchemaName;
	}

	public String getDefaultSchemaToken() {
		return defaultSchemaToken;
	}

	public void setDefaultSchemaToken(String defaultSchemaToken) {
		this.defaultSchemaToken = defaultSchemaToken;
	}

	public String getDefaultDTDString() {
		return defaultDTDString;
	}

	public void setDefaultDTDString(String defaultDTDString) {
		this.defaultDTDString = defaultDTDString;
	}

	public String getNewDTDString() {
		return newDTDString;
	}

	public void setNewDTDString(String newDTDString) {
		this.newDTDString = newDTDString;
	}

	public String getDefaultComment() {
		return defaultComment;
	}

	public void setDefaultComment(String defaultComment) {
		this.defaultComment = defaultComment;
	}

	public String getNewComment() {
		return newComment;
	}

	public void setNewComment(String newComment) {
		this.newComment = newComment;
	}

	public String getNewSchemaName() {
		return newSchemaName;
	}

	public void setNewSchemaName(String newSchemaName) {
		this.newSchemaName = newSchemaName;
	}

}
