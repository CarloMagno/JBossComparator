package jbosscomp.view.backing;


import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.faces.bi.component.graph.UIGraph;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.fragment.RichPageTemplate;
import oracle.adf.view.rich.component.rich.input.RichInputNumberSpinbox;
import oracle.adf.view.rich.component.rich.layout.RichPanelBorderLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelTabbed;
import oracle.adf.view.rich.component.rich.layout.RichShowDetailItem;
import oracle.adf.view.rich.component.rich.output.RichSeparator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Comparator {
    private RichForm f1;
    private RichDocument d1;
    private RichPanelTabbed pt2;
    private RichPageTemplate pt1;
    private RichShowDetailItem sdi1;
    private RichPanelGroupLayout pgl1;
    private RichPanelGroupLayout pgl3;
    private RichPanelBox pb7;
    private RichPanelBox pb8;
    private RichPanelBox pb1;
    private RichInputNumberSpinbox exProcY1;
    private RichInputNumberSpinbox newProcY1;
    private RichInputNumberSpinbox coresProcY1;
    private RichInputNumberSpinbox coreFactorY1;
    private RichInputNumberSpinbox totalProcY1;
    private RichInputNumberSpinbox licenseCostY1;
    private RichInputNumberSpinbox licenseDiscY1;
    private RichInputNumberSpinbox totalLicenseY1;
    private RichSeparator s1;
    private RichInputNumberSpinbox suppCostY1;
    private RichInputNumberSpinbox totalSuppY1;
    private RichSeparator s2;
    private RichInputNumberSpinbox totalCostY1;
    private UIGraph barGraph1;
    private RichPanelBorderLayout pbl1;

    private RichPanelBox pb4;
    private RichPanelGroupLayout pgl2;
    private RichPanelGroupLayout pgl4;
    private RichInputNumberSpinbox jbossCoresY1;
    private RichInputNumberSpinbox jbossManagementCostY1;
    private RichSeparator s4;
    private RichInputNumberSpinbox jbTotalCostY1;
    private RichInputNumberSpinbox managementCostY1;
    private RichSeparator s5;

    private static BigDecimal _totalCostY1;
    private static BigDecimal _totalSuppCostY1;
    private static BigDecimal _managementCostY1;
    private static BigDecimal _licenseCostY1;

    private static BigDecimal _jb_totalCostY1;
    private static BigDecimal _jb_totalSuppCostY1;
    private static BigDecimal _jb_managementCostY1;

    private UIGraph lineGraph1;
    private UIGraph barGraph3;
    private UIGraph barGraph2;
    private RichPanelGroupLayout pgl5;
    private RichPanelGroupLayout pgl6;
    private RichInputNumberSpinbox jbossSubscriptionCostY1;

    private static String[] subscriptionCosts = new String[] {"0", "24000", "48000", "72000", 
                                                        "90000",  "114000", "138000", "162000",
                                                        "180000", "204000", "228000", "252000",
                                                        "270000", "294000", "318000", "342000",
                                                        "360000", "384000", "408000", "432000",
                                                        "450000", "474000", "498000", "522000",
                                                        "540000", "564000", "588000", "612000",
                                                        "630000", "654000", "678000", "702000",
                                                        "720000", "744000", "768000", "792000",
                                                        "810000", "834000", "858000", "882000",
                                                        "900000", "924000", "948000", "972000",
                                                        "990000", "1014000","1038000","1062000",
                                                        "1080000","1104000","1128000","1152000",
                                                        "1170000","1194000","1218000","1242000",
                                                        "1260000","1284000","1308000","1332000",
                                                        "1350000","1374000","1398000","1422000",
                                                        "1440000","1464000"};

    public static Object get(String expr) {
      FacesContext ctx = FacesContext.getCurrentInstance();
      return ctx.getApplication().evaluateExpressionGet(ctx, expr,Object.class);
    }

    public BigDecimal getSubscriptionCost(){
        RichInputNumberSpinbox rinsTotalJBossCores = (RichInputNumberSpinbox)get("#{backingBeanScope.backing_comparator.jbossCoresY1}");
        BigDecimal totalCores = new BigDecimal(rinsTotalJBossCores.getValue().toString());
        Double index = totalCores.doubleValue() / 16.0;
        index = Math.ceil(index);
        int i = index.intValue();
        if(i >= subscriptionCosts.length) i = 0;
        return new BigDecimal(subscriptionCosts[i]);
    }

    public List getTabularData() {       
        RichInputNumberSpinbox rinsTotalCostY1 = (RichInputNumberSpinbox)get("#{backingBeanScope.backing_comparator.totalCostY1}");
        RichInputNumberSpinbox rinsSuppTotalCostY1 = (RichInputNumberSpinbox)get("#{backingBeanScope.backing_comparator.totalSuppY1}");
        RichInputNumberSpinbox rinsManagementCostY1 = (RichInputNumberSpinbox)get("#{backingBeanScope.backing_comparator.managementCostY1}");
        
        RichInputNumberSpinbox jbRinsTotalCostY1 = (RichInputNumberSpinbox)get("#{backingBeanScope.backing_comparator.jbTotalCostY1}");
        RichInputNumberSpinbox jbRinsSuppTotalCostY1 = (RichInputNumberSpinbox)get("#{backingBeanScope.backing_comparator.jbossSubscriptionCostY1}");
        RichInputNumberSpinbox jbRinsManagementCostY1 = (RichInputNumberSpinbox)get("#{backingBeanScope.backing_comparator.jbossManagementCostY1}");
        
        _totalSuppCostY1 = new BigDecimal(rinsSuppTotalCostY1.getValue().toString());
        _totalCostY1 = new BigDecimal(rinsTotalCostY1.getValue().toString());
        _managementCostY1 = new BigDecimal(rinsManagementCostY1.getValue().toString());
        
        _jb_totalCostY1 = new BigDecimal(jbRinsTotalCostY1.getValue().toString());
        _jb_totalSuppCostY1 = new BigDecimal(jbRinsSuppTotalCostY1.getValue().toString());
        _jb_managementCostY1 = new BigDecimal(jbRinsManagementCostY1.getValue().toString());               
        
        BigDecimal oracleRestYears = _totalSuppCostY1.add(_managementCostY1);
        BigDecimal jbossRestYears = _jb_totalSuppCostY1.add(_jb_managementCostY1);
        
        ArrayList list = new ArrayList();
        String[] rowLabels  = new String[] {"Oracle", "JBoss"};
        String[] colLabels  = new String[] {"Year 1", "Year 2", "Year 3", "Year 4", "Year 5"};
        BigDecimal [] [] values = new BigDecimal[][]{
            {_totalCostY1, oracleRestYears, oracleRestYears, oracleRestYears, oracleRestYears},
            {_jb_totalCostY1, jbossRestYears, jbossRestYears, jbossRestYears, jbossRestYears}
            };
        for (int c = 0; c < colLabels.length; c++)
        {
         for (int r = 0; r < rowLabels.length; r++)
           {
            list.add (new Object [] {colLabels[c], rowLabels[r], 
                new Double (values[r][c].doubleValue())});
           }
        }
        return list;
    }

    public List getJBossTabularData() { 

        RichInputNumberSpinbox jbRinsSuppTotalCostY1 = (RichInputNumberSpinbox)get("#{backingBeanScope.backing_comparator.jbossSubscriptionCostY1}");
        RichInputNumberSpinbox jbRinsManagementCostY1 = (RichInputNumberSpinbox)get("#{backingBeanScope.backing_comparator.jbossManagementCostY1}");
        
        _jb_totalSuppCostY1 = new BigDecimal(jbRinsSuppTotalCostY1.getValue().toString());
        _jb_managementCostY1 = new BigDecimal(jbRinsManagementCostY1.getValue().toString());
        
        ArrayList list = new ArrayList();
        String[] rowLabels  = new String[] {"Subscription", "Management"};
        String[] colLabels  = new String[] {"Year 1", "Year 2", "Year 3", "Year 4", "Year 5"};
        
        BigDecimal [][] values = new BigDecimal[][]{
            {_jb_totalSuppCostY1, _jb_totalSuppCostY1, _jb_totalSuppCostY1, _jb_totalSuppCostY1, _jb_totalSuppCostY1},
            {_jb_managementCostY1, _jb_managementCostY1, _jb_managementCostY1, _jb_managementCostY1, _jb_managementCostY1},
            };

        for (int c = 0; c < colLabels.length; c++)
        {
         for (int r = 0; r < rowLabels.length; r++)
           {                         /* Year */    /* Concepto */
            list.add (new Object [] {colLabels[c], rowLabels[r], 
                new Double (values[r][c].doubleValue())});
           }
        }
        return list;
    }
    
    public List getOracleTabularData() { 
        
        RichInputNumberSpinbox rinsLicenseCostY1 = (RichInputNumberSpinbox)get("#{backingBeanScope.backing_comparator.totalLicenseY1}");
        RichInputNumberSpinbox rinsSuppTotalCostY1 = (RichInputNumberSpinbox)get("#{backingBeanScope.backing_comparator.totalSuppY1}");
        RichInputNumberSpinbox rinsManagementCostY1 = (RichInputNumberSpinbox)get("#{backingBeanScope.backing_comparator.managementCostY1}");
                
        _totalSuppCostY1 = new BigDecimal(rinsSuppTotalCostY1.getValue().toString());
        _licenseCostY1 = new BigDecimal(rinsLicenseCostY1.getValue().toString());
        _managementCostY1 = new BigDecimal(rinsManagementCostY1.getValue().toString());
        
        ArrayList list = new ArrayList();
        String[] rowLabels  = new String[] {"License", "Support", "Management"};
        String[] colLabels  = new String[] {"Year 1", "Year 2", "Year 3", "Year 4", "Year 5"};
        
        BigDecimal [][] values = new BigDecimal[][]{
            {_licenseCostY1, new BigDecimal(0.0), new BigDecimal(0.0), new BigDecimal(0.0), new BigDecimal(0.0)},
            {_totalSuppCostY1, _totalSuppCostY1, _totalSuppCostY1, _totalSuppCostY1, _totalSuppCostY1},
            {_managementCostY1, _managementCostY1, _managementCostY1, _managementCostY1, _managementCostY1}
            };

        for (int c = 0; c < colLabels.length; c++)
        {
         for (int r = 0; r < rowLabels.length; r++)
           {                         /* Year */    /* Concepto */
            list.add (new Object [] {colLabels[c], rowLabels[r], 
                new Double (values[r][c].doubleValue())});
           }
        }
        return list;
    }
    
    
    public void setF1(RichForm f1) {
        this.f1 = f1;
    }

    public RichForm getF1() {
        return f1;
    }

    public void setD1(RichDocument d1) {
        this.d1 = d1;
    }

    public RichDocument getD1() {
        return d1;
    }


    public void setPt2(RichPanelTabbed pt2) {
        this.pt2 = pt2;
    }

    public RichPanelTabbed getPt2() {
        return pt2;
    }

    public void setSdi1(RichShowDetailItem sdi1) {
        this.sdi1 = sdi1;
    }

    public RichShowDetailItem getSdi1() {
        return sdi1;
    }


    public void setPb1(RichPanelBox pb1) {
        this.pb1 = pb1;
    }

    public RichPanelBox getPb1() {
        return pb1;
    }

    public void setExProcY1(RichInputNumberSpinbox ins1) {
        this.exProcY1 = ins1;
    }

    public RichInputNumberSpinbox getExProcY1() {
        return exProcY1;
    }

    public void setNewProcY1(RichInputNumberSpinbox ins2) {
        this.newProcY1 = ins2;
    }

    public RichInputNumberSpinbox getNewProcY1() {
        return newProcY1;
    }

    public void setS1(RichSeparator s1) {
        this.s1 = s1;
    }

    public RichSeparator getS1() {
        return s1;
    }


    public void setS2(RichSeparator s2) {
        this.s2 = s2;
    }

    public RichSeparator getS2() {
        return s2;
    }

    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
    }


    public void setCoresProcY1(RichInputNumberSpinbox coresProcY1) {
        this.coresProcY1 = coresProcY1;
    }

    public RichInputNumberSpinbox getCoresProcY1() {
        return coresProcY1;
    }

    public void setCoreFactorY1(RichInputNumberSpinbox coreFactorY1) {
        this.coreFactorY1 = coreFactorY1;
    }

    public RichInputNumberSpinbox getCoreFactorY1() {
        return coreFactorY1;
    }

    public void setTotalProcY1(RichInputNumberSpinbox totalProcY1) {
        this.totalProcY1 = totalProcY1;
    }

    public RichInputNumberSpinbox getTotalProcY1() {
        return totalProcY1;
    }

    public void setLicenseCostY1(RichInputNumberSpinbox licenseCostY1) {
        this.licenseCostY1 = licenseCostY1;
    }

    public RichInputNumberSpinbox getLicenseCostY1() {
        return licenseCostY1;
    }

    public void setLicenseDiscY1(RichInputNumberSpinbox licenseDiscY1) {
        this.licenseDiscY1 = licenseDiscY1;
    }

    public RichInputNumberSpinbox getLicenseDiscY1() {
        return licenseDiscY1;
    }

    public void setTotalLicenseY1(RichInputNumberSpinbox totalLicenseY1) {
        this.totalLicenseY1 = totalLicenseY1;
    }

    public RichInputNumberSpinbox getTotalLicenseY1() {
        return totalLicenseY1;
    }

    public void setSuppCostY1(RichInputNumberSpinbox suppCostY1) {
        this.suppCostY1 = suppCostY1;
    }

    public RichInputNumberSpinbox getSuppCostY1() {
        return suppCostY1;
    }

    public void setTotalSuppY1(RichInputNumberSpinbox totalSuppY1) {
        this.totalSuppY1 = totalSuppY1;
    }

    public RichInputNumberSpinbox getTotalSuppY1() {
        return totalSuppY1;
    }

    public void setTotalCostY1(RichInputNumberSpinbox totalCostY1) {
        this.totalCostY1 = totalCostY1;
    }

    public RichInputNumberSpinbox getTotalCostY1() {
        return totalCostY1;
    }


    public void setPgl3(RichPanelGroupLayout pgl3) {
        this.pgl3 = pgl3;
    }

    public RichPanelGroupLayout getPgl3() {
        return pgl3;
    }

    public void setPt1(RichPageTemplate pt1) {
        this.pt1 = pt1;
    }

    public RichPageTemplate getPt1() {
        return pt1;
    }


    public void setBarGraph1(UIGraph barGraph1) {
        this.barGraph1 = barGraph1;
    }

    public UIGraph getBarGraph1() {
        return barGraph1;
    }

    public void setPbl1(RichPanelBorderLayout pbl1) {
        this.pbl1 = pbl1;
    }

    public RichPanelBorderLayout getPbl1() {
        return pbl1;
    }

    public void setPb4(RichPanelBox pb4) {
        this.pb4 = pb4;
    }

    public RichPanelBox getPb4() {
        return pb4;
    }

    public void setPgl2(RichPanelGroupLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGroupLayout getPgl2() {
        return pgl2;
    }

    public void setPgl4(RichPanelGroupLayout pgl4) {
        this.pgl4 = pgl4;
    }

    public RichPanelGroupLayout getPgl4() {
        return pgl4;
    }

    public void setJbossCoresY1(RichInputNumberSpinbox ins1) {
        this.jbossCoresY1 = ins1;
    }

    public RichInputNumberSpinbox getJbossCoresY1() {
        return jbossCoresY1;
    }

    public void setJbossManagementCostY1(RichInputNumberSpinbox ins1) {
        this.jbossManagementCostY1 = ins1;
    }

    public RichInputNumberSpinbox getJbossManagementCostY1() {
        return jbossManagementCostY1;
    }

    public void setS4(RichSeparator s4) {
        this.s4 = s4;
    }

    public RichSeparator getS4() {
        return s4;
    }

    public void setJbTotalCostY1(RichInputNumberSpinbox ins2) {
        this.jbTotalCostY1 = ins2;
    }

    public RichInputNumberSpinbox getJbTotalCostY1() {
        return jbTotalCostY1;
    }

    public void setManagementCostY1(RichInputNumberSpinbox ins1) {
        this.managementCostY1 = ins1;
    }

    public RichInputNumberSpinbox getManagementCostY1() {
        return managementCostY1;
    }

    public void setS5(RichSeparator s5) {
        this.s5 = s5;
    }

    public RichSeparator getS5() {
        return s5;
    }


    public void setPb7(RichPanelBox pb7) {
        this.pb7 = pb7;
    }

    public RichPanelBox getPb7() {
        return pb7;
    }

    public void setPb8(RichPanelBox pb8) {
        this.pb8 = pb8;
    }

    public RichPanelBox getPb8() {
        return pb8;
    }


    public void setLineGraph1(UIGraph lineGraph1) {
        this.lineGraph1 = lineGraph1;
    }

    public UIGraph getLineGraph1() {
        return lineGraph1;
    }


    public void setBarGraph3(UIGraph barGraph3) {
        this.barGraph3 = barGraph3;
    }

    public UIGraph getBarGraph3() {
        return barGraph3;
    }

    public void setBarGraph2(UIGraph barGraph2) {
        this.barGraph2 = barGraph2;
    }

    public UIGraph getBarGraph2() {
        return barGraph2;
    }

    public void setPgl5(RichPanelGroupLayout pgl5) {
        this.pgl5 = pgl5;
    }

    public RichPanelGroupLayout getPgl5() {
        return pgl5;
    }

    public void setPgl6(RichPanelGroupLayout pgl6) {
        this.pgl6 = pgl6;
    }

    public RichPanelGroupLayout getPgl6() {
        return pgl6;
    }

    public void setJbossSubscriptionCostY1(RichInputNumberSpinbox ins1) {
        this.jbossSubscriptionCostY1 = ins1;
    }

    public RichInputNumberSpinbox getJbossSubscriptionCostY1() {
        return jbossSubscriptionCostY1;
    }
}
