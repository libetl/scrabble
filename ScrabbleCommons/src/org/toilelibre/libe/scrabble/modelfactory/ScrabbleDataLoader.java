package org.toilelibre.libe.scrabble.modelfactory;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import org.apache.log4j.Logger;
import org.toilelibre.libe.scrabble.model.ModelElement;
import org.toilelibre.libe.scrabble.model.ScrabbleData;

public final class ScrabbleDataLoader {
	private static final Logger	LOG	= Logger.getLogger (ScrabbleDataLoader.class);

	private List<String>	    locations;
	private List<BuildProcess>	processes;

	private List<String>	    properties;

	private ScrabbleDataLoader () {

	}

	private void build (final String location, final BuildProcess bp,
	        final List<ModelElement> list) {
		try {
			final List<ModelElement> lme = list;
			final ClassLoader cld = Thread.currentThread ()
			        .getContextClassLoader ();
			final URL url = cld.getResource (location);
			final File folderFile = new File (url.toURI ());
			if (folderFile.isDirectory ()) {
				for (final String sf : folderFile.list ()) {
					if (sf.endsWith (".dtd")) {
						continue;
					}
					bp.setFile (location + sf);
					final ModelElement me = bp.process ();
					if (me != null) {
						lme.add (me);
					}
				}
			}
		} catch (final SecurityException e) {
			ScrabbleDataLoader.LOG.error (e.getMessage ());
		} catch (final IllegalArgumentException e) {
			ScrabbleDataLoader.LOG.error (e.getMessage ());
		} catch (final URISyntaxException e) {
			ScrabbleDataLoader.LOG.error (e.getMessage ());
		}
	}

	public List<String> getLocations () {
		return this.locations;
	}

	public List<BuildProcess> getProcesses () {
		return this.processes;
	}

	public List<String> getProperties () {
		return this.properties;
	}

	@SuppressWarnings ("unchecked")
	public ScrabbleData load (final ScrabbleData sd) {
		for (int i = 0; i < this.processes.size (); i += 1) {
			this.build (
			        this.locations.get (i),
			        this.processes.get (i),
			        (List<ModelElement>) FieldAccessor.get (sd,
			                this.properties.get (i)));
		}
		return sd;
	}

	public void setLocations (final List<String> loc) {
		this.locations = loc;
	}

	public void setProcesses (final List<BuildProcess> p) {
		this.processes = p;
	}

	public void setProperties (final List<String> p) {
		this.properties = p;
	}
}
