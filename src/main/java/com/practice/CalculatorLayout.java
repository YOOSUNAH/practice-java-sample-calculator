package com.practice;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CalculatorLayout extends JFrame {
    private final int width = 240;
    private final int height = 370;

    private GridBagLayout grid = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();
    private Color darkColor = new Color(80, 82, 85);
    TitledBorder tB = new TitledBorder(new LineBorder(darkColor, 1));
    private ButtonActionListener buttonActionListener = new ButtonActionListener();

    String button_names[] = {"C", "±", "%", "÷", "7", "8", "9", "x", "4", "5", "6", "-", "1", "2", "3", "+", "0", ".", "="};
    String buttonString = "C±%÷789x456-123+0.=";
    JButton buttons[] = new JButton[button_names.length];

    public CalculatorLayout() {

        setLayout(null);

        UserInput.SPACE.setEditable(false); // 편집 불가능
        UserInput.SPACE.setBackground(darkColor); // 배경은 화이트
        UserInput.SPACE.setHorizontalAlignment(JTextField.RIGHT);  // 정렬 위치
        UserInput.SPACE.setFont(new Font("Dialog", Font.PLAIN, 40)); // 글씨 체
        UserInput.SPACE.setBounds(0, 0, width, 70); // x:8, y:10 위치 270x70 크기
        UserInput.SPACE.setBorder(new LineBorder(Color.gray, 0));
        UserInput.SPACE.setForeground(Color.white);

        JPanel buttonPanel = new JPanel();
        // 버튼 레이어 셋팅
        buttonPanel.setLayout(grid);
        buttonPanel.setBounds(0, 70, width, 274); // x:0, y:70 위치 240x274 크기
        buttonPanel.setBackground(darkColor);

        //======
        gbc.fill = GridBagConstraints.BOTH; // 꽉 채워줌
        gbc.weightx = 1.0; // x축 안 넘어감
        gbc.weighty = 1.0;// y축 안 넘어감
        //========

        int x = 0;
        int y = 0;
        for (int i = 0; i < button_names.length; i++) {
            buttons[i] = new JButton(button_names[i]);
            buttons[i].setFont(new Font("Dialog", Font.BOLD, 20));
            buttons[i].setForeground(Color.white);

            // 버튼 색
            if (button_names[i].matches("[÷+=x-]")) {
                buttons[i].setBackground(new Color(255, 159, 9));
            } else if (button_names[i].matches("[C±%]")) {
                buttons[i].setBackground(new Color(97, 99, 102));
            } else {
                buttons[i].setBackground(new Color(123, 125, 127));
            }

            // 격자 형태 생성 ======
            if (button_names[i] == "0") {
                makeFrame(buttons[i], x, y, 2, 1);
                x++;
            } else {
                makeFrame(buttons[i], x, y, 1, 1);
            }

            x++;
            if (x > 3) {
                x = 0;
                y++;
            }
            // ====== ======

            buttons[i].setBorder(tB);
            buttonPanel.add(buttons[i]);
            buttons[i].setOpaque(true);

            buttons[i].addActionListener(buttonActionListener);
            buttons[i].addMouseListener(new MouseActionListener());
        }

        add(UserInput.SPACE);
        add(buttonPanel);

        setTitle("계산기");
        setVisible(true);
        setSize(width, height);
        setBackground(Color.DARK_GRAY);
        setLocationRelativeTo(null); // 화면 가운데에 띄우기
        setResizable(false); // 사이즈조절 불가
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창을 닫을때 실행중인 프로그램 도 종료
    }

    // 버튼의 크기 설정
    public void makeFrame(JButton c, int x, int y, int w, int h) {
        gbc.gridy = y;
        gbc.gridx = x;
        gbc.gridheight = h;
        gbc.gridwidth = w;
        grid.setConstraints(c, gbc);
    }

    class MouseActionListener implements MouseListener {
        @Override
        public void mousePressed(MouseEvent e) {
            JButton jb = (JButton) e.getSource();
            int target = buttonString.indexOf(jb.getText());
            buttons[target].setBorder(new LineBorder(Color.black));

            if (jb.getText().matches("[÷+=x-]")) {
                buttons[target].setBackground(Color.green);
            } else if (jb.getText().matches("[C±%]")) {
                buttons[target].setBackground(Color.green);
            } else {
                buttons[target].setBackground(Color.green);
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            JButton jb = (JButton) e.getSource();
            int target = buttonString.indexOf(jb.getText());
            buttons[target].setBorder(tB);
            // 버튼 색
            if (jb.getText().matches("[÷+=x-]")) {
                buttons[target].setBackground(new Color(255, 159, 9));
            } else if (jb.getText().matches("[C±%]")) {
                buttons[target].setBackground(new Color(97, 99, 102));
            } else {
                buttons[target].setBackground(new Color(123, 125, 127));
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }
    }


}