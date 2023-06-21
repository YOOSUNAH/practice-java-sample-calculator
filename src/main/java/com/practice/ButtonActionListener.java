package com.practice;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ButtonActionListener implements ActionListener {
    List<String> inputs = new ArrayList<>();

    //    {"1", "0", "3", "+", "1", "0", "0"}
    private static Scanner scanner = new Scanner(System.in);

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = e.getActionCommand();

        System.out.println("입력된 값 : " + e.getActionCommand());
        if (isNumber(input)) {
            inputs.add(input);
            // 사칙연산 포함이면 숫자 보여주면 안됨.  사칙연산 포함 아니면 숫자 보여줘도 됨.
            Integer operationIdx = findNotNumberIdx();
            if (operationIdx == null) {
                UserInput.SPACE.setText(String.join("", inputs));
            return;
            } UserInput.SPACE.setText(input);

        }

        if (!isNumber(input)) {
//          사칙연산 이냐 ?
            inputs.add(input);
            UserInput.SPACE.setText(input);

//          초기화냐 ?
            if (input.equals("C")) {
                inputs.clear();
                UserInput.SPACE.setText("");
            }
//          결과값 요청이냐 ?
            if (input.equals("=")) {
                Integer notNumberIdx = findNotNumberIdx();
                String operation = inputs.get(notNumberIdx);

                Integer firstOperationIdx = findNotNumberIdx();
                Integer numbersBeforeOperation = Integer.parseInt(String.join("", inputs.subList(0, firstOperationIdx)));

                List<String> strings = inputs.subList(firstOperationIdx + 1, inputs.size()-1);
                Integer numbersAfterOperation = Integer.parseInt(String.join("", strings));

                // 사칙연산 = 2개 있으면 그 뒤에 숫자 랑 계산해야 함



                Integer result = null;
                if (operation.equals("+")) {
                    result = plus(numbersBeforeOperation, numbersAfterOperation);
                }
                if (operation.equals("-")) {
                    result = minus(numbersBeforeOperation, numbersAfterOperation);
                }
                if (operation.equals("x")) {
                    result = multiply(numbersBeforeOperation, numbersAfterOperation);
                }
                if (operation.equals("÷")) {
                    result = divide(numbersBeforeOperation, numbersAfterOperation);
                }
                UserInput.SPACE.setText(String.valueOf(result));
                System.out.println("계산 된 값 = " + result);
                inputs.add(String.valueOf(result));

            }

        }
    }

    private Integer divide(Integer numbersBeforeOperation, Integer numbersAfterOperation) {
        return numbersBeforeOperation / numbersAfterOperation;
    }

    private Integer multiply(Integer numbersBeforeOperation, Integer numbersAfterOperation) {
        return numbersBeforeOperation * numbersAfterOperation;
    }

    private Integer minus(Integer numbersBeforeOperation, Integer numbersAfterOperation) {
        return numbersBeforeOperation - numbersAfterOperation;
    }

    private Integer plus(Integer numbersBeforeOperation, Integer numbersAfterOperation) {
        return numbersBeforeOperation + numbersAfterOperation;
    }

    private Integer findNotNumberIdx() {
        for (int i = 0; i < inputs.size(); i++) {
            if (!isNumber(inputs.get(i))) {
                return i;
            }
        }
        return null;
    }

    private boolean isNumber(String input) {
        try {
            int number = Integer.parseInt(input);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

}


