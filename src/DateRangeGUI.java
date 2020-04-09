import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DateRangeGUI extends javax.swing.JPanel {
    private javax.swing.JLabel lblFrom;
    private javax.swing.JLabel lblUntil;
    private Properties p;
    private JDatePickerImpl dpFrom;
    private JDatePickerImpl dpUntil;

    public DateRangeGUI() {
        super();
        initGUI();
    }

    private void initGUI() {
        lblFrom = new javax.swing.JLabel("From:");
        lblUntil = new javax.swing.JLabel("Until:");

        p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");

        dpFrom = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel(), p), new DateComponentFormatter());
        dpUntil = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel(), p), new DateComponentFormatter());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblFrom)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(dpFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblUntil)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(dpUntil, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblFrom)
                                        .addComponent(dpFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblUntil)
                                        .addComponent(dpUntil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    public List<LocalDate> returnDateRange() {
        List<LocalDate> dateRange = new ArrayList<>();
        if (dpFrom.getModel().getValue() == null || dpUntil.getModel().getValue() == null) {
            JOptionPane.showMessageDialog(null,
                    "Error: Incomplete date range. Term dates unchanged.",
                    "Error", JOptionPane.ERROR_MESSAGE,
                    null);
        } else {
            //date.getMonth() returns a value between 0 and 11, not 1-12; +1 as a workaround
            //date.getDay() however, returns a value between 1 and 31. Very weird...
            LocalDate ldFrom = LocalDate.of(dpFrom.getModel().getYear(), dpFrom.getModel().getMonth() + 1, dpFrom.getModel().getDay());
            LocalDate ldUntil = LocalDate.of(dpUntil.getModel().getYear(), dpUntil.getModel().getMonth() + 1, dpUntil.getModel().getDay());
            if (ldFrom.compareTo(ldUntil) > 0) {
                JOptionPane.showMessageDialog(null,
                        "Error: Invalid date range. Term dates unchanged.",
                        "Error", JOptionPane.ERROR_MESSAGE,
                        null);
            } else {
                long dayDifference = ChronoUnit.DAYS.between(ldFrom, ldUntil) + 1;
                dateRange = IntStream.iterate(0, i -> i + 1)
                        .limit(dayDifference)
                        .mapToObj(i -> ldFrom.plusDays(i))
                        .collect(Collectors.toList());
            }
        }
        return dateRange;
    }
}
