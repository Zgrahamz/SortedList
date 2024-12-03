import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SLFrame extends JFrame implements ActionListener {
    JPanel mainPnl, searchPnl, ctrlPnl;
    JTextField txtSearch;
    JTextArea txtResults;
    JButton addBtn, searchBtn, quitBtn;

    ArrayList<String> binarList = new ArrayList<String>();
    int target;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addBtn) {
            binarList.add(txtSearch.getText());
        } else if (e.getSource() == searchBtn) {
            int result = binarSearch(binarList);
            if(result >= 0) {
                txtResults.append(binarList.get(result));
            } else {
                txtResults.append("No results found, item location would be " + binarList.get(target) + " if it were in the ArrayList.");
            }
        } else if(e.getSource() == quitBtn) {
            int c = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
            if (c == JOptionPane.YES_OPTION)
                System.exit(0);
        }
    }

    public SLFrame() {
        setTitle("SortedList");
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());
        mainPnl.setSize(800,800);
        add(mainPnl);
        setVisible(true);
        searchPnl = new JPanel();
        searchPnl.setLayout(new BorderLayout());
        ctrlPnl = new JPanel();
        ctrlPnl.setLayout(new BorderLayout());

        txtSearch = new JTextField(10);
        searchPnl.add(txtSearch, BorderLayout.NORTH);
        txtResults = new JTextArea(30,30);
        searchPnl.add(txtResults, BorderLayout.CENTER);
        addBtn = new JButton("Add");
        addBtn.addActionListener(this);
        searchBtn = new JButton("Search");
        searchBtn.addActionListener(this);
        quitBtn = new JButton("Quit");
        quitBtn.addActionListener(this);
        ctrlPnl.add(addBtn, BorderLayout.EAST);
        ctrlPnl.add(searchBtn, BorderLayout.WEST);
        ctrlPnl.add(quitBtn, BorderLayout.SOUTH);
        mainPnl.add(searchPnl, BorderLayout.NORTH);
        mainPnl.add(ctrlPnl, BorderLayout.SOUTH);

    }

    public int binarSearch(ArrayList<String> binarList) {
        target = binarList.indexOf(txtSearch.getText());
        int start = 0;
        int end = binarList.size() - 1;
        int mid = (start + end) / 2;

        while (start <= end) {
            if(binarList.get(mid).equals(txtSearch.getText())) {
                return mid;
            } else if (binarList.indexOf(mid) < target) {
                start = mid + 1;
            } else if (binarList.indexOf(mid) > target) {
                start = mid - 1;
            }
        }
        return -1;
    }
}


