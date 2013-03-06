package com.tw.rich.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputController {

    private BufferedReader bufferedReader;
    private String input;
    private boolean validatedInput = false;

    public final static String YES = "Y";
    public final static String NO = "N";

    public final static int FIRST = 1;
    public final static int SECOND = 2;
    public final static int THIRD = 3;

    public final static String EXIT = "F";

    public InputController() {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void readFromUser() {
        try {
            this.setInput(bufferedReader.readLine().trim().toUpperCase());
        } catch (IOException e) {
            setValidatedInput(false);
            return;
        }
        if (this.getInput().equals(YES) || this.getInput().equals(NO))
            setValidatedInput(true);
        else
            setValidatedInput(false);
    }

    public void propsHouseInputValidated() {
        try {
            this.setInput(bufferedReader.readLine().trim().toUpperCase());
        } catch (IOException e) {
            setValidatedInput(false);
            return;
        }
        if (this.input != EXIT) {
            try {
                int i = Integer.parseInt(input);
                if (i == THIRD || i == SECOND || i == FIRST)
                    setValidatedInput(true);
                else
                    setValidatedInput(false);
            } catch (Exception e) {
                setValidatedInput(false);
                return;
            }
        } else {
            setValidatedInput(true);
        }

    }

    public boolean getValidatedInput() {
        return validatedInput;
    }

    public void setValidatedInput(boolean validatedInput) {
        this.validatedInput = validatedInput;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
