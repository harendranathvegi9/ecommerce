package com.aripd.ecommerce.view.administrator;

import com.aripd.ecommerce.model.ReportFormModel;
import com.aripd.ecommerce.service.SalelineService;
import com.aripd.util.MessageUtil;
import java.io.Serializable;
import java.util.Calendar;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.chart.PieChartModel;

@Named
@ViewScoped
public class ReportController implements Serializable {
    
    private PieChartModel pieModel1;
    private PieChartModel pieModel2;
    
    private ReportFormModel reportFormModel;
    
    @Inject
    private SalelineService salelineService;
    
    @Inject
    MessageUtil messageUtil;
    
    public ReportController() {
        reportFormModel = new ReportFormModel();
        Calendar cal = Calendar.getInstance();
        reportFormModel.setEnd(cal.getTime());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        reportFormModel.setStart(cal.getTime());
    }
    
    @PostConstruct
    public void init() {
        createPieModels();
    }
    
    public void doDrawChart(ActionEvent actionEvent) {
        createPieModels();
    }
    
    private void createPieModels() {
        createPieModel1();
        createPieModel2();
    }
    
    private void createPieModel1() {
        pieModel1 = new PieChartModel();
        
        pieModel1.setData(salelineService.getSalesRevenueData(reportFormModel));
        
        pieModel1.setTitle(messageUtil.getI18nResource("Sales Revenue"));
        pieModel1.setLegendPosition("e");
        pieModel1.setFill(true);
        pieModel1.setShowDataLabels(true);
        pieModel1.setDiameter(150);
    }
    
    private void createPieModel2() {
        pieModel2 = new PieChartModel();
        
        pieModel2.setData(salelineService.getSalesVolumeData(reportFormModel));
        
        pieModel2.setTitle(messageUtil.getI18nResource("Sales Volume"));
        pieModel2.setLegendPosition("e");
        pieModel2.setFill(false);
        pieModel2.setShowDataLabels(true);
        pieModel2.setDiameter(150);
    }
    
    public ReportFormModel getReportFormModel() {
        return reportFormModel;
    }
    
    public void setReportFormModel(ReportFormModel reportFormModel) {
        this.reportFormModel = reportFormModel;
    }
    
    public PieChartModel getPieModel1() {
        return pieModel1;
    }
    
    public void setPieModel1(PieChartModel pieModel1) {
        this.pieModel1 = pieModel1;
    }
    
    public PieChartModel getPieModel2() {
        return pieModel2;
    }
    
    public void setPieModel2(PieChartModel pieModel2) {
        this.pieModel2 = pieModel2;
    }
    
}
