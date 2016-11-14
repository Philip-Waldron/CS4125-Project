package cs4125.project.leaderboards;

/**
 *
 * @author Philip Waldron
 */
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LeaderboardUI extends javax.swing.JFrame {
    
    /**
     * Creates new form LeaderboardUI
     */
    public LeaderboardUI() {
        initComponents();
    }
                      
    private void initComponents() {

        currentStandings = new javax.swing.JLabel();
        sortMethod = new javax.swing.JLabel();
        alphabeticSortButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        leaderboardTable = new javax.swing.JTable();
        winSortButton = new javax.swing.JButton();
        wLRatioSortButton = new javax.swing.JButton();
        filter = new javax.swing.JLabel();
        filterTextArea = new javax.swing.JTextField();
        filterButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        leaderboardTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                
            },
            new String [] {
                "Name", "Wins", "Losses", "W/L Ratio"
            }) {

            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        jScrollPane1.setViewportView(leaderboardTable);
        if (leaderboardTable.getColumnModel().getColumnCount() > 0) {
            leaderboardTable.getColumnModel().getColumn(0).setResizable(false);
            leaderboardTable.getColumnModel().getColumn(1).setResizable(false);
        }
        
        currentStandings.setText("Current standings");

        sortMethod.setText("Sort Method");

        alphabeticSortButton.setText("Sort Alphabetically");

        winSortButton.setText("Sort By Wins");

        wLRatioSortButton.setText("Sort by W/L Ratio");

        filter.setText("Filter");

        filterTextArea.setText("Enter name");

        filterButton.setText("Apply Filter");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(currentStandings))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(filter)
                    .addComponent(sortMethod)
                    .addComponent(wLRatioSortButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(winSortButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(alphabeticSortButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(filterTextArea)
                    .addComponent(filterButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(currentStandings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sortMethod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(alphabeticSortButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(winSortButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(wLRatioSortButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(filter)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filterTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filterButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        pack();
    }

    public javax.swing.JTextField filterTextArea;
    private javax.swing.JButton alphabeticSortButton;
    private javax.swing.JLabel currentStandings;
    private javax.swing.JLabel filter;
    private javax.swing.JButton filterButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable leaderboardTable;
    private javax.swing.JLabel sortMethod;
    private javax.swing.JButton wLRatioSortButton;
    private javax.swing.JButton winSortButton;
    private javax.swing.table.DefaultTableModel model;
    
    public void addAlphabeticButtonActionListener(ActionListener listener) {
        alphabeticSortButton.addActionListener(listener);
    }

    public void addWinButtonActionListener(ActionListener listener) {
        winSortButton.addActionListener(listener);
    }
    
    public void addWLRatioButtonActionListener(ActionListener listener) {
        wLRatioSortButton.addActionListener(listener);
    }
    
    public void addFilterButtonActionListener(ActionListener listener) {
        filterButton.addActionListener(listener);
    }
    
    public void populateTable(ArrayList<String[]> entries) {
        model = (javax.swing.table.DefaultTableModel) leaderboardTable.getModel();
        model.setRowCount(0);
        for(int i = 0; i < entries.size(); i++) {
            String[] line = entries.get(i);
            model.addRow(line);
        }
    }
}
