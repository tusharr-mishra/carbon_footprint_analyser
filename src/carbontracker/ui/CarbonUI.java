package carbontracker.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import carbontracker.service.CarbonTrackerService;
import carbontracker.model.*;
import carbontracker.util.FileUtil;

public class CarbonUI extends JFrame {

    private CarbonTrackerService service;

    private JTextArea outputArea;

    public CarbonUI() {

        service = new CarbonTrackerService();
        setTitle("Carbon Footprint Analyzer Dashboard");
        setSize(550, 450);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel title = new JLabel("CARBON FOOTPRINT ANALYZER");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title);
        JButton transportBtn = new JButton("Add Transport");
        JButton energyBtn = new JButton("Add Energy");
        JButton foodBtn = new JButton("Add Food");
        JButton totalBtn = new JButton("Total Emission");
        JButton saveBtn = new JButton("Save Report");
        outputArea = new JTextArea(15, 45);
        JScrollPane scroll = new JScrollPane(outputArea);
        add(transportBtn);
        add(energyBtn);
        add(foodBtn);
        add(totalBtn);
        add(saveBtn);
        add(scroll);
        transportBtn.addActionListener(e -> {
            try {
                String vehicle = JOptionPane.showInputDialog("Enter vehicle type");
                double distance = Double.parseDouble(JOptionPane.showInputDialog("Enter distance"));
                double factor = Double.parseDouble(JOptionPane.showInputDialog("Enter emission factor"));
                TransportActivity t = new TransportActivity("Transport", factor, "Transport", vehicle, distance);
                service.addActivity(t);
                outputArea.append("Transport activity added\n");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Invalid input");
            }
        });
        energyBtn.addActionListener(e -> {
            try {
                double units = Double.parseDouble(JOptionPane.showInputDialog("Enter units"));
                double factor = Double.parseDouble(JOptionPane.showInputDialog("Enter emission factor"));
                EnergyActivity en = new EnergyActivity("Energy", factor, "Energy", units);
                service.addActivity(en);
                outputArea.append("Energy activity added\n");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Invalid input");
            }
        });
        foodBtn.addActionListener(e -> {
            try {
                String type = JOptionPane.showInputDialog("Enter food type");
                int days = Integer.parseInt(JOptionPane.showInputDialog("Enter days"));
                double factor = Double.parseDouble(JOptionPane.showInputDialog("Enter emission factor"));
                FoodActivity f = new FoodActivity("Food", factor, "Food", type, days);
                service.addActivity(f);
                outputArea.append("Food activity added\n");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Invalid input");
            }
        });
        totalBtn.addActionListener(e -> {
            double total = service.calculateTotalEmission();
            outputArea.append("Total Emission: " + total + "\n");
        });
        saveBtn.addActionListener(e -> {
            double total = service.calculateTotalEmission();
            FileUtil.saveReport(total);
            outputArea.append("Report saved to file\n");
        });
        setVisible(true);
    }
}
