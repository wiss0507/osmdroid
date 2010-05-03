package org.andnav.osm.views.util.android;

import org.andnav.osm.util.GeoPoint;

import android.graphics.Point;

public class Mercator extends org.andnav.osm.views.util.Mercator {
	
	/**
	 * Mercator projection of GeoPoint at given zoom level 
	 * @param aGeoPoint
	 * @param zoom zoom level
	 * @param aUseAsReturnValue
	 * @return Point with x,y in the range [-2^(zoom-1) to 2^(zoom-1)]
	 */
	public static Point projectGeoPoint(final GeoPoint aGeoPoint, final int aZoom, final Point aUseAsReturnValue) {
		final Point p = (aUseAsReturnValue != null) ? aUseAsReturnValue : new Point();
		
		final double aLon = aGeoPoint.getLongitudeE6()*1E-6;
		final double aLat = aGeoPoint.getLatitudeE6()*1E-6; 
		p.x = (int) Math.floor((aLon + 180) / 360 * (1 << aZoom));
		p.y = (int) Math.floor((1 - Math.log(Math.tan(aLat * DEG2RAD) + 1 / Math.cos(aLat * DEG2RAD)) / Math.PI) / 2 * (1 << aZoom));
		
		return p;
	}
	
}
