package Interfaces;

import Objects.Search;

import javafx.util.Pair;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Properties;

public class SearchPanel extends javax.swing.JPanel {
    private javax.swing.JComboBox<String> cbDAvailability;
    private javax.swing.JComboBox<String> cbSAvailability;
    private javax.swing.JComboBox<String> cbSize;
    private javax.swing.JComboBox<String> cbType;
    private javax.swing.JLabel lblDAvailability;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblSAvailability;
    private javax.swing.JLabel lblSize;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblType;
    private javax.swing.JTextField tfID;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfSize;
    private javax.swing.JTextField tfStatus;
    private JDatePickerImpl dpSAvailability;
    private Properties p;
    private LocalDate selectedDate;

    public SearchPanel() {
        super();
        initGUI();
    }

    private void initGUI() {
        lblID = new javax.swing.JLabel("ID:");
        lblName = new javax.swing.JLabel("Name:");
        lblSize = new javax.swing.JLabel("Size:");
        lblType = new javax.swing.JLabel("Type:");
        lblDAvailability = new javax.swing.JLabel("Default Availability:");
        lblSAvailability = new javax.swing.JLabel("Specific Availability:");
        lblStatus = new javax.swing.JLabel("Status:");

        tfID = new javax.swing.JTextField();
        tfName = new javax.swing.JTextField();
        tfSize = new javax.swing.JTextField();
        tfStatus = new javax.swing.JTextField();
        cbDAvailability = new javax.swing.JComboBox<>();
        cbSAvailability = new javax.swing.JComboBox<>();
        cbSize = new javax.swing.JComboBox<>();
        cbType = new javax.swing.JComboBox<>();

        cbDAvailability.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"", "Weekday PM", "Weekend AM", "Weekend PM", "Holiday AM", "Holiday PM"}));
        cbSAvailability.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"", "AM", "PM" }));
        cbSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"", "Equals", "Is Less Than", "Is More Than"}));
        cbType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"", "Computer Lab", "Tutorial Room", "Lecture Theatre"}));

        p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        // Set default date in case of no click
        selectedDate = LocalDate.of(1970, 01, 01);
        dpSAvailability = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel(), p), new DateComponentFormatter());
        dpSAvailability.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedDate = LocalDate.of(dpSAvailability.getModel().getYear(),
                        dpSAvailability.getModel().getMonth() + 1,
                        dpSAvailability.getModel().getDay());
            }
        });

        tfSize.setEditable(false);
        cbSize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbSize.getSelectedIndex() == 0) {
                    tfSize.setEditable(false);
                } else {
                    tfSize.setEditable(true);
                }
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblName)
                                        .addComponent(lblSize)
                                        .addComponent(lblType)
                                        .addComponent(lblDAvailability)
                                        .addComponent(lblID)
                                        .addComponent(lblSAvailability)
                                        .addComponent(lblStatus))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(dpSAvailability, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cbSAvailability, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(tfStatus)
                                        .addComponent(tfID)
                                        .addComponent(cbDAvailability, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(cbSize, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tfSize, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(tfName)
                                        .addComponent(cbType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblID)
                                        .addComponent(tfID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(7, 7, 7)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblName)
                                        .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblSize)
                                        .addComponent(tfSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblType)
                                        .addComponent(cbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblDAvailability)
                                        .addComponent(cbDAvailability, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblSAvailability)
                                        .addComponent(dpSAvailability, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbSAvailability, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblStatus)
                                        .addComponent(tfStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    public Search getSnapshot() {
        return new Search(tfID.getText(),
                tfName.getText(),
                new Pair<Integer, String>(cbSize.getSelectedIndex(),
                        tfSize.getText()),
                cbType.getSelectedIndex(),
                cbDAvailability.getSelectedIndex(),
                new Pair<LocalDate, Integer>(selectedDate,
                        cbSAvailability.getSelectedIndex()),
                tfStatus.getText());
    }
}
