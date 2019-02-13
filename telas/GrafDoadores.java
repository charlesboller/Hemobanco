package telas;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;
import dao.RelEstoqueDAOImpl;
import dao.RelEstoqueDAOItf;
import exception.UsuarioException;
import model.SangueDisponivel;
import model.Usuario;




public class GrafDoadores extends JInternalFrame {

    private static final long serialVersionUID = 1L;

    public GrafDoadores(String applicationTitle, String chartTitle) {
        super(applicationTitle);
        PieDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset, chartTitle);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

    }

    /**
     * Creates a sample dataset
     */
    private  PieDataset createDataset() {
        
    	RelEstoqueDAOItf dao = new RelEstoqueDAOImpl();
    	DefaultPieDataset result = new DefaultPieDataset();
    	try {
			for (SangueDisponivel p : dao.listarDoadores()) {
				
				int sangue = p.getTipoSangue();
				switch (sangue) {
				case 1:
					result.setValue("A+", p.getQtdSangue());
					break;
				case 2:
					result.setValue("A-", p.getQtdSangue());
					break;
				case 3:
					result.setValue("B+", p.getQtdSangue());
					break;
				case 4:
					result.setValue("B-", p.getQtdSangue());
					break;
				case 5:
					result.setValue("AB+", p.getQtdSangue());
					break;
				case 6:
					result.setValue("AB-", p.getQtdSangue());
					break;
				case 7:
					result.setValue("O+", p.getQtdSangue());
					break;
				case 8:
					result.setValue("O-", p.getQtdSangue());
					break;
				case 9:
					result.setValue("Indefinido", p.getQtdSangue());
					break;
				default:
					System.out.println("Este não é um sangue válido!");
				}
			
			
			}
		} catch (UsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return result;
        
    }

    /**
     * Creates a chart
     */
    private JFreeChart createChart(PieDataset dataset, String title) {

        JFreeChart chart = ChartFactory.createPieChart3D(
            title,                  
            dataset,               
            true,        
            true,
            false
        );

        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        return chart;

    }
}