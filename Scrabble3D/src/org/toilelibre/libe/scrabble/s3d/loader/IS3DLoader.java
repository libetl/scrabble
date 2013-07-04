/**
 * 
 */
package org.toilelibre.libe.scrabble.s3d.loader;

/**
 * @author lionel
 * 
 */
public interface IS3DLoader {
	Object init (final String clazzName, final String fichier3D)
	        throws S3DLoaderException;
}
