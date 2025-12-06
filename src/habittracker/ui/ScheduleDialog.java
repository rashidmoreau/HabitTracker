package habittracker.ui;

import javax.swing.*;
import java.awt.*;
import java.time.DayOfWeek;
import java.util.EnumSet;
import java.util.Set;

// dialog to select habit schedule
public class ScheduleDialog extends JDialog {

    private final JCheckBox[] dayBoxes = new JCheckBox[7];
    private boolean confirmed = false;

    // creates schedule dialog
    public ScheduleDialog(JFrame parent, Set<DayOfWeek> existingDays) {
        super(parent, "Select Habit Schedule", true);
        setSize(300, 300);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        JPanel panel = new JPanel(new GridLayout(7, 1));
        int i = 0;

        // add checkboxes for each day
        for (DayOfWeek day : DayOfWeek.values()) {
            JCheckBox box = new JCheckBox(day.toString());
            if (existingDays != null && existingDays.contains(day)) {
                box.setSelected(true);
            }
            dayBoxes[i++] = box;
            panel.add(box);
        }

        JButton ok = new JButton("OK");
        JButton cancel = new JButton("Cancel");

        ok.addActionListener(e -> {
            confirmed = true;
            dispose();
        });

        cancel.addActionListener(e -> dispose());

        JPanel bottom = new JPanel();
        bottom.add(ok);
        bottom.add(cancel);

        add(panel, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }

    // check if user confirmed selection
    public boolean isConfirmed() {
        return confirmed;
    }

    // get selected days from checkboxes
    public Set<DayOfWeek> getSelectedDays() {
        Set<DayOfWeek> selected = EnumSet.noneOf(DayOfWeek.class);

        for (int i = 0; i < 7; i++) {
            if (dayBoxes[i].isSelected()) {
                selected.add(DayOfWeek.of(i + 1));
            }
        }

        // if none selected, default to all days
        return selected.isEmpty() ? EnumSet.allOf(DayOfWeek.class) : selected;
    }
}
