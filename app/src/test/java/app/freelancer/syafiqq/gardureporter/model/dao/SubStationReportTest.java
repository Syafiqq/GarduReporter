package app.freelancer.syafiqq.gardureporter.model.dao;

import com.google.gson.Gson;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This <GarduReporter> project created by :
 * Name         : syafiq
 * Date / Time  : 04 June 2017, 4:34 PM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public class SubStationReportTest
{
    private SubStationReport report;

    @Before
    public void setUp() throws Exception
    {
        this.report = new SubStationReport("GA", 0., 1., new Location(-7.604019641876221, 112.78269309550524));
    }

    @After
    public void tearDown() throws Exception
    {
        this.report = null;
    }

    @Test
    public void getSubstation() throws Exception
    {
        Assert.assertEquals("GA", this.report.getSubstation());
    }

    @Test
    public void setSubstation() throws Exception
    {
        this.report.setSubstation("GA");
        this.getSubstation();
    }

    @Test
    public void getVoltage() throws Exception
    {
        Assert.assertEquals(0., this.report.getVoltage(), 0);
    }

    @Test
    public void setVoltage() throws Exception
    {
        this.report.setVoltage(0.);
        this.getVoltage();
    }

    @Test
    public void getCurrent() throws Exception
    {
        Assert.assertEquals(1., this.report.getCurrent(), 0);
    }

    @Test
    public void setCurrent() throws Exception
    {
        this.report.setCurrent(1.);
        this.getCurrent();
    }

    @Test
    public void getLocation() throws Exception
    {
        if(this.report.getLocation() != null)
        {
            Assert.assertEquals(-7.604019641876221, this.report.getLocation().getLatitude(), 0);
            Assert.assertEquals(112.78269309550524, this.report.getLocation().getLongitude(), 0);
        }
        else
        {
            Assert.assertNull(this.report.getLocation());
        }
    }

    @Test
    public void setLocation() throws Exception
    {
        this.report.setLocation(new Location(-7.604019641876221, 112.78269309550524));
        this.getLocation();
    }

    @Test
    public void toJSON()
    {
        final Gson gson = new Gson();
        System.out.println(gson.toJson(this.report));
    }
}