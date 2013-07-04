package org.toilelibre.libe.scrabble.s3d.model;

public abstract class AbstractPoint3D implements IPoint3D {

	private double	x;
	private double	y;
	private double	z;

	public AbstractPoint3D () {
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}

	public AbstractPoint3D (final double xa, final double ya, final double za) {
		this.x = xa;
		this.y = ya;
		this.z = za;
	}

	public abstract Object getObj ();

	public final double getX () {
		return this.x;
	}

	public final double getY () {
		return this.y;
	}

	public final double getZ () {
		return this.z;
	}

	/**
	 * @param x1
	 *            the x to set
	 */
	protected final void setX (final double x1) {
		this.x = x1;
	}

	/**
	 * @param y1
	 *            the y to set
	 */
	protected final void setY (final double y1) {
		this.y = y1;
	}

	/**
	 * @param z1
	 *            the z to set
	 */
	protected final void setZ (final double z1) {
		this.z = z1;
	}

	@Override
	public final String toString () {
		final String comma = ", ";
		return "{" + this.x + comma + this.y + comma + this.z + "}";
	}
}
