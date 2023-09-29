import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
Задача: На лекции был написан фрейм, содержащий одну кнопку – начать игру
и расположением самого окна настроек автоматически, относительно игрового окна.


Добавить на экран компоновщик-сетку с одним столбцом и добавить над существующей кнопкой следующие компоненты в заданном порядке:
    JLabel с заголовком «Выберите режим игры», сгруппированные в ButtonGroup переключаемые
    JRadioButton с указанием режимов «Человек против компьютера» и «Человек против человека»,

    JLabel с заголовком «Выберите размеры поля»,
    JLabel с заголовком «Установленный размер поля:»,
    JSlider со значениями 3..10,

    JLabel с заголовком «Выберите длину для победы»,
    JLabel с заголовком «Установленная длина:»,
    JSlider со значениями 3..10.
 */

/*
     Задача: Добавить компонентам интерактивности, _x000b_а именно,
     при перемещении ползунка слайдера _x000b_в соответствующих лейблах должны появляться текущие значения слайдеров.
     Для этого необходимо добавить _x000b_к слайдеру слушателя изменений (как это было сделано для действия кнопки).
 */

public class SettingWindow extends JFrame {
    private static final int WIDTH = 230;
    private static final int HEIGHT = 350;

    JButton btnStart;
    JLabel labelChoiceMode, labelChoiceSize, labelCurrentSize, labelWinSize, labelCurrentWinSize;
    JPanel mainPanel;
    JRadioButton radioBtnHuman;
    JRadioButton radioBtnAI;
    ButtonGroup btnGroup;
    JSlider sliderFieldSize, sliderWinSize;
    GameWindow gameWindow;

    String CURRENT_FIELD_SIZE = "Установленный размер поля: ";
    final int MIN_VALUE = 3;
    final int MODE_HVH = 0;
    final int MODE_HVA = 1;


    String CURRENT_WIN_SIZE = "Установленная длина: ";

    SettingWindow(GameWindow gameWindow){
        this.gameWindow = gameWindow;
        mainPanel = new JPanel(new GridLayout(9,1));
        labelChoiceMode = new JLabel("Выберите режим игры");



        btnGroup = new ButtonGroup();
        radioBtnHuman = new JRadioButton("Человек против человека");
        radioBtnAI = new JRadioButton("Человек против компьютера");
        radioBtnAI.setSelected(true);
        btnGroup.add(radioBtnHuman);
        btnGroup.add(radioBtnAI);


        labelChoiceSize = new JLabel("Выберите размер поля");
        labelCurrentSize = new JLabel(CURRENT_FIELD_SIZE + MIN_VALUE);
        sliderFieldSize = new JSlider(MIN_VALUE,10,MIN_VALUE);

        sliderFieldSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                labelCurrentSize.setText(CURRENT_FIELD_SIZE + sliderFieldSize.getValue());
            }
        });




        labelWinSize = new JLabel("Выберите длину для победы");
        labelCurrentWinSize = new JLabel(CURRENT_WIN_SIZE + MIN_VALUE);
        sliderWinSize = new JSlider(MIN_VALUE,10,MIN_VALUE);

        sliderWinSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                labelCurrentWinSize.setText(CURRENT_WIN_SIZE + sliderWinSize.getValue());
                sliderWinSize.setMaximum(sliderFieldSize.getValue());
            }
        });



        mainPanel.add(labelChoiceMode,0);
        mainPanel.add(radioBtnHuman);
        mainPanel.add(radioBtnAI);
        mainPanel.add(labelChoiceSize);
        mainPanel.add(labelCurrentSize);
        mainPanel.add(sliderFieldSize);
        mainPanel.add(labelWinSize);
        mainPanel.add(labelCurrentWinSize);
        mainPanel.add(sliderWinSize);



        btnStart = new JButton("Start new game");

        setLocationRelativeTo(gameWindow);
        setSize(WIDTH, HEIGHT);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                startGame();
            }
        });
        add(mainPanel);
        add(btnStart,BorderLayout.SOUTH);
    }

    private void startGame(){
        int mode;
        if(radioBtnAI.isSelected()){
            mode = MODE_HVA;
        }else if (radioBtnHuman.isSelected()){
            mode = MODE_HVH;
        }else {
            throw new RuntimeException("Unknown game mode");
        }
        int size = sliderFieldSize.getValue();
        int winLength = sliderWinSize.getValue();
        gameWindow.startNewGame(mode,size,size,winLength);
    }
}