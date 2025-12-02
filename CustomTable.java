package sis.components;

import sis.utils.Colors;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class CustomTable extends JTable {
    
    public CustomTable() {
        super();
        customizeTable();
    }
    
    private void customizeTable() {
        setFont(new Font("Tahoma", Font.PLAIN, 13));
        setRowHeight(40);
        setShowGrid(false);
        setIntercellSpacing(new Dimension(0, 0));
        setSelectionBackground(Colors.LIGHT_GRAY);
        setSelectionForeground(Colors.CHARCOAL_DARK);

        JTableHeader header = getTableHeader();
        header.setFont(new Font("Tahoma", Font.BOLD, 13));
        header.setBackground(Colors.CHARCOAL);
        header.setForeground(Colors.CHARCOAL_DARK);
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 45));

        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : Colors.LIGHT_GRAY);
                }
                
                setForeground(Colors.CAVIAR);
                setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                
                return c;
            }
        });
    }
}