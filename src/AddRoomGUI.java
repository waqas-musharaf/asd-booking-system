import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.stream.Collectors;

public class AddRoomGUI extends javax.swing.JFrame implements Runnable, Observer {
    private javax.swing.JButton btnAvailability;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JComboBox<String> cbType;
    private javax.swing.JLabel lblAvailability;
    private javax.swing.JLabel lblRoomName;
    private javax.swing.JLabel lblSize;
    private javax.swing.JLabel lblType;
    private javax.swing.JTextField tfAvailability;
    private javax.swing.JTextField tfRoomName;
    private javax.swing.JTextField tfSize;

    private RoomTable roomTable;

    @Override
    public void run() {
        this.setVisible(true);
    }

    public AddRoomGUI(RoomTable tableState) {
        super();

        roomTable = tableState;
        roomTable.addObserver(this);

        initGUI();
        updateSharedTable();
    }

    private void initGUI() {
        btnAvailability = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnConfirm = new javax.swing.JButton();
        cbType = new javax.swing.JComboBox<>();
        lblAvailability = new javax.swing.JLabel();
        lblRoomName = new javax.swing.JLabel();
        lblSize = new javax.swing.JLabel();
        lblType = new javax.swing.JLabel();
        tfAvailability = new javax.swing.JTextField();
        tfRoomName = new javax.swing.JTextField();
        tfSize = new javax.swing.JTextField();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setName("JFrame");
        setTitle("Add Room");
        setPreferredSize(new java.awt.Dimension(300, 200));
        setResizable(false);

        lblRoomName.setText("Room Name:");
        lblSize.setText("Size:");
        lblType.setText("Type:");
        lblAvailability.setText("Availability:");

        tfAvailability.setText("Click button to fill");
        tfAvailability.setEditable(false);

        btnAvailability.setText("▼");
        btnAvailability.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAvailabilityActionPerformed(evt);
            }
        });
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        btnConfirm.setText("Confirm");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnConfirmActionPerformed(evt);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        cbType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Computer Lab", "Tutorial Room", "Lecture Theatre"}));
        cbType.setSelectedIndex(-1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(btnCancel)
                                                        .addComponent(lblRoomName))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                                                                .addComponent(btnConfirm))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(12, 12, 12))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblAvailability)
                                                        .addComponent(lblSize)
                                                        .addComponent(lblType))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(tfRoomName)
                                                        .addComponent(cbType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(tfSize)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(tfAvailability, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(btnAvailability, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblRoomName)
                                        .addComponent(tfRoomName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblSize)
                                        .addComponent(tfSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblType)
                                        .addComponent(cbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblAvailability)
                                        .addComponent(btnAvailability, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tfAvailability))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnCancel)
                                        .addComponent(btnConfirm))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void btnAvailabilityActionPerformed(java.awt.event.ActionEvent evt) {
        String[] options = new String[] {"Term Time Weekday PM", "Weekend AM", "Weekend PM", "Holiday AM", "Holiday PM"};
        JCheckBox[] checkBoxes = new JCheckBox[options.length];

        JPanel panel = new JPanel(new GridLayout(options.length, 1));

        for (int i = 0; i < options.length; i++) {
            checkBoxes[i] = new JCheckBox(options[i]);
        }

        for (JCheckBox c : checkBoxes) {
            panel.add(c);
        }

        int input = JOptionPane.showConfirmDialog(null, panel, "Availability" , JOptionPane.OK_CANCEL_OPTION);
        ArrayList<String> selected = new ArrayList<String>();

        if (input == 0) {
            for (int i = 0; i < checkBoxes.length; i++) {
                if (checkBoxes[i].isSelected()) {
                    selected.add(options[i]);
                }
            }

            if (selected.size() > 0) {
                tfAvailability.setText(selected.stream().collect(Collectors.joining(";")));
            } else if (selected.size() == 0) {
                tfAvailability.setText("Click button to fill");
            }
        }
    }

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        if (tfRoomName.getText().equals("")) {
            JOptionPane.showMessageDialog(null,
                    "Error: Room name cannot be blank. Please try again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else if (tfSize.getText().equals("")) {
            JOptionPane.showMessageDialog(null,
                    "Error: Room size cannot be blank. Please try again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else if (cbType.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(null,
                    "Error: Room type cannot be blank. Please try again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else if (tfAvailability.getText().equals("Click button to fill")) {
            JOptionPane.showMessageDialog(null,
                    "Error: Room availability must be filled. Please try again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            int roomSize = intTryParse(tfSize.getText(), Integer.MIN_VALUE);
            if (roomSize < 0) {
                JOptionPane.showMessageDialog(null,
                        "Error: Room size is invalid. Please try again.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                int id = 1;
                Room maxId;

                try {
                    maxId = Collections.max(roomTable.getTable(), Comparator.comparing(Room::getRoomId));
                } catch (NoSuchElementException e) {
                    maxId = null;
                }

                if (maxId != null) {
                    id = maxId.roomId + 1;
                }

                if (roomTable.getRoomFromTable(tfRoomName.getText()) != null) {
                    int input = JOptionPane.showConfirmDialog(null,
                            "Warning: Room name already exists. Would you like to overwrite it?",
                            "Warning",
                            JOptionPane.YES_NO_CANCEL_OPTION);
                    if (input == 0) {
                        id = roomTable.getRoomFromTable(tfRoomName.getText()).roomId;
                        roomTable.removeRoomFromTable(roomTable.getRoomFromTable(tfRoomName.getText()));
                        Room room = new Room(
                                id,
                                tfRoomName.getText(),
                                roomSize,
                                cbType.getSelectedItem().toString(),
                                tfAvailability.getText(),
                                "Available"
                        );
                        roomTable.addRoomToTable(room);
                        this.dispose();
                    } else if (input == 1) {
                        this.dispose();
                    } else if (input != 2) {
                        throw new Exception("Error: Unexpected input");
                    }
                } else {
                    Room room = new Room(
                            id,
                            tfRoomName.getText(),
                            roomSize,
                            cbType.getSelectedItem().toString(),
                            tfAvailability.getText(),
                            "Available"
                    );
                    roomTable.addRoomToTable(room);
                    this.dispose();
                }
            }
        }
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        // the method called when the shared table changes - updates the GUI if required
        updateSharedTable();
    }

    private void updateSharedTable() {
        roomTable.equals(roomTable.getTable());
    }

    public int intTryParse(String value, int defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}