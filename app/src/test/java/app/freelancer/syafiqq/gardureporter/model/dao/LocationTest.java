package app.freelancer.syafiqq.gardureporter.model.dao;

import com.google.gson.Gson;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This <GarduReporter> project created by :
 * Name         : syafiq
 * Date / Time  : 04 June 2017, 4:22 PM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public class LocationTest
{
    private Location location;

    @Before
    public void setUp() throws Exception
    {
        this.location = new Location(-7.604019641876221, 112.78269309550524);
    }

    @After
    public void tearDown() throws Exception
    {
        this.location = null;
    }

    @Test
    public void getLatitude() throws Exception
    {
        Assert.assertEquals(-7.604019641876221, this.location.getLatitude(), 0);
    }

    @Test
    public void setLatitude() throws Exception
    {
        this.location.setLatitude(-7.604019641876221);
        this.getLatitude();
    }

    @Test
    public void getLongitude() throws Exception
    {
        Assert.assertEquals(112.78269309550524, this.location.getLongitude(), 0);
    }

    @Test
    public void setLongitude() throws Exception
    {
        this.location.setLongitude(112.78269309550524);
        this.getLongitude();
    }

    @Test
    public void toJSON()
    {
        final Gson gson = new Gson();
        Assert.assertEquals("{\"latitude\":-7.604019641876221,\"longitude\":112.78269309550524}", gson.toJson(this.location));
    }
}