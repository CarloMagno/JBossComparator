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
import oracle.adf.view.rich.component.rich.output.RichActiveOutputText;
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
    private RichPanelBox pb2;
    private RichInputNumberSpinbox exProcY2;
    private RichInputNumberSpinbox newProcY2;
    private RichInputNumberSpinbox coresProcY2;
    private RichInputNumberSpinbox coreFactorY2;
    private RichInputNumberSpinbox totalProcY2;
    private RichInputNumberSpinbox licenseCostY2;
    private RichInputNumberSpinbox licenseDiscY2;
    private RichInputNumberSpinbox totalLicenseY2;
    private RichSeparator s3;
    private RichInputNumberSpinbox suppCostY2;
    private RichInputNumberSpinbox totalSuppY2;
    private RichSeparator s10;
    private RichInputNumberSpinbox totalCostY2;
    private RichPanelBox pb3;
    private RichInputNumberSpinbox exProcY3;
    private RichInputNumberSpinbox newProcY3;
    private RichInputNumberSpinbox coresProcY3;
    private RichInputNumberSpinbox coreFactorY3;
    private RichInputNumberSpinbox totalProcY3;
    private RichInputNumberSpinbox licenseCostY3;
    private RichInputNumberSpinbox licenseDiscY3;
    private RichInputNumberSpinbox totalLicenseY3;
    private RichSeparator s11;
    private RichInputNumberSpinbox suppCostY3;
    private RichInputNumberSpinbox totalSuppY3;
    private RichSeparator s12;
    private RichInputNumberSpinbox totalCostY3;
    private UIGraph barGraph1;
    private RichPanelBorderLayout pbl1;

    private static BigDecimal costY1;
    private static BigDecimal costY2;
    private static BigDecimal costY3;
    private RichActiveOutputText aot1;

    public static Object get(String expr) {
      FacesContext ctx = FacesContext.getCurrentInstance();
      return ctx.getApplication().evaluateExpressionGet(ctx, expr,Object.class);
    }

    public List getTabularData() {       
        /*
        if(totalCostY1 == null)costY1 = new Double((String)totalCostY1.getValue());
        else costY1++;

        if(totalCostY2 == null)costY2 = new Double((String)totalCostY2.getValue());
        else costY2 = 0.0;
        
        if(totalCostY3 == null)costY3 = new Double((String)totalCostY3.getValue());
        else costY3 = 0.0;
        */
        
        /*
        DCBindingContainer dcBindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry(); 
        DCIteratorBinding iterator = (DCIteratorBinding)dcBindings.get("Comparator"); 
        String attribute = (String) iterator.getCurrentRow().getAttribute("SomeAttributeName");
        */
        
        RichInputNumberSpinbox rins1 = (RichInputNumberSpinbox)get("#{backingBeanScope.backing_comparator.totalCostY1}");
        RichInputNumberSpinbox rins2 = (RichInputNumberSpinbox)get("#{backingBeanScope.backing_comparator.totalCostY2}");
        RichInputNumberSpinbox rins3 = (RichInputNumberSpinbox)get("#{backingBeanScope.backing_comparator.totalCostY3}");
        
        System.out.println("rins1 = "+rins1.toString());
        System.out.println("rins2 = "+rins2.toString());
        System.out.println("rins3 = "+rins3.toString());
        
        costY1 = new BigDecimal (rins1.getValue().toString());
        costY2 = new BigDecimal (rins2.getValue().toString());
        costY3 = new BigDecimal (rins3.getValue().toString());
    
        System.out.println("costY1 = "+costY1);
        System.out.println("costY2 = "+costY2);
        System.out.println("costY3 = "+costY3);
        
        
        ArrayList list = new ArrayList();
        String[] rowLabels  = new String[] {"Oracle", "JBoss"};
        String[] colLabels  = new String[] {"Year 1", "Year 2", "Year 3"};
        BigDecimal [] [] values = new BigDecimal[][]{
            {costY1, costY2, costY3},
            {new BigDecimal(1.0), new BigDecimal(2.0), new BigDecimal(3.0)}
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

    public void setPb2(RichPanelBox pb2) {
        this.pb2 = pb2;
    }

    public RichPanelBox getPb2() {
        return pb2;
    }

    public void setPb3(RichPanelBox pb3) {
        this.pb3 = pb3;
    }

    public RichPanelBox getPb3() {
        return pb3;
    }

    public void setS3(RichSeparator s3) {
        this.s3 = s3;
    }

    public RichSeparator getS3() {
        return s3;
    }

    public void setS10(RichSeparator s10) {
        this.s10 = s10;
    }

    public RichSeparator getS10() {
        return s10;
    }

    public void setS11(RichSeparator s11) {
        this.s11 = s11;
    }

    public RichSeparator getS11() {
        return s11;
    }

    public void setS12(RichSeparator s12) {
        this.s12 = s12;
    }

    public RichSeparator getS12() {
        return s12;
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

    public void setExProcY2(RichInputNumberSpinbox exProcY2) {
        this.exProcY2 = exProcY2;
    }

    public RichInputNumberSpinbox getExProcY2() {
        return exProcY2;
    }

    public void setNewProcY2(RichInputNumberSpinbox newProcY2) {
        this.newProcY2 = newProcY2;
    }

    public RichInputNumberSpinbox getNewProcY2() {
        return newProcY2;
    }

    public void setCoresProcY2(RichInputNumberSpinbox coresProcY2) {
        this.coresProcY2 = coresProcY2;
    }

    public RichInputNumberSpinbox getCoresProcY2() {
        return coresProcY2;
    }

    public void setCoreFactorY2(RichInputNumberSpinbox coreFactorY2) {
        this.coreFactorY2 = coreFactorY2;
    }

    public RichInputNumberSpinbox getCoreFactorY2() {
        return coreFactorY2;
    }

    public void setTotalProcY2(RichInputNumberSpinbox totalProcY2) {
        this.totalProcY2 = totalProcY2;
    }

    public RichInputNumberSpinbox getTotalProcY2() {
        return totalProcY2;
    }

    public void setLicenseCostY2(RichInputNumberSpinbox licenseCostY2) {
        this.licenseCostY2 = licenseCostY2;
    }

    public RichInputNumberSpinbox getLicenseCostY2() {
        return licenseCostY2;
    }

    public void setLicenseDiscY2(RichInputNumberSpinbox licenseDiscY2) {
        this.licenseDiscY2 = licenseDiscY2;
    }

    public RichInputNumberSpinbox getLicenseDiscY2() {
        return licenseDiscY2;
    }

    public void setTotalLicenseY2(RichInputNumberSpinbox totalLicenseY2) {
        this.totalLicenseY2 = totalLicenseY2;
    }

    public RichInputNumberSpinbox getTotalLicenseY2() {
        return totalLicenseY2;
    }

    public void setSuppCostY2(RichInputNumberSpinbox suppCostY2) {
        this.suppCostY2 = suppCostY2;
    }

    public RichInputNumberSpinbox getSuppCostY2() {
        return suppCostY2;
    }

    public void setTotalSuppY2(RichInputNumberSpinbox totalSuppY2) {
        this.totalSuppY2 = totalSuppY2;
    }

    public RichInputNumberSpinbox getTotalSuppY2() {
        return totalSuppY2;
    }

    public void setTotalCostY2(RichInputNumberSpinbox totalCostY2) {
        this.totalCostY2 = totalCostY2;
    }

    public RichInputNumberSpinbox getTotalCostY2() {
        return totalCostY2;
    }

    public void setExProcY3(RichInputNumberSpinbox exProcY3) {
        this.exProcY3 = exProcY3;
    }

    public RichInputNumberSpinbox getExProcY3() {
        return exProcY3;
    }

    public void setNewProcY3(RichInputNumberSpinbox newProcY3) {
        this.newProcY3 = newProcY3;
    }

    public RichInputNumberSpinbox getNewProcY3() {
        return newProcY3;
    }

    public void setCoresProcY3(RichInputNumberSpinbox coresProcY3) {
        this.coresProcY3 = coresProcY3;
    }

    public RichInputNumberSpinbox getCoresProcY3() {
        return coresProcY3;
    }

    public void setCoreFactorY3(RichInputNumberSpinbox coreFactorY3) {
        this.coreFactorY3 = coreFactorY3;
    }

    public RichInputNumberSpinbox getCoreFactorY3() {
        return coreFactorY3;
    }

    public void setTotalProcY3(RichInputNumberSpinbox totalProcY3) {
        this.totalProcY3 = totalProcY3;
    }

    public RichInputNumberSpinbox getTotalProcY3() {
        return totalProcY3;
    }

    public void setLicenseCostY3(RichInputNumberSpinbox licenseCostY3) {
        this.licenseCostY3 = licenseCostY3;
    }

    public RichInputNumberSpinbox getLicenseCostY3() {
        return licenseCostY3;
    }

    public void setLicenseDiscY3(RichInputNumberSpinbox licenseDiscY3) {
        this.licenseDiscY3 = licenseDiscY3;
    }

    public RichInputNumberSpinbox getLicenseDiscY3() {
        return licenseDiscY3;
    }

    public void setTotalLicenseY3(RichInputNumberSpinbox totalLicenseY3) {
        this.totalLicenseY3 = totalLicenseY3;
    }

    public RichInputNumberSpinbox getTotalLicenseY3() {
        return totalLicenseY3;
    }

    public void setSuppCostY3(RichInputNumberSpinbox suppCostY3) {
        this.suppCostY3 = suppCostY3;
    }

    public RichInputNumberSpinbox getSuppCostY3() {
        return suppCostY3;
    }

    public void setTotalSuppY3(RichInputNumberSpinbox totalSuppY3) {
        this.totalSuppY3 = totalSuppY3;
    }

    public RichInputNumberSpinbox getTotalSuppY3() {
        return totalSuppY3;
    }

    public void setTotalCostY3(RichInputNumberSpinbox totalCostY3) {
        this.totalCostY3 = totalCostY3;
    }

    public RichInputNumberSpinbox getTotalCostY3() {
        return totalCostY3;
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

    public void setAot1(RichActiveOutputText aot1) {
        this.aot1 = aot1;
    }

    public RichActiveOutputText getAot1() {
        return aot1;
    }
}
